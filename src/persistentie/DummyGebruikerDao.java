package persistentie;

import java.util.List;

import domein.Gebruiker;
import javafx.collections.ObservableList;

public class DummyGebruikerDao implements GebruikerDao{
	
	private PersistentieDummy pd;
	
	public DummyGebruikerDao() {
		this.pd = PersistentieDummy.getInstance();
	}

	@Override
	public ObservableList<Gebruiker> findAll() { //
		return pd.getGebruikers();
	}

	@Override
	public Gebruiker get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gebruiker update(Gebruiker object) {
		// update object in de databank, niet van toepassing in de dummy
		return null;
	}

	@Override
	public void delete(Gebruiker object) { //
		pd.verwijderGebruiker(object);
		
	}

	@Override
	public void insert(Gebruiker object) { //
		pd.addGebruiker(object);
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Gebruiker getGebruikerByEmail(String email) { //
		return pd.getGebruikerByEmail(email);
	}
}
