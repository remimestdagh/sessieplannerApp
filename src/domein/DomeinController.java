package domein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.PersistentieDummy;
import persistentie.Seeder;

public class DomeinController {
	
	
	//PARAMETERS
	private Gebruiker ingelogdeGebruiker;
	private PersistentieDummy pd;
	
	
	//CONSTRUCTOR
	public DomeinController()
	{
		Seeder.seedDatabase(); //locaal opvullen van een fictieve database.
		pd = new PersistentieDummy();
	}
	
	//METHODES
	public boolean gebruikerIsHoofdverantwoordelijke() {
		if(ingelogdeGebruiker.getType() == GebruikerType.HoofdVerantwoordelijke) {
			return true;
		}
		return false;
	}
	
	/*
	 * tracht een account met doorgekregen info uit de persistentie op te halen. error vanuit persistentiedummy geworpen
	 * lukt het wel, dan stellen we de ingelogde gebruiker in
	 */
	public void login(String emailadres, String password){
		Gebruiker gebruiker = pd.getGebruiker(emailadres, password);
		if(gebruiker.getType() == GebruikerType.Gewone_Gebruiker) {
			throw new IllegalAccessError("Toegang gewijgerd, niet gemachtigd!");
		}
		ingelogdeGebruiker = gebruiker;
	}
	
	public String getNaamIngelogdeGebruiker() {
		return ingelogdeGebruiker.getNaam();
	}
	
	public ObservableList<Sessie> getSessies(){
		List<Sessie> sessies = pd.getSessies();
		ObservableList<Sessie> obsList = FXCollections.<Sessie>observableArrayList(sessies);
		return obsList;
	}
	
	public ObservableList<Gebruiker> getGebruikers(){
		List<Gebruiker> gebruikers = pd.getGebruikers();
		ObservableList<Gebruiker> obsList = FXCollections.<Gebruiker>observableArrayList(gebruikers);
		return obsList;
	}
	
	public ObservableList<SessieKalender> getSessieKalenders(){
		List<SessieKalender> sessieKalenders = pd.getSessieKalenders();
		ObservableList<SessieKalender> obsList = FXCollections.<SessieKalender>observableArrayList(sessieKalenders);
		return obsList;
	}
	
	public void verwijderGebruiker(String naam) {
		pd.verwijderGebruiker(naam);
	}
	
	public void addGebruiker(String naam,String naamChamilo, String emailadres, String wachtwoord, String status, String type) {
		Gebruiker gebruiker = new Gebruiker(naam,naamChamilo,emailadres,wachtwoord,status,type);
		pd.addGebruiker(gebruiker);
	}
	
	public void addSessie(String titel, String naamGastspreker,
			String lokaalCode, int MAX_CAPACITEIT,
			String startDatum, String eindDatum) {
		Sessie sessie = new Sessie(titel,naamGastspreker,lokaalCode,MAX_CAPACITEIT,new Date(startDatum),new Date(eindDatum),ingelogdeGebruiker.getNaam());
		pd.addSessie(sessie);
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
		return geselecteerdeSessie.getStartDatum().toGMTString();
	}
	public String getGeselecteerdeSessieEindDatum() {
		return geselecteerdeSessie.getEindDatum().toGMTString();
	}
	public String getGeselecteerdeSessieSessieAanmaker() {
		return geselecteerdeSessie.getSessieAanmaker();
	}
	public String getGeselecteerdeSessieStatus() {
		return geselecteerdeSessie.getStatus().toString();
	}
	public void editGeselecteerdeSessie(String titel, String naamGastspreker, String lokaalCode, int plaatsen, String startDatum,String eindDatum, String status) {
		geselecteerdeSessie.editSessie(titel, naamGastspreker, lokaalCode, plaatsen, startDatum, eindDatum, status);
	}
	public void addAankondigingToGeselecteerdeSessie(String inhoud) {
		geselecteerdeSessie.addAankondiging(new Aankondiging(inhoud, ingelogdeGebruiker.getNaam(), new Date()));
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
		return geselecteerdeSessieKalender.getStartdatum().toGMTString();
	}
	public String getGeselecteerdeSessieKalenderEinddatum() {
		return geselecteerdeSessieKalender.getEinddatum().toGMTString();
	}
	public void editGeselecteerdeSessieKalender(String academiejaar, String startdatum, String einddatum) {
		geselecteerdeSessieKalender.editSessieKalender(academiejaar, new Date(startdatum), new Date(einddatum));
	}
	public void addSessieToGeselecteerdeSessieKalender(String titel, String naamGastspreker,
			String lokaalCode, int MAX_CAPACITEIT,
			String startDatum, String eindDatum) {
		Sessie sessie = new Sessie(titel,naamGastspreker,lokaalCode,MAX_CAPACITEIT,new Date(startDatum),new Date(eindDatum),ingelogdeGebruiker.getNaam());
		geselecteerdeSessieKalender.addSessie(sessie);
	}
	public ObservableList<Sessie> getSessiesfromGeselecteerdeSessieKalender(){
		List<Sessie> sessies = geselecteerdeSessieKalender.getSessieList();
		ObservableList<Sessie> obsList = FXCollections.<Sessie>observableArrayList(sessies);
		return obsList;
	}
	

}
