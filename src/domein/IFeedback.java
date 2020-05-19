package domein;

import javafx.beans.property.StringProperty;

public interface IFeedback {
	
	public String getFeedbackTekst();

	public String getFeedbackAuteur();
	
	public StringProperty textProperty();
	
	public StringProperty auteurProperty();
}
