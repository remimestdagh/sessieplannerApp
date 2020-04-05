package domein;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessieKalender {
	
	//PARAMETERS
	private String academiejaar;
	private LocalDateTime startdatum;
	private LocalDateTime einddatum;
	private List<Sessie> sessieList;

	//CONSTRUCTOR
	public SessieKalender(String academiejaar, LocalDateTime startdatum, LocalDateTime einddatum) {
		setAcademiejaar(academiejaar);
		setStartdatum(startdatum);
		setEinddatum(einddatum);
		sessieList = new ArrayList();
	}
	
	//METHODS
	public void addSessie(Sessie sessie) {
		sessieList.add(sessie);
	}
	public void removeSessie(Sessie sessie) {
		sessieList.remove(sessie);
	}
	public void editSessieKalender(String academiejaar, LocalDateTime startdatum, LocalDateTime einddatum) {
		setAcademiejaar(academiejaar);
		setStartdatum(startdatum);
		setEinddatum(einddatum);
	}
	

	//GETTERS AND SETTERS
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
	public List<Sessie> getSessieList() {
		return sessieList;
	}
	public void setSessieList(List<Sessie> sessieList) {
		this.sessieList = sessieList;
	}
}
