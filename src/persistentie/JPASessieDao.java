package persistentie;

import java.util.List;

import javax.persistence.EntityManager;

import domein.Gebruiker;
import domein.Sessie;
import javafx.collections.ObservableList;

public class JPASessieDao extends JPADao{
	// PARAMETERS

	
	
	// CONSTRUCTOR
	public JPASessieDao() {
		super(Sessie.class);
	}
	
	
	// METHODS
	//Alle basis persistence methoden generiek in de parent gedefinieerd. Nu nog de Dao implementatie speciefieke:
	/*
	 * Vraagt een gebruiker op op email adres
	 */

	public ObservableList<Sessie> getSessiesFromVerantwoordelijke(String naamVerantwoordelijke) {
		
		return null;
	}

}
