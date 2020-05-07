package persistentie;

import javax.persistence.TypedQuery;
import domein.Gebruiker;

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
	}

}
