package no.hvl.dat109.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat109.model.Game;
import no.hvl.dat109.model.Player;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet(name = "LogInServlet", urlPatterns = { "/login" })
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Player user;
	Game game;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("loggedIn", user);
		game.addPlayer(user);
		response.sendRedirect("game");
	}

}
