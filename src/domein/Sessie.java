package domein;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sessie {
	// PARAMETERS
	private String titel;
	private String naamGastspreker;
	private String lokaalCode;
	
	private int MAX_CAPACITEIT;
	
	private Date startDatum;
	private Date eindDatum;
	
	private Gebruiker sessieAanmaker;
	private SessieStatus status;// Aangemaakt, Geopent, Gestart, Gesloten
	
	private List<Media> gebruikteMedia;
	private List<Aankondiging> geplaatsteAankondigingen;
	private List<Feedback> geplaatstFeedback;
	private List<Gebruiker> ingeschrevenGebruikers;
	private List<Gebruiker> aanwezigeGebruikers;
	
	//private boolean stuurtHerinnering;
	//private List<Herinnering> herinneringen; is dit nodig?

	public Sessie(String titel, String naamGastspreker,
			String lokaalCode, int MAX_CAPACITEIT,
			Date startDatum, Date eindDatum,
			Gebruiker sessieAanmaker) {
		
		setTitel(titel);
		setNaamGastspreker(naamGastspreker);
		
		setLokaalCode(lokaalCode);
		setMAX_CAPACITEIT(MAX_CAPACITEIT);
		
		setStartDatum(startDatum);
		setEindDatum(eindDatum);
		
		this.sessieAanmaker = sessieAanmaker;
		status = SessieStatus.AANGEMAAKT;
		
		gebruikteMedia = new ArrayList<>();
		geplaatsteAankondigingen = new ArrayList<>();
		geplaatstFeedback = new ArrayList<>();
		ingeschrevenGebruikers = new ArrayList<>();
		aanwezigeGebruikers = new ArrayList<>();
		
		/*
		if (DateUtils.addDays(new Date(), 1).before(startDatum)) {
			throw new IllegalArgumentException("De startdatum moet minstens een dag in de toekomst liggen");
		}
		if (!DateUtils.addMinutes(startDatum, 30).after(eindDatum)) {
			throw new IllegalArgumentException("De startdatum moet minstends 30 minuten voor de einddatum liggen");
		}
		*/
	}

	// METHODS
	
	public String getNaamVerantwoordelijke() {
		return sessieAanmaker.getNaam();
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
	public void setMedia(List<Media> media) {
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
		public void editSessie(String titel, String naamGastspreker, String lokaalCode, int plaatsen, String startDatum,String eindDatum, String status) {
			if (!isAangemaakt()) {
				throw new IllegalArgumentException("De sessie is al geopend. Geen wijzigingen meer mogelijk");
			}
			setTitel(titel);
			setNaamGastspreker(naamGastspreker);
			setLokaalCode(lokaalCode);
			setMAX_CAPACITEIT(plaatsen);
			setStartDatum(new Date(startDatum));
			setEindDatum(new Date(eindDatum));

			switch(status) {
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
	public Gebruiker getSessieAanmaker() {
		return sessieAanmaker;
	}
	public void setSessieAanmaker(Gebruiker sessieAanmaker) {
		this.sessieAanmaker = sessieAanmaker;
	}
	public Date getStartDatum() {
		return startDatum;
	}
	protected void setStartDatum(Date startDatum) {
		if (startDatum == null) {
			throw new IllegalArgumentException("De startdatum moet ingevuld worden");
		}
		this.startDatum = startDatum;
	}
	public Date getEindDatum() {
		return eindDatum;
	}
	protected void setEindDatum(Date eindDatum) {
		if (eindDatum == null) {
			throw new IllegalArgumentException("De einddatum moet ingevuld worden");
		}
		this.eindDatum = eindDatum;
	}
	public String getNaamGastspreker() {
		return naamGastspreker;
	}
	public void setNaamGastspreker(String naamGastspreker) {
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
	public List<Media> getGebruikteMedia() {
		return gebruikteMedia;
	}
	public void setGebruikteMedia(List<Media> gebruikteMedia) {
		this.gebruikteMedia = gebruikteMedia;
	}
	public List<Aankondiging> getGeplaatsteAankondigingen() {
		return geplaatsteAankondigingen;
	}
	public void setGeplaatsteAankondigingen(List<Aankondiging> geplaatsteAankondigingen) {
		this.geplaatsteAankondigingen = geplaatsteAankondigingen;
	}
	public List<Feedback> getGeplaatstFeedback() {
		return geplaatstFeedback;
	}
	public void setGeplaatstFeedback(List<Feedback> geplaatstFeedback) {
		this.geplaatstFeedback = geplaatstFeedback;
	}
	public List<Gebruiker> getIngeschrevenGebruikers() {
		return ingeschrevenGebruikers;
	}
	public void setIngeschrevenGebruikers(List<Gebruiker> ingeschrevenGebruikers) {
		this.ingeschrevenGebruikers = ingeschrevenGebruikers;
	}
	public List<Gebruiker> getAanwezigeGebruikers() {
		return aanwezigeGebruikers;
	}
	public void setAanwezigeGebruikers(List<Gebruiker> aanwezigeGebruikers) {
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
