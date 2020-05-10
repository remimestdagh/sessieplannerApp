package persistentie;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import domein.Aankondiging;
import domein.Feedback;
import domein.Gebruiker;
import domein.GebruikerStatus;
import domein.GebruikerType;
import domein.Herinnering;
import domein.Media;
import domein.Sessie;
import domein.SessieKalender;
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
		//gebruikerId nodig bij het seeden
		g1.setGebruikerId(1);
		g2.setGebruikerId(2);
		g3.setGebruikerId(3);
		g4.setGebruikerId(4);
		g5.setGebruikerId(5);
		g6.setGebruikerId(6);
		
		
		//--Sessies--
		Sessie s1 = new Sessie("Github for dummys", "Frederik Haesbrouk", "B3.31", 20, LocalDateTime.of(2020,2,13,12,30), LocalDateTime.of(2020,2,13,13,30), g1.getNaam());
		Sessie s2 = new Sessie("Scrum is the truth", "Frederik Haesbrouk", "B2.17", 50, LocalDateTime.of(2020,2,15,12,30), LocalDateTime.of(2020,2,15,13,30), g6.getNaam());
		Sessie s3 = new Sessie("Linux Sucks", "Alexander De Baene", "B3.33", 25, LocalDateTime.of(2020,3,13,14,30), LocalDateTime.of(2020,3,13,17,0), g2.getNaam());
		Sessie s4 = new Sessie("Linux Is Just Amazing!", "Bert Suffys", "B0.00", 25, LocalDateTime.of(2020,3,13,14,30), LocalDateTime.of(2020,3,13,17,0), g3.getNaam());
		Sessie s5 = new Sessie("Linux Is pretty average", "Alexander De Baene", "B3.33", 25, LocalDateTime.of(2020,3,13,14,30), LocalDateTime.of(2020,3,13,17,0), g4.getNaam());
		Sessie s6 = new Sessie("The saddening history of Ubuntu", "Hugh Mungus", "B6.66", 25, LocalDateTime.of(2020,3,13,14,30), LocalDateTime.of(2020,3,13,17,0), g5.getNaam());

		s1.setSessieId(1);
		s2.setSessieId(2);
		s3.setSessieId(3);
		s4.setSessieId(4);
		s5.setSessieId(5);
		s6.setSessieId(6);
		//--SessieKalenders--
		SessieKalender sk1 = new SessieKalender("2019-2020", LocalDateTime.now(), LocalDateTime.now().plusYears(1));
		
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
		s1.setGebruikteMedia(Arrays.asList(m1));
		s1.setGeplaatsteAankondigingen(Arrays.asList(a1));
		s1.setGeplaatstFeedback(Arrays.asList(f1,f2));
		//s1.setIngeschrevenGebruikers(Arrays.asList(g1,g2,g5,g6));
		
		//g1.setSessiesWaarvoorIngeschreven(Arrays.asList(s1));
		//g2.setSessiesWaarvoorIngeschreven(Arrays.asList(s1));
		//g3.setSessiesWaarvoorIngeschreven(Arrays.asList(s1));
		//g4.setSessiesWaarvoorIngeschreven(Arrays.asList(s1));
		//g5.setSessiesWaarvoorIngeschreven(Arrays.asList(s1));
		//g6.setSessiesWaarvoorIngeschreven(Arrays.asList(s1));

		//g1.addAanwezigheid(s2);
		//g2.addAanwezigheid(s2);
		//g3.addAanwezigheid(s2);
		//g4.addAanwezigheid(s2);
		//g5.addAanwezigheid(s2);
		//g6.addAanwezigheid(s2);
		
		s2.setHerinneringen(Arrays.asList(h3));
		s2.setGebruikteMedia(Arrays.asList(m2));
		s3.setGeplaatstFeedback(Arrays.asList(f3));
		
		s4.setGeplaatstFeedback(Arrays.asList(f5,f4));
		s4.setGebruikteMedia(Arrays.asList(m3,m4));
		
		
		
		//--SessieKalenders vullen met sessies--
		sk1.addSessie(s1);
		sk1.addSessie(s2);
		sk1.addSessie(s3);
		sk1.addSessie(s4);
		sk1.addSessie(s5);
		sk1.addSessie(s6);
		
		//--Persistentie tools ophalen--
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory(); //Persistence unit naam is "school", zie de xml file.
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query query1 = em.createNativeQuery(String.format("insert into GebruikerSessie values (1,2,0)"));
		Query query2 = em.createNativeQuery(String.format("insert into GebruikerSessie values (2,2,1)"));
		Query query3 = em.createNativeQuery(String.format("insert into GebruikerSessie values (4,1,1)"));
		Query query4 = em.createNativeQuery(String.format("insert into GebruikerSessie values (5,1,0)"));
		Query query5 = em.createNativeQuery(String.format("insert into GebruikerSessie values (6,4,1)"));
		Query query6 = em.createNativeQuery(String.format("insert into GebruikerSessie values (3,4,0)"));
		query1.executeUpdate();
		query2.executeUpdate();
		query3.executeUpdate();
		query4.executeUpdate();
		query5.executeUpdate();
		query6.executeUpdate();
		
		Stream.of(s1,s2,s3,s4,s5,s6).forEach(em::persist);
		Stream.of(g1,g2,g3, g4, g5, g6).forEach(em::persist);
		Stream.of(h1,h2,h3).forEach(em::persist);
		Stream.of(f1, f2, f3, f4, f5).forEach(em::persist);
		Stream.of(m1, m2, m3, m4).forEach(em::persist);
		Stream.of(a1, a2).forEach(em::persist);
		em.persist(sk1);
		em.getTransaction().commit();
		
		//--Persistence tools afsluiten--
		em.close();
		
		
		
		
	}
	
}
