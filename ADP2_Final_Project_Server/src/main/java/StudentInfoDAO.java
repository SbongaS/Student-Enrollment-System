import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *  @authors
 *  1.Asimbonge Mbende(221090754)
 *  2.Thandolwethu Zamasiba Khoza(221797289)
 *  3.Sbonga Shweni(219143188)
 */
public class StudentInfoDAO {

    private Connection connection;
    private PreparedStatement preStmt;

    public StudentInfoDAO() {

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

    public List<StudentInfo> getAllStudentInfo() {

        List<StudentInfo> studInfo = new ArrayList<>();
        String query = "SELECT * FROM StudentInfo";
        try {
            preStmt = connection.prepareStatement(query);
            ResultSet resultSet = preStmt.executeQuery();

            while (resultSet.next()) {
                String studNum = resultSet.getString("Student_Number");
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("Surname");
                String subjCode = resultSet.getString("Subject_Code");
                String subjTitle = resultSet.getString("Subject_Title");

                studInfo.add(new StudentInfo(studNum, name, surname, subjCode, subjTitle));
            }

        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error: " + sqlException.getMessage());
        }

        return studInfo;
    }
}
