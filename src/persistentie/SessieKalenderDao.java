package persistentie;

import domein.SessieKalender;

public interface SessieKalenderDao extends Dao{

	/**
	 * Extensie op hoofdinterface met benodigde uitbreiding voor het ophalen van sessiekalender voor dit moment.
	 */
	public SessieKalender getHuidigeSessieKalender();
	
}
