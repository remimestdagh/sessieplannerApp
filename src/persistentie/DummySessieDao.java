package persistentie;

import java.util.List;

import domein.Sessie;
import javafx.collections.ObservableList;

public class DummySessieDao implements SessieDao {

	private PersistentieDummy pd;
	
	public DummySessieDao() {
		this.pd = PersistentieDummy.getInstance();
	}
	
	@Override
	public ObservableList<Sessie> findAll() { //
		return pd.getSessies();
	}

	@Override
	public Sessie get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sessie update(Sessie object) {
		// update object in de databank, niet van toepassing in de dummy
		return null;
	}

	@Override
	public void delete(Sessie object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Sessie object) { 
		// TODO Auto-generated method stub
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ObservableList<Sessie> getSessiesFromVerantwoordelijke(String naamVerantwoordelijke) {
		return pd.getSessiesFromVerantwoordelijke(naamVerantwoordelijke);
	}
}
