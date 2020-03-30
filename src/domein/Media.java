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
	
	//METHODS
	
	//GETTERS AND SETTERS
	
}
