package domein;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GebruikerSessie")
public class GebruikerSessie {

	@Id
	private int gebruikerId;
	@Id
	private int sessieId;
	private boolean gebruikerWasAanwezig;
	
	public GebruikerSessie() {}
	public GebruikerSessie(int gebruikerId, int sessieId, boolean gebruikerWasAanwezig) {
		this.gebruikerId = gebruikerId;
		this.sessieId = sessieId;
		this.gebruikerWasAanwezig = gebruikerWasAanwezig;
	}

	@Access(AccessType.FIELD)
	public int getGebruikerId() {
		return gebruikerId;
	}

	public void setGebruikerId(int gebruikerId) {
		this.gebruikerId = gebruikerId;
	}

	@Access(AccessType.FIELD)
	public int getSessieId() {
		return sessieId;
	}

	public void setSessieId(int sessieId) {
		this.sessieId = sessieId;
	}

	@Access(AccessType.FIELD)
	public boolean isGebruikerWasAanwezig() {
		return gebruikerWasAanwezig;
	}

	public void setGebruikerWasAanwezig(boolean gebruikerWasAanwezig) {
		this.gebruikerWasAanwezig = gebruikerWasAanwezig;
	}
	
	
}
