package persistentie;

import domein.SessieKalender;
import javafx.collections.ObservableList;

public class DummySessieKalenderDao extends DummyDao<SessieKalender> implements SessieKalenderDao{

	private PersistentieDummy pd;
	
	public DummySessieKalenderDao() {
		//super(SessieKalender.class);
		this.pd = PersistentieDummy.getInstance();
	}
	

	public ObservableList<SessieKalender> findAll() { //
		return pd.getSessieKalenders();
	}

	public SessieKalender get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessieKalender update(Object kalender) { //
		pd.updateSessieKalender((SessieKalender)kalender);
		return null;
	}

	@Override
	public void delete(Object kalender) {
		pd.verwijderSessieKalender((SessieKalender)kalender);
	}

	@Override
	public void insert(Object kalender) {
		pd.addSessieKalender((SessieKalender)kalender);
	}


	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}


	public SessieKalender getHuidigeSessieKalender() {
		return pd.getHuidigeSessieKalender();
	}
}
