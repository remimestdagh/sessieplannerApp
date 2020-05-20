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
		return FXCollections.observableArrayList(sessieDao.getSessiesFromVerantwoordelijke(ingelogdeGebruiker.getNaam()));
	}
	
	/*
	 * tracht een account met doorgekregen info uit de persistentie op te halen. error vanuit persistentiedummy geworpen
	 * lukt het wel, dan stellen we de ingelogde gebruiker in
	 */
	public void login(String emailadres, String password){
		Gebruiker gebruiker = gebruikerDao.getGebruikerByEmail(emailadres);
		if(gebruiker.getWachtwoord() !=password.hashCode()) {
			throw new IllegalArgumentException("Verkeerd wachtwoord!");
		}
		if(gebruiker.getType() == GebruikerType.GewoneGebruiker) {
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
		if(ingelogdeGebruiker.getType() != GebruikerType.HoofdVerantwoordelijke) {
			throw new IllegalAccessError("Actie gewijgerd, niet gemachtigd!");
		}
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
		if(ingelogdeGebruiker.getType() != GebruikerType.HoofdVerantwoordelijke) {
			throw new IllegalAccessError("Actie gewijgerd, niet gemachtigd!");
		}
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
		return (ObservableList<ISessie>)(Object)geselecteerdeGebruiker.getSessiesWaarvoorAanwezigObservable();
	}
	
	//geselecteerde Sessie
	
	private Sessie geselecteerdeSessie;
	
	public void addSessieListener(PropertyChangeListener listener) {
		changes.addPropertyChangeListener("geselecteerdeSessie", listener);
	}
	public void setGeselecteerdeSessie(Sessie sessie) {
		Sessie oudesessie=null;
		if(!(this.geselecteerdeSessie==null)) {
			oudesessie=this.geselecteerdeSessie;
		}
		if(sessie != null) {
			this.geselecteerdeSessie = sessieDao.getSessieById(sessie.getSessieId());
		}
		changes.firePropertyChange("geselecteerdeSessie", oudesessie, sessie);
		
	}
	public ISessie getGeselecteerdeSessie() {
		return geselecteerdeSessie;
	}
	public void editGeselecteerdeSessie(SessieDTO dto) throws IllegalAccessException {
		try {
			geselecteerdeSessieKalender.editSessie(this.geselecteerdeSessie , dto);
		} catch (IllegalAccessException e) {
			throw new IllegalAccessException();
		}
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
	/**
	 * Deze methode voegt een herinnering toe aan een sessie en controleert of opgegeven string geldig is.
	 */
	public void addHerinneringToGeselecteerdeSessie(String type) {
		geselecteerdeSessie.addHerinnering(new Herinnering(type,1));
		sessieDao.update(geselecteerdeSessie);
	}
	


	
	public void verwijderMediaFromGeselecteerdeSessie(Media media) {
		if(ingelogdeGebruiker.getType() != GebruikerType.HoofdVerantwoordelijke 
				&& !ingelogdeGebruiker.getNaam().equals(geselecteerdeSessie.getNaamAanmaker())) {
			throw new IllegalAccessError("Actie gewijgerd, niet gemachtigd!");
		}
		geselecteerdeSessie.removeMedia(media);
		sessieDao.update(geselecteerdeSessie);
	}
	
	//getters
	public ObservableList<IMedia> getMediafromGeselecteerdeSessie(){
		return (ObservableList<IMedia>)(Object)geselecteerdeSessie.getGebruikteMediaObservable();
	}
	public ObservableList<IAankondiging> getAankondigingenfromGeselecteerdeSessie(){
		return (ObservableList<IAankondiging>)(Object)geselecteerdeSessie.getGeplaatsteAankondigingenObservable();
	}
	public ObservableList<IGebruiker> getGebruikersFromGeselecteerdeSessie(){
		return (ObservableList<IGebruiker>)(Object)geselecteerdeSessie.getAanwezigeGebruikersObservable();
		/*if(geselecteerdeSessie.getAanwezigeGebruikers().isEmpty()) {
			return FXCollections.emptyObservableList();
		}*/
	}
	public ObservableList<IHerinnering> getHerinneringenFromGeselecteerdeSessie(){
		return (ObservableList<IHerinnering>)(Object)geselecteerdeSessie.getHerinneringenObservable();
	}
	public ObservableList<IFeedback> getFeedbackFromGeselecteerdeSessie(){
		return (ObservableList<IFeedback>)(Object)geselecteerdeSessie.getGeplaatstFeedbackObservable();
	}
	
	//geselecteerde Sessie Kalender
	private SessieKalender geselecteerdeSessieKalender;
	
	
	
	public void setGeselecteerdeSessieKalender(SessieKalender sessieKalender) {
		if(sessieKalender != null) {
			sessieKalender.setHuidigeGebruiker(this.ingelogdeGebruiker);
		}
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
		if(ingelogdeGebruiker.getType() != GebruikerType.HoofdVerantwoordelijke 
				&& !ingelogdeGebruiker.getNaam().equals(geselecteerdeSessie.getNaamAanmaker())) {
			throw new IllegalAccessError("Actie gewijgerd, niet gemachtigd!");
		}
		if(!gebruikerIsHoofdverantwoordelijke()) {
			geselecteerdeSessieKalender = sessieKalenderDao.getHuidigeSessieKalender();
		}
		geselecteerdeSessieKalender.removeSessie((Sessie) sessie);
		sessieDao.delete(sessie);
		
		changes.firePropertyChange("SessieList",0,1);
	}
	
	public ObservableList<ISessie> getSessiesfromGeselecteerdeSessieKalender(){
		return (ObservableList<ISessie>)(Object)geselecteerdeSessieKalender.getSessieListObservable();
	}
	
	public void mailNaarGebruikers(String bericht, String type, String[] recipients)
	{
		
		String host = "smtp.gmail.com"; // google SMTP Server voor het versturen van email adressen.
		String port = "587"; // standaard smtp poort voor wanneer een email via goede mail server verstuurd
								// word
		String mailFrom = "noreply.itlabhogent@gmail.com";
		String password = "Groep25Proj";


		//bouw lijst van alle recipients dmv hun emailaddress in string formaat
		/*
		String[] mailTo = new String[getGebruikersFromGeselecteerdeSessie().size()];
		int i = 0;
		for (IGebruiker gebruiker : getGebruikersFromGeselecteerdeSessie()) {
		    mailTo[i]=gebruiker.getEmailadres();
		    i++;
		}
		*/
		//bouw het mail bericht op
		String subject = String.format("U hebt een nieuwe %s ontvangen van het IT-Lab!", type);
		String message = bericht;

		//emailer object
		Emailer mailer = new Emailer();
		try {
			mailer.sendPlainTextEmail(host, port, mailFrom, password, recipients, subject, message);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

}
