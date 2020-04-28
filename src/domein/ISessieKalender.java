package domein;

import java.time.LocalDateTime;

public interface ISessieKalender {

	public String getAcademiejaar();
	
	public LocalDateTime getStartdatum();
	
	public LocalDateTime getEinddatum();
}
