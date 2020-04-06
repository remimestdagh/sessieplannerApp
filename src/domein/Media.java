package domein;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Media")
public class Media {
	
	//PARAMETERS
	@Id
	@Column(name = "Type")
	private String type;


	
	
	
	//CONSTRUCTOR
	protected Media() {}
	public Media(String type) {
		this.type = type;
	}
	
	
	
	//METHODS
	
	//GETTERS AND SETTERS
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
