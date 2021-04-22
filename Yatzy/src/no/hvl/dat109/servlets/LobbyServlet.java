package no.hvl.dat109.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LobbyServlet
 */
@WebServlet(name = "LobbyServlet", urlPatterns = { "/lobby" })
public class LobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void init(ServletConfig config) throws ServletException {
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
