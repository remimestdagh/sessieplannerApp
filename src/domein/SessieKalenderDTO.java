package domein;

import java.time.LocalDateTime;

public class SessieKalenderDTO {

	// PARAMETERS
	private String academiejaar;
	private LocalDateTime startdatum;
	private LocalDateTime einddatum;

	// CONSTRUCTOR
	public SessieKalenderDTO() {
	}

	// GETTERS AND SETTERS
	public String getAcademiejaar() {
		return academiejaar;
	}

	public void setAcademiejaar(String academiejaar) {
		this.academiejaar = academiejaar;
	}

	public LocalDateTime getStartdatum() {
		return startdatum;
	}

	public void setStartdatum(LocalDateTime startdatum) {
		this.startdatum = startdatum;
	}

	public LocalDateTime getEinddatum() {
		return einddatum;
	}

	public void setEinddatum(LocalDateTime einddatum) {
		this.einddatum = einddatum;
	}
}
