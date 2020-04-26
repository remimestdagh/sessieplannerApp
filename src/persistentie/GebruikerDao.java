package persistentie;

import domein.Gebruiker;

public interface GebruikerDao extends Dao{
	/**
	 * Extensie op hoofdinterface met benodigde uitbreiding voor het ophalen van gebruiker op email
	 */
	public Gebruiker getGebruikerByEmail(String email);
}
