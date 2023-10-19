import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *  @authors
 *  1.Asimbonge Mbende(221090754)
 *  2.Thandolwethu Zamasiba Khoza(221797289)
 *  3.Sbonga Shweni(219143188)
 */
public class DBConnection {

    public static Connection getConnection() throws SQLException {
        String DB_URL = "jdbc:derby://localhost:1527/StudentEnrolmentDB";
        String DB_USERNAME = "administrator";
        String DB_PASSWORD = "password";

        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        return conn;
    }

}
