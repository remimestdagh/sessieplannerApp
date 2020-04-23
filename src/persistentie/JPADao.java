package persistentie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import domein.Gebruiker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.JPAUtil;

public class JPADao<T> implements Dao {
	// PARAMETERS
	protected static EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
	protected static EntityManager em = emf.createEntityManager();
	private final Class<T> type; // bijhouden welk implementatietype van de JPADao het is zodat we generiek de
									// methoden kunnen afwerken

	// CONSTRUCTOR
	public JPADao(Class<T> type) {
		this.type = type;
	}

	// METHODS
	@Override
	public ObservableList findAll() {
		return (ObservableList) em.createQuery("select entity from " + type.getName() + " entity", type)
				.getResultList();
	}

	
	@Override
	public Object get(Long id) {
		T entity = em.find(type, id);
		return entity;
	}

	
	
	@Override
	public Object update(Object object) {
		return em.merge(object);
	}

	@Override
	public void delete(Object object) {
		em.remove(em.merge(object));
	}

	@Override
	public void insert(Object object) {
		em.persist(object);
	}

	@Override
	public boolean exists(Long id) {
		T entity = em.find(type, id);
		return entity != null;
	}

	
	//onderstaande methoden worden aangeroepen samen met de effectieve persistentie methoden hierboven
	public static void closePersistency() {
		em.close();
		emf.close();
	}

	public static void startTransaction() {
		em.getTransaction().begin();
	}

	public static void commitTransaction() {
		em.getTransaction().commit();
	}

	public static void rollbackTransaction() {
		em.getTransaction().rollback();
	}

}
