
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @authors
 * 1.Asimbonge Mbende(221090754)
 * 2.Thandolwethu Zamasiba Khoza(221797289)
 * 3.Sbonga Shweni(219143188)
 */

public class StudentDAO {

    private Connection connection;
    private PreparedStatement preStmt;

    public StudentDAO() {

        try {
            this.connection = DBConnection.getConnection();

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Error: Failed to establish database connection" + exception.getMessage());
        }
    }

    public void closeConnections() {
        try {
            this.connection.close();
        } catch (SQLException exception) {
        }
    }

    public boolean addStudent(AddStudent student) {

        String query = "INSERT INTO Students (Student_Number, Name, Surname, Gender, Password) VALUES (?, ?, ?, ?, ?)";

        try {
            preStmt = connection.prepareStatement(query);
            preStmt.setString(1, student.getStudentNumber());
            preStmt.setString(2, student.getStudentName());
            preStmt.setString(3, student.getStudentSurname());
            preStmt.setString(4, student.getGender());
            preStmt.setString(5, student.getPassword());

            int rowsAffected = preStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successfully inserted the student!");
                return true;
            } else {
                System.out.println("No records were inserted.");
                return false;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + sqlException.getMessage());
            return false;
        } finally {

            try {
                if (preStmt != null) {
                    preStmt.close();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public boolean isStudentNumberUnique(AddStudent student) {
        ResultSet resultSet = null;
        boolean unique = true;
        String query = "SELECT * FROM Students WHERE Student_Number = ?";
        try {
            preStmt = connection.prepareStatement(query);
            preStmt.setString(1, student.getStudentNumber());
            resultSet = preStmt.executeQuery();

            if (resultSet.next()) {
                unique = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preStmt != null) {
                    preStmt.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
        return unique;
    }

    public Student getStudent(String studentNumber) {
        Student student = null;
        ResultSet resultSet = null;
        String query = "SELECT Name, Surname FROM Students WHERE Student_Number=?";
        try {
            preStmt = this.connection.prepareStatement(query);
            preStmt.setString(1, studentNumber);
            resultSet = preStmt.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("Surname");

                student = new Student();
                student.setStudentName(name);
                student.setStudentSurname(surname);
            }

        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error: " + sqlException);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Error: " + exception);
        } finally {
            try {
                if (preStmt != null) {
                    preStmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }

        return student;
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT Student_Number, Name, Surname, Gender FROM Students";
        ResultSet resultSet = null;
        try {
            preStmt = this.connection.prepareStatement(query);
            resultSet = preStmt.executeQuery();

            while (resultSet.next()) {
                String studNum = resultSet.getString("Student_Number");
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("Surname");
                String gender = resultSet.getString("Gender");

                students.add(new Student(studNum, name, surname, gender));
            }

        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error: " + sqlException);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Error: " + exception);
        } finally {

            try {
                if (preStmt != null) {
                    preStmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }

        }

        return students;
    }


    public boolean delete(List<Student> students) {
        System.out.println("Students to be deleted within DAO: " + students.toString());
        try {
            String query = "DELETE FROM Students WHERE Student_Number = ?";
            preStmt = this.connection.prepareStatement(query);

            for (Student student : students) {
                preStmt.setString(1, student.getStudentNumber());
                preStmt.addBatch(); // Add the statement to the batch
            }

            int[] rowsAffected = preStmt.executeBatch();

            // Check if any rows were deleted
            int totalRowsDeleted = Arrays.stream(rowsAffected).sum();
            if (totalRowsDeleted > 0) {
                return true; // Deletion was successful
            } else {
                return false; // No records were deleted
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error: " + sqlException);
            return false; // Deletion failed due to an exception
        } finally {
            // Close the PreparedStatement in a finally block
            try {
                if (preStmt != null) {
                    preStmt.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
