package domein;

import java.time.LocalDateTime;
import javafx.collections.ObservableList;

public interface ISessie {

	public String getTitel();
	
	public String getSessieAanmaker();
	
	public LocalDateTime getStartDatum();
	
	public LocalDateTime getEindDatum();
	
	public String getNaamGastspreker();
	
	public String getLokaalCode();
	
	public int getMAX_CAPACITEIT();
	
	public SessieStatus getStatus();
}
