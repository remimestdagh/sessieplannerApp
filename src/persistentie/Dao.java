package persistentie;

import javafx.collections.ObservableList;

public interface Dao<T> {
	
	public ObservableList<T> findAll();  
	
	public T get(Long id);
	
	public T update(T object);
	
	public void delete(T object);
	
	public void insert(T object);
	
	public boolean exists(Long id);
}
