package persistentie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	}
	
	public Sessie getSessieById(int id) {
		
		Sessie s = null;
		TypedQuery<Sessie> query = em
				.createQuery(String.format("SELECT s FROM Sessie AS s WHERE s.sessieId = %d", id), Sessie.class);
		
		s = query.getSingleResult();
		
		TypedQuery<Gebruiker> querySessiesWaarvoorAanwezig = em.createQuery(String.format("SELECT g FROM Gebruiker g join GebruikerSessie gs on g.gebruikerId = gs.GebruikerId WHERE gs.SessieId = %d and gs.GebruikerWasAanwezig = 1", id), Gebruiker.class);
		List<Gebruiker> lijstAanwezig = querySessiesWaarvoorAanwezig.getResultList();
		s.setAanwezigeGebruikers(lijstAanwezig);
		TypedQuery<Gebruiker> querySessiesWaarvoorIngeschreven = em.createQuery(String.format("SELECT g FROM Gebruiker g join GebruikerSessie gs on g.gebruikerId = gs.GebruikerId WHERE gs.SessieId = %d and gs.GebruikerWasAanwezig = 0", id), Gebruiker.class);
		List<Gebruiker> lijstIngeschreven = querySessiesWaarvoorIngeschreven.getResultList();
		if(lijstAanwezig != null) {
			lijstIngeschreven.addAll(lijstAanwezig);
		}
		s.setIngeschrevenGebruikers(lijstIngeschreven);
		s.initialiseerSessieStatus();
		
		return s;
	}


	@Override
	public List<Sessie> findAll() {
		List<Sessie> list = null;
		list =  em.createQuery(String.format("SELECT s FROM Sessie s"), Sessie.class).getResultList();
		for(int i = 0; i< list.size(); i++) {
			TypedQuery<Gebruiker> querySessiesWaarvoorAanwezig = em.createQuery(String.format("SELECT g FROM Gebruiker g join GebruikerSessie gs on g.gebruikerId = gs.GebruikerId WHERE gs.SessieId = %d and gs.GebruikerWasAanwezig = 1", list.get(i).getSessieId()), Gebruiker.class);
			List<Gebruiker> lijstAanwezig = querySessiesWaarvoorAanwezig.getResultList();
			list.get(i).setAanwezigeGebruikers(lijstAanwezig);
			TypedQuery<Gebruiker> querySessiesWaarvoorIngeschreven = em.createQuery(String.format("SELECT g FROM Gebruiker g join GebruikerSessie gs on g.gebruikerId = gs.GebruikerId WHERE gs.SessieId = %d and gs.GebruikerWasAanwezig = 0", list.get(i).getSessieId()), Gebruiker.class);
			List<Gebruiker> lijstIngeschreven = querySessiesWaarvoorIngeschreven.getResultList();
			if(lijstAanwezig != null) {
				lijstIngeschreven.addAll(lijstAanwezig);
			}
			list.get(i).setIngeschrevenGebruikers(lijstIngeschreven);
			list.get(i).initialiseerSessieStatus();
		}
		return list;
	}
	
	@Override
	public void delete(Object sessie) {
		Sessie s = (Sessie)sessie;
		startTransaction();
		em.remove(sessie);
		em.createNativeQuery(String.format("delete from GebruikerSessie where sessieId = %d", s.getSessieId())).executeUpdate();
		commitTransaction();
	}/*

	@Override
	public void insert(Object sessie) { 
		em.persist(sessie);
	}*/

}
