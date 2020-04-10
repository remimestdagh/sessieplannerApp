package persistentie;

import domein.Sessie;
import javafx.collections.ObservableList;

public interface SessieDao extends Dao<Sessie> {
	
	public ObservableList<Sessie> getSessiesFromVerantwoordelijke(String naamVerantwoordelijke);
}
