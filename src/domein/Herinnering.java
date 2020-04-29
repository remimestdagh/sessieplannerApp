package domein;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(name = "Herinnering")
public class Herinnering implements IHerinnering{
	
	
	//PARAMETERS
	@Id
	@Column(name = "HerinerringId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int herinneringId;
	private String bericht;
	//@Column(nullable = false)
	private int tijdstipOfVoorhand;
	
	//CONSTRUCTOR
	protected Herinnering() {}
	public Herinnering(String bericht, int tijdstipOfVoorhand) {
		this.bericht = bericht;
		this.tijdstipOfVoorhand = tijdstipOfVoorhand;
	}
	
	//METHODS
	
	
	//GETTERS AND SETTERS
	
	public int getHerinneringId() {
		return herinneringId;
	}
	public void setHerinneringId(int herinneringId) {
		this.herinneringId = herinneringId;
	}
	public String getBericht() {
		return bericht;
	}
	public void setBericht(String bericht) {
		this.bericht = bericht;
	}
	public int getTijdstipOfVoorhand() {
		return tijdstipOfVoorhand;
	}
	public void setTijdstipOfVoorhand(int tijdstipOfVoorhand) {
		this.tijdstipOfVoorhand = tijdstipOfVoorhand;
	}
	
}
