package no.hvl.dat109.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat109.model.Game;
import no.hvl.dat109.model.Player;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name = "RegisterServlet", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<Player> users;
	private Player player;
	
	@Override
	public void init() throws ServletException {
		
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Henter listen over brukere
		users = (List<Player>) request.getSession().getAttribute("users");
		
		request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		//Ta imot parametre fra form skjema
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		if(username != "" && password != "" && name != "") {
			//Legge inn ny bruker i listen over brukere
			player = new Player(username, password, name);
			users.add(player);
			//Lagre listen over brukere for seinare bruk i andre servlets
			request.getSession().setAttribute("users", users);
			message = "User with username " + username + " registered! Please proceed to login.";
			request.getSession().setAttribute("message", message);
			request.getSession().setAttribute("error", "");
			
		} else {
			message = "You need to register a username, password and name, please try again!";
			request.getSession().setAttribute("message", "");
			request.getSession().setAttribute("error", message);
		}
		
		
		
		response.sendRedirect("register");
	}
}
