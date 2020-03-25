package domein;

import java.util.Date;


public class Aankondiging {
	
	//PARAMETERS
	private String inhoud;
	private Gebruiker auteur;
	private Date publicatieDatum;
	
	//CONSTRUCTOR
	
	public Aankondiging(String inhoud, Gebruiker auteur, Date publicatieDatum) {
		this.inhoud = inhoud;
		this.auteur = auteur;
		this.publicatieDatum = publicatieDatum;
	}
	
	//METHODS
	
	public String getAuteurNaam() {
		return auteur.getNaam();
	}
	
	//GETTERS AND SETTERS
	
	public String getInhoud() {
		return inhoud;
	}
	public void setInhoud(String inhoud) {
		this.inhoud = inhoud;
	}
	public Gebruiker getAuteur() {
		return auteur;
	}
	public void setAuteur(Gebruiker auteur) {
		this.auteur = auteur;
	}
	public Date getPublicatieDatum() {
		return publicatieDatum;
	}
	public void setPublicatieDatum(Date publicatieDatum) {
		this.publicatieDatum = publicatieDatum;
	}
}
