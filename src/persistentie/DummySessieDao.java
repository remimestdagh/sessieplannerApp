package persistentie;

import java.util.List;

import domein.Sessie;
import javafx.collections.ObservableList;

public class DummySessieDao extends DummyDao<Sessie> implements SessieDao{

	private PersistentieDummy pd;
	
	public DummySessieDao() {
		//super(Sessie.class);
		this.pd = PersistentieDummy.getInstance();
	}
	

	public ObservableList<Sessie> findAll() { //
		return pd.getSessies();
	}


	public Sessie get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public Sessie update(Sessie object) {
		// update object in de databank, niet van toepassing in de dummy
		return null;
	}


	public void delete(Sessie object) {
	}


	public void insert(Sessie object) { 
		// TODO Auto-generated method stub
	}


	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public ObservableList<Sessie> getSessiesFromVerantwoordelijke(String naamVerantwoordelijke) {
		return pd.getSessiesFromVerantwoordelijke(naamVerantwoordelijke);
	}


	@Override
	public Sessie getSessieById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
