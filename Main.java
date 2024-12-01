import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== HRM and Payroll System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Payroll");
            System.out.println("3. View Employee Payroll");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Position: ");
                    String position = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
    
                    db.addEmployee(firstName, lastName, phone, position, department, salary);
                    break;
    
                case 2:
                    System.out.print("Enter Employee ID: ");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Pay Period Start (YYYY-MM-DD): ");
                    String payPeriodStart = scanner.nextLine();
                    System.out.print("Enter Pay Period End (YYYY-MM-DD): ");
                    String payPeriodEnd = scanner.nextLine();
                    System.out.print("Enter Base Salary: ");
                    double baseSalary = scanner.nextDouble();
                    System.out.print("Enter Bonus: ");
                    double bonus = scanner.nextDouble();
                    System.out.print("Enter Deductions: ");
                    double deductions = scanner.nextDouble();
                    System.out.print("Enter Payment Date (YYYY-MM-DD): ");
                    scanner.nextLine();
                    String paymentDate = scanner.nextLine();
    
                    db.addPayroll(employeeId, payPeriodStart, payPeriodEnd, baseSalary, bonus, deductions, paymentDate);
                    break;
    
                case 3:
                    db.getEmployeePayroll();
                    break;
    
                case 4:
                    System.out.print("Enter employee ID to delete: ");
                    employeeId = scanner.nextInt();
                    db.deleteEmployee(employeeId);
                    break;
    
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            scanner.nextLine(); 
        }
        scanner.close();
    }
}

