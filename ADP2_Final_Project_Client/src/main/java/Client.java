
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @authors
 * 1.Asimbonge Mbende(221090754)
 * 2.Thandolwethu Zamasiba Khoza(221797289)
 * 3.Sbonga Shweni(219143188)
 */

public class Client {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket clientSocket;
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8008;
    Object response;

    public Client() {
        try {
            clientSocket = new Socket(SERVER_IP, SERVER_PORT);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Method to send user details (either Admins or Student) to the server for authentication
    public boolean sendUserDetails(Object obj) {
        try {
            // Send the object to the server
            out.writeObject(obj);
            out.flush();

            // Receive the authentication result from the server
            response = in.readObject();

            if (response instanceof Boolean) {
                return (Boolean) response;
            } else {
                // Handle unexpected response from the server
                return false;
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addStudent(AddStudent student) {
        try {
            // Send the student object to the server for addition
            out.writeObject(student);
            System.out.println("Student sent");
            out.flush();
            // Receive the response from the server
            response = in.readObject();
            if (response instanceof Boolean) {
                return (Boolean) response;
            } else {
                return false; // Unexpected response
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addSubject(Subject subject) {
        try {
            // Send the subject object to the server for addition
            out.writeObject(subject);
            out.flush();

            // Receive the response from the server
            response = in.readObject();

            if (response instanceof Boolean) {
                return (Boolean) response;
            } else {
                return false; // Unexpected response
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Student> retrieveStudents() {
        try {
            // Send a request to retrieve students
            out.writeObject("Retrieve Students");
            out.flush();

            // Receive the list of students from the server
            response = in.readObject();

            if (response instanceof List<?>) {
                return (List<Student>) response;
            } else {
                return null; // Unexpected response
            }

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<StudentInfo> retrieveStudentInfo() {
        try {
            // Send a request to retrieve students
            out.writeObject("Retrieve All");
            out.flush();

            // Receive the list of students from the server
            response = in.readObject();

            if (response instanceof List<?>) {
                return (List<StudentInfo>) response;
            } else {
                return null; // Unexpected response
            }

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Subject> retrieveSubjects() {
        try {
            // Send a request to retrieve Subjects
            out.writeObject("Retrieve Subjects");
            out.flush();

            // Receive the list of Subjects from the server
            response = in.readObject();

            if (response instanceof List<?>) {
                return (List<Subject>) response;
            } else {
                return null; // Unexpected response
            }

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void sendEnrolDetails(Object obj) {
        try {
            // Send the object to the server
            out.writeObject(obj);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Object recieveEnrolDetails() {
        try {
            response = in.readObject();
           
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return response;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public void deleteSubject(Object obj) {
        try {
            // Send the object to the server
            out.writeObject(obj);
            out.flush();
            response = in.readObject();
            if (response instanceof Boolean) {
                JOptionPane.showMessageDialog(null, "Subject(s) successfully deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete subject(s).");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cancelSubject(Object obj) {
        try {
            // Send the object to the server
            out.writeObject(obj);
            out.flush();
            response = in.readObject();
            if (response instanceof Boolean) {
                JOptionPane.showMessageDialog(null, "Subject successfully deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete subject.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteStudent(Object obj) {
        try {
            // Send the object to the server
            out.writeObject(obj);
            out.flush();
            response = in.readObject();
            if (response instanceof Boolean) {
                JOptionPane.showMessageDialog(null, "Student(s) successfully deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete student(s).");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object recieveEnrolledDetails() {
        try {
            response = in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public void closeClient() {
        try {
            out.writeObject("Exit");
            out.flush();
            out.close();
            in.close();
            clientSocket.close();
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
