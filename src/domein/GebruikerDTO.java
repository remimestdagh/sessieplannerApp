package domein;

public class GebruikerDTO {

	private String naam;
	private String naamChamilo;
	private String emailadres;
	private String wachtwoord;
	private GebruikerStatus status;
	private GebruikerType type;
	private String unhashed;

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

	public GebruikerStatus getStatus() {
		return status;
	}

	public void setStatus(GebruikerStatus status) {
		this.status = status;
	}

	public GebruikerType getType() {
		return type;
	}

	public void setType(GebruikerType type) {
		this.type = type;
	}

	public String getTypeString() {
		return this.type.toString();
	}
	
	public String getUnhashed()
	{
		return unhashed;
	}
	public void setUnhashed(String pw)
	{
		this.unhashed=pw;
	}
}
