package persistentie;
import domein.SessieKalender;

public interface SessieKalenderDao extends Dao<SessieKalender>{
	
	public SessieKalender getHuidigeSessieKalender();
}
