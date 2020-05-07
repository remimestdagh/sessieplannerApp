package persistentie;

import java.util.List;

import domein.Gebruiker;
import domein.SessieKalender;
import javafx.collections.ObservableList;

public class JPASessieKalenderDao extends JPADao implements SessieKalenderDao{

	// PARAMETERS

	// CONSTRUCTOR
	public JPASessieKalenderDao() {
		super(SessieKalender.class);
	}

	// METHODS
	// Alle basis persistence methoden generiek in de parent gedefinieerd. Nu nog de
	// Dao implementatie speciefieke:
	/*
	 * Vraagt de sessiekalender die matcht met huidige datum
	 */

	public SessieKalender getHuidigeSessieKalender() {
		return null;
	}/*


	@Override
	public List<SessieKalender> findAll() {
		List<SessieKalender> list = null;
		list =  em.createQuery(String.format("SELECT s FROM SessieKalender s"), SessieKalender.class).getResultList();
		return list;
	}
	
	@Override
	public void delete(Object sessieKalender) {
		em.remove(sessieKalender);
	}

	@Override
	public void insert(Object sessieKalender) { 
		em.persist(sessieKalender);
	}*/
}
