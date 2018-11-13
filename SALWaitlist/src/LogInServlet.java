
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	String dbPass = "root";
	private String connString = String.format("jdbc:mysql://localhost:3306/finalDB?user=root&password=%s&useSSL=false", dbPass);

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		String nextPage = request.getParameter("nextPage");
		String email = request.getParameter("email").toLowerCase();
		String name = request.getParameter("name");
		String imgURL = request.getParameter("imgURL");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connString);
			
			if (action.equals("authenticate")) {
				String sql = "SELECT * FROM CP WHERE Email = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, email);
				rs = ps.executeQuery();
				
				if (rs.next()) {
					out.write("cp");
				} else {
					sql = "SELECT * FROM Students WHERE Email = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, email);
					rs = ps.executeQuery();
					
					if (rs.next()) {
						out.write("student");
					} else {
						sql = "INSERT INTO Students (Email, Full_Name, Profile_Picture_URL) VALUES (?,?,?)";
						ps = conn.prepareStatement(sql);
						ps.setString(1, email);
						ps.setString(2, name);
						ps.setString(3, imgURL);
						ps.executeUpdate();
						out.write("new");
					}
				}
			} else if (action.equals("redirect")) {
				request.getSession().setAttribute("email", email);
				request.getSession().setAttribute("name", name);
				request.getSession().setAttribute("imgURL", imgURL);
				if (nextPage.equals("/cp-home.jsp")) {
					request.getRequestDispatcher("/WaitlistServlet").forward(request, response);
				} else {
					request.getRequestDispatcher(nextPage).forward(request, response);
				}
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle closing conn: " + sqle.getMessage());
			}
		}
	}
}
