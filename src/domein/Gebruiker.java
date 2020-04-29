package domein;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



@Entity
@Table(name="Gebruiker")
public class Gebruiker implements IGebruiker{
	
	//PARAMETERS
	@Id
	@Column(name = "GebruikerId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gebruikerId;
	private String naam;
	private String naamChamilo;
	private String emailadres;
	private String wachtwoord;
	//enum mapping
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "GebruikerStatus")
	private GebruikerStatus status;
	@Enumerated(EnumType.STRING)
	@Column(name = "Type")
	private GebruikerType type;
	//relation mapping
	@JoinTable(name="GebruikerSessie") //ter herbenoeming tussentabel (match met dotnet)
	@ManyToMany(cascade = CascadeType.PERSIST) //Tussentabel!
	private ObservableList<Sessie> sessiesWaarvoorIngeschreven;
	
	//private String profielFoto;
	//private Date inschrijvingsDatum;
	@Transient //voorlopig niet gemapt, moet eigenlijk corresponderen met wasAanwezig in tussentabel!
	private ObservableList<Sessie> sessiesWaarvoorAanwezig; 

	
	//CONSTRUCTOR
	protected Gebruiker() {}
	public Gebruiker(String naam,String naamChamilo, String emailadres, String wachtwoord,
			GebruikerStatus status, GebruikerType type) {
		
		setNaam(naam);
		setNaamChamilo(naamChamilo);
		setEmailadres(emailadres);
		setWachtwoord(wachtwoord);
		
		setStatus(status);
		setType(type);
		
		sessiesWaarvoorIngeschreven = FXCollections.<Sessie>observableArrayList();
		sessiesWaarvoorAanwezig = FXCollections.<Sessie>observableArrayList();
	}
	
	public Gebruiker(String naam,String naamChamilo, String emailadres, String wachtwoord,
			String status, String type) {
		
		setNaam(naam);
		setNaamChamilo(naamChamilo);
		setEmailadres(emailadres);
		setWachtwoord(wachtwoord);
		
		setStatus(convertStatus(status));
		setType(convertType(type));
		
		sessiesWaarvoorIngeschreven = FXCollections.<Sessie>observableArrayList();
		sessiesWaarvoorAanwezig = FXCollections.<Sessie>observableArrayList();
	}
	
	public Gebruiker(GebruikerDTO dto) {
		
		setNaam(dto.getNaam());
		setNaamChamilo(dto.getNaamChamilo());
		setEmailadres(dto.getEmailadres());
		setWachtwoord(dto.getWachtwoord());
		
		setStatus(convertStatus(dto.getStatus()));
		setType(convertType(dto.getType()));
		
		sessiesWaarvoorIngeschreven = FXCollections.<Sessie>observableArrayList();
		sessiesWaarvoorAanwezig = FXCollections.<Sessie>observableArrayList();
	}

	//METHODS
	
	public void addInschrijving(Sessie sessie) {
		sessiesWaarvoorIngeschreven.add(sessie);
	}
	public void addAanwezigheid(Sessie sessie) {
		sessiesWaarvoorAanwezig.add(sessie);
	}
	public void editGeselecteerdeGebruiker(GebruikerDTO dto) {
		setNaam(dto.getNaam());
		setNaamChamilo(dto.getNaamChamilo());
		setEmailadres(dto.getEmailadres());
		setStatus(convertStatus(dto.getStatus()));
		setType(convertType(dto.getType()));
	}
	
	private GebruikerStatus convertStatus(String status) {
		switch(status) {
		case "ACTIEF":
			return GebruikerStatus.ACTIEF;
		case "GEBLOKKEERD":
			return GebruikerStatus.GEBLOKKEERD;
		case "NIET_ACTIEF":
			return GebruikerStatus.NIET_ACTIEF;
		default:
			throw new IllegalArgumentException("Geen geldige status!");
		}
	}
	
	private GebruikerType convertType(String type) {
		switch(type) {
		case "HoofdVerantwoordelijke":
			return GebruikerType.HoofdVerantwoordelijke;
		case "Verantwoordelijke":
			return GebruikerType.Verantwoordelijke;
		case "Gewone_Gebruiker":
			return GebruikerType.Gewone_Gebruiker;
		default:
			throw new IllegalArgumentException("Geen geldig type!");
		}
	}
	
	//GETTERS AND SETTERS
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		if(naam.isBlank()|naam.isEmpty()) {
			throw new IllegalArgumentException("De naam van de gebruiker mag niet leeg zijn");
		}
		this.naam = naam;
	}
	public String getNaamChamilo() {
		return naamChamilo;
	}
	public void setNaamChamilo(String naamChamilo) {
		if(naamChamilo.isBlank()|naamChamilo.isEmpty()) {
			throw new IllegalArgumentException("De gebruikersnaam mag niet leeg zijn");
		}
		this.naamChamilo = naamChamilo;
	}
	public String getEmailadres() {
		return emailadres;
	}
	public void setEmailadres(String emailadres) {
		if(emailadres.isBlank()|emailadres.isEmpty()) {
			throw new IllegalArgumentException("Het emailadres mag niet leeg zijn");
		}
		this.emailadres = emailadres;
	}
	public String getWachtwoord() {
		return wachtwoord;
	}
	public void setWachtwoord(String wachtwoord) {
		if(wachtwoord.isBlank()|wachtwoord.isEmpty()) {
			throw new IllegalArgumentException("Het wachtwoord mag niet leeg zijn");
		}
		this.wachtwoord = wachtwoord;
	}
	public GebruikerStatus getStatus() {
		return status;
	}
	public void setStatus(GebruikerStatus status) {
		if(status==null) {
			throw new IllegalArgumentException("De status moet een waarde hebben");
		}
		this.status = status;
	}
	public GebruikerType getType() {
		return type;
	}
	public void setType(GebruikerType type) {
		if(type==null) {
			throw new IllegalArgumentException("De gebruiker moet een type hebben");
		}
		this.type = type;
	}
	public ObservableList<Sessie> getSessiesWaarvoorIngeschreven() {
		return sessiesWaarvoorIngeschreven;
	}
	public void setSessiesWaarvoorIngeschreven(ObservableList<Sessie> sessieList) {
		this.sessiesWaarvoorIngeschreven = sessieList;
	}
	public ObservableList<Sessie> getSessiesWaarvoorAanwezig() {
		return sessiesWaarvoorAanwezig;
	}
	public void setSessiesWaarvoorAanwezig(ObservableList<Sessie> sessieList) {
		this.sessiesWaarvoorAanwezig = sessieList;
	}
	public String getTypeString() {
		return this.type.toString();
	}
}
