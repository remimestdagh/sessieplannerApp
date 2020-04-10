package persistentie;

import java.util.List;

import domein.Gebruiker;
import domein.SessieKalender;
import javafx.collections.ObservableList;

public class JPASessieKalenderDao extends JPADao{

	// PARAMETERS

	// CONSTRUCTOR
	public JPASessieKalenderDao() {
		super(Gebruiker.class);
	}

	// METHODS
	// Alle basis persistence methoden generiek in de parent gedefinieerd. Nu nog de
	// Dao implementatie speciefieke:
	/*
	 * Vraagt de sessiekalender die matcht met huidige datum
	 */

	public SessieKalender getHuidigeSessieKalender() {
		return null;
	}


}
