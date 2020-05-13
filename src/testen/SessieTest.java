package testen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Sessie;
import domein.SessieDTO;

public class SessieTest {

	private Sessie sessie1;

	@BeforeEach
	public void before() {
		sessie1 = new Sessie("Scrum tactics", "gastspreker", "lokaalcode", 100, LocalDateTime.now().plusDays(1),
				LocalDateTime.now().plusDays(1).plusHours(1), "aanmaker");
	}

	@ParameterizedTest
	@ValueSource(strings = { "       ", "", " " })
	public void sessieAanmakenLegeTitelGeeftException(String titel) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Sessie(titel, "gastspreker", "lokaalcode",
				100, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1), "aanmaker"));

	}

	public void sessieAanmakenGeenBeginDatumGeeftException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Sessie("titel", "gastspreker", "lokaalcode",
				100, null, LocalDateTime.now().plusDays(1).plusHours(1), "aanmaker"));
	}

	public void sessieAanmakenGeenEindDatumGeeftException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Sessie("titel", "gastspreker", "lokaalcode",
				100, LocalDateTime.now().plusHours(1), null, "aanmaker"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "       ", "", " " })
	public void sessieAanmakenLegeLokaalCodeGeeftException(String lokaalcode) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Sessie("titel", "gastspreker", lokaalcode,
				100, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1), "aanmaker"));

	}

	@ParameterizedTest
	@ValueSource(strings = { "       ", "", " " })
	public void sessieAanmakenLegeAanmakerGeeftException(String aanmaker) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Sessie("titel", "gastspreker", "lokaalcode",
				100, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1), aanmaker));

	}

	@ParameterizedTest
	@ValueSource(ints = { -1, 0, -100 })
	public void sessieAanmakenLegeMaxCapacitietGeeftException(int getal) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Sessie("titel", "gastspreker", "lokaalcode",
				getal, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1), "aanmaker"));

	}

	@Test
	public void sessieAanpassenMagNietSessieOpenen() {
		sessie1.setGeopend();
		// todo beter formaat van datums
		SessieDTO dtojamin = new SessieDTO();
		dtojamin.setTitel("nieuwe titel");
		dtojamin.setNaamGastspreker("nieuwe gastSpreker");
		dtojamin.setLokaalCode("nieuwe code");
		dtojamin.setStartDatum(LocalDateTime.now().plusDays(5));
		dtojamin.setEindDatum(LocalDateTime.now().plusDays(5).plusHours(5));
		dtojamin.setStatus("GEOPEND");
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> sessie1.editSessie(dtojamin));
	}
	@Test
	public void sessieAanmakenJuisteManierGeenErrors() {
		new Sessie("nieuwe titel",
				"nieuwe gastspreker", "nieuwe code", 100, LocalDateTime.now().plusDays(2),LocalDateTime.now().plusDays(2).plusHours(2),"aanmaker");
		
	}
	/*
	@Test
	public void sessieAanmakenDatumsNietMinstens1DagInToekomstGeeftException(String datum1) {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Sessie("scrum tactics", "gastspreker", "lokaalcode",
				100, LocalDateTime.now(), LocalDateTime.now().plusHours(1), "aanmaker");
		});
	}*/
	

}
