package persistentie;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import domein.Aankondiging;
import domein.Gebruiker;
import domein.GebruikerType;
import domein.Sessie;
import domein.SessieKalender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import domein.GebruikerStatus;

public class PersistentieDummy {
	
	//PARAMETERS
	private static PersistentieDummy uniqueInstance;
	
	private ObservableList<Gebruiker> gebruikers;
	private ObservableList<SessieKalender> sessieKalenders;
	private ObservableList<Sessie> sessies;
	
	//CONSTRUCTOR
	private PersistentieDummy() {
		gebruikers = FXCollections.<Gebruiker>observableArrayList();
		sessieKalenders = FXCollections.<SessieKalender>observableArrayList();
		sessies = FXCollections.<Sessie>observableArrayList();
		
		Gebruiker g1 = new Gebruiker("Maxim Van Cauwenberge", "862687mv", "maxim.vancauwenberge@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Verantwoordelijke);
		Gebruiker g2 = new Gebruiker("Alexander De Baene", "862656ad", "alexander.debaene@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Gewone_Gebruiker);
		Gebruiker g3 = new Gebruiker("Admin", "admin", "@", "p", GebruikerStatus.ACTIEF, GebruikerType.HoofdVerantwoordelijke);
		Gebruiker g4 = new Gebruiker("Jef Seys", "640431js", "jef.seys.y0431@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Gewone_Gebruiker);
		Gebruiker g5 = new Gebruiker("Remi Mestdagh", "757957rm", "remi.mestdagh@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Gewone_Gebruiker);
		Gebruiker g6 = new Gebruiker("Bert Suffys", "861835bs", "bert.suffys@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Gewone_Gebruiker);
		
		Sessie s1 = new Sessie("Github for dummys", "Frederik Haesbrouk", "B3.31", 20, LocalDateTime.of(2020,2,13,12,30), LocalDateTime.of(2020,2,13,13,30), g1.getNaam());
		Sessie s2 = new Sessie("Scrum is the truth", "Frederik Haesbrouk", "B2.17", 50, LocalDateTime.of(2020,2,15,12,30), LocalDateTime.of(2020,2,15,13,30), g1.getNaam());
		Sessie s3 = new Sessie("Linux Sucks", "Alexander De Baene", "B3.33", 25, LocalDateTime.of(2020,3,13,14,30), LocalDateTime.of(2020,3,13,17,0), g2.getNaam());
		
		s1.addAankondiging(new Aankondiging("Een sessie voor dummys die github onder de knie willen krijgen", g1.getNaam(), new Date()));
		s2.addAankondiging(new Aankondiging("Een uurtje Frederik horen zagen over Scrum", g1.getNaam(), new Date()));
		s3.addAankondiging(new Aankondiging("Niet voor Linux fans", g1.getNaam(), new Date()));
		
		SessieKalender sk = new SessieKalender("2019-2020", LocalDateTime.of(2019,8,1,12,0), LocalDateTime.of(2020,6,1,12,0));
		SessieKalender sk2 = new SessieKalender("2018-2019", LocalDateTime.of(2018,8,1,12,0), LocalDateTime.of(2019,6,1,12,0));
		SessieKalender sk3 = new SessieKalender("2020-2021", LocalDateTime.of(2020,8,1,12,0), LocalDateTime.of(2021,6,1,12,0));
		
		sk.addSessie(s1);
		sk.addSessie(s2);
		sk.addSessie(s3);
		
		s1.addAanwezigheid(g1);s1.addInschrijving(g1);
		s1.addAanwezigheid(g2);s1.addInschrijving(g2);
		s1.addAanwezigheid(g4);s1.addInschrijving(g4);
		s1.addAanwezigheid(g5);s1.addInschrijving(g5);
		s1.addAanwezigheid(g6);s1.addInschrijving(g6);
		
		s2.addAanwezigheid(g1);s2.addInschrijving(g1);
		s2.addAanwezigheid(g4);s2.addInschrijving(g4);
		s2.addAanwezigheid(g6);s2.addInschrijving(g6);
		s2.addInschrijving(g5);
		
		s3.addAanwezigheid(g1);s3.addInschrijving(g1);
		s3.addAanwezigheid(g2);s3.addInschrijving(g2);
		s3.addInschrijving(g5);
		
		g1.addAanwezigheid(s1);g1.addInschrijving(s1);
		g1.addAanwezigheid(s2);g1.addInschrijving(s2);
		g1.addAanwezigheid(s3);g1.addInschrijving(s3);
		
		g2.addAanwezigheid(s1);g2.addInschrijving(s1);
		g2.addAanwezigheid(s2);g2.addInschrijving(s2);
		g2.addAanwezigheid(s3);g2.addInschrijving(s3);
		
		g4.addAanwezigheid(s1);g4.addInschrijving(s1);
		g4.addAanwezigheid(s2);g4.addInschrijving(s2);
		
		g5.addAanwezigheid(s1);g5.addInschrijving(s1);
		g5.addInschrijving(s2);g5.addInschrijving(s3);
		
		g6.addAanwezigheid(s1);g6.addInschrijving(s1);
		g6.addAanwezigheid(s2);g6.addInschrijving(s2);
		
		gebruikers.add(g1);
		gebruikers.add(g2);
		gebruikers.add(g3);
		gebruikers.add(g4);
		gebruikers.add(g5);
		gebruikers.add(g6);
		
		sessies.add(s1);
		sessies.add(s2);
		sessies.add(s3);
		
		sessieKalenders.add(sk);
		sessieKalenders.add(sk2);
		sessieKalenders.add(sk3);
	}
	
	//METHODS
	
	public static PersistentieDummy getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new PersistentieDummy();
		}
		return uniqueInstance;
	}
	
	// Sessie methodes
	
	public ObservableList<Sessie> getSessies(){
		return sessies;
	}
	
	public void addSessieToKalender(Sessie sessie, SessieKalender kalender) {
		kalender.addSessie(sessie);
	}
	
	// Sessie Kalender methodes
	
	public ObservableList<SessieKalender> getSessieKalenders(){
		return sessieKalenders;
	}
	
	// Gebruikers methodes
	
	/*
	 * Op gekregen email returnen we het account of werpen we error met text.
	 */
	public Gebruiker getGebruikerByEmail(String emailadres) {
		for(Gebruiker geb: gebruikers){
			if(geb.getEmailadres().equals(emailadres)) {
				return geb;
			}
		}
		throw new IllegalArgumentException("Email niet gekend!");
	}
	
	public ObservableList<Gebruiker> getGebruikers(){
		return gebruikers;
	}
	
	public void verwijderGebruiker(Gebruiker gebruiker) {
		gebruikers.remove(gebruiker);
	}
	
	public void addGebruiker(Gebruiker gebruiker) {
		gebruikers.add(gebruiker);
	}
	
	public SessieKalender getHuidigeSessieKalender() {
		for(SessieKalender kalender: sessieKalenders) {
			LocalDateTime nu = LocalDateTime.now();
			if(kalender.getStartdatum().isBefore(nu) && kalender.getEinddatum().isAfter(nu)) {
				return kalender;
			}
		}
		throw new IllegalArgumentException("Huidige kalender niet gevonden!");
	}
	
	public ObservableList<Sessie> getSessiesFromVerantwoordelijke(String naamVerantwoordelijke) {
		ObservableList<Sessie> sessies = FXCollections.<Sessie>observableArrayList();
		
		for(Sessie sessie: getSessies()) {
			if(sessie.getNaamAanmaker().equals(naamVerantwoordelijke)) {
				sessies.add(sessie);
			}
		}
		
		return sessies;
	}

	public void updateSessieKalender(SessieKalender object) {
		for(Sessie s: object.getSessieList()) {
			if(!sessies.contains(s)) {
				sessies.add(s);
			}
		}
	}
}
