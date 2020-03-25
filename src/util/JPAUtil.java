package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	//PARAMETERS
	private final static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("school");
	
	//CONSTRUCTOR
	private JPAUtil() {
	}
	
	//METHODS
	/**
	 * This method returns the factory
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}