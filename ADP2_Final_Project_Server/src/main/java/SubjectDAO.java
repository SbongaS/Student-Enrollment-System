
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

public class SubjectDAO {

    private Connection connection;
    private PreparedStatement preStmt;

    public SubjectDAO() {

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

    public void addSubjects(Subject subject) {
        String query = "INSERT INTO Subjects (Subject_Code,Subject_Title) VALUES (?, ?)";
        try {
            preStmt = connection.prepareStatement(query);
            preStmt.setString(1, subject.getSubjectCode());
            preStmt.setString(2, subject.getSubjectTitle());
            int rowsAffected = preStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully inserted the subject!");
            } else {
                System.out.println("No subject records were inserted!");

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
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public boolean isSubjectCodeUnique(Subject subject) {
        ResultSet resultSet = null;
        boolean unique = true;
        String query = "SELECT * FROM Subjects WHERE Subject_Code = ?";

        try {

            preStmt = connection.prepareStatement(query);
            preStmt.setString(1, subject.getSubjectCode());
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

    public List<Subject> getAllSubjects() {

        List<Subject> subjects = new ArrayList<>();
        String query = "SELECT * FROM Subjects";
        try {
            preStmt = connection.prepareStatement(query);
            ResultSet resultSet = preStmt.executeQuery();

            while (resultSet.next()) {
                String subjCode = resultSet.getString("Subject_Code");
                String subjTitle = resultSet.getString("Subject_Title");
                subjects.add(new Subject(subjCode, subjTitle));
            }

        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error: " + sqlException.getMessage());
        }

        return subjects;
    }



public boolean delete(List<Subject> subjects) {
    System.out.println("Subjects to be deleted within DAO: " + subjects.toString());
    try {
        String query = "DELETE FROM Subjects WHERE Subject_Code = ?";
        preStmt = this.connection.prepareStatement(query);

        for (Subject subject : subjects) {
            preStmt.setString(1, subject.getSubjectCode());
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
