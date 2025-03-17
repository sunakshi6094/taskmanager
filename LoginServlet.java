import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/todo_list_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sonu@22";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        String username = request.getParameter("username");  // Get username from form

        try {
            // Step 1: Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Connect to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Step 3: Insert login details into the table
            String sql = "INSERT INTO login_details (username) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.executeUpdate();

            // Step 4: Close the connection
            stmt.close();
            conn.close();

            response.getWriter().write("Login recorded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
