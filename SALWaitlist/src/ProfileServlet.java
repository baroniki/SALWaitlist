
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

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	String dbPass = "root";
	private String connString = String.format("jdbc:mysql://localhost:3306/finalDB?user=root&password=%s&useSSL=false", dbPass);

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		String email = request.getParameter("email").toLowerCase();
		String phoneNumber = request.getParameter("phoneNumber");
		String classes = request.getParameter("classes");
		
		List<String> classList = new ArrayList<String>();
		int index = 0;
		while (index != -1) {
			index = classes.indexOf("CSCI", index);
			if (index != -1) {
				String className = classes.substring(index, index + 8);
				classList.add(className);
				index += className.length();
			}
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connString);
			
			String sql = "";
			if (phoneNumber != null && !phoneNumber.equals("")) {
				sql = "UPDATE Students SET Phone_Number = ? WHERE Email = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, phoneNumber);
				ps.setString(2, email);
				ps.executeUpdate();
			}
			
			sql = "INSERT INTO Enrollment (Class_ID, Email) VALUES (?,?)";
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < classList.size(); i++) {
				ps.setString(1, classList.get(i));
				ps.setString(2, email);
				ps.addBatch();
			}
			ps.executeBatch();
			
			
	// DEBUG
			sql = "SELECT * FROM Students";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String emailRS = rs.getString("Email");
				String nameRS = rs.getString("Full_Name");
				String imgURL = rs.getString("Profile_Picture_URL");
				String phone = rs.getString("Phone_Number");
				
				System.out.print("Email: " + emailRS);
				System.out.print(", Name: " + nameRS);
				System.out.print(", Image URL: " + imgURL);
				System.out.println(", Phone Number: " + phone);
			}
			System.out.println();
			
			sql = "SELECT * FROM Enrollment";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String classID = rs.getString("Class_ID");
				String emailRS = rs.getString("Email");
				
				System.out.print("Class ID: " + classID);
				System.out.println(", Email: " + emailRS);
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
