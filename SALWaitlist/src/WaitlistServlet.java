

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SALWaitlist.Student;

@WebServlet("/WaitlistServlet")
public class WaitlistServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		String email = request.getParameter("email").toLowerCase();
		List<Student> students = new ArrayList<Student>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalDB?user=root&password=12345&userSSL=false");
			
			String sql = "SELECT * FROM CP WHERE Email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			String classID = "";
			if (rs.next()) {
				classID = rs.getString("Class_ID");
				
				sql = "SELECT * FROM Current_Users WHERE Class_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, classID);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					String studentEmail = rs.getString("Email");
					
					sql = "SELECT * FROM Students WHERE Email = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, studentEmail);
					
					try (ResultSet studentRS = ps.executeQuery()) {
						if (studentRS.next()) {
							String name = studentRS.getString("Full_Name");
							String imgURL = studentRS.getString("Profile_Picture_URL");
							String phoneNumber = studentRS.getString("Phone_Number");
							
							Student student = new Student(studentEmail, name, imgURL, phoneNumber);
							students.add(student);
						}
					}
				}

				request.getSession().setAttribute("students", students);
				request.getRequestDispatcher("/cp-home.jsp").forward(request, response);
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		} finally {
			try {
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
