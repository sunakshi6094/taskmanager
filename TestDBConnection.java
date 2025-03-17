
import java.DatabaseConnection;
import java.sql.Connection;

public class TestDBConnection {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ Database Connected Successfully!");
        } else {
            System.out.println("❌ Database Connection Failed!");
        }
    }
}
