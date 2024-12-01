import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Load JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded.");

        // Define Connection URL
        String url = "jdbc:mysql://localhost:3306/Payroll?useSSL=false&serverTimezone=UTC&socket=/Applications/XAMPP/xamppfiles/var/mysql/mysql.sock";
        String user = "root";
        String password = ""; 

        // Establish Connection
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to the database.");
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Payroll?useSSL=false&serverTimezone=UTC&socket=/Applications/XAMPP/xamppfiles/var/mysql/mysql.sock";
        String user = "root";
        String password = ""; 
        return DriverManager.getConnection(url, user, password);
    }
    
    //Employees 
    public void addEmployee(String firstName, String lastName, String phone, String position, String department, double salary) {
        String sql = "INSERT INTO Employee (first_name, last_name, phone, position, department, salary) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, phone);
            pstmt.setString(4, position);
            pstmt.setString(5, department);
            pstmt.setDouble(6, salary);
            pstmt.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to add employee: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //deleting employees
    public void deleteEmployee(int employeeId) {
        String sql = "DELETE FROM Employee WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted.");
            } else {
                System.out.println("Employee not founded.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to delete: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
   
    //  Payrollzzzzz
    public void addPayroll(int employeeId, String payPeriodStart, String payPeriodEnd, double baseSalary, double bonus, double deductions, String paymentDate) {
        String sql = "INSERT INTO Payroll (employee_id, pay_period_start, pay_period_end, base_salary, bonus, deductions, payment_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            pstmt.setDate(2, Date.valueOf(payPeriodStart)); 
            pstmt.setDate(3, Date.valueOf(payPeriodEnd));   
            pstmt.setDouble(4, baseSalary);
            pstmt.setDouble(5, bonus);
            pstmt.setDouble(6, deductions);
            pstmt.setDate(7, Date.valueOf(paymentDate)); 
            pstmt.executeUpdate();
            System.out.println("Payroll record added successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to add payroll record: " + e.getMessage());
            e.printStackTrace();
        }
    }

  
    public void getEmployeePayroll() {
        String sql = "SELECT e.first_name, e.last_name, p.pay_period_start, p.pay_period_end, p.base_salary, p.bonus, p.deductions, p.net_pay " +
                     "FROM Employee e JOIN Payroll p ON e.id = p.employee_id";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name") +
                                   ", Pay Period: " + rs.getDate("pay_period_start") + " to " + rs.getDate("pay_period_end") +
                                   ", Base Salary: " + rs.getDouble("base_salary") +
                                   ", Bonus: " + rs.getDouble("bonus") +
                                   ", Deductions: " + rs.getDouble("deductions") +
                                   ", Net Pay: " + rs.getDouble("net_pay"));
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch payroll records: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
