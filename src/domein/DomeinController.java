package domein;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private GebruikerDao gebruikerDao;
	private SessieDao sessieDao;
	private SessieKalenderDao sessieKalenderDao;
	
	
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
	
	public ObservableList<SessieKalender> getSessieKalenders(){
		return sessieKalenderDao.findAll();
	}
	
	public void verwijderGebruiker(Gebruiker gebruiker) {
		gebruikerDao.delete(gebruiker);
	}
	
	public void addGebruiker(String naam,String naamChamilo, String emailadres, String wachtwoord, String status, String type) {
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
		geselecteerdeSessie.addAankondiging(new Aankondiging(inhoud, ingelogdeGebruiker.getNaam(), new Date()));
		
		sessieDao.update(geselecteerdeSessie);
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
	public String getGeselecteerdeSessieKalenderStartdatum() {
		return geselecteerdeSessieKalender.getStartdatum().toString();
	}
	public String getGeselecteerdeSessieKalenderEinddatum() {
		return geselecteerdeSessieKalender.getEinddatum().toString();
	}
	public void editGeselecteerdeSessieKalender(String academiejaar, String startdatum, String einddatum) {
		geselecteerdeSessieKalender.editSessieKalender(academiejaar, LocalDateTime.parse(startdatum), LocalDateTime.parse(einddatum));
		
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
