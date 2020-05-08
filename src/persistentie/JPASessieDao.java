package persistentie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import domein.Gebruiker;
import domein.Sessie;
import domein.SessieKalender;
import javafx.collections.ObservableList;

public class JPASessieDao extends JPADao implements SessieDao{
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
	}/*


	@Override
	public List<Sessie> findAll() {
		List<Sessie> list = null;
		list =  em.createQuery(String.format("SELECT s FROM Sessie s"), Sessie.class).getResultList();
		return list;
	}
	
	@Override
	public void delete(Object sessie) {
		em.remove(sessie);
	}

	@Override
	public void insert(Object sessie) { 
		em.persist(sessie);
	}*/

}
