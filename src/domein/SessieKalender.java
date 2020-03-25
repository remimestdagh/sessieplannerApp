package domein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessieKalender {
	
	private String academiejaar;
	
	private Date startdatum;
	private Date einddatum;
	
	private List<Sessie> sessieList;

	public SessieKalender(String academiejaar, Date startdatum, Date einddatum) {
		setAcademiejaar(academiejaar);
		setStartdatum(startdatum);
		setEinddatum(einddatum);
		
		sessieList = new ArrayList();
	}
	//Methodes
	
	public void addSessie(Sessie sessie) {
		sessieList.add(sessie);
	}
	public void removeSessie(Sessie sessie) {
		sessieList.remove(sessie);
	}
	public void editSessieKalender(String academiejaar, Date startdatum, Date einddatum) {
		setAcademiejaar(academiejaar);
		setStartdatum(startdatum);
		setEinddatum(einddatum);
	}
	
	//Getters en Setters
	
	public String getAcademiejaar() {
		return academiejaar;
	}
	public void setAcademiejaar(String academiejaar) {
		this.academiejaar = academiejaar;
	}
	public Date getStartdatum() {
		return startdatum;
	}
	public void setStartdatum(Date startdatum) {
		this.startdatum = startdatum;
	}
	public Date getEinddatum() {
		return einddatum;
	}
	public void setEinddatum(Date einddatum) {
		this.einddatum = einddatum;
	}
	public List<Sessie> getSessieList() {
		return sessieList;
	}
	public void setSessieList(List<Sessie> sessieList) {
		this.sessieList = sessieList;
	}
}
