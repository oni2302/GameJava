
package data;
import java.sql.*;
/**
 *
 * @author oni
 */
public class Connect {
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game","root","P@ssd2302");
        } catch (Exception e) {
        }
 return conn;

    }
}
