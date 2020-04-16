package persistentie;

import javafx.collections.ObservableList;

public class DummyDao<T> implements Dao {

	// CONSTRUCTOR
	public DummyDao() {
	}

	// METHODS
	@Override
	public ObservableList findAll() {
		return null;
	}

	@Override
	public Object get(Long id) {
		return null;
	}

	@Override
	public Object update(Object object) {
		return null;
	}

	@Override
	public void delete(Object object) {

	}

	@Override
	public void insert(Object object) {

	}

	@Override
	public boolean exists(Long id) {
		return false;
	}

}
