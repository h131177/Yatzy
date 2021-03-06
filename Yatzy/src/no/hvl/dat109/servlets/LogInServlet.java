package no.hvl.dat109.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat109.model.Game;
import no.hvl.dat109.model.Player;
import no.hvl.dat109.model.PlayerDAO;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet(name = "LogInServlet", urlPatterns = { "/login" })
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<Player> users;
	private Player player;
	private Game game;
	private int count = 0;
	
	@EJB
	private PlayerDAO playerDAO = new PlayerDAO();
	
	@Override
	public void init() throws ServletException {
		users = new ArrayList<>();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Skal berre kjøres første gangen
		if(count == 0) {
			player = new Player("Nokia", "Nokia", "Kjetil");
			users.add(player);
			//Lagre listen over brukere for seinare bruk i andre servlets
			request.getSession().setAttribute("users", users);
		}
		count++;
		playerDAO.addPlayer(player);
		List<Player> liste = playerDAO.getPlayers();
		for(Player p : liste) {
			System.out.println(p.getUsername());
		}
		
		//Sletter eventuelle feilmeldinger fra register siden
		String referer = request.getHeader("Referer");
		if(referer.equals("http://localhost:8080/yatzy/register")) {
			request.getSession().setAttribute("message", "");
			request.getSession().setAttribute("error", "");
		}
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		player = findPlayer(username, password);
		
		if(player != null) {
			request.getSession().setAttribute("loggedIn", player);
			request.getSession().setAttribute("error", "");
			response.sendRedirect("lobby");
		} else {
			String errorMessage = "Invalid username or password";
			request.getSession().setAttribute("error", errorMessage);
			response.sendRedirect("login");
		}
	}
	
	public Player findPlayer(String user, String password) {
		for(Player p : users) {
			if(user.equals(p.getUsername())) {
				if(p.getPassword().equals(password)) {
					return p;
				}
			}
		}
		return null;
	}

}
