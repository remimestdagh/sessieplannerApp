package domein;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "Feedback")
public class Feedback implements IFeedback{

	// PARAMETERS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FeedbackId")
	private int feedbackId;
	@Column(name = "AuteursNaam")
	private StringProperty feedbackAuteur;
	@Column(name = "FeedbackTekst")
	private StringProperty feedbackTekst;
	@ManyToOne
	private Sessie sessie;

	// CONSTRUCTORS
	protected Feedback() {}
	public Feedback(String auteur, String tekst) {
		setFeedbackAuteur(auteur);
		setFeedbackTekst(tekst);
	}

	// METHODS
	

	// GETTERS AND SETTERS
	@Access(AccessType.PROPERTY)
	public String getFeedbackTekst() {
		return feedbackTekst.get();
	}
	
	@Access(AccessType.PROPERTY)
	public String getFeedbackAuteur() {
		return feedbackAuteur.get();
	}
	
	public StringProperty textProperty() {
        return feedbackTekst;
    }
	
	public StringProperty auteurProperty() {
        return feedbackAuteur;
    }

	public void setFeedbackTekst(String feedbackTekst) {
		if (feedbackTekst.isEmpty() || feedbackTekst.isBlank()) {
			throw new IllegalArgumentException("De feedback mag niet leeg zijn");

		}
		this.feedbackTekst = new SimpleStringProperty(feedbackTekst);
	}

	public void setFeedbackAuteur(String feedbackAuteur) {
		if (feedbackAuteur.isBlank() || feedbackAuteur.isEmpty()) {
			throw new IllegalArgumentException("De auteur moet ingevuld zijn bij feedback");
		}
		this.feedbackAuteur = new SimpleStringProperty(feedbackAuteur);
	}
	public Sessie getSessie() {
		return sessie;
	}
	public void setSessie(Sessie sessie) {
		this.sessie = sessie;
	}
	
	
}
