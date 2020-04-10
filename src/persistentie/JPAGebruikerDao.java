package persistentie;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import domein.Gebruiker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.JPAUtil;

public class JPAGebruikerDao implements GebruikerDao {

	// PARAMETERS
	EntityManagerFactory emf;
	EntityManager em;

	// CONSTRUCTOR
	public JPAGebruikerDao() {
		emf = JPAUtil.getEntityManagerFactory();
		em = emf.createEntityManager();
	}

	// METHODS
	/*
	 * Haalt alle gebruikers op
	 */

	@Override
	public ObservableList<Gebruiker> findAll() {
		ObservableList<Gebruiker> alleGebruikers = FXCollections.<Gebruiker>observableArrayList();
		TypedQuery<Gebruiker> query = em.createQuery("SELECT * FROM gebruikers", Gebruiker.class);
		alleGebruikers = (ObservableList<Gebruiker>) query.getResultList();
		return alleGebruikers;
	}

	/*
	 * Haalt 1 gebruiker op op id
	 */
	@Override
	public Gebruiker get(Long id) {
		Gebruiker gebruiker = em.find(Gebruiker.class, id);
		return gebruiker;
	}

	/*
	 * Updatet 1 doorgekregen object in de databank.
	 */
	@Override
	public Gebruiker update(Gebruiker object) {
		return em.merge(object);

	}

	/*
	 * deletet 1 doorgekregen gebruiker
	 */
	@Override
	public void delete(Gebruiker object) {
		em.remove(em.merge(object));

	}

	/*
	 * Persisteert een nieuwe binnengekregen object
	 */
	@Override
	public void insert(Gebruiker object) {
		em.persist(object);

	}

	/*
	 * vraagt aan de databank of een gebruiker op een bepaalde id bestaat.
	 */
	@Override
	public boolean exists(Long id) {
		Gebruiker entity = em.find(Gebruiker.class, id);
		return entity != null;
	}

	/*
	 * Vraagt een gebruiker op op email adres
	 */
	@Override
	public Gebruiker getGebruikerByEmail(String email) {
		Gebruiker gebruiker = null;
		TypedQuery<Gebruiker> query = em
				.createQuery(String.format("SELECT * FROM gebruikers WHERE emailadres = '%s'", email), Gebruiker.class);
		gebruiker = query.getSingleResult();
		return gebruiker;
	}

	public void closePersistency() {
		em.close();
		emf.close();
	}

	public void startTransaction() {
		em.getTransaction().begin();
	}

	public void commitTransaction() {
		em.getTransaction().commit();
	}

	public void rollbackTransaction() {
		em.getTransaction().rollback();
	}

}
