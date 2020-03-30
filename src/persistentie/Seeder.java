package persistentie;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import domein.Aankondiging;
import domein.Feedback;
import domein.Gebruiker;
import domein.GebruikerStatus;
import domein.GebruikerType;
import domein.Herinnering;
import domein.Media;
import domein.Sessie;
import util.JPAUtil;

public class Seeder {
	//METHODS
	public static void seedDatabase()
	{
		////--Eerste Gebruikers--
		Gebruiker g1 = new Gebruiker("Maxim Van Cauwenberge", "862687mv", "maxim.vancauwenberge@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Verantwoordelijke);
		Gebruiker g2 = new Gebruiker("Alexander De Baene", "862656ad", "alexander.debaene@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Gewone_Gebruiker);
		Gebruiker g3 = new Gebruiker("Admin", "admin", "@", "p", GebruikerStatus.ACTIEF, GebruikerType.HoofdVerantwoordelijke);
		Gebruiker g4 = new Gebruiker("Jef Seys", "640431js", "jef.seys.y0431@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Gewone_Gebruiker);
		Gebruiker g5 = new Gebruiker("Remi Mestdagh", "757957rm", "remi.mestdagh@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Gewone_Gebruiker);
		Gebruiker g6 = new Gebruiker("Bert Suffys", "861835bs", "bert.suffys@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Gewone_Gebruiker);
		
		//--Eerste Sessies--
		Sessie s1 = new Sessie("Github for dummys", "Frederik Haesbrouk", "B3.31", 20, new Date(2020,2,13,12,30), new Date(2020,2,13,13,30), g1);
		Sessie s2 = new Sessie("Scrum is the truth", "Frederik Haesbrouk", "B2.17", 50, new Date(2020,2,15,12,30), new Date(2020,2,15,13,30), g1);
		Sessie s3 = new Sessie("Linux Sucks", "Alexander De Baene", "B3.33", 25, new Date(2020,3,13,14,30), new Date(2020,3,13,17,0), g2);
		
		//--Eerste Sessie--
		Herinnering h1 = new Herinnering("Het zal fijn worden!", 1);
		Herinnering h2 = new Herinnering("Wees op tijd!", 2);
		Media m1 = new Media("Playstation 3");
		Aankondiging a1 = new Aankondiging("Gedurende de eerste 30 minuten maken we groepjes!",g1,new Date(2020,2,13,12,30));
		Feedback f1 = new Feedback("Ruben Vandermeersch", "Heel boeiend!!");
		Feedback f2 = new Feedback("Elias Vervaecke", "Ik vond de spreker goed gearticuleerd!");
		


		//--Persistentie tools ophalen--
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory(); //Persistence unit naam is "school", zie de xml file.
		EntityManager em = emf.createEntityManager();
		
		
		// de te storen data effectief storen
		em.getTransaction().begin();
		
		Stream.of(s1).forEach(em::persist);
		s1.setHerinneringen(Arrays.asList(h1,h2)); //herinneringen toevoegen
		Stream.of(h1,h2).forEach(em::persist);
		s1.setMedia(Arrays.asList(m1));
		em.persist(m1);
		s1.setGeplaatsteAankondigingen(Arrays.asList(a1));
		em.persist(a1);
		s1.setGeplaatstFeedback(Arrays.asList(f1,f2));
		Stream.of(f1,f2).forEach(em::persist);
		
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
