
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

/**
 * @authors 
 * 1.Asimbonge Mbende(221090754)
 * 2.Thandolwethu Zamasiba Khoza(221797289)
 * 3.Sbonga Shweni(219143188)
 */
public class AdminGUI extends JFrame implements ActionListener {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Font ft1, ft2, ft3;
    Client client;

    public AdminGUI(Client client) {
        setTitle("University System");
        setSize(700, 325);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ft1 = new Font("Arial", Font.BOLD, 32);
        ft2 = new Font("Goudy Stout", Font.BOLD, 15);
        ft3 = new Font("Arial", Font.PLAIN, 26);

        // Initialize CardLayout and cardPanel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create and add the first card with a menu bar
        menuCard();
        cardPanel.add(menuCard());

        // Create and add other cards
        AddStudentCard();
        AddSubjectCard();
        RetrieveStudentsCard();
        RetrieveSubjectsCard();
        RetrieveAllCard();

        // Add the cardPanel to the frame
        add(cardPanel);

        setVisible(true);
        this.client = client;
    }

    private JPanel menuCard() {
        JPanel card1 = new JPanel();
        card1.setLayout(new BorderLayout());

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu cardMenu = new JMenu("Menu");

        // Create menu items for switching between cards
        JMenuItem itemStud = new JMenuItem("Add Student");
        itemStud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Second Card");
            }
        });

        JMenuItem itemAdmin = new JMenuItem("Add Subjects");
        itemAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Third Card");
            }
        });

        JMenuItem itemRetrieveStud = new JMenuItem("Retrieve Students");
        itemRetrieveStud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Forth Card");
            }
        });

        JMenuItem itemRetrieveSubject = new JMenuItem("Retrieve Subjects");
        itemRetrieveSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Fifth Card");
            }
        });
        JMenuItem itemData = new JMenuItem("Retrieve Students & Subjects");
        itemData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Sixth Card");
            }
        });

        JMenuItem itemBack = new JMenuItem("Close");
        itemBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Close")) {
                    LoginApp loginApp = new LoginApp(client);
                    loginApp.setGUI();
                    dispose();
                }
            }
        });
        cardMenu.add(itemStud);
        cardMenu.add(itemAdmin);
        cardMenu.add(itemRetrieveStud);
        cardMenu.add(itemRetrieveSubject);
        cardMenu.add(itemData);

        cardMenu.add(itemBack);

        menuBar.add(cardMenu);

        // Add the menu bar to the card
        card1.add(menuBar, BorderLayout.NORTH);

        // Add some content to the card
        JLabel label1 = new JLabel("Select from the menu to proceed.");
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setFont(ft2);
        label1.setForeground(new Color(232, 245, 255));
        card1.setBackground(new Color(0, 106, 255));
        card1.add(label1, BorderLayout.CENTER);

        return card1;
    }

    private void AddStudentCard() {  //Regist student to the system
        JPanel card2 = new JPanel();
        card2.setLayout(new BorderLayout());

        JPanel pnlNorth = new JPanel();
        JPanel pnlCentre = new JPanel();
        JPanel pnlSouth = new JPanel();

        pnlNorth.setLayout(new FlowLayout());
        pnlCentre.setLayout(new GridLayout(6, 2));
        pnlSouth.setLayout(new GridLayout(1, 2));

        JLabel lblLogo = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("Register.jpg").getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
        lblLogo.setIcon(imageIcon);

        JLabel lblHeading = new JLabel("Add Student");
        JLabel lblStudNumber = new JLabel("Student Number:  ");
        JLabel lblName = new JLabel("Name: ");
        JLabel lblSurname = new JLabel("Surname: ");
        JLabel lblPassword = new JLabel("Password: ");
        JLabel lblConf = new JLabel("Confirm Password: ");

        JTextField txtStudNumber = new JTextField(20);
        JTextField txtName = new JTextField(20);
        JTextField txtSurname = new JTextField(20);
        JRadioButton radFemale = new JRadioButton("Female");
        JRadioButton radMale = new JRadioButton("Male");
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(radFemale);
        btnGroup.add(radMale);
        JPasswordField txtPassword = new JPasswordField(20);
        JPasswordField txtConf = new JPasswordField(20);

        JButton btnSave = new JButton("Save");
        btnSave.setFocusable(false);
        btnSave.setFont(ft3);
        
        JButton btnBack = new JButton("Back");
        btnBack.setFocusable(false);
        btnBack.setFont(ft3);

        pnlNorth.add(lblLogo);
        pnlNorth.add(lblHeading);
        lblHeading.setFont(ft1);
        lblHeading.setForeground(Color.yellow);
        pnlNorth.setBackground(new Color(0, 106, 255));

        pnlCentre.add(lblStudNumber);
        pnlCentre.add(txtStudNumber);

        pnlCentre.add(lblName);
        pnlCentre.add(txtName);

        pnlCentre.add(lblSurname);
        pnlCentre.add(txtSurname);

        pnlCentre.add(radFemale);
        pnlCentre.add(radMale);

        pnlCentre.add(lblPassword);
        pnlCentre.add(txtPassword);

        pnlCentre.add(lblConf);
        pnlCentre.add(txtConf);

        pnlSouth.add(btnSave);
        pnlSouth.add(btnBack);

        card2.add(pnlNorth, BorderLayout.NORTH);
        card2.add(pnlCentre, BorderLayout.CENTER);
        card2.add(pnlSouth, BorderLayout.SOUTH);
//ADD STUDENT NUMBER,NAME,SURNAME,PASSWORD AND GENDER TO STUDENTS TABLE
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentNumber = txtStudNumber.getText();
                String name = txtName.getText();
                String surname = txtSurname.getText();
                String gender = radFemale.isSelected() ? "Female" : "Male";
                String password = new String(txtPassword.getPassword());
                String confirmPassword = new String(txtConf.getPassword());
                if (studentNumber.isEmpty() || name.isEmpty() || surname.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Password and Confirm Password do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AddStudent student = new AddStudent(studentNumber, name, surname, gender, password);

                boolean success = client.addStudent(student);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Student added successfully!");
                    txtStudNumber.setText("");
                    txtName.setText("");
                    txtSurname.setText("");
                    txtPassword.setText("");
                    txtConf.setText("");
                    radFemale.setSelected(false);
                    radMale.setSelected(false);
                } else {

                    JOptionPane.showMessageDialog(null, "Student number: " + studentNumber + " already exists. Please use a different student number.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        btnBack.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                cardLayout.show(cardPanel, "First Card");
                card2.setVisible(false);
            }
        }
        );

        cardPanel.add(card2,
                "Second Card");
    }

    private void AddSubjectCard() {
        JPanel card3 = new JPanel();
        card3.setLayout(new BorderLayout());

        JPanel pnlNorth = new JPanel();
        JPanel pnlCentre = new JPanel();
        JPanel pnlSouth = new JPanel();

        pnlNorth.setLayout(new FlowLayout());
        pnlCentre.setLayout(new GridLayout(2, 2));
        pnlSouth.setLayout(new GridLayout(1, 2));

        JLabel lblLogo = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("Subjects.jpg").getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
        lblLogo.setIcon(imageIcon);

        JLabel lblHeading = new JLabel("Add Subject");
        JLabel lblSubjectCode = new JLabel("Subject Code: ");
        JLabel lblSubjectName = new JLabel("Subject Name: ");

        JTextField txtSubjectCode = new JTextField(20);
        JTextField txtSubjectName = new JTextField(20);

        JButton btnSave = new JButton("Save");
        btnSave.setFocusable(false);
        btnSave.setFont(ft3);
        
        JButton btnBack = new JButton("Back");
        btnBack.setFocusable(false);
        btnBack.setFont(ft3);

        pnlNorth.add(lblLogo);
        pnlNorth.add(lblHeading);
        lblHeading.setFont(ft1);
        lblHeading.setForeground(Color.yellow);
        pnlNorth.setBackground(new Color(0, 106, 255));

        pnlCentre.add(lblSubjectCode);
        pnlCentre.add(txtSubjectCode);

        pnlCentre.add(lblSubjectName);
        pnlCentre.add(txtSubjectName);

        pnlSouth.add(btnSave);
        pnlSouth.add(btnBack);

        card3.add(pnlNorth, BorderLayout.NORTH);
        card3.add(pnlCentre, BorderLayout.CENTER);
        card3.add(pnlSouth, BorderLayout.SOUTH);
        //ADD SUBJECT CODE AND SUBJECT TITLE TO SUBJECTS TABLE

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subjCode = txtSubjectCode.getText();
                String subjName = txtSubjectName.getText();

                Subject sub = new Subject(subjCode, subjName);
                boolean success = client.addSubject(sub);
                if (success) {
                    System.out.println("TESTING>>" + sub);
                    JOptionPane.showMessageDialog(null, "Subject added successfully!");
                    txtSubjectCode.setText("");
                    txtSubjectName.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "Subject Code: " + subjCode + " already exists. Please use a different subject code.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "First Card");
                card3.setVisible(false);
            }
        });

        cardPanel.add(card3, "Third Card");
    }
// Define the CustomTableCellRenderer class 

    class CustomTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected) {
                comp.setBackground(Color.GREEN); // Set the background color to green when selected
            } 
            else {
                // Set alternating row colors (pink for even rows, gray for odd rows)
                if (row % 2 == 0) {
                    comp.setBackground(new Color(223, 172, 190));
                } else {
                    comp.setBackground(new Color(162, 163, 162));
                }
            }

            return comp;
        }
    }

    private void RetrieveStudentsCard() {
        JPanel card4 = new JPanel();
        card4.setLayout(new BorderLayout());

        JPanel pnlNorth = new JPanel();
        JPanel pnlSearch = new JPanel();
        JPanel pnlCentre = new JPanel();
        JPanel pnlSouth = new JPanel();

        JLabel lblLogo = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("Students.jpg").getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
        lblLogo.setIcon(imageIcon);

        JLabel lblHeading = new JLabel("Availabe Students");
        JLabel lblSearch = new JLabel("Search");
        JTextField txtSearch = new JTextField(30);
        JButton btnRetrieve = new JButton("Retrieve");
        JButton btnDelete = new JButton("Delete");
        JButton btnBack = new JButton("Back");

        pnlNorth.setLayout(new FlowLayout());
        pnlSearch.setLayout(new FlowLayout());
        pnlCentre.setLayout(new BorderLayout());

        pnlSouth.setLayout(new GridLayout(1, 3));

        DefaultTableModel tableModel = new DefaultTableModel();
        //Creating search
        TableRowSorter sorter = new TableRowSorter<>(tableModel);
        JTable table = new JTable(tableModel);
        table.setRowSorter(sorter);
        table.setRowHeight(25);
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(txtSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(txtSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(txtSearch.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });
 
        tableModel.addColumn("Student Number");
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Gender");

        pnlNorth.add(lblLogo);
        pnlNorth.add(lblHeading);
        lblHeading.setFont(ft1);
        lblHeading.setForeground(Color.yellow);
        pnlNorth.setBackground(new Color(0, 106, 255));
        pnlCentre.add(pnlSearch, BorderLayout.NORTH);
        pnlCentre.add(new JScrollPane(table), BorderLayout.CENTER);

        pnlSearch.add(lblSearch);
        pnlSearch.add(txtSearch);

        pnlSouth.add(btnRetrieve);
        btnRetrieve.setFocusable(false);
        btnRetrieve.setFont(ft3);
        
        pnlSouth.add(btnDelete);
        btnDelete.setFocusable(false);
        btnDelete.setFont(ft3);
        
        pnlSouth.add(btnBack);
        btnBack.setFocusable(false);
        btnBack.setFont(ft3);

        card4.add(pnlNorth, BorderLayout.NORTH);
        card4.add(pnlCentre, BorderLayout.CENTER);
        card4.add(pnlSouth, BorderLayout.SOUTH);
//RETRIEVE STUDENT NUMBER,NAME SURNAME AND GENDER FROM STUDENTS TABLE
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Student> students = client.retrieveStudents();

                if (students != null) {
                    // Clear the table
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    tableModel.setRowCount(0);

                    // Populate the table with retrieved students
                    for (Student student : students) {
                        tableModel.addRow(new Object[]{
                            student.getStudentNumber(),
                            student.getStudentName(),
                            student.getStudentSurname(),
                            student.getGender()
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to retrieve students.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        //Button Delete
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int[] selectedRows = table.getSelectedRows();
                ArrayList<Student> selectedStudentsList = new ArrayList<>();

                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int selectedRow = selectedRows[i];
                    String studNum = (String) table.getValueAt(selectedRow, 0);
                    String name = (String) table.getValueAt(selectedRow, 1);
                    String surname = (String) table.getValueAt(selectedRow, 2);
                    String gender = (String) table.getValueAt(selectedRow, 3);
                    selectedStudentsList.add(new Student(studNum, name, surname, gender));

                }
                System.out.println(selectedStudentsList.toString());
                client.deleteStudent(selectedStudentsList);
                List<Student> students = client.retrieveStudents();

                if (students != null) {
                    // Clear the table
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    tableModel.setRowCount(0);

                    // Populate the table with retrieved students
                    for (Student student : students) {
                        tableModel.addRow(new Object[]{
                            student.getStudentNumber(),
                            student.getStudentName(),
                            student.getStudentSurname(),
                            student.getGender()
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to retrieve students.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "First Card");
                card4.setVisible(false);
            }
        });

        cardPanel.add(card4, "Forth Card");
    }

    private void RetrieveSubjectsCard() {
        JPanel card5 = new JPanel();
        card5.setLayout(new BorderLayout());

        JPanel pnlNorth = new JPanel();
        JPanel pnlSearch = new JPanel();
        JPanel pnlCentre = new JPanel();
        JPanel pnlSouth = new JPanel();

        JLabel lblLogo = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("Subjects.jpg").getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
        lblLogo.setIcon(imageIcon);

        JLabel lblHeading = new JLabel("Availabe Subjects");
        JLabel lblSearch = new JLabel("Search");
        JTextField txtSearch = new JTextField(30);
        JButton btnRetrieve = new JButton("Retrieve");
        JButton btnBack = new JButton("Back");
        JButton btnDelete = new JButton("Delete");

        pnlNorth.setLayout(new FlowLayout());
        pnlSearch.setLayout(new FlowLayout());
        pnlCentre.setLayout(new BorderLayout());
        pnlSouth.setLayout(new GridLayout(1, 3));

        DefaultTableModel tableModel = new DefaultTableModel();
        //Creating search
        TableRowSorter sorter = new TableRowSorter<>(tableModel);
        JTable table = new JTable(tableModel);
        table.setRowSorter(sorter);
        table.setRowHeight(25);
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(txtSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(txtSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(txtSearch.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });
        
        tableModel.addColumn("Subject Code");
        tableModel.addColumn("Subject Name");

        pnlNorth.add(lblLogo);
        pnlNorth.add(lblHeading);
        lblHeading.setFont(ft1);
        lblHeading.setForeground(Color.yellow);
        pnlNorth.setBackground(new Color(0, 106, 255));

        pnlCentre.add(pnlSearch, BorderLayout.NORTH);
        pnlCentre.add(new JScrollPane(table), BorderLayout.CENTER);

        pnlSearch.add(lblSearch);
        pnlSearch.add(txtSearch);

        pnlSouth.add(btnRetrieve);
        btnRetrieve.setFocusable(false);
        btnRetrieve.setFont(ft3);
        
        pnlSouth.add(btnDelete);
        btnDelete.setFocusable(false);
        btnDelete.setFont(ft3);
        
        pnlSouth.add(btnBack);
        btnBack.setFocusable(false);
        btnBack.setFont(ft3);

        card5.add(pnlNorth, BorderLayout.NORTH);
        card5.add(pnlCentre, BorderLayout.CENTER);
        card5.add(pnlSouth, BorderLayout.SOUTH);

        //RETRIEVE SUBJECT CODE AND SUBJECT NAME FROM SUBJECTS TABLE
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Subject> subjects = client.retrieveSubjects();

                if (subjects != null) {
                    // Clear the table
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    tableModel.setRowCount(0);

                    // Populate the table with retrieved students
                    for (Subject subject : subjects) {
                        tableModel.addRow(new Object[]{
                            subject.getSubjectCode(),
                            subject.getSubjectTitle()
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to retrieve subjects.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //Button Delete
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int[] selectedRows = table.getSelectedRows();
                ArrayList<Subject> selectedSubjectsList = new ArrayList<>();

                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int selectedRow = selectedRows[i];
                    String subjectCode = (String) table.getValueAt(selectedRow, 0);
                    String subjectTitle = (String) table.getValueAt(selectedRow, 1);
                    selectedSubjectsList.add(new Subject(subjectCode, subjectTitle));
                }
                System.out.println(selectedSubjectsList.toString());
                client.deleteSubject(selectedSubjectsList);
                List<Subject> subjects = client.retrieveSubjects();

                if (subjects != null) {
                    // Clear the table
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    tableModel.setRowCount(0);

                    // Populate the table with retrieved students
                    for (Subject subject : subjects) {
                        tableModel.addRow(new Object[]{
                            subject.getSubjectCode(),
                            subject.getSubjectTitle()
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to retrieve subjects.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        btnBack.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                cardLayout.show(cardPanel, "First Card");
                card5.setVisible(false);
            }
        }
        );

        cardPanel.add(card5,
                "Fifth Card");

    }

    private void RetrieveAllCard() {
        JPanel card6 = new JPanel();
        card6.setLayout(new BorderLayout());

        JPanel pnlNorth = new JPanel();
        JPanel pnlSearch = new JPanel();
        JPanel pnlCentre = new JPanel();
        JPanel pnlSouth = new JPanel();

        JLabel lblLogo = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("BookStud.jpg").getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
        lblLogo.setIcon(imageIcon);

        JLabel lblHeading = new JLabel("List of Students & their Subjects");
        JLabel lblSearch = new JLabel("Search");
        JTextField txtSearch = new JTextField(30);

        JButton btnRetrieve = new JButton("Retrieve All");
        btnRetrieve.setFont(ft3);
        btnRetrieve.setFocusable(false);
        
        JButton btnBack = new JButton("Back");
        btnBack.setFont(ft3);
        btnBack.setFocusable(false);

        pnlNorth.setLayout(new FlowLayout());
        pnlSearch.setLayout(new FlowLayout());
        pnlCentre.setLayout(new BorderLayout());
//        pnlCentre.setLayout(new GridLayout(1, 1));
        pnlSouth.setLayout(new GridLayout(1, 2));

        DefaultTableModel tableModel = new DefaultTableModel();
        //Creating search
        TableRowSorter sorter = new TableRowSorter<>(tableModel);
        JTable table = new JTable(tableModel);
        table.setRowSorter(sorter);
        table.setRowHeight(25);
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(txtSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(txtSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(txtSearch.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });
        tableModel.addColumn("Student Number");
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Subject Code");
        tableModel.addColumn("Subject Name");
        pnlNorth.add(lblLogo);
        pnlNorth.add(lblHeading);
        lblHeading.setFont(ft3);
        lblHeading.setForeground(Color.yellow);
        pnlNorth.setBackground(new Color(0, 106, 255));

        pnlCentre.add(pnlSearch, BorderLayout.NORTH);
        pnlCentre.add(new JScrollPane(table), BorderLayout.CENTER);

        pnlSearch.add(lblSearch);
        pnlSearch.add(txtSearch);

        pnlSouth.add(btnRetrieve);
        pnlSouth.add(btnBack);

        card6.add(pnlNorth, BorderLayout.NORTH);
        card6.add(pnlCentre, BorderLayout.CENTER);
        card6.add(pnlSouth, BorderLayout.SOUTH);

        //RETRIEVE STUDENT NUMBER,NAME,SURNAME,SUBJECT CODE AND SUBJECT TITLE FROM THE VIEWS TABLE(Not yet created)
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<StudentInfo> studInfo = client.retrieveStudentInfo();

                if (studInfo != null) {
                    // Clear the table
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    tableModel.setRowCount(0);

                    // Populate the table with retrieved students
                    for (StudentInfo studinfo : studInfo) {
                        tableModel.addRow(new Object[]{
                            studinfo.getStudentNumber(),
                            studinfo.getName(),
                            studinfo.getSurname(),
                            studinfo.getSubjectCode(),
                            studinfo.getSubjectTitle()
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to retrieve student information.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "First Card");
                card6.setVisible(false);
            }
        });

        cardPanel.add(card6, "Sixth Card");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
