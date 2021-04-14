package no.hvl.dat109.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat109.helper.Helper;
import no.hvl.dat109.model.Dice;
import no.hvl.dat109.model.Game;
import no.hvl.dat109.model.Player;
import no.hvl.dat109.model.Position;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet(name = "GameServlet", urlPatterns = { "/game" })
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Game game;
	//private List<List<Integer>> points;
	private List<Dice> dice;
	private List<Boolean> hold;
	private List<Integer> roundPoints;
	private List<String> info;
	private List<Integer> sum;
	private Position position;
	private HashMap<String, Integer> players;
	private int counter;
	
	@Override
	public void init() throws ServletException {
		dice = new ArrayList<>(5);
		Dice d1 = new Dice();
		Dice d2 = new Dice();
		Dice d3 = new Dice();
		Dice d4 = new Dice();
		Dice d5 = new Dice();
		dice.add(d1);
		dice.add(d2);
		dice.add(d3);
		dice.add(d4);
		dice.add(d5);
		hold = new ArrayList<>();
		hold.add(false);
		hold.add(false);
		hold.add(false);
		hold.add(false);
		hold.add(false);
		roundPoints = new ArrayList<>();
		roundPoints.add(0);
		info = new ArrayList<>();
		sum = new ArrayList<>();
		sum.add(0);
		position = new Position(0, 0);
		counter = 0;
		players = new HashMap<String, Integer>();
		
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(counter == 0) {
			for (Dice d : dice) {
				d.setValue(0);
			}
			request.getSession().setAttribute("numbers", dice);
		}
		
		Player p = (Player) request.getSession().getAttribute("loggedIn");
		players.put(p.getName(), position.getPlayer());
		game = (Game) request.getSession().getAttribute("game");
		request.getSession().setAttribute("points", game.getPoints());
		request.getSession().setAttribute("counter", counter);
		info = Helper.getInfo();
		request.getSession().setAttribute("info", info);
		request.getRequestDispatcher("WEB-INF/jsp/game.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Hidden parameter slik at det blir mulig å sjekke om det er roll eller done knappen som trykkes
		String hidden = request.getParameter("roll");
		for(int c = 0; c <= 4; c++) {
			String b = request.getParameter("check" + c);
			if(b != null) {
				hold.set(c, true);
			}
		}
		if(hidden != null) {
			//Roll knappen
			// Ta inn input fra checkboxes -> Liste med Boolean
			//trille terninger
			int i = 0;
			for (Dice d : dice) {
				//if unchecked
				if(hold.get(i) != true) {
					d.RollDice();
				}
				i++;
			}
			request.getSession().setAttribute("numbers", dice);
			counter++;
		} else {
			//Done knappen
			counter = 0;
			//Regne ut poengsum ved hjelp av helper metode
			roundPoints.set(0,Helper.calculatePoints(position.getRow() + 1, dice));
			game.addPoints(position.getRow(), roundPoints);
			sum.set(0, sum.get(0) + roundPoints.get(0));
			request.getSession().setAttribute("sum", sum);
			request.getSession().setAttribute("game", game);
			request.getSession().setAttribute("points", game.getPoints());
			//Ved fleire spillere må ein også sjekke at alle spillere er ferdig med runden
			position.setRow(position.getRow() + 1);
		}
		for(int c = 0; c <= 4; c++) {
			hold.set(c, false);
		}
		
		response.sendRedirect("game");
	}

}
