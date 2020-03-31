package domein;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Aankondiging")
public class Aankondiging {
	
	//PARAMETERS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private int aankondigingId;
	private Date publicatieDatum;
	private String inhoud;
	private String auteur;

	
	//CONSTRUCTOR
	protected Aankondiging() {}
	public Aankondiging(String inhoud, String auteur, Date publicatieDatum) {
		this.inhoud = inhoud;
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
		this.inhoud = inhoud;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public Date getPublicatieDatum() {
		return publicatieDatum;
	}
	public void setPublicatieDatum(Date publicatieDatum) {
		this.publicatieDatum = publicatieDatum;
	}
}
