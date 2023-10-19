
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *  @authors
 *  1.Asimbonge Mbende(221090754)
 *  2.Thandolwethu Zamasiba Khoza(221797289)
 *  3.Sbonga Shweni(219143188)
 */
public class UsersDAO {

    private Connection connection;
    private PreparedStatement preStmt;

    public UsersDAO() {

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

    public boolean verifyAdminLogin(String username, String password) {
        String query = "SELECT * FROM Admins WHERE Username = ? AND Password = ?";
        ResultSet resultSet = null;
        try {
            preStmt = connection.prepareStatement(query);
            preStmt.setString(1, username);
            preStmt.setString(2, password);
            resultSet = preStmt.executeQuery();
            return resultSet.next(); // Returns true if a matching record is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (preStmt != null) {
                try {
                    preStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean verifyStudentLogin(String username, String password) {
        String query = "SELECT Student_Number,Password FROM Students WHERE Student_Number = ? AND Password = ?";
        ResultSet resultSet = null;
        try {
            preStmt = connection.prepareStatement(query);
            preStmt.setString(1, username);
            preStmt.setString(2, password);
            resultSet = preStmt.executeQuery();
            return resultSet.next(); // Returns true if a matching record is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (preStmt != null) {
                try {
                    preStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
