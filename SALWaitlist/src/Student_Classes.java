import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Student_Classes
 */
@WebServlet("/Student_Classes")
public class Student_Classes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String connString = "jdbc:mysql://localhost:3306/finalDB?user=root&password=root&useSSL=false";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_Classes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String returned_classes = grabStudentClasses(email);
		out.print(returned_classes);
	}
	public String grabStudentClasses(String email) {
		java.sql.Connection conn = null;
		Statement st = null;
		String returned_classes="";
		try {
			//connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connString);
			st = conn.createStatement();
			//access database
			st.executeUpdate("USE finalDB;");
			//prepared statement to grab student classes
			PreparedStatement statement = conn.prepareStatement("SELECT Class_ID FROM Enrollment WHERE email='"+email+"'");
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				returned_classes += result.getString("Class_ID") + "&";
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return returned_classes;
	}
}
