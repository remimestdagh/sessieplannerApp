package persistentie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import domein.Gebruiker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.JPAUtil;

public class JPADao<T> implements Dao {
	// PARAMETERS
	protected static EntityManagerFactory emf;
	protected static EntityManager em;
	private final Class<T> type; // bijhouden welk implementatietype van de JPADao het is zodat we generiek de
									// methoden kunnen afwerken

	// CONSTRUCTOR
	public JPADao(Class<T> type) {
		this.type = type;
		openPersistentie();
	}

	// METHODS
	@Override
	public List<T> findAll() {
		List<T> list = null;
		list =  em.createQuery("select entity from " + type.getName() + " entity", type)
				.getResultList();
		return list;
	}

	
	@Override
	public Object get(Long id) {
		T entity = em.find(type, id);
		return entity;
	}

	
	
	@Override
	public Object update(Object object) {
		startTransaction();
		Object b = em.merge(object);
		commitTransaction();
		return b;
	}

	@Override
	public void delete(Object object) {
		startTransaction();
		em.remove(em.merge(object));
		commitTransaction();
	}

	@Override
	public void insert(Object object) {
		startTransaction();
		em.persist(object);
		commitTransaction();
	}

	@Override
	public boolean exists(Long id) {
		T entity = em.find(type, id);
		return entity != null;
	}

	
	//onderstaande methoden worden aangeroepen samen met de effectieve persistentie methoden hierboven
	private void openPersistentie() {
        emf = JPAUtil.getEntityManagerFactory();
        em = emf.createEntityManager();
    }
	
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
