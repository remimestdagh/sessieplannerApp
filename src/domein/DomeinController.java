package domein;

import java.util.Date;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import persistentie.Dao;
import persistentie.DummyGebruikerDao;
import persistentie.DummySessieDao;
import persistentie.DummySessieKalenderDao;
import persistentie.GebruikerDao;
import persistentie.JPAGebruikerDao;
import persistentie.JPASessieDao;
import persistentie.JPASessieKalenderDao;
import persistentie.SessieDao;
import persistentie.SessieKalenderDao;

import static domein.Config.*;

public class DomeinController {
	
	
	//PARAMETERS
	private Gebruiker ingelogdeGebruiker;
	private GebruikerDao gebruikerDao;				//repo voor gebruikers (finaal type Dao?)
	private SessieDao sessieDao;					//repo voor sessies (finaal type Dao?)
	private SessieKalenderDao sessieKalenderDao;	//repo voor sessiekalenders (finaal type Dao?)
	
	
	//CONSTRUCTOR
	public DomeinController(){
		
		if(USE_DUMMY) {
			this.gebruikerDao = new DummyGebruikerDao();
			this.sessieDao = new DummySessieDao();
			this.sessieKalenderDao = new DummySessieKalenderDao();
		}
		
		if(USE_JPA) {
			this.gebruikerDao = new JPAGebruikerDao();
			this.sessieDao = new JPASessieDao();
			this.sessieKalenderDao = new JPASessieKalenderDao();
			//Seeder.seedDatabase();									//dummy for data
		}
	}
	
	//METHODES
	
	public boolean gebruikerIsHoofdverantwoordelijke() {
		if(ingelogdeGebruiker.getType() == GebruikerType.HoofdVerantwoordelijke) {
			return true;
		}
		return false;
	}
	
	public ObservableList<ISessie> getSessiesFromVerantwoordelijke() {
		return (ObservableList<ISessie>)(Object)sessieDao.getSessiesFromVerantwoordelijke(ingelogdeGebruiker.getNaam());
	}
	
	/*
	 * tracht een account met doorgekregen info uit de persistentie op te halen. error vanuit persistentiedummy geworpen
	 * lukt het wel, dan stellen we de ingelogde gebruiker in
	 */
	public void login(String emailadres, String password){
		Gebruiker gebruiker = gebruikerDao.getGebruikerByEmail(emailadres);
		if(!gebruiker.getWachtwoord().equals(password)) {
			throw new IllegalArgumentException("Verkeerd wachtwoord!");
		}
		if(gebruiker.getType() == GebruikerType.Gewone_Gebruiker) {
			throw new IllegalAccessError("Toegang gewijgerd, niet gemachtigd!");
		}
		ingelogdeGebruiker = gebruiker;
	}
	
	public String getNaamIngelogdeGebruiker() {
		return ingelogdeGebruiker.getNaam();
	}
	
	public ObservableList<ISessie> getSessies(){
		return sessieDao.findAll();
	}
	
	public ObservableList<IGebruiker> getGebruikers(){
		return gebruikerDao.findAll();
	}
	
	public ObservableList<IGebruiker> getGebruikersMetNaam(String naam){
		return (ObservableList<IGebruiker>)(Object)new FilteredList<Gebruiker>(gebruikerDao.findAll(), g -> g.getNaam().toLowerCase().contains(naam.toLowerCase()));
	}
	
	public ObservableList<ISessieKalender> getSessieKalenders(){
		return sessieKalenderDao.findAll();
	}
	
	public void verwijderGebruiker(IGebruiker gebruiker) {
		gebruikerDao.delete(gebruiker);
	}
	
	/**
	 * Voegt een nieuwe gebruiker toe indien opgegeven gegevens geldig zijn.
	 */
	public void addGebruiker(GebruikerDTO dto) {
		Gebruiker gebruiker = new Gebruiker(dto);
		gebruikerDao.insert(gebruiker);
	}
	
	// geselecteerde gebruiker
	private Gebruiker geselecteerdeGebruiker;
	
	public void setGeselecteerdeGebruiker(Gebruiker gebruiker) {
		this.geselecteerdeGebruiker = gebruiker;
	}
	public IGebruiker getGeselecteerdeGebruiker() {
		return geselecteerdeGebruiker;
	}
	public void editGeselecteerdeGebruiker(GebruikerDTO dto) {
		geselecteerdeGebruiker.editGeselecteerdeGebruiker(dto);
		gebruikerDao.update(geselecteerdeGebruiker);
	}
	public ObservableList<ISessie> getSessiesfromGeselecteerdeGebruiker(){
		return (ObservableList<ISessie>)(Object)geselecteerdeGebruiker.getSessiesWaarvoorAanwezig();
	}
	
	//geselecteerde Sessie
	
	private Sessie geselecteerdeSessie;
	
	public void setGeselecteerdeSessie(Sessie sessie) {
		this.geselecteerdeSessie = sessie;
	}
	public ISessie getGeselecteerdeSessie() {
		return geselecteerdeSessie;
	}
	public void editGeselecteerdeSessie(SessieDTO dto) {
		geselecteerdeSessie.editSessie(dto);
		sessieDao.update(geselecteerdeSessie);
	}
	public void addAankondigingToGeselecteerdeSessie(String inhoud) {
		geselecteerdeSessie.addAankondiging(new Aankondiging(inhoud, ingelogdeGebruiker.getNaam(), new Date()));
		sessieDao.update(geselecteerdeSessie);
	}
	/**
	 * Deze methode voegt media toe aan een sessie en controleert of opgegeven string geldig is.
	 */
	public void addMediaToGeselecteerdeSessie(String type) {
		geselecteerdeSessie.addMedia(new Media(type));
		sessieDao.update(geselecteerdeSessie);
	}
	public void verwijderMediaFromGeselecteerdeSessie(Media media) {
		geselecteerdeSessie.removeMedia(media);
		sessieDao.update(geselecteerdeSessie);
	}
	public ObservableList<IMedia> getMediafromGeselecteerdeSessie(){
		return (ObservableList<IMedia>) (Object)geselecteerdeSessie.getGebruikteMedia();
	}
	public ObservableList<IAankondiging> getAankondigingenfromGeselecteerdeSessie(){
		return (ObservableList<IAankondiging>)(Object)geselecteerdeSessie.getGeplaatsteAankondigingen();
	}
	public ObservableList<IGebruiker> getGebruikersFromGeselecteerdeSessie(){
		return (ObservableList<IGebruiker>)(Object)geselecteerdeSessie.getAanwezigeGebruikers();
	}
	
	//geselecteerde Sessie Kalender
	
	private SessieKalender geselecteerdeSessieKalender;
	
	public void setGeselecteerdeSessieKalender(SessieKalender sessieKalender) {
		this.geselecteerdeSessieKalender = sessieKalender;
	}
	public ISessieKalender getGeselecteerdeSessieKalender() {
		return geselecteerdeSessieKalender;
	}
	public void editGeselecteerdeSessieKalender(SessieKalenderDTO dto) {
		geselecteerdeSessieKalender.editSessieKalender(dto);
		sessieKalenderDao.update(geselecteerdeSessieKalender);
	}
	public void addSessieToGeselecteerdeSessieKalender(SessieDTO dto) {
		if(!gebruikerIsHoofdverantwoordelijke()) {
			geselecteerdeSessieKalender = sessieKalenderDao.getHuidigeSessieKalender();
		}
		Sessie sessie = new Sessie(dto);
		geselecteerdeSessieKalender.addSessie(sessie);
		sessieKalenderDao.update(geselecteerdeSessieKalender);
	}
	public ObservableList<ISessie> getSessiesfromGeselecteerdeSessieKalender(){
		return (ObservableList<ISessie>)(Object)geselecteerdeSessieKalender.getSessieList();
	}
	

}
