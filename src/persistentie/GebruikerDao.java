package persistentie;

import domein.Gebruiker;

public interface GebruikerDao extends Dao<Gebruiker>{
	
	public Gebruiker getGebruikerByEmail(String email);
}
