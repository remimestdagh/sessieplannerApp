package persistentie;

import java.util.List;

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


	public SessieKalender update(SessieKalender object) { //
		pd.updateSessieKalender(object);
		return null;
	}

	public void delete(SessieKalender object) {
		// TODO Auto-generated method stub
		
	}


	public void insert(SessieKalender object) {
		// TODO Auto-generated method stub
		
	}


	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}


	public SessieKalender getHuidigeSessieKalender() {
		return pd.getHuidigeSessieKalender();
	}
}
