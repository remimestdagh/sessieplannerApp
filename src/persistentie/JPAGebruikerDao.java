package persistentie;

import java.util.Arrays;
import java.util.List;
import javax.persistence.TypedQuery;
import domein.Gebruiker;
import domein.Sessie;
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
		
		TypedQuery<Sessie> querySessiesWaarvoorAanwezig = em.createQuery(String.format("SELECT s FROM Sessie s join GebruikerSessie gs on s.sessieId = gs.SessieId WHERE gs.GebruikerId = %d and gs.GebruikerWasAanwezig = 1", gebruiker.getGebruikerId()), Sessie.class);
		List<Sessie> lijstAanwezig = querySessiesWaarvoorAanwezig.getResultList();
		gebruiker.setSessiesWaarvoorAanwezig(lijstAanwezig);
		
		TypedQuery<Sessie> querySessiesWaarvoorIngeschreven = em.createQuery(String.format("SELECT s FROM Sessie s join GebruikerSessie gs on s.sessieId = gs.SessieId WHERE gs.GebruikerId = %d and gs.GebruikerWasAanwezig = 0", gebruiker.getGebruikerId()), Sessie.class);
		List<Sessie> lijstIngeschreven = querySessiesWaarvoorIngeschreven.getResultList();
		lijstIngeschreven.addAll(lijstAanwezig);
		gebruiker.setSessiesWaarvoorIngeschreven(lijstIngeschreven);
		
		return gebruiker;
	}


	@Override
	public List<Gebruiker> findAll() {
		List<Gebruiker> list = null;
		list =  em.createQuery(String.format("SELECT g FROM Gebruiker g"), Gebruiker.class).getResultList();
		for(int i = 0; i< list.size(); i++) {
			TypedQuery<Sessie> querySessiesWaarvoorAanwezig = em.createQuery(String.format("SELECT s FROM Sessie s join GebruikerSessie gs on s.sessieId = gs.SessieId WHERE gs.GebruikerId = %d and gs.GebruikerWasAanwezig = 1", list.get(i).getGebruikerId()), Sessie.class);
			List<Sessie> lijstAanwezig = querySessiesWaarvoorAanwezig.getResultList();
			list.get(i).setSessiesWaarvoorAanwezig(lijstAanwezig);
			TypedQuery<Sessie> querySessiesWaarvoorIngeschreven = em.createQuery(String.format("SELECT s FROM Sessie s join GebruikerSessie gs on s.sessieId = gs.SessieId WHERE gs.GebruikerId = %d and gs.GebruikerWasAanwezig = 0", list.get(i).getGebruikerId()), Sessie.class);
			List<Sessie> lijstIngeschreven = querySessiesWaarvoorIngeschreven.getResultList();
			lijstIngeschreven.addAll(lijstAanwezig);
			list.get(i).setSessiesWaarvoorIngeschreven(lijstIngeschreven);
		}
		
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
		Gebruiker g = (Gebruiker)gebruiker;
		startTransaction();
		em.createNativeQuery(String.format("delete from gebruikerwachtwoord where gebruiker_gebruikerid = %d", g.getGebruikerId())).executeUpdate();
		em.createNativeQuery(String.format("delete from GebruikerSessie where gebruikerid = %d", g.getGebruikerId())).executeUpdate();
		em.remove(gebruiker);
		commitTransaction();
	}/*

	@Override
	public void insert(Object gebruiker) { 
		startTransaction();
		em.persist(gebruiker);
		commitTransaction();
	}*/

}
