package domein;

import java.util.List;

public interface IGebruiker {
	
	public String getNaam();
	
	public String getNaamChamilo();
	
	public String getEmailadres();
	
	public int getWachtwoord();
	
	public GebruikerStatus getStatus();
	
	public GebruikerType getType();
	
	public String getTypeString();
}
