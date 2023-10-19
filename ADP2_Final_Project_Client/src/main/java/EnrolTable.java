
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @authors
 * 1.Asimbonge Mbende(221090754) 
 * 2.Thandolwethu Zamasiba Khoza(221797289) 
 * 3.Sbonga Shweni(219143188)
 */

public class EnrolTable extends JFrame implements ActionListener {

    private JPanel buttonPnl, tablePnl, northPnl;
    private JLabel lblStudentnumber, studentNumber, lblSubjectTable, lblEnrollmentTable;
    private JButton btnEnroll, btnExit, btnCancel, btnBack;
    private DefaultTableModel model, destinationTableModel;
    private JTable table, destinationTable;
    Client client;
    public static ArrayList<EnrolmentPOJO> selectedSubjectsList = new ArrayList<>();
    private String studentNo;
    private Font ft1, ft2, ft3;

    public EnrolTable(Client client, String studentNum) {
        super("Subject Enrolment Table ");
        northPnl = new JPanel(new FlowLayout());
        northPnl.setBackground(new Color(0, 106, 255));
        tablePnl = new JPanel();
        tablePnl.setBackground(new Color(0, 106, 255));
        tablePnl.setLayout(new GridLayout(4, 1));
        buttonPnl = new JPanel();
        this.client = client;
        ft1 = new Font("Arial", Font.BOLD, 32);
        ft2 = new Font("Arial", Font.BOLD, 22);
        ft3 = new Font("Arial", Font.PLAIN, 24);

        //passing the student number below
        this.studentNo = studentNum;
        lblStudentnumber = new JLabel("Student Number: ");
        lblStudentnumber.setFont(ft1);
        lblStudentnumber.setForeground(Color.yellow);
        studentNumber = new JLabel("");
        studentNumber.setFont(ft1);
        studentNumber.setForeground(Color.black);
        //takes the student number passed from the login gui below
        studentNumber.setText(studentNum);
        lblSubjectTable = new JLabel("Subject List", SwingConstants.CENTER);
        lblSubjectTable.setFont(ft2);
        lblSubjectTable.setForeground(new Color(232, 245, 255));
        lblEnrollmentTable = new JLabel("Enrollment Table", SwingConstants.CENTER);
        lblEnrollmentTable.setFont(ft2);
        lblEnrollmentTable.setForeground(new Color(232, 245, 255));

        northPnl.add(lblStudentnumber);
        northPnl.add(studentNumber);
        northPnl.add(studentNumber);

        btnEnroll = new JButton("Enroll");
        btnEnroll.setFont(ft3);
        btnEnroll.addActionListener(this);
        btnExit = new JButton("Exit");
        btnExit.setFont(ft3);
        btnExit.addActionListener(this);
        btnBack = new JButton("Back");
        btnBack.setFont(ft3);
        btnBack.addActionListener(this);
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(ft3);
        btnCancel.addActionListener(this);

        model = new DefaultTableModel();
        model.addColumn("Subject Code");
        model.addColumn("Subject Name");
        model.addColumn("Select");

        table = new JTable(model);

        
        TableColumn checkboxColumn = table.getColumnModel().getColumn(2);
        checkboxColumn.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setSelected(value != null && (boolean) value);
                return checkBox;
            }
        });
        checkboxColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowHeight(25);
        tablePnl.add(lblSubjectTable);
        tablePnl.add(scrollPane);

        retrieveSubject();
        // adding buttons
        buttonPnl.add(btnEnroll);
        buttonPnl.add(btnCancel);
        buttonPnl.add(btnBack);
        buttonPnl.add(btnExit);

        // New destination Table
        destinationTableModel = new DefaultTableModel();
        destinationTableModel.addColumn("Subject Code");
        destinationTableModel.addColumn("Subject Name");

        destinationTable = new JTable(destinationTableModel) {

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                destinationTable.setRowHeight(25);
                // Set alternating row colors (pink for even rows, gray for odd rows)
                if (row % 2 == 0) {
                    comp.setBackground(new Color(223, 172, 190));
                } else {
                    comp.setBackground(new Color(162, 163, 162));
                }

                // Check if the row is selected and set the background color to green
                if (isRowSelected(row)) {
                    comp.setBackground(Color.GREEN);
                }

                return comp;
            }
        };
        JScrollPane destinationScrollPane = new JScrollPane(destinationTable);
        tablePnl.add(lblEnrollmentTable);
        tablePnl.add(destinationScrollPane);
        //retreives the enrollment list
        viewEnrollments();
        this.add(northPnl, BorderLayout.NORTH);
        this.add(tablePnl, BorderLayout.CENTER);
        this.add(buttonPnl, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 550);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
    }
public void enrolledSubjects() {
    selectedSubjectsList = new ArrayList<>();
    selectedSubjectsList.clear();

    //selected subjects from the source table to the destination table
    for (int i = 0; i < model.getRowCount(); i++) {
        boolean isSelected = (boolean) model.getValueAt(i, 2);
        if (isSelected) {
            String subjectCode = (String) model.getValueAt(i, 0);
            String subjectName = (String) model.getValueAt(i, 1);
            boolean subjectExistsInDestination = false;

            // Check if the subject code already exists in the destination table
            for (int j = 0; j < destinationTableModel.getRowCount(); j++) {
                String existingSubjectCode = (String) destinationTableModel.getValueAt(j, 0);
                if (subjectCode.equals(existingSubjectCode)) {
                    subjectExistsInDestination = true;
                    break;
                }
            }

            if (!subjectExistsInDestination) {
                // If it doesn't exist, add it to the destination table
                selectedSubjectsList.add(new EnrolmentPOJO(studentNo, subjectCode, subjectName));
                destinationTableModel.addRow(new Object[]{subjectCode, subjectName});
            } else {
                
                JOptionPane.showMessageDialog(this, studentNo+" is already enrolled for " + subjectName);
            }
        }
    }

    // Send the object to the server AFTER updating the destination table
    client.sendEnrolDetails(selectedSubjectsList);
    viewEnrollments();
}
    public void retrieveSubject() {
        //sends the prompt to retrieve subjects to the server
        client.sendEnrolDetails("Retrieve Subjects");
        // client the list of subjects from the server
        List<Subject> subjects = (List<Subject>) client.recieveEnrolDetails();
        // adds the list to table
        for (Subject subject : subjects) {
            model.addRow(new Object[]{subject.getSubjectCode(), subject.getSubjectTitle(), false});
        }
    }

    public void viewEnrollments() {
        client.sendEnrolDetails("Retrieve Enrolledlist" + studentNo);
        List<EnrolmentPOJO> subjects = (List<EnrolmentPOJO>) client.recieveEnrolledDetails();

        // Clear existing data in the destination table
        destinationTableModel.setRowCount(0);

        for (EnrolmentPOJO subject : subjects) {
            destinationTableModel.addRow(new Object[]{subject.getSubjectCode(), subject.getSubjectTitle()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEnroll) {
            enrolledSubjects();
        } else if (e.getSource() == btnBack) {
            LoginApp loginApp = new LoginApp(client);
            loginApp.setGUI();
            dispose();
        } else if (e.getSource() == btnCancel) {
            int[] selectedRows = destinationTable.getSelectedRows();
            if (selectedRows.length == 0) {
              
                JOptionPane.showMessageDialog(null, "Please select a subject to cancel from Enrollment Table.");
            } else {
                String subjectCode = null;
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int selectedRow = selectedRows[i];
                    subjectCode = (String) destinationTable.getValueAt(selectedRow, 0);
                }
                client.cancelSubject("cancel" + studentNo + subjectCode);
            }
            viewEnrollments();
        } else if (e.getSource() == btnExit) {
            client.closeClient();

        }
    }

}
