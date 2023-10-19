


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *  @authors
 *  1.Asimbonge Mbende(221090754)
 *  2.Thandolwethu Zamasiba Khoza(221797289)
 *  3.Sbonga Shweni(219143188)
 */
public class LoginApp extends JFrame implements ActionListener {

    private JPanel pnlNorth, pnlCentre, pnlSouth;
    private ImageIcon icon;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblImage, lblHeading, lblUsername, lblPassword;
    private JRadioButton radAdmin, radStud;
    private ButtonGroup btnGroup;
    private JButton btnLogin, btnExit;
    private Font ft1, ft2, ft3;
    Client client;


    public LoginApp(Client client) {
        super("User Auntentication");
        this.client = client; // Set the client instance
        pnlNorth = new JPanel();
        pnlCentre = new JPanel();
        pnlSouth = new JPanel();

        icon = new ImageIcon(new ImageIcon("Login.png").getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));

        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);

        lblImage = new JLabel(icon);
        lblHeading = new JLabel("Sign in");
        lblUsername = new JLabel("Username: ");
        lblPassword = new JLabel("Password: ");

        radAdmin = new JRadioButton("Admin");
        radStud = new JRadioButton("Student");

        btnGroup = new ButtonGroup();
        btnGroup.add(radAdmin);
        btnGroup.add(radStud);

        btnLogin = new JButton("Login");
        btnLogin.setFocusable(false);

        btnExit = new JButton("Exit");
        btnExit.setFocusable(false);

        ft1 = new Font("Arial", Font.BOLD, 32);
        ft2 = new Font("Arial", Font.PLAIN, 22);
        ft3 = new Font("Arial", Font.PLAIN, 24);
    }

    public void setGUI() {
        pnlNorth.setLayout(new GridLayout(1, 2));
        pnlCentre.setLayout(new GridLayout(3, 2));
        pnlSouth.setLayout(new GridLayout(1, 2));

        pnlNorth.add(lblImage);
        pnlNorth.add(lblHeading);
        lblHeading.setFont(ft1);
        lblHeading.setForeground(Color.yellow);
        pnlNorth.setBackground(new Color(0, 106, 255));

        pnlCentre.add(lblUsername);
        pnlCentre.add(txtUsername);

        pnlCentre.add(lblPassword);
        pnlCentre.add(txtPassword);

        pnlCentre.add(radAdmin);
        pnlCentre.add(radStud);

        pnlSouth.add(btnLogin);
        btnLogin.setFont(ft3);
        pnlSouth.add(btnExit);
        btnExit.setFont(ft3);

        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlCentre, BorderLayout.CENTER);
        this.add(pnlSouth, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnLogin.addActionListener(this);
        btnExit.addActionListener(this);

        this.setSize(300, 300);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    private void userLogin(Client client) {
        String userType = radAdmin.isSelected() ? "Admin" : "Student";
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        boolean isAuthenticated;

        if (userType.equals("Admin")) {
            // Create an Admins object and send it to the server for authentication
            Admins admin = new Admins(username, password);
            isAuthenticated = client.sendUserDetails(admin);

            if (isAuthenticated) {
                JOptionPane.showMessageDialog(null, "Welcome back " + username + "!");
                dispose();
                AdminGUI adminGUI = new AdminGUI(this.client);
                adminGUI.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid " + userType + " credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (userType.equals("Student")) {
            // Create a Student object and send it to the server for authentication
            Student student = new Student(username, password);
            isAuthenticated = client.sendUserDetails(student);

            if (isAuthenticated) {
                JOptionPane.showMessageDialog(null, "Welcome back " + username + "!");
                // Perform actions for successful login
                dispose();
                EnrolTable enrolTable = new EnrolTable(this.client,username);
                enrolTable.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid " + userType + " credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            userLogin(client);

        } else if (e.getActionCommand().equals("Exit")) {
            client.closeClient();
        }

    }
}
