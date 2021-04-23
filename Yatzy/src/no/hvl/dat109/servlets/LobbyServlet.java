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

	public void init(ServletConfig config) throws ServletException {
		games = new ArrayList<>();
		system = new YatzySystem(users, games);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Henter listen over brukere
		users = (List<Player>) request.getSession().getAttribute("users");
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
				break;
			case "start":
				
				break;
			case "join":
				String gameNr = request.getParameter("games");
				String joined = "You have joined " + gameNr + "! Please wait for the game to start";
				request.getSession().setAttribute("joined", joined);
				break;
			case "view":
				//Sjå på gamle spill som er ferdig
				break;
		}
		if(button.equals("start")) {
			response.sendRedirect("game");
		} else {
			response.sendRedirect("lobby");
		}
	}

}
