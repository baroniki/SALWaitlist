

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

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalDB?user=root&password=12345&userSSL=false");
			
			String sql = "SELECT * FROM CP WHERE Email=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email.toLowerCase());
			rs = ps.executeQuery();
			
			if (rs.next()) {
				out.write("cp");
			} else {
				sql = "SELECT * FROM Students WHERE Email=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, email.toLowerCase());
				rs = ps.executeQuery();
				
				if (rs.next()) {
					out.write("student");
				} else {
					out.write("new");
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
