package persistentie;

import java.util.Comparator;
import java.util.stream.Collectors;

import domein.Gebruiker;
import javafx.collections.ObservableList;

public class DummyGebruikerDao extends DummyDao<Gebruiker> implements GebruikerDao{

	private PersistentieDummy pd;
	
	public DummyGebruikerDao() {
		this.pd = PersistentieDummy.getInstance();
	}

	public Gebruiker getGebruikerByEmail(String emailadres) {
		return pd.getGebruikerByEmail(emailadres);
	}
	
	@Override
	public ObservableList<Gebruiker> findAll() { //
		return pd.getGebruikers();
	}
	
	@Override
	public void delete(Object gebruiker) {
		pd.verwijderGebruiker((Gebruiker)gebruiker);
	}

	@Override
	public void insert(Object gebruiker) { 
		pd.addGebruiker((Gebruiker)gebruiker);
	}

}
