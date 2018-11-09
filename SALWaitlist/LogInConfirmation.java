

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
 * Servlet implementation class LogInConfirmation
 */
@WebServlet("/LogInConfirmation")
public class LogInConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInConfirmation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String profPic = request.getParameter("profpic");
		if (isCP(email)) {
			out.println("cp");
		}
		Students st = getUserInfo(email);
		else if (st != null) {
			//user is in database
			//redirect to normal page
			out.println("student");
		}
		else {
			//users not in database
			//redirect to sign up page to get phone #
			addUser(email, name, profpic, "NONE");
			out.println("new");
		}
	}

	boolean isCP(String email) {
		java.sql.Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=brighton1101&useSSL=false");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM CP WHERE Email='"+email+"';");
			if (!rs.isBeforeFirst() ) {    
			    return false;
			}
			else {
				return true;
			}

		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
	
	void addUser(String email, String name, String profpic, String number) {
		java.sql.Connection conn = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=brighton1101&useSSL=false");
			st = conn.createStatement();
			//BELOW LINE IS ASSUMING WE CHANGED DB TO HAVE ONLY ONE NAME COLUMN
			st.executeUpdate("INSERT INTO Students VALUES('"+email+"', '"+name+"', '"+profpic+"', '"+number+"') ;");
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
	
	Students getUserInfo(String email) {
		Students s = new Students();
		java.sql.Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=brighton1101&useSSL=false");
			st = conn.createStatement();
			st.executeUpdate("USE finalDB"); //ACCESS DATABASE HERE
			
			rs = st.executeQuery("SELECT * FROM Students WHERE Email='" + email + "';");
			if (!rs.isBeforeFirst() ) {    
			    return null;
			}
			while(rs.next()) {
				s.Email = email;
				s.Fname = rs.getString("Fname");
				s.Lname = rs.getString("Lname");
				s.Phone_Number = rs.getString("Phone_Number");
				s.Profile_Picture_URL = rs.getString("Profile_Picture_URL");
				
			}
		}
		catch (SQLException e) {
			System.out.println("Problem with accessing database in LogInConfirmation");
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
		return s;
	}

}


