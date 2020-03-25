package persistentie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import domein.Aankondiging;
import domein.Gebruiker;
import domein.GebruikerType;
import domein.Sessie;
import domein.SessieKalender;
import domein.GebruikerStatus;

public class PersistentieDummy {
	
	private List<Gebruiker> gebruikers;
	private List<Sessie> sessies;
	private List<SessieKalender> sessieKalenders;
	
	public PersistentieDummy() {
		sessies = new ArrayList();
		gebruikers = new ArrayList<>();
		sessieKalenders = new ArrayList();
		
		Gebruiker g1 = new Gebruiker("Maxim Van Cauwenberge", "862687mv", "maxim.vancauwenberge@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Verantwoordelijke);
		Gebruiker g2 = new Gebruiker("Alexander De Baene", "862656ad", "alexander.debaene@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Gewone_Gebruiker);
		Gebruiker g3 = new Gebruiker("Admin", "admin", "@", "p", GebruikerStatus.ACTIEF, GebruikerType.HoofdVerantwoordelijke);
		Gebruiker g4 = new Gebruiker("Jef Seys", "640431js", "jef.seys.y0431@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Gewone_Gebruiker);
		Gebruiker g5 = new Gebruiker("Remi Mestdagh", "757957rm", "remi.mestdagh@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Gewone_Gebruiker);
		Gebruiker g6 = new Gebruiker("Bert Suffys", "861835bs", "bert.suffys@student.hogent.be", "password", GebruikerStatus.ACTIEF, GebruikerType.Gewone_Gebruiker);
		
		Sessie s1 = new Sessie("Github for dummys", "Frederik Haesbrouk", "B3.31", 20, new Date(2020,2,13,12,30), new Date(2020,2,13,13,30), g1);
		Sessie s2 = new Sessie("Scrum is the truth", "Frederik Haesbrouk", "B2.17", 50, new Date(2020,2,15,12,30), new Date(2020,2,15,13,30), g1);
		Sessie s3 = new Sessie("Linux Sucks", "Alexander De Baene", "B3.33", 25, new Date(2020,3,13,14,30), new Date(2020,3,13,17,0), g2);
		
		s1.addAankondiging(new Aankondiging("Een sessie voor dummys die github onder de knie willen krijgen", g1, new Date()));
		s2.addAankondiging(new Aankondiging("Een uurtje Frederik horen zagen over Scrum", g1, new Date()));
		s3.addAankondiging(new Aankondiging("Niet voor Linux fans", g1, new Date()));
		
		SessieKalender sk = new SessieKalender("2019-2020", new Date(), new Date());
		
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
		
		sessies.add(s1);
		sessies.add(s2);
		sessies.add(s3);
		
		gebruikers.add(g1);
		gebruikers.add(g2);
		gebruikers.add(g3);
		gebruikers.add(g4);
		gebruikers.add(g5);
		gebruikers.add(g6);
		
		sessieKalenders.add(sk);
	}
	
	public List<Sessie> getSessies(){
		return sessies;
	}
	
	public List<SessieKalender> getSessieKalenders(){
		return sessieKalenders;
	}
	
	public Gebruiker getGebruiker(String emailadres, String password) {
		
		for(Gebruiker geb: gebruikers){
			if(geb.getEmailadres().equals(emailadres) && geb.getWachtwoord().equals(password)) {
				return geb;
			}
		}
		throw new IllegalArgumentException("Email of password verkeerd!");
	}
	
	public Gebruiker getGebruikerByName(String name) {
		for(Gebruiker geb: gebruikers){
			if(geb.getNaam().equals(name)) {
				return geb;
			}
		}
		throw new IllegalArgumentException("Geen gebruiker met die naam!");
	}
	
	public List<Gebruiker> getGebruikers(){
		return gebruikers;
	}
	
	public void verwijderGebruiker(String naam) {
		Gebruiker teverwijderen = null;
		for(Gebruiker geb: gebruikers){
			if(geb.getNaam().equals(naam)) {
				teverwijderen = geb;
			}
		}
		if(teverwijderen != null) {
			gebruikers.remove(teverwijderen);
		}
	}
	
	public void addGebruiker(Gebruiker gebruiker) {
		gebruikers.add(gebruiker);
	}
	
	public void addSessie(Sessie sessie) {
		sessies.add(sessie);
	}
	
	public void editGebruiker(String naam, String chamiloNaam, String status, String type) {
		Gebruiker gebruiker = null;
		for(Gebruiker geb: gebruikers){
			if(geb.getNaam().equals(naam)) {
				gebruiker = geb;
			}
		}
		if(gebruiker != null) {
			gebruiker.setNaamChamilo(chamiloNaam);
			switch(status.toUpperCase()) {
			case "GEBLOKKEERD":
				gebruiker.setStatus(GebruikerStatus.GEBLOKKEERD);
				break;
			case "NIET_ACTIEF":
				gebruiker.setStatus(GebruikerStatus.NIET_ACTIEF);
				break;
			case "ACTIEF":
			default:
				gebruiker.setStatus(GebruikerStatus.ACTIEF);
				break;
			}
			
		}
	}
}
