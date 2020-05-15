package domein;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="GebruikerWachtwoord")
@Access(AccessType.FIELD)
public class GebruikerWachtwoord {

	@OneToOne
	private Gebruiker gebruiker;
	@Id
	@Column(name = "GebruikerWachtwoordId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="Wachtwoord")
	private int wachtwoord;

	public GebruikerWachtwoord() {}
	public GebruikerWachtwoord(String wachtwoord, Gebruiker gebruiker) {
		setWachtwoord(wachtwoord);
		setGebruiker(gebruiker);
	}
	
	public Gebruiker getGebruiker() {
		return gebruiker;
	}
	
	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}
	
	public int getWachtwoord() {
		return wachtwoord;
	}
	
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord.hashCode();
	}
}
