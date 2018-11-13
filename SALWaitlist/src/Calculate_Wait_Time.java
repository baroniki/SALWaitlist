
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Calculate_Wait_Time
 */
@WebServlet("/Calculate_Wait_Time")
public class Calculate_Wait_Time extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String dbPass = "root";
	private String connString = String.format("jdbc:mysql://localhost:3306/finalDB?user=root&password=%s&useSSL=false", dbPass);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calculate_Wait_Time() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String classid = request.getParameter("classid");
		int wait_time = calculateWaitTime(email, classid);
		int num_ppl_ahead = wait_time/6;
		out.print(num_ppl_ahead);
	}
	
	public int calculateWaitTime(String email, String classid) {
		java.sql.Connection conn = null;
		Statement st = null;
		try {
			//connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connString);
			st = conn.createStatement();
			//access database
			st.executeUpdate("USE finalDB;");
			//prepared statement to determine row count
			PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) as rowcount FROM Current_Users WHERE Class_ID='"+classid+"'");
			ResultSet result = statement.executeQuery();
			result.next();
			int count = result.getInt("rowcount");
			//determine total wait time
			int total_wait_time = count*6;
			result.close();
			
			return total_wait_time;
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
		return 0;
	}
}
