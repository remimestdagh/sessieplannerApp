package domein;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.JPADao;

@Entity
@TableGenerator(name = "SessieKalender")
@Access(AccessType.FIELD)
public class SessieKalender implements ISessieKalender{
	
	//PARAMETERS
	@Id
	@Column(name = "SessieKalenderId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sessieKalenderId;
	private String academiejaar;
	private LocalDateTime startdatum;
	private LocalDateTime einddatum;
	@OneToMany
	private ObservableList<Sessie> sessieList;

	//CONSTRUCTOR
	public SessieKalender() {}
	public SessieKalender(String academiejaar, LocalDateTime startdatum, LocalDateTime einddatum) {
		setAcademiejaar(academiejaar);
		setStartdatum(startdatum);
		setEinddatum(einddatum);
		sessieList = FXCollections.<Sessie>observableArrayList();
	}
	
	public SessieKalender(SessieKalenderDTO dto) {
		setAcademiejaar(dto.getAcademiejaar());
		setStartdatum(dto.getStartdatum());
		setEinddatum(dto.getEinddatum());
		sessieList = FXCollections.<Sessie>observableArrayList();
	}
	
	//METHODS
	public void addSessie(Sessie sessie) {

		sessieList.add(sessie);

	}
	public void removeSessie(Sessie sessie) {
		sessieList.remove(sessie);
	}
	
	public void editSessieKalender(SessieKalenderDTO dto) {
		if(dto.getEinddatum().isBefore(dto.getStartdatum())) {
			throw new IllegalArgumentException("Startdatum moet voor einddatum plaatsvinden");
		}
		setAcademiejaar(dto.getAcademiejaar());
		setStartdatum(dto.getStartdatum());
		setEinddatum(dto.getEinddatum());
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
	@Access(AccessType.PROPERTY)
	public List<Sessie> getSessieList() {
		return sessieList;
	}
	public void setSessieList(List<Sessie> sessieList) {
		this.sessieList = FXCollections.observableList(sessieList);
	}
	public void setSessieList(ObservableList<Sessie> sessieList) {
		this.sessieList = sessieList;
	}
	public ObservableList<Sessie> getSessieListObservable(){
		return sessieList;
	}
}
