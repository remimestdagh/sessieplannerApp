package domein;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SessieKalender {
	
	//PARAMETERS
	private String academiejaar;
	private LocalDateTime startdatum;
	private LocalDateTime einddatum;
	private ObservableList<Sessie> sessieList;

	//CONSTRUCTOR
	public SessieKalender(String academiejaar, LocalDateTime startdatum, LocalDateTime einddatum) {
		setAcademiejaar(academiejaar);
		setStartdatum(startdatum);
		setEinddatum(einddatum);
		sessieList = FXCollections.<Sessie>observableArrayList();
	}
	
	//METHODS
	public void addSessie(Sessie sessie) {
		sessieList.add(sessie);
	}
	public void removeSessie(Sessie sessie) {
		sessieList.remove(sessie);
	}
	public void editSessieKalender(String academiejaar, LocalDateTime startdatum, LocalDateTime einddatum) {
		if(einddatum.isBefore(startdatum)) {
			throw new IllegalArgumentException("Startdatum moet voor einddatum plaatsvinden");
		}
		setAcademiejaar(academiejaar);
		setStartdatum(startdatum);
		setEinddatum(einddatum);
	}
	

	//GETTERS AND SETTERS
	public String getAcademiejaar() {
		return academiejaar;
	}
	public void setAcademiejaar(String academiejaar) {
		if(academiejaar.isEmpty() || academiejaar.isBlank()) {
			throw new IllegalArgumentException("Vul alle velden in!");
		}
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
	public ObservableList<Sessie> getSessieList() {
		return sessieList;
	}
	public void setSessieList(ObservableList<Sessie> sessieList) {
		this.sessieList = sessieList;
	}
}
