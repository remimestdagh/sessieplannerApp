package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	//PARAMETERS
	private final static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("school");
	


	//METHODS
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	//SINGLETON CONSTRUCTOR
	private JPAUtil() {
	}
}
