

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserSession
 */
@WebServlet("/UserSession")
public class UserSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("get")) {
			response.getWriter().print(request.getSession().getAttribute("email"));
		} else if(action.equals("set")) {
			String email = request.getParameter("email");
			request.getSession().setAttribute("email", email);
			System.out.println("set email: " + email);
		}
		
		response.getWriter().close();
	}
}
