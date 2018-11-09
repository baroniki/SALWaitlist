import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String number = request.getParameter("number");
		String classesString = request.getParameter("classes");
		String[] classes = classesString.split(" ");
		updateProfile(email, number, classes);
	}
	public void updateProfile(String email, String number, String[] classes) {
		java.sql.Connection conn = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=brighton1101&useSSL=false");
			st = conn.createStatement();
			st.executeUpdate("USE finalDB"); //ACCESS DATABASE HERE
			st.executeUpdate("UPDATE Students SET Phone_Number='"+number+"' WHERE Email='"+email+"';");//insert phone number
			for (int i = 0; i < classes.length; i++) {
				st.executeUpdate("INSERT INTO Classes VALUES('"+classes[i]+"', '"+email+"');");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
