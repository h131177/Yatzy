package no.hvl.dat109.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class PlayerDAO {

	@PersistenceContext(name = "playerPU")
	private EntityManager em;

	// Adds a player to the DB
	public void addPlayer(Player p) {
		em.persist(p);
	}

	// Removes a player from the DB
	public void deletePlayer(Player p) {
		try {
			em.find(Player.class, p);
		} catch (NoResultException e) {
			System.out.println("No such player in database.");
		}
		em.remove(p);
	}

	// Returns a list of all the players registered in the DB
	public List<Player> getPlayers() {
		TypedQuery<Player> query = em.createQuery("SELECT p FROM Player as p ORDER BY p.name", Player.class);
		return query.getResultList();
	}
}
