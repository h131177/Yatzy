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
	private List<Integer> bonus;
	private List<Integer> totalScore;
	private Position position;
	private HashMap<String, Integer> players;
	private int counter;
	private int count = 0;
	
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
		info = new ArrayList<>();
		sum = new ArrayList<>();
		bonus = new ArrayList<>();
		totalScore = new ArrayList<>();
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
		//Skal berre kjøres første gangen
		if(count == 0) {
			for (int i = 0; i < game.getPlayers().size(); i++) {
				roundPoints.add(0);
				sum.add(0);
				bonus.add(0);
				totalScore.add(0);
			}
		}
		count++;
		
		request.getSession().setAttribute("points", game.getPoints());
		request.getSession().setAttribute("counter", counter);
		info = Helper.getInfo();
		request.getSession().setAttribute("info", info);
		request.getSession().setAttribute("row", position.getRow());
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
			roundPoints.set(position.getPlayer(),Helper.calculate(position.getRow() + 1, dice));
			game.addPoints(position.getRow(), roundPoints);
			if(position.getRow() <= 6) {
				sum.set(position.getPlayer(), sum.get(position.getPlayer()) + roundPoints.get(position.getPlayer()));
			}
			
			//Ved fleire spillere må ein også sjekke at alle spillere er ferdig med runden
			if(position.getPlayer() != (game.getPlayers().size() - 1)) {
				position.setPlayer(position.getPlayer() + 1);
			} else {
				position.setPlayer(0);
				//Øke med totalt 2, slik at ein hopper over å spille runder i sum og bonus raden
				if(position.getRow() == 5) {
					position.setRow(position.getRow() + 1);
					//Etter å ha oppdatert må du legge inn verdier fra sum raden i game
					game.addPoints(position.getRow(), sum);
					for(int i = 0; i < bonus.size(); i++) {
						if(sum.get(position.getPlayer()) >= 63) {
							bonus.set(position.getPlayer(), 50);
						}
						position.setPlayer(position.getPlayer() + 1);
					}
					position.setRow(position.getRow() + 1);
					game.addPoints(position.getRow(), bonus);
				}
				position.setRow(position.getRow() + 1);
				position.setPlayer(0);
			}
			
			for(int i = 0; i < totalScore.size(); i++) {
				totalScore.set(i, totalScore.get(i) + roundPoints.get(i) + bonus.get(i));
			}
			request.getSession().setAttribute("sum", sum);
			request.getSession().setAttribute("total", totalScore);
			request.getSession().setAttribute("game", game);
			request.getSession().setAttribute("points", game.getPoints());
		}
		for(int c = 0; c <= 4; c++) {
			hold.set(c, false);
		}
		
		response.sendRedirect("game");
	}

}
