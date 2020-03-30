package domein;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(name = "Herinnering")
public class Herinnering {
	
	
	//PARAMETERS
	@Id
	@Column(name = "HerinerringId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int herinneringId;
	private String bericht;
	//@Column(nullable = false)
	private int tijdstipOfVoorhand;

	
	
	//CONSTRUCTOR
	public Herinnering(String bericht, int tijdstipOfVoorhand) {
		this.bericht = bericht;
		this.tijdstipOfVoorhand = tijdstipOfVoorhand;
	}
	
	
	//METHODS
	
	
	
	//GETTERS AND SETTERS
	
	
	
}
