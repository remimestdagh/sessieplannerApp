package domein;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import persistentie.PersistentieDummy;
import persistentie.Seeder;
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
	
	public ObservableList<Sessie> getSessiesFromVerantwoordelijke() {
		return sessieDao.getSessiesFromVerantwoordelijke(ingelogdeGebruiker.getNaam());
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
	
	public ObservableList<Sessie> getSessies(){
		return sessieDao.findAll();
	}
	
	public ObservableList<Gebruiker> getGebruikers(){
		return gebruikerDao.findAll();
	}
	
	public ObservableList<Gebruiker> getGebruikersMetNaam(String naam){
		return new FilteredList<Gebruiker>(gebruikerDao.findAll(), g -> g.getNaam().toLowerCase().contains(naam.toLowerCase()));
	}
	
	public ObservableList<SessieKalender> getSessieKalenders(){
		return sessieKalenderDao.findAll();
	}
	
	public void verwijderGebruiker(Gebruiker gebruiker) {
		gebruikerDao.delete(gebruiker);
	}
	
	/**
	 * Voegt een nieuwe gebruiker toe indien opgegeven gegevens geldig zijn.
	 */
	public void addGebruiker(String naam,String naamChamilo, String emailadres, String wachtwoord, String status, String type) {
		
		String errorMessage ="";
		if(naam.isEmpty() || naam.isBlank() || naamChamilo.isEmpty() || naamChamilo.isBlank() || emailadres.isEmpty() || emailadres.isBlank() || wachtwoord.isEmpty() || wachtwoord.isBlank())
		{
			throw new IllegalArgumentException("Vul alle velden in!");
		}

		Gebruiker gebruiker = new Gebruiker(naam,naamChamilo,emailadres,wachtwoord,status,type);
		gebruikerDao.insert(gebruiker);
	}
	
	// geselecteerde gebruiker
	private Gebruiker geselecteerdeGebruiker;
	
	public void setGeselecteerdeGebruiker(Gebruiker gebruiker) {
		this.geselecteerdeGebruiker = gebruiker;
	}
	public String getGeselecteerdeGebruikerNaam() {
		return geselecteerdeGebruiker.getNaam();
	}
	public String getGeselecteerdeGebruikerNaamChamilo() {
		return geselecteerdeGebruiker.getNaamChamilo();
	}
	public String getGeselecteerdeGebruikerEmailadres() {
		return geselecteerdeGebruiker.getEmailadres();
	}
	public String getGeselecteerdeGebruikerStatus() {
		return geselecteerdeGebruiker.getStatus().toString();
	}
	public String getGeselecteerdeGebruikerType() {
		return geselecteerdeGebruiker.getType().toString();
	}
	public void editGeselecteerdeGebruiker(String naam, String naamChamilo, String email, String status, String type) {
		geselecteerdeGebruiker.editGeselecteerdeGebruiker(naam, naamChamilo, email, status, type);
		
		gebruikerDao.update(geselecteerdeGebruiker);
	}
	public ObservableList<Sessie> getSessiesfromGeselecteerdeGebruiker(){
		List<Sessie> sessies = geselecteerdeGebruiker.getSessiesWaarvoorAanwezig();
		ObservableList<Sessie> obsList = FXCollections.<Sessie>observableArrayList(sessies);
		return obsList;
	}
	
	//geselecteerde Sessie
	
	private Sessie geselecteerdeSessie;
	
	public void setGeselecteerdeSessie(Sessie sessie) {
		this.geselecteerdeSessie = sessie;
	}
	public String getGeselecteerdeSessieTitel() {
		return geselecteerdeSessie.getTitel();
	}
	public String getGeselecteerdeSessieNaamGastspreker() {
		return geselecteerdeSessie.getNaamGastspreker();
	}
	public String getGeselecteerdeSessieLokaalCode() {
		return geselecteerdeSessie.getLokaalCode();
	}
	public String getGeselecteerdeSessieMAX_CAPACITEIT() {
		return ""+geselecteerdeSessie.getMAX_CAPACITEIT();
	}
	public String getGeselecteerdeSessieStartDatum() {
		return geselecteerdeSessie.getStartDatum().toString();
	}
	public String getGeselecteerdeSessieEindDatum() {
		return geselecteerdeSessie.getEindDatum().toString();
	}
	public String getGeselecteerdeSessieSessieAanmaker() {
		return geselecteerdeSessie.getSessieAanmaker();
	}
	public String getGeselecteerdeSessieStatus() {
		return geselecteerdeSessie.getStatus().toString();
	}
	public void editGeselecteerdeSessie(String titel, String naamGastspreker, String lokaalCode, int plaatsen, String startDatum,String eindDatum, String status) {
		geselecteerdeSessie.editSessie(titel, naamGastspreker, lokaalCode, plaatsen, startDatum, eindDatum, status);
		
		sessieDao.update(geselecteerdeSessie);
	}
	
	
	
	public void addAankondigingToGeselecteerdeSessie(String inhoud) {
		
		if(inhoud.isBlank() || inhoud.isEmpty())
		{
			throw new IllegalArgumentException("Gelieve uw aankondiging te voorzien van tekst!");
		}
		
		geselecteerdeSessie.addAankondiging(new Aankondiging(inhoud, ingelogdeGebruiker.getNaam(), new Date()));
		
		sessieDao.update(geselecteerdeSessie);
	}
	
	/**
	 * Deze methode voegt media toe aan een sessie en controleert of opgegeven string geldig is.
	 */
	public void addMediaToGeselecteerdeSessie(String type) {
		if(type.isBlank() || type.isEmpty())
		{
			throw new IllegalArgumentException("Gelieve een naam voor het type media op te geven!");
		}
		geselecteerdeSessie.addMedia(new Media(type));
		sessieDao.update(geselecteerdeSessie);
	}
	
	
	public void verwijderMediaFromGeselecteerdeSessie(Media media) {
		geselecteerdeSessie.removeMedia(media);
		
		sessieDao.update(geselecteerdeSessie);
	}
	public ObservableList<Media> getMediafromGeselecteerdeSessie(){
		return (ObservableList<Media>) geselecteerdeSessie.getGebruikteMedia();
	}
	public ObservableList<Aankondiging> getAankondigingenfromGeselecteerdeSessie(){
		List<Aankondiging> aankondigingen = geselecteerdeSessie.getGeplaatsteAankondigingen();
		ObservableList<Aankondiging> obsList = FXCollections.<Aankondiging>observableArrayList(aankondigingen);
		return obsList;
	}
	public ObservableList<Gebruiker> getGebruikersFromGeselecteerdeSessie(){
		List<Gebruiker> gebruikers = geselecteerdeSessie.getAanwezigeGebruikers();
		ObservableList<Gebruiker> obsList = FXCollections.<Gebruiker>observableArrayList(gebruikers);
		return obsList;
	}
	
	//geselecteerde Sessie Kalender
	
	private SessieKalender geselecteerdeSessieKalender;
	
	public void setGeselecteerdeSessieKalender(SessieKalender sessieKalender) {
		this.geselecteerdeSessieKalender = sessieKalender;
	}
	public String getGeselecteerdeSessieKalenderAcademiejaar() {
		return geselecteerdeSessieKalender.getAcademiejaar();
	}
	public LocalDateTime getGeselecteerdeSessieKalenderStartdatum() {
		return geselecteerdeSessieKalender.getStartdatum();
	}
	public LocalDateTime getGeselecteerdeSessieKalenderEinddatum() {
		return geselecteerdeSessieKalender.getEinddatum();
	}
	public void editGeselecteerdeSessieKalender(String academiejaar, LocalDateTime startdatum, LocalDateTime einddatum) {
		geselecteerdeSessieKalender.editSessieKalender(academiejaar, startdatum, einddatum);
		
		sessieKalenderDao.update(geselecteerdeSessieKalender);
	}
	public void addSessieToGeselecteerdeSessieKalender(String titel, String naamGastspreker,
			String lokaalCode, int MAX_CAPACITEIT,
			LocalDateTime startDatum, LocalDateTime eindDatum) {
		
		if(!gebruikerIsHoofdverantwoordelijke()) {
			geselecteerdeSessieKalender = sessieKalenderDao.getHuidigeSessieKalender();
		}
		Sessie sessie = new Sessie(titel,naamGastspreker,lokaalCode,MAX_CAPACITEIT,startDatum,eindDatum,ingelogdeGebruiker.getNaam());
		geselecteerdeSessieKalender.addSessie(sessie);
		
		sessieKalenderDao.update(geselecteerdeSessieKalender);
	}
	public ObservableList<Sessie> getSessiesfromGeselecteerdeSessieKalender(){
		List<Sessie> sessies = geselecteerdeSessieKalender.getSessieList();
		ObservableList<Sessie> obsList = FXCollections.<Sessie>observableArrayList(sessies);
		return obsList;
	}
	

}
