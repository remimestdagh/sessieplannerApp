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
		
		//--Sessies--
		Sessie s1 = new Sessie("Github for dummys", "Frederik Haesbrouk", "B3.31", 20, new Date(2020,2,13,12,30), new Date(2020,2,13,13,30), g1.getNaam());
		Sessie s2 = new Sessie("Scrum is the truth", "Frederik Haesbrouk", "B2.17", 50, new Date(2020,2,15,12,30), new Date(2020,2,15,13,30), g6.getNaam());
		Sessie s3 = new Sessie("Linux Sucks", "Alexander De Baene", "B3.33", 25, new Date(2020,3,13,14,30), new Date(2020,3,13,17,0), g2.getNaam());
		Sessie s4 = new Sessie("Linux Is Just Amazing!", "Bert Suffys", "B0.00", 25, new Date(2020,3,13,14,30), new Date(2020,3,13,17,0), g3.getNaam());
		Sessie s5 = new Sessie("Linux Is pretty average", "Alexander De Baene", "B3.33", 25, new Date(2020,3,13,14,30), new Date(2020,3,13,17,0), g4.getNaam());
		Sessie s6 = new Sessie("The saddening history of Ubuntu", "Hugh Mungus", "B6.66", 25, new Date(2020,3,13,14,30), new Date(2020,3,13,17,0), g5.getNaam());
		
		//--Sessie relaties--
		Herinnering h1 = new Herinnering("Het zal fijn worden!", 1);
		Herinnering h2 = new Herinnering("Wees op tijd!", 2);
		Herinnering h3 = new Herinnering("Deze sessie is verplicht!", 2);
		Media m1 = new Media("Playstation 3");
		Media m2 = new Media("Tablet");
		Media m3 = new Media("HDMI kabels");
		Media m4 = new Media("Headsets");
		Aankondiging a1 = new Aankondiging("Gedurende de eerste 30 minuten maken we groepjes!",g1.getNaam(),new Date(2020,2,13,12,30));
		Aankondiging a2 = new Aankondiging("Wie denkt van afwezig te zijn zal het merken aan z'n punten!",g1.getNaam(),new Date(2020,3,14,12,30));
		Aankondiging a3 = new Aankondiging("Gedurende de eerste 30 minuten maken we groepjes!",g1.getNaam(),new Date(2020,2,13,12,30));
		Feedback f1 = new Feedback("Ruben Vandermeersch", "Heel boeiend!!");
		Feedback f2 = new Feedback("Elias Vervaecke", "Ik vond de spreker goed gearticuleerd!");
		Feedback f3 = new Feedback("Wim Decoene", "Man, ik viel bijna in slaap!");
		Feedback f4 = new Feedback("Randy Six", "Ik herkende de gastspreker, maar kon er mn vinger niet opleggen!");
		Feedback f5 = new Feedback("Ruben Vandermeersch", "Wederom heel boeiend!!");
		

		//-- sessies opbouwen met alles er in, en dan enkel sessie storen --
		
		s1.setHerinneringen(Arrays.asList(h1,h2));
		s1.setMedia(Arrays.asList(m1));
		s1.setGeplaatsteAankondigingen(Arrays.asList(a1));
		s1.setGeplaatstFeedback(Arrays.asList(f1,f2));
		s1.setIngeschrevenGebruikers(Arrays.asList(g1,g2,g5,g6));
		
		g1.setSessiesWaarvoorIngeschreven(Arrays.asList(s1));
		g2.setSessiesWaarvoorIngeschreven(Arrays.asList(s1));
		g5.setSessiesWaarvoorIngeschreven(Arrays.asList(s1));
		g6.setSessiesWaarvoorIngeschreven(Arrays.asList(s1));
		
		s2.setHerinneringen(Arrays.asList(h3));
		s2.setMedia(Arrays.asList(m2));
		s3.setGeplaatstFeedback(Arrays.asList(f3));
		
		s4.setGeplaatstFeedback(Arrays.asList(f5,f4));
		s4.setMedia(Arrays.asList(m3,m4));


		

		
		//--Persistentie tools ophalen--
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory(); //Persistence unit naam is "school", zie de xml file.
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Stream.of(s1,s2,s3,s4,s5,s6).forEach(em::persist);
		em.getTransaction().commit();
		
		//--Persistence tools afsluiten--
		em.close();
		emf.close();
		
		
		
		/*
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
