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
	private List<Player> players;
	private List<List<Integer>> points;
	private List<Game> joinGames;
	private List<Game> viewGames;

	public void init(ServletConfig config) throws ServletException {
		games = new ArrayList<>();
		system = new YatzySystem(users, games);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Henter listen over brukere
		users = (List<Player>) request.getSession().getAttribute("users");
		player = (Player) request.getSession().getAttribute("loggedIn");
		
		//Algoritme for hente ferdigspilt spill:
		//Hente inn game fra session
		game = (Game) request.getSession().getAttribute("game");
		if(game != null) {
			//Sjekke om game i session er ferdig spilt
			System.out.println("game sin id: " + game.getId());
			if(game.isFinished()) {
				//Finne posisjonen til game i games
				for(int i = 0; i < games.size(); i++) {
					if(game.getId() == games.get(i).getId()) {
						//Erstatte game i games med game fra session
						games.set(i, game);
						System.out.println("SPILL FERDIG!");
						request.getSession().setAttribute("game", null);
						game = null;
						break;
					}
				}
			}
		}
		// Lager lister til dropdown:
		// join (Liste med alle games som ikkje er starta)
		// view (Liste med alle games som brukeren har spilt ferdig)
		if(games != null) {
			joinGames = new ArrayList<>();
			viewGames = new ArrayList<>();
			for(Game g : games) {
				if(!g.isStarted()) {
					joinGames.add(g);
				} else if(g.getPlayers().contains(player) && g.isFinished()) {
					viewGames.add(g);
				}
			}
			request.getSession().setAttribute("joinGames", joinGames);
			request.getSession().setAttribute("viewGames", viewGames);
		}
		
		request.getSession().setAttribute("games", games);
		request.getRequestDispatcher("WEB-INF/jsp/lobby.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Henter verdi for å vite hvilken knapp som blei trykka
		String button = request.getParameter("hidden");
		switch (button) {
			case "create":
				String create = "";
				if(game == null) {
					create = "You have created a new game! Please wait for people to join.";
					players = new ArrayList<>();
					players.add(player);
					points = new ArrayList<>();
					game = new Game(games.size() + 1,users, points, player);
					games.add(game);
					request.getSession().setAttribute("game", game);
					request.getSession().setAttribute("message", create);
					request.getSession().setAttribute("error", "");
				} else {
					create = "You can't create a game when you already are in a game";
					request.getSession().setAttribute("error", create);
					request.getSession().setAttribute("message", "");
				}
				
				break;
			case "start":
				if(game != null && !game.isStarted()) {
					for(int i = 0; i < 18; i++) {
						List<Integer> l = new ArrayList<>();
						for(int j = 0; j < game.getPlayers().size(); j++) {
							l.add(0);
						}
						points.add(l);
					}
					game.setStarted(true);
					request.getSession().setAttribute("game", game);
				} else {
					String start = "You need to create a new game first!";
					request.getSession().setAttribute("error", start);
					request.getSession().setAttribute("message", "");
				}
				
				break;
			case "join":
				String gameNr = request.getParameter("games");
				String joined;
				if(joinGames.size() != 0) {
					joined = "You have joined " + gameNr + "! Please wait for the game to start";
					game.addPlayer(player);
					request.getSession().setAttribute("game", game);
					request.getSession().setAttribute("message", joined);
					request.getSession().setAttribute("error", "");
				} else {
					joined = "No game to join yet.";
					request.getSession().setAttribute("error", joined);
					request.getSession().setAttribute("message", "");
				}
				
				break;
			case "view":
				if(viewGames.size() != 0) {
					int viewGame = Integer.parseInt(request.getParameter("oldgames"));
					for (Game g : games) {
						if(g.getId() == viewGame) {
							request.getSession().setAttribute("game", g);
							break;
						}
					}
				}
				
				break;
		}
		if(button.equals("start") && game != null && game.isStarted() || button.equals("view") && viewGames.size() != 0) {
			request.getSession().setAttribute("error", "");
			request.getSession().setAttribute("message", "");
			response.sendRedirect("game");
		} else {
			response.sendRedirect("lobby");
		}
	}

}
