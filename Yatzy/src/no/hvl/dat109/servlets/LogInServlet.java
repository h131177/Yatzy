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
 * Servlet implementation class LogInServlet
 */
@WebServlet(name = "LogInServlet", urlPatterns = { "/login" })
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<Player> users;
	private List<Game> games;
	private Player player;
	private Game game;
	private int count = 0;
	
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
		
		List<Player> players = new ArrayList<>();
		List<List<Integer>> points = new ArrayList<>();
		for(int i = 0; i < 18; i++) {
			List<Integer> l = new ArrayList<>();
			l.add(0);
			l.add(0);
			points.add(l);
		}
		game = new Game(players, points);
		game.addPlayer(player);
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		player = findPlayer(username);
		//Sjekker om ein logger inn med "admin" brukeren eller ein ny registrert bruker
		//Siden "admin" brukeren allerede er lagt til i game, skal den ikkje legges til på nytt
		if(!player.getUsername().equals("Nokia")) {
			game.addPlayer(player);
		}
		
		request.getSession().setAttribute("loggedIn", player);
		request.getSession().setAttribute("game", game);
		response.sendRedirect("game");
	}
	
	public Player findPlayer(String user) {
		for(Player p : users) {
			if(user.equals(p.getUsername())) {
				return p;
			}
		}
		return null;
	}

}
