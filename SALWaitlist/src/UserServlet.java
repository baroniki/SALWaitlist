

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	String connString = "";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String classID = request.getParameter("classID");
		CP notCP = new CP();
		notCP.isCP = false;
		UserClient a = new UserClient("localhost", 6789, email, classID, notCP);
		while (true) {
			//waiting
			//String q = "a";
			System.out.print(a.getPos());
			if (a.getPos().equals("Ready")) {
				Notification message = new Notification("+18056464945");
				//System.out.println("Made it");
				break;
			}
			if (a.getPos().equals("Done")) {
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Made it for user: "+email);
	}
	
	public String getPhoneNumber(String e) {
		String number = null;
		Connection conn = null;
    	Statement st = null;
    	ResultSet rs = null;
    	try {
    		conn = DriverManager.getConnection(connString);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM ");
			while (rs.next()) {
				number = rs.getString("");
			}
    	}
    	catch (SQLException q) {
    		
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
    		} catch (SQLException q) {
    			
    		}
    		
    	}
    	return number;
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
