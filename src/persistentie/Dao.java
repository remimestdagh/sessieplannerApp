package persistentie;

import javafx.collections.ObservableList;
/*
 * Generieke interface Dao die definieert welke operaties zullen kunnen uitgevoerd worden op gepersisteerde items
 * Deze gepersisteerde items zullen dezelfde operaties over zich krijgen en daarom is het generiek gedefinieerd.
 * findAll-> Gebruikers,  findAll->Sessies, ...
 */

public interface Dao<T> {
	
	public ObservableList<T> findAll();  
	
	public T get(Long id);
	
	public T update(T object);
	
	public void delete(T object);
	
	public void insert(T object);
	
	public boolean exists(Long id);
}
