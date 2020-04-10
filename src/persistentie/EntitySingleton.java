package persistentie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import util.JPAUtil;

public class EntitySingleton {
	// PARAMETERS
	public static EntitySingleton entitySingleTon = new EntitySingleton();
	private EntityManagerFactory emf;
	private EntityManager em;

	// CONSTRUCTOR
	private EntitySingleton() {
		this.emf = JPAUtil.getEntityManagerFactory(); // "Eager Loaded, gezien continue nodig, en expensive"
		this.em = emf.createEntityManager();
	}

	// METHODS
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

	// GETTERS AND SETTERS
	public EntityManager getEntityManager()
	{
		return this.em;
	}
	public EntitySingleton getInstance()
	{
		return this;
	}
}
