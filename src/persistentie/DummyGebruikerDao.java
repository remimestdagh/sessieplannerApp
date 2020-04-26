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
	
	public void delete(Gebruiker gebruiker) {
		pd.verwijderGebruiker(gebruiker);
	}

	public void insert(Gebruiker gebruiker) { 
		pd.addGebruiker(gebruiker);
	}

}
