package testen;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Gebruiker;
import domein.Sessie;

public class GebruikerTest {
	private Gebruiker gebruiker1;

	@BeforeEach
	public void before() {
		gebruiker1 = new Gebruiker("Boris Hamster", "bhamstr1", "boris.hamster@student.hogent.be", "Kaas1234!",
				"ACTIEF", "GewoneGebruiker");
		gebruiker1.addInschrijving(new Sessie("Scrum tactics", "gastspreker", "lokaalcode", 100, LocalDateTime.now().plusDays(1),
				LocalDateTime.now().plusDays(1).plusHours(1), "aanmaker"));

	}

	@ParameterizedTest
	@ValueSource(strings = { " ", "", "   " })
	public void legeNaamGeeftException(String naam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			gebruiker1.setNaam(naam);
		});

	}

	@ParameterizedTest
	@ValueSource(strings = { " ", "", "   " })
	public void legeGebruikernaamGeeftException(String naam) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			gebruiker1.setNaamChamilo(naam);
		});

	}
	@ParameterizedTest
	@ValueSource(strings = { " ", "", "   " })
	public void legeEmailGeeftException(String email) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			gebruiker1.setEmailadres(email);
		});

	}
	

	@ParameterizedTest
	@ValueSource(strings = { " ", "", "   " })
	public void leegTypeGeeftException(String type) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Gebruiker("Boris Hamster", "bhamstr1", "boris.hamster@student.hogent.be", "Kaas1234!", "ACTIEF", "");
		});

	}
	public void correcteAanmaakGebruikerGeeftGeenException() {
		Assertions.assertDoesNotThrow(()->{
			new Gebruiker("Boris Bever", "bbever1", "boris.bever@student.hogent.be", "Kaas1234!",
					"ACTIEF", "Gewone_Gebruiker");
		});
	}
	public void inschrijvenVoorSessiePastLijstAan() {
		Assertions.assertEquals(1, gebruiker1.getSessiesWaarvoorIngeschreven().size());
		gebruiker1.addInschrijving(new Sessie("titel","gastspreker", "lokaalcode", 100, LocalDateTime.now().plusDays(1),
				LocalDateTime.now().plusDays(1).plusHours(1), "aanmaker"));
		Assertions.assertEquals(2, gebruiker1.getSessiesWaarvoorIngeschreven().size());
	}
	public void aanwezigeSessieToevoegenPastLijstAan() {
		Assertions.assertEquals(0, gebruiker1.getSessiesWaarvoorAanwezig().size());
		gebruiker1.addInschrijving(new Sessie("titel","gastspreker", "lokaalcode", 100, LocalDateTime.now().plusDays(1),
				LocalDateTime.now().plusDays(1).plusHours(1), "aanmaker"));
		Assertions.assertEquals(1, gebruiker1.getSessiesWaarvoorAanwezig().size());
		
	}

}
