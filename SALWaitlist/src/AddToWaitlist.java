
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddToWaitlist
 */
@WebServlet("/AddToWaitlist")
public class AddToWaitlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String dbPass = "root";
	private String connString = String.format("jdbc:mysql://localhost:3306/finalDB?user=root&password=%s&useSSL=false", dbPass);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToWaitlist() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String classid = request.getParameter("classid");
		String email = request.getParameter("email");
		addToWaitlist(email, classid);
	}

	public void addToWaitlist(String email, String classid) {
		java.sql.Connection conn = null;
		Statement st = null;
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connString);
			st = conn.createStatement();
			// access database
			st.executeUpdate("USE finalDB;");
			// insert into database
			String query = "INSERT INTO Current_Users(Email, Class_ID)" + "values(?, ?)";
			// prepared statement to grab student classes
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, classid);
			statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
