
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @authors
 * 1.Asimbonge Mbende(221090754)
 * 2.Thandolwethu Zamasiba Khoza(221797289)
 * 3.Sbonga Shweni(219143188)
 */

public class EnrolmentDAO {

    private Connection connection;
    private PreparedStatement preStmt;

    public EnrolmentDAO() {
        try {
            this.connection = DBConnection.getConnection();

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Error: Failed to establish database connection" + exception.getMessage());
        }
    }


    public void addEnrolData(List<EnrolmentPOJO> enrollmentList) {
        String query = "INSERT INTO Enrollments (Student_Number, Subject_Code, Subject_Title) VALUES (?, ?, ?)";

        try {
            preStmt = connection.prepareStatement(query);

            for (EnrolmentPOJO subject : enrollmentList) {
                preStmt.setString(1, subject.getStudentNumber());
                preStmt.setString(2, subject.getSubjectCode());
                preStmt.setString(3, subject.getSubjectTitle());

                int rowsAffected = preStmt.executeUpdate();

                // You may choose to log or keep track of the successfully inserted rows here.
                if (rowsAffected > 0) {
                    System.out.println("Successfully inserted 1 row.");
                } else {
                    System.out.println("Failed to insert a row.");
                }
            }
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error: " + sqlException);
        } finally {
            // Close the PreparedStatement
            try {
                if (preStmt != null) {
                    preStmt.close();
                }
            } catch (SQLException e) {
                // Handle the exception or log it
            }
        }
    }

    public List<EnrolmentPOJO> getSubjects(String studentNumber) {
        List<EnrolmentPOJO> enrollmentList = new ArrayList<>();
        String query = "SELECT Subject_Code,Subject_Title FROM Enrollments WHERE Student_Number=?";
        try {
            preStmt = connection.prepareStatement(query);
            preStmt.setString(1, studentNumber);
            ResultSet resultSet = preStmt.executeQuery();

            while (resultSet.next()) {
                String subjCode = resultSet.getString("Subject_Code");
                String subjTitle = resultSet.getString("Subject_Title");
                enrollmentList.add(new EnrolmentPOJO(subjCode, subjTitle));
            }

        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error: " + sqlException.getMessage());
        }

        return enrollmentList;
    }

    public boolean cancelSubj(String studentNum, String subjCode) {
        try {
            String query = "DELETE FROM Enrollments WHERE Student_Number = ? AND Subject_Code = ?";
            preStmt = this.connection.prepareStatement(query);
            preStmt.setString(1, studentNum);
            preStmt.setString(2, subjCode);
            int rowsAffected = preStmt.executeUpdate();

            // Check if any rows were deleted
            if (rowsAffected > 0) {
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
