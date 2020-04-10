package persistentie;

import java.util.List;

import domein.SessieKalender;
import javafx.collections.ObservableList;

public class DummySessieKalenderDao implements SessieKalenderDao{

	private PersistentieDummy pd;
	
	public DummySessieKalenderDao() {
		this.pd = PersistentieDummy.getInstance();
	}
	
	@Override
	public ObservableList<SessieKalender> findAll() { //
		return pd.getSessieKalenders();
	}

	@Override
	public SessieKalender get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessieKalender update(SessieKalender object) { //
		pd.updateSessieKalender(object);
		return null;
	}

	@Override
	public void delete(SessieKalender object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(SessieKalender object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SessieKalender getHuidigeSessieKalender() {
		return pd.getHuidigeSessieKalender();
	}
}
