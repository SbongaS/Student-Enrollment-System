
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @authors
 * 1.Asimbonge Mbende(221090754)
 * 2.Thandolwethu Zamasiba Khoza(221797289)
 * 3.Sbonga Shweni(219143188)
 */

public class Server {

    public static ObjectOutputStream out;
    public static ObjectInputStream in;
    public static ServerSocket serverSocket;
    public static Socket clientSocket;
    public static Object receivedObject;
    public static EnrolmentDAO enrolDAO = new EnrolmentDAO();
    public static StudentDAO studDAO = new StudentDAO();
    public static SubjectDAO subjDAO = new SubjectDAO();

    public Server() {
        try {
            serverSocket = new ServerSocket(8008); // Listen on port 8008
            System.out.println("Server is running and waiting for connections...");
            while (true) {
                clientSocket = serverSocket.accept(); // Accept incoming connection
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Input and output streams
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());

                processClient(); // Handle client requests
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void processClient() {
        while (true) {
            try {
                boolean isAdminLoggedIn = false, isStudentLoggedIn = false;

                receivedObject = in.readObject();
                if (receivedObject instanceof Admins) {
                    Admins admin = (Admins) receivedObject;
                    UsersDAO user = new UsersDAO();
                    isAdminLoggedIn = user.verifyAdminLogin(admin.getUsername(), admin.getPassword());
                    out.writeObject(isAdminLoggedIn);
                    out.flush();
                } else if (receivedObject instanceof Student) {
                    Student student = (Student) receivedObject;
                    UsersDAO user = new UsersDAO();
                    isStudentLoggedIn = user.verifyStudentLogin(student.getStudentNumber(), student.getPassword());
                    out.writeObject(isStudentLoggedIn);
                    out.flush();
                } else if (receivedObject instanceof String && receivedObject.equals("Login")) {
                    // Send authentication results to the client
                    out.writeObject(isAdminLoggedIn || isStudentLoggedIn);
                    out.flush();
                } else if (receivedObject instanceof Subject) {
                    Subject subject = (Subject) receivedObject;
                    if (subjDAO.isSubjectCodeUnique(subject)) {
                        subjDAO.addSubjects(subject);
                        out.writeObject(true);
                        System.out.println("Subject added successfully!");
                    } else {
                        out.writeObject(false);
                        System.out.println("Failed to insert the subject!");
                    }
                    out.flush();
                }else if (receivedObject instanceof String && receivedObject.equals("Retrieve All")) {
                    StudentInfoDAO studInfo = new StudentInfoDAO();
                    out.writeObject(studInfo.getAllStudentInfo());
                    out.flush();
                } else if (receivedObject instanceof String && receivedObject.equals("Retrieve Subjects")) {
                    SubjectDAO sub = new SubjectDAO();
                    out.writeObject(sub.getAllSubjects());
                    out.flush();
                } else if (receivedObject instanceof String && receivedObject.equals("Retrieve Students")) {
                    out.writeObject(studDAO.getAllStudents());
                    out.flush();
                } else if (receivedObject instanceof String && ((String) receivedObject).contains("Retrieve Enrolledlist")) {
                    // Remove "Retrieve" from the receivedObject
                    String enrolledListString = ((String) receivedObject).replace("Retrieve Enrolledlist", "");
                    out.writeObject(enrolDAO.getSubjects(enrolledListString));
                    out.flush();
                } else if (receivedObject instanceof String && ((String) receivedObject).contains("cancel")) {
                    // Remove "Retrieve" from the receivedObject
                    String enrolledListString = ((String) receivedObject).replace("cancel", "");
                    // the first 10 characters is a student number
                    String studentNumber = enrolledListString.substring(0, 10);
                    String subjectCode = enrolledListString.substring(10);
                    out.writeObject(enrolDAO.cancelSubj(studentNumber, subjectCode));
                    out.flush();
                } else if (receivedObject instanceof AddStudent) {
                    AddStudent student = (AddStudent) receivedObject;
                    if (studDAO.isStudentNumberUnique(student)) {
                        studDAO.addStudent(student);
                        out.writeObject(true);
                    } else {
                        out.writeObject(false);
                    }
                    out.flush();
                } else if (receivedObject instanceof ArrayList<?>) {
                    ArrayList<?> receivedList = (ArrayList<?>) receivedObject;
                    if (!receivedList.isEmpty() && receivedList.get(0) instanceof EnrolmentPOJO) {
                        ArrayList<EnrolmentPOJO> enrolmentList = (ArrayList<EnrolmentPOJO>) receivedList;
                        enrolDAO.addEnrolData(enrolmentList);
                    } else if (!receivedList.isEmpty() && receivedList.get(0) instanceof Subject) {
                        ArrayList<Subject> subjectList = (ArrayList<Subject>) receivedList;
                        out.writeObject(subjDAO.delete(subjectList));
                        out.flush();
                    } else if (!receivedList.isEmpty() && receivedList.get(0) instanceof Student) {
                        ArrayList<Student> studentList = (ArrayList<Student>) receivedList;
                        out.writeObject(studDAO.delete(studentList));
                        out.flush();

                    } else {
                        System.out.println("Received object is not an ArrayList<EnrolmentPOJO>.");
                    }
                } else if (receivedObject instanceof String && ((String) receivedObject).equals("Exit")) {
                    closeConnection();
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void closeConnection() {
        try {
            in.close();
            out.close();
            serverSocket.close();
            clientSocket.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
