package domein;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Entity
@Table(name = "Sessie")
public class Sessie implements ISessie{
	// PARAMETERS
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sessieId;
	private String titel;
	private String naamGastspreker;
	private String lokaalCode;
	private int MAX_CAPACITEIT;
	private LocalDateTime startDatum;
	private LocalDateTime eindDatum;
	private String sessieAanmaker;
	//relaties 
	@Enumerated
	private SessieStatus status;// Aangemaakt, Geopent, Gestart, Gesloten
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "sessieId") //(Joincolumn, anders automatisch tussentabel bij OneToMany)
	private ObservableList<Media> gebruikteMedia;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "sessieId")
	private ObservableList<Herinnering> herinneringen;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "sessieId")
	private ObservableList<Aankondiging> geplaatsteAankondigingen;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "sessieId")
	private ObservableList<Feedback> geplaatstFeedback;
	@ManyToMany(mappedBy="sessiesWaarvoorIngeschreven",cascade=CascadeType.PERSIST) //Tussentabel!
	private ObservableList<Gebruiker> ingeschrevenGebruikers;
	@OneToMany
	@JoinColumn(name = "sessieId")
	private ObservableList<Gebruiker> aanwezigeGebruikers;
	
	//private boolean stuurtHerinnering;
	//private List<Herinnering> herinneringen; is dit nodig?

	
	//CONSTRUCTOR
	protected Sessie() {}
	public Sessie(String titel, String naamGastspreker,
			String lokaalCode, int MAX_CAPACITEIT,
			LocalDateTime startDatum, LocalDateTime eindDatum,
			String sessieAanmaker) {
		
		setTitel(titel);
		setNaamGastspreker(naamGastspreker);
		
		setLokaalCode(lokaalCode);
		setMAX_CAPACITEIT(MAX_CAPACITEIT);
		
		setStartDatum(startDatum);
		setEindDatum(eindDatum);
		
		setSessieAanmaker(sessieAanmaker);
		status = SessieStatus.AANGEMAAKT;
		
		gebruikteMedia = FXCollections.<Media>observableArrayList();
		geplaatsteAankondigingen = FXCollections.<Aankondiging>observableArrayList();
		geplaatstFeedback = FXCollections.<Feedback>observableArrayList();
		ingeschrevenGebruikers = FXCollections.<Gebruiker>observableArrayList();
		aanwezigeGebruikers = FXCollections.<Gebruiker>observableArrayList();
		//todo controle op datum
		/*
		if (DateUtils.addDays(new Date(), 1).before(startDatum)) {
			throw new IllegalArgumentException("De startdatum moet minstens een dag in de toekomst liggen");
		}
		if (!DateUtils.addMinutes(startDatum, 30).after(eindDatum)) {
			throw new IllegalArgumentException("De startdatum moet minstends 30 minuten voor de einddatum liggen");
		}
		*/
	}
	
	public Sessie(SessieDTO dto) {
		
		setTitel(dto.getTitel());
		setNaamGastspreker(dto.getNaamGastspreker());
		
		setLokaalCode(dto.getLokaalCode());
		setMAX_CAPACITEIT(dto.getMAX_CAPACITEIT());
		
		setStartDatum(dto.getStartDatum());
		setEindDatum(dto.getEindDatum());
		
		setSessieAanmaker(dto.getSessieAanmaker());
		status = SessieStatus.AANGEMAAKT;
		
		gebruikteMedia = FXCollections.<Media>observableArrayList();
		geplaatsteAankondigingen = FXCollections.<Aankondiging>observableArrayList();
		geplaatstFeedback = FXCollections.<Feedback>observableArrayList();
		ingeschrevenGebruikers = FXCollections.<Gebruiker>observableArrayList();
		aanwezigeGebruikers = FXCollections.<Gebruiker>observableArrayList();
	}

	// METHODS
	
	public String getNaamAanmaker() {
		return sessieAanmaker;
	}
	
	public String getAanwezigenOrVrijePlaatsen() {
		if(status == SessieStatus.AANGEMAAKT) {
			return "" + (MAX_CAPACITEIT - ingeschrevenGebruikers.size());
		}else {
			return "" + aanwezigeGebruikers.size();
		}
	}
	
	public void addInschrijving(Gebruiker gebruiker) {
		ingeschrevenGebruikers.add(gebruiker);
	}
	
	/**
	 * Initialiseert de lijst van herinneringen
	 */
	public void setHerinneringen(ObservableList<Herinnering> herinneringen)
	{
		this.herinneringen = herinneringen;
	}
	
	public void addAanwezigheid(Gebruiker gebruiker) {
		aanwezigeGebruikers.add(gebruiker);
	}
	
	public void addAankondiging(Aankondiging aankondiging) {
		geplaatsteAankondigingen.add(aankondiging);
	}

	/**
	 * Voegt 1 media toe aan de lijst van media
	 */
	public void addMedia(Media media) {
		gebruikteMedia.add(media);
	}

	/**
	 * Overschrijft de gebruikte media met gekregenmedia
	 */
	public void setMedia(ObservableList<Media> media) {
		gebruikteMedia = media;
	}

	/**
	 * Verwijdert 1 media uit de media lijst naargelang de doorgegeven parameter
	 */
	public void removeMedia(Media media) {
		gebruikteMedia.remove(media);
	}

	/**
	 * Zet de sessie open
	 */
	public void setGeopend() {
		status = SessieStatus.GEOPEND;
	}
	
	public void setAangemaakt() {
		status = SessieStatus.AANGEMAAKT;
	}
	
	public void setGestart() {
		status = SessieStatus.GESTART;
	}
	
	public void setGesloten() {
		status = SessieStatus.GESLOTEN;
	}
	
	public boolean isGeopend() {
		if(status == SessieStatus.GEOPEND) {
			return true;
		}
		return false;
	}
	
	public boolean isGesloten() {
		if(status == SessieStatus.GESLOTEN) {
			return true;
		}
		return false;
	}
	
	public boolean isGestart() {
		if(status == SessieStatus.GESTART) {
			return true;
		}
		return false;
	}
	
	public boolean isAangemaakt() {
		if(status == SessieStatus.AANGEMAAKT) {
			return true;
		}
		return false;
	}
	
	//sessie beheren/aanpassen methodes
		public void editSessie(SessieDTO dto) {
			if (!isAangemaakt()) {
				throw new IllegalArgumentException("De sessie is al geopend. Geen wijzigingen meer mogelijk");
			}
			setTitel(dto.getTitel());
			setNaamGastspreker(dto.getNaamGastspreker());
			setLokaalCode(dto.getLokaalCode());
			setMAX_CAPACITEIT(dto.getMAX_CAPACITEIT());
			setStartDatum(dto.getStartDatum());
			setEindDatum(dto.getEindDatum());

			switch(dto.getStatus()) {
			case"AANGEMAAKT":
				this.status = SessieStatus.AANGEMAAKT;
				break;
			case"GEOPEND":
				this.status = SessieStatus.GEOPEND;
				break;
			case"GESTART":
				this.status = SessieStatus.GESTART;
				break;
			case"GESLOTEN":
				this.status = SessieStatus.GESLOTEN;
				break;
			default:
				throw new IllegalArgumentException("Geen geldige status!");
			}
		}

	// Getters and setters

	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		if (titel.isBlank() || titel.isEmpty()) {
			throw new IllegalArgumentException("De titel mag niet leeg zijn");
		}
		this.titel = titel;
	}
	public String getSessieAanmaker() {
		return sessieAanmaker;
	}
	public void setSessieAanmaker(String sessieAanmaker) {
		if(sessieAanmaker.isBlank()||sessieAanmaker.isEmpty()){
			throw new IllegalArgumentException("De aanmaker mag niet leeg zijn");
		}
		this.sessieAanmaker = sessieAanmaker;
	}
	public LocalDateTime getStartDatum() {
		return startDatum;
	}
	protected void setStartDatum(LocalDateTime startDatum) {
		if (startDatum == null) {
			throw new IllegalArgumentException("De startdatum moet ingevuld worden");
		}
		this.startDatum = startDatum;
	}
	public LocalDateTime getEindDatum() {
		return eindDatum;
	}
	protected void setEindDatum(LocalDateTime eindDatum) {
		if (eindDatum == null) {
			throw new IllegalArgumentException("De einddatum moet ingevuld worden");
		}
		this.eindDatum = eindDatum;
	}
	public String getNaamGastspreker() {
		return naamGastspreker;
	}
	public void setNaamGastspreker(String naamGastspreker) {
		if(naamGastspreker.isBlank()||naamGastspreker.isEmpty()){
			throw new IllegalArgumentException("De gastspreker mag niet leeg zijn");
		}
		this.naamGastspreker = naamGastspreker;
	}
	public String getLokaalCode() {
		return lokaalCode;
	}
	public void setLokaalCode(String lokaalCode) {
		if (lokaalCode.isBlank() || lokaalCode.isEmpty()) {
			throw new IllegalArgumentException("het lokaal moet ingevuld worden");
		}
		this.lokaalCode = lokaalCode;
	}
	public ObservableList<Media> getGebruikteMedia() {
		return gebruikteMedia;
	}
	public void setGebruikteMedia(ObservableList<Media> gebruikteMedia) {
		this.gebruikteMedia = gebruikteMedia;
	}
	public ObservableList<Aankondiging> getGeplaatsteAankondigingen() {
		return geplaatsteAankondigingen;
	}
	public void setGeplaatsteAankondigingen(ObservableList<Aankondiging> geplaatsteAankondigingen) {
		this.geplaatsteAankondigingen = geplaatsteAankondigingen;
	}
	public ObservableList<Feedback> getGeplaatstFeedback() {
		return geplaatstFeedback;
	}
	public void setGeplaatstFeedback(ObservableList<Feedback> geplaatstFeedback) {
		this.geplaatstFeedback = geplaatstFeedback;
	}
	public ObservableList<Gebruiker> getIngeschrevenGebruikers() {
		return ingeschrevenGebruikers;
	}
	public void setIngeschrevenGebruikers(ObservableList<Gebruiker> ingeschrevenGebruikers) {
		this.ingeschrevenGebruikers = ingeschrevenGebruikers;
	}
	public ObservableList<Gebruiker> getAanwezigeGebruikers() {
		return aanwezigeGebruikers;
	}
	public void setAanwezigeGebruikers(ObservableList<Gebruiker> aanwezigeGebruikers) {
		this.aanwezigeGebruikers = aanwezigeGebruikers;
	}
	public int getMAX_CAPACITEIT() {
		return MAX_CAPACITEIT;
	}
	public void setMAX_CAPACITEIT(int max) {
		if (max <= 0) {
			throw new IllegalArgumentException("Max capaciteit moet ingevuld worden");
		}
		this.MAX_CAPACITEIT = max;
	}
	public SessieStatus getStatus() {
		return status;
	}
	public void setStatus(SessieStatus status) {
		this.status = status;
	}
}
