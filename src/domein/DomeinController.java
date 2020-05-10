package domein;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
	
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	
	
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
			Seeder.seedDatabase();									//dummy for data
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
		return FXCollections.observableArrayList(sessieDao.getSessiesFromVerantwoordelijke(ingelogdeGebruiker.getNaam()));
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
		return FXCollections.observableArrayList(sessieDao.findAll());
	}
	
	public ObservableList<IGebruiker> getGebruikers(){
		return FXCollections.observableArrayList(gebruikerDao.findAll());
	}
	
	public ObservableList<IGebruiker> getGebruikersMetNaam(String naam){
		return (ObservableList<IGebruiker>) new  FilteredList<IGebruiker>(FXCollections.observableArrayList( gebruikerDao.findAll()), g -> g.getNaam().toLowerCase().contains(naam.toLowerCase()));
	}
	
	public ObservableList<ISessieKalender> getSessieKalenders(){
		return FXCollections.observableArrayList(sessieKalenderDao.findAll());
	}
	
	public void verwijderGebruiker(IGebruiker gebruiker) {
		gebruikerDao.delete(gebruiker);
		changes.firePropertyChange("GebruikerList",0,1);
		setGeselecteerdeGebruiker(null);
	}
	
	/**
	 * Voegt een nieuwe gebruiker toe indien opgegeven gegevens geldig zijn.
	 */
	public void addGebruiker(GebruikerDTO dto) {
		Gebruiker gebruiker = new Gebruiker(dto);
		gebruikerDao.insert(gebruiker);
		setGeselecteerdeGebruiker(gebruiker);
		changes.firePropertyChange("GebruikerList",0,1);
	}
	
	public void addSessieKalender(SessieKalenderDTO dto) {
		SessieKalender kalender = new SessieKalender(dto);
		sessieKalenderDao.insert(kalender);
		setGeselecteerdeSessieKalender(kalender);
		changes.firePropertyChange("SessieKalenderList",0,1);
	}
	
	public void verwijderSessieKalender(ISessieKalender kalender) {
		sessieKalenderDao.delete(kalender);
		changes.firePropertyChange("SessieKalenderList",0,1);
		setGeselecteerdeSessieKalender(null);
	}
	
	public void addGebruikerListListener(PropertyChangeListener listener) {
		changes.addPropertyChangeListener("GebruikerList", listener);
	}
	
	public void addSessieKalenderListListener(PropertyChangeListener listener) {
		changes.addPropertyChangeListener("SessieKalenderList", listener);
	}
	
	public void addSessieListListener(PropertyChangeListener listener) {
		changes.addPropertyChangeListener("SessieList", listener);
	}
	
	// geselecteerde gebruiker
	private Gebruiker geselecteerdeGebruiker;
	
	public void addGebruikerListener(PropertyChangeListener listener) {
		changes.addPropertyChangeListener("geselecteerdeGebruiker", listener);
	}
	public void setGeselecteerdeGebruiker(Gebruiker gebruiker) {
		Gebruiker oldGebruiker = this.geselecteerdeGebruiker;
		this.geselecteerdeGebruiker = gebruiker;
		changes.firePropertyChange("geselecteerdeGebruiker", oldGebruiker, gebruiker);
	}
	public IGebruiker getGeselecteerdeGebruiker() {
		return geselecteerdeGebruiker;
	}
	public void editGeselecteerdeGebruiker(GebruikerDTO dto) {
		geselecteerdeGebruiker.editGeselecteerdeGebruiker(dto);
		gebruikerDao.update(geselecteerdeGebruiker);
		changes.firePropertyChange("GebruikerList",0,1);
	}
	public ObservableList<ISessie> getSessiesfromGeselecteerdeGebruiker(){
		return FXCollections.observableArrayList(geselecteerdeGebruiker.getSessiesWaarvoorAanwezig());
	}
	
	//geselecteerde Sessie
	
	private Sessie geselecteerdeSessie;
	
	public void addSessieListener(PropertyChangeListener listener) {
		changes.addPropertyChangeListener("geselecteerdeSessie", listener);
	}
	public void setGeselecteerdeSessie(Sessie sessie) {
		this.geselecteerdeSessie = sessie;
		changes.firePropertyChange("geselecteerdeSessie", 0, sessie);
	}
	public ISessie getGeselecteerdeSessie() {
		return geselecteerdeSessie;
	}
	public void editGeselecteerdeSessie(SessieDTO dto) {
		geselecteerdeSessie.editSessie(dto);
		sessieDao.update(geselecteerdeSessie);
		changes.firePropertyChange("SessieList",0,1);
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
		return FXCollections.observableArrayList(geselecteerdeSessie.getGebruikteMedia());
	}
	public ObservableList<IAankondiging> getAankondigingenfromGeselecteerdeSessie(){
		return FXCollections.observableArrayList(geselecteerdeSessie.getGeplaatsteAankondigingen());
	}
	public ObservableList<IGebruiker> getGebruikersFromGeselecteerdeSessie(){
		return FXCollections.observableArrayList(geselecteerdeSessie.getAanwezigeGebruikers());
	}
	
	//geselecteerde Sessie Kalender
	
	private SessieKalender geselecteerdeSessieKalender;
	
	public void setGeselecteerdeSessieKalender(SessieKalender sessieKalender) {
		this.geselecteerdeSessieKalender = sessieKalender;
		changes.firePropertyChange("geselecteerdeSessieKalender", 0, sessieKalender);
	}
	public void addSessieKalenderListener(PropertyChangeListener listener) {
		changes.addPropertyChangeListener("geselecteerdeSessieKalender", listener);
	}
	public ISessieKalender getGeselecteerdeSessieKalender() {
		return geselecteerdeSessieKalender;
	}
	public void editGeselecteerdeSessieKalender(SessieKalenderDTO dto) {
		geselecteerdeSessieKalender.editSessieKalender(dto);
		sessieKalenderDao.update(geselecteerdeSessieKalender);
		changes.firePropertyChange("SessieKalenderList",0,1);
	}
	public void addSessieToGeselecteerdeSessieKalender(SessieDTO dto) {
		if(!gebruikerIsHoofdverantwoordelijke()) {
			geselecteerdeSessieKalender = sessieKalenderDao.getHuidigeSessieKalender();
		}
		dto.setSessieAanmaker(ingelogdeGebruiker.getNaam());
		Sessie sessie = new Sessie(dto);
		geselecteerdeSessieKalender.addSessie(sessie);
		sessieKalenderDao.update(geselecteerdeSessieKalender);
		//setGeselecteerdeSessie(sessie);
		changes.firePropertyChange("SessieList",0,1);
	}
	public void verwijderSessieFromGeselecteerdeSessieKalender(ISessie sessie) {
		if(!gebruikerIsHoofdverantwoordelijke()) {
			geselecteerdeSessieKalender = sessieKalenderDao.getHuidigeSessieKalender();
		}
		geselecteerdeSessieKalender.removeSessie((Sessie) sessie);
		changes.firePropertyChange("SessieList",0,1);
	}
	public ObservableList<ISessie> getSessiesfromGeselecteerdeSessieKalender(){
		return FXCollections.observableArrayList(geselecteerdeSessieKalender.getSessieList());
	}
	

}
