package persistentie;

import domein.Sessie;
import javafx.collections.ObservableList;

public interface SessieDao extends Dao{

	/**
	 * Extensie op hoofdinterface met benodigde uitbreiding voor het ophalen van van sessies op meegegeven verantwoordelijke
	 */
	public ObservableList<Sessie> getSessiesFromVerantwoordelijke(String naamVerantwoordelijke);
}
