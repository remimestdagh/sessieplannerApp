package domein;

import java.time.LocalDateTime;

public class SessieDTO {

	// PARAMETERS

	private String titel;
	private String naamGastspreker;
	private String lokaalCode;
	private int MAX_CAPACITEIT;
	private LocalDateTime startDatum;
	private LocalDateTime eindDatum;
	private String sessieAanmaker;
	private SessieStatus status;

	// CONSTRUCTOR

	public SessieDTO() {}

	// Getters and setters

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getSessieAanmaker() {
		return sessieAanmaker;
	}

	public void setSessieAanmaker(String sessieAanmaker) {
		this.sessieAanmaker = sessieAanmaker;
	}

	public LocalDateTime getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(LocalDateTime startDatum) {
		this.startDatum = startDatum;
	}

	public LocalDateTime getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(LocalDateTime eindDatum) {
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
		this.lokaalCode = lokaalCode;
	}

	public int getMAX_CAPACITEIT() {
		return MAX_CAPACITEIT;
	}

	public void setMAX_CAPACITEIT(int max) {
		this.MAX_CAPACITEIT = max;
	}

	public SessieStatus getStatus() {
		return status;
	}

	public void setStatus(SessieStatus status) {
		this.status = status;
	}
}
