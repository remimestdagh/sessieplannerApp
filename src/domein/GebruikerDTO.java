package domein;

public class GebruikerDTO {

	private String naam;
	private String naamChamilo;
	private String emailadres;
	private String wachtwoord;
	private String status;
	private String type;

	// private String profielFoto;
	// private LocalDateTime inschrijvingsDatum;

	// CONSTRUCTOR

	public GebruikerDTO() {
	}

	// GETTERS AND SETTERS

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getNaamChamilo() {
		return naamChamilo;
	}

	public void setNaamChamilo(String naamChamilo) {
		this.naamChamilo = naamChamilo;
	}

	public String getEmailadres() {
		return emailadres;
	}

	public void setEmailadres(String emailadres) {
		this.emailadres = emailadres;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeString() {
		return this.type.toString();
	}
}
