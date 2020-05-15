package domein;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Aankondiging")
public class Aankondiging implements IAankondiging{
	
	//PARAMETERS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int aankondigingId;
	@Column(name="PublicatieDatum")
	private Date publicatieDatum;
	@Column(name="Inhoud")
	private String inhoud;
	@Column(name="Auteur")
	private String auteur;

	@ManyToOne
	private Sessie sessie;

	
	//CONSTRUCTOR
	protected Aankondiging() {}
	public Aankondiging(String inhoud, String auteur, Date publicatieDatum) {
		setInhoud(inhoud);
		setAuteur(auteur);
		setPublicatieDatum(publicatieDatum);

		this.auteur = auteur;
		this.publicatieDatum = publicatieDatum;
	}
	
	//METHODS
	
	public String getAuteurNaam() {
		return auteur;
	}
	
	//GETTERS AND SETTERS
	
	public String getInhoud() {
		return inhoud;
	}
	public void setInhoud(String inhoud) {
		if(inhoud.isEmpty()|inhoud.isBlank()) {
			throw new IllegalArgumentException("De inhoud van de aankondiging moet ingevuld worden");
		}
		
		if(inhoud.isBlank() || inhoud.isEmpty())
		{
			throw new IllegalArgumentException("Gelieve uw aankondiging te voorzien van tekst!");
		}
		this.inhoud = inhoud;
		
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		if(auteur.isEmpty()|auteur.isBlank()) {
			throw new IllegalArgumentException("De auteur van de aankondiging kon niet opgehaald worden");
		}
		this.auteur = auteur;
	}
	public Date getPublicatieDatum() {
		return publicatieDatum;
	}
	public void setPublicatieDatum(Date publicatieDatum) {
		this.publicatieDatum = publicatieDatum;
	}
	public int getAankondigingId() {
		return aankondigingId;
	}
	public void setAankondigingId(int aankondigingId) {
		this.aankondigingId = aankondigingId;
	}
	public Sessie getSessie() {
		return sessie;
	}
	public void setSessie(Sessie sessie) {
		this.sessie = sessie;
	}
	
	
}
