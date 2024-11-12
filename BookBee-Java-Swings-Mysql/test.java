import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {

    public static void main(String[] args) {

        // Database connection parameters
        String dbUrl = "jdbc:mysql://localhost:3306/bookbee";
        String username = "root";
        String password = "Mysql@78298365";

        // JDBC driver name
        String driverName = "com.mysql.cj.jdbc.Driver";

        try {
            // Load JDBC driver
            Class.forName(driverName);

            // Establish connection
            Connection conn = DriverManager.getConnection(dbUrl, username, password);

            // Test connection
            if (conn != null) {
                System.out.println("JDBC connection established successfully!");
            } else {
                System.out.println("Failed to establish JDBC connection.");
            }

            // Close connection
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error establishing JDBC connection: " + e.getMessage());
        }
    }
}