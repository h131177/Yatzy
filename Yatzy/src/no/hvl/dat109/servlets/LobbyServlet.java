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
		
		//Algoritme for hente ferdigspilt spill:
		//Hente inn game fra session
		game = (Game) request.getSession().getAttribute("game");
		//Sjekke om game i session er ferdig spilt
		System.out.println("game sin id: " + game.getId());
		if(game.isFinished()) {
			//games = (List<Game>) request.getSession().getAttribute("games");
			games.add(game);
			//Finne posisjonen til game i games
			for(int i = 0; i < games.size(); i++) {
				if(game.getId() == games.get(i).getId()) {
					//Erstatte game i games med game fra session
					games.set(i, game);
					System.out.println("SPILL FERDIG!");
				}
			}
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
				if(game != null && !game.isStarted()) {
					create = "You have created a new game! Please wait for people to join.";
					
					players = new ArrayList<>();
					players.add(player);
					points = new ArrayList<>();
					game = new Game(games.size() + 1,users, points, player);
					games.add(game);
					request.getSession().setAttribute("game", game);
				} else {
					create = "You can't create a game when you already are in a game";
				}
				request.getSession().setAttribute("create", create);
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
					request.getSession().setAttribute("start", start);
				}
				
				break;
			case "join":
				String gameNr = request.getParameter("games");
				String joined = "You have joined " + gameNr + "! Please wait for the game to start";
				request.getSession().setAttribute("joined", joined);
				game.addPlayer(player);
				request.getSession().setAttribute("game", game);
				break;
			case "view":
				int viewGame = Integer.parseInt(request.getParameter("oldgames"));
				for (Game g : games) {
					if(g.getId() == viewGame) {
						request.getSession().setAttribute("game", g);
						break;
					}
				}
				break;
		}
		if(button.equals("start") && game != null && !game.isStarted() || button.equals("view")) {
			response.sendRedirect("game");
		} else {
			response.sendRedirect("lobby");
		}
	}

}
