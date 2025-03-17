import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TaskManager {

    // Add Task
    public static void addTask(String task) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Database connection failed!");
            return;
        }

        String query = "INSERT INTO tasks (task) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, task);
            pstmt.executeUpdate();
            System.out.println("✅ Task added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Tasks
    public static void getTasks() {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Database connection failed!");
            return;
        }

        String query = "SELECT * FROM tasks";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (!rs.isBeforeFirst()) { // Check if result set is empty
                System.out.println("📭 No tasks found.");
                return;
            }

            System.out.println("\n📋 Your To-Do List:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String task = rs.getString("task");
                boolean status = rs.getBoolean("status");

                String statusText = status ? "[✔ Completed]" : "[❌ Pending]";
                System.out.println(id + ". " + statusText + " " + task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mark Task as Completed
    public static void updateTaskStatus(int taskId) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Database connection failed!");
            return;
        }

        String query = "UPDATE tasks SET status = TRUE WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, taskId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Task ID " + taskId + " marked as completed!");
            } else {
                System.out.println("⚠ Task ID " + taskId + " not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n📌 To-Do List Manager");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task: ");
                    String task = scanner.nextLine();
                    addTask(task);
                    break;
                case 2:
                    getTasks();
                    break;
                case 3:
                    System.out.print("Enter Task ID to mark as completed: ");
                    int taskId = scanner.nextInt();
                    updateTaskStatus(taskId);
                    break;
                case 4:
                    System.out.println("👋 Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }
}
