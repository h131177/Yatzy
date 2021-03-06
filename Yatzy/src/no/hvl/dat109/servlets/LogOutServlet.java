package no.hvl.dat109.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogOutServlet
 */
@WebServlet(name = "LogOutServlet", urlPatterns = { "/logout" })
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO Nullstille 
		request.getSession().setAttribute("loggedIn", null);
		request.getSession().setAttribute("error", "");
		request.getSession().setAttribute("message", "");
		request.getRequestDispatcher("WEB-INF/jsp/logout.jsp").forward(request, response);
	}

}
