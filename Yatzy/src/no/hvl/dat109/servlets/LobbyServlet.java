package no.hvl.dat109.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat109.model.Game;
import no.hvl.dat109.model.Player;
import no.hvl.dat109.model.YatzySystem;

/**
 * Servlet implementation class LobbyServlet
 */
@WebServlet(name = "LobbyServlet", urlPatterns = { "/lobby" })
public class LobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<Player> users;
	private List<Game> games;
	private YatzySystem system;
	private Game game;
	private Player player;
	List<Player> players;
	List<List<Integer>> points;

	public void init(ServletConfig config) throws ServletException {
		games = new ArrayList<>();
		system = new YatzySystem(users, games);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Henter listen over brukere
		users = (List<Player>) request.getSession().getAttribute("users");
		player = (Player) request.getSession().getAttribute("loggedIn");
		request.getSession().setAttribute("games", games);
		request.getRequestDispatcher("WEB-INF/jsp/lobby.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Henter verdi for å vite hvilken knapp som blei trykka
		String button = request.getParameter("hidden");
		switch (button) {
			case "create":
				String create = "You have created a new game! Please wait for people to join.";
				request.getSession().setAttribute("create", create);
				players = new ArrayList<>();
				players.add(player);
				points = new ArrayList<>();
				game = new Game(1,users, points, player);
				games.add(game);
				request.getSession().setAttribute("game", game);
				break;
			case "start":
				for(int i = 0; i < 18; i++) {
					List<Integer> l = new ArrayList<>();
					for(int j = 0; j < game.getPlayers().size(); j++) {
						l.add(0);
					}
					points.add(l);
				}
				game.setStarted(true);
				request.getSession().setAttribute("game", game);
				break;
			case "join":
				String gameNr = request.getParameter("games");
				String joined = "You have joined " + gameNr + "! Please wait for the game to start";
				request.getSession().setAttribute("joined", joined);
				game.addPlayer(player);
				request.getSession().setAttribute("game", game);
				break;
			case "view":
				//TODO fikse lobby.jsp slik at det blir returnert id
				int viewGame = Integer.parseInt(request.getParameter("oldgames"));
				for (Game g : games) {
					if(g.getId() == viewGame) {
						request.getSession().setAttribute("game", g);
						break;
					}
				}
				//TODO fikse game.jsp slik at knapper ikkje vises om spillet er ferdig
				break;
		}
		if(button.equals("start") || button.equals("view")) {
			response.sendRedirect("game");
		} else {
			response.sendRedirect("lobby");
		}
	}

}
