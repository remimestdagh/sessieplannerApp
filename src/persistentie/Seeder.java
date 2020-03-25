package persistentie;

import java.util.Date;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import domein.Sessie;
import util.JPAUtil;

public class Seeder {

	
	//METHODS
	public static void seedDatabase()
	{

		//te storen data
		//Sessie s1 = new Sessie("Netacad discussion session", "Ryan Jaunzemis","Jens Joostssens",true,true, 100, true, "B.0.1");
		//Sessie s2 = new Sessie("Competitive Packet-tracing", "Ethan klein","Hubert Jeannine", true, false, 25, true, "B.0.1");
		
		// persistentie tools ophalen
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory(); //Persistence unit naam is "school", zie de xml file.
		EntityManager em = emf.createEntityManager();
		
		// de te storen data effectief storen
		em.getTransaction().begin();
		//Stream.of(s1,s2).forEach(em::persist);
		
		//de transactie verifyen
		em.getTransaction().commit();
		
		//de persistentie tools weer afsluiten
		em.close();
		emf.close();
		/*
		 * 
		 * 

		//Toe te voegen docenten
		Docent a = new Docent( "Jan", "Baard", new BigDecimal(8000));
		Docent b = new Docent( "Ros", "Beros", new BigDecimal(10000));
		Docent c = new Docent( "Joris", "ZonderBaard", new BigDecimal(12000));
		//op te vragen docent
		Docent docent = new Docent("Bert", "Suffys", BigDecimal.TEN);
		docent.opslag(BigDecimal.ONE);
		
		//factory maken en manager er uit halen
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory(); //Persistence unit naam is "school", zie de xml file.
		EntityManager em = emf.createEntityManager();
		
		//transactie starten
		em.getTransaction().begin();
		//em.persist(a);
		//em.persist(b);
		//em.persist(c);
		Stream.of(a,b,c).forEach(em::persist);
		
		//Nu gaan we iets zoeken
		Docent x = em.find(Docent.class, docent.getId()); //zoekt in de database naar de entiteit en of die bestaat op de key = id?
		if(x!=null) // ie zit er al in.
		{ 
			x.opslag(new BigDecimal(200)); // we voeren een update door.
		} 
		else // ie zat er nog niet in.
		{
		System.out.println("Docent 2 niet gevonden");
		}
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		*/
		
		
	}
	
}
