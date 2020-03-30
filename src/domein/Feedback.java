package domein;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Feedback")
public class Feedback {

	// PARAMETERS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedbackId;
	@Column(name = "AuteursNaam")
	private String feedbackTekst;
	private String feedbackAuteur;

	// CONSTRUCTORS
	public Feedback(String auteur, String tekst) {
		setFeedbackAuteur(auteur);
		setFeedbackTekst(tekst);
	}

	// METHODS
	

	// GETTERS AND SETTERS
	public String getFeedbackTekst() {
		return feedbackTekst;
	}

	public String getFeedbackAuteur() {
		return feedbackAuteur;
	}

	public void setFeedbackTekst(String feedbackTekst) {
		if (feedbackTekst.isEmpty() || feedbackTekst.isBlank()) {
			throw new IllegalArgumentException("De feedback mag niet leeg zijn");

		}
		this.feedbackTekst = feedbackTekst;
	}

	public void setFeedbackAuteur(String feedbackAuteur) {
		if (feedbackAuteur.isBlank() || feedbackAuteur.isEmpty()) {
			throw new IllegalArgumentException("De auteur moet ingevuld zijn bij feedback");
		}
		this.feedbackAuteur = feedbackAuteur;
	}
}
