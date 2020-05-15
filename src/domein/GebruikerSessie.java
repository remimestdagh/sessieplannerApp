package domein;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GebruikerSessie")
@Access(AccessType.FIELD)
public class GebruikerSessie {

	@Id
	private int GebruikerId;
	@Id
	private int SessieId;
	private boolean GebruikerWasAanwezig;
}
