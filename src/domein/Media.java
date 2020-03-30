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
	public Media(String type) {
		this.type = type;
	}
	
	
	//METHODS
	
	//GETTERS AND SETTERS
	
}
