package no.hvl.dat109.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat109.model.Dice;
import no.hvl.dat109.model.Position;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet(name = "GameServlet", urlPatterns = { "/game" })
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//INIT
	private List<Dice> dice;
	private List<Boolean> hold;
	private List<Integer> tall; 
	private Position position;
	private int counter;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/game.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Roll
		// Ta inn input fra checkboxes -> Liste med Boolean
		//trille terninger
		//counter++
		
		//Done
		//counter == 0
		//Regne ut poengsum ved hjelp av helper metode
		
	}

}
