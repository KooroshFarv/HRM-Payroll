import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//dbbbbbb

public class TestConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/payroll";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        System.out.println("Testing connection to the database...");

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                System.out.println("Connected : " + DB_URL);
            } else {
                System.out.println("Failed!");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed");
            System.err.println("Error message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
