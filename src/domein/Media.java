package domein;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Media")
public class Media implements IMedia{
	
	//PARAMETERS
	@Id
	@Column(name = "Type")
	private String type;
	@ManyToOne
	private Sessie sessie;
	
	//CONSTRUCTOR
	protected Media() {}
	public Media(String type) {
		setType(type);
	}
	
	//METHODS
	
	//GETTERS AND SETTERS
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		
		if(type.isBlank() || type.isEmpty())
		{
			throw new IllegalArgumentException("Gelieve een naam voor het type media op te geven!");
		}
		this.type = type;
	}
	public Sessie getSessie() {
		return sessie;
	}
	public void setSessie(Sessie sessie) {
		this.sessie = sessie;
	}
	
}
