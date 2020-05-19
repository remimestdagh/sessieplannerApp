package domein;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

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
public class Feedback implements IFeedback {

	// PARAMETERS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FeedbackId")
	private int feedbackId;
	@Column(name = "AuteursNaam")
	private String feedbackAuteur;
	@Column(name = "FeedbackTekst")
	private String feedbackTekst;
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
	public Sessie getSessie() {
		return sessie;
	}
	public void setSessie(Sessie sessie) {
		this.sessie = sessie;
	}
}
