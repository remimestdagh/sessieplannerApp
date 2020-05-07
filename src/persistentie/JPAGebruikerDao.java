package persistentie;

import java.util.List;

import javax.persistence.TypedQuery;
import domein.Gebruiker;
import domein.SessieKalender;

public class JPAGebruikerDao extends JPADao implements GebruikerDao{

	// PARAMETERS

	// CONSTRUCTOR
	public JPAGebruikerDao() {
		super(Gebruiker.class);
	}

	// METHODS
	// Alle basis persistence methoden generiek in de parent gedefinieerd. Nu nog de
	// Dao implementatie speciefieke:
	/*
	 * Vraagt een gebruiker op op email adres
	 */
	
	public Gebruiker getGebruikerByEmail(String emailadres) {
		
		Gebruiker gebruiker = null;
		TypedQuery<Gebruiker> query = em
				.createQuery(String.format("SELECT g FROM Gebruiker AS g WHERE g.emailadres = '%s'", emailadres), Gebruiker.class);
		gebruiker = query.getSingleResult();
		
		return gebruiker;
	}/*


	@Override
	public List<Gebruiker> findAll() {
		List<Gebruiker> list = null;
		list =  em.createQuery(String.format("SELECT g FROM Gebruiker g"), Gebruiker.class).getResultList();
		return list;
	}

	@Override
	public Object update(Object gebruiker) {
		startTransaction();
		Object b = em.merge(gebruiker);
		commitTransaction();
		return b;
	}
	
	@Override
	public void delete(Object gebruiker) {
		startTransaction();
		em.remove(gebruiker);
		commitTransaction();
	}

	@Override
	public void insert(Object gebruiker) { 
		startTransaction();
		em.persist(gebruiker);
		commitTransaction();
	}*/

}
