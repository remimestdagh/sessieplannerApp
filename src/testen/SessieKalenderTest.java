package testen;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Herinnering;
import domein.Sessie;
import domein.SessieKalender;

public class SessieKalenderTest {
	private SessieKalender sessieKalender1;

	@BeforeEach
	public void before() {
		sessieKalender1 = new SessieKalender("2023-2024",LocalDateTime.of(2023,Month.SEPTEMBER,21,19,30,40),
				LocalDateTime.of(2024,Month.SEPTEMBER,21,19,30,40));	
	}
	@Test
	public void nieuweKalenderTest() {
		SessieKalender sessieKalender = new SessieKalender("2023-2024",LocalDateTime.of(2023,Month.SEPTEMBER,21,19,30,40),
				LocalDateTime.of(2024,Month.SEPTEMBER,21,19,30,40));
		Assertions.assertNotNull(sessieKalender.getSessieList());
		Assertions.assertNotNull(sessieKalender);
	}
	@Test
	public void sessieToevoegen() {
		this.sessieKalender1.addSessie(new Sessie("Scrum tactics", "gastspreker", "lokaalcode", 100, LocalDateTime.of(2023,Month.OCTOBER,21,3,30),
				LocalDateTime.of(2023,Month.OCTOBER,21,5,30), "aanmaker"));
		Assertions.assertEquals(1, this.sessieKalender1.getSessieList().size());
	}
	@Test
	public void sessieVerwijderen() {
		Sessie sessie = new Sessie("Scrum tactics", "gastspreker", "lokaalcode", 100, LocalDateTime.of(2023,Month.OCTOBER,21,3,30),
				LocalDateTime.of(2023,Month.OCTOBER,21,5,30), "aanmaker");
		this.sessieKalender1.addSessie(sessie);
		Assertions.assertEquals(1, this.sessieKalender1.getSessieList().size());
		this.sessieKalender1.removeSessie(sessie);
		Assertions.assertEquals(0, this.sessieKalender1.getSessieList().size());
	}
	
	@ParameterizedTest
	@ValueSource(strings= {""," ","  "})
	public void academiejaarLeegGeeftException(String academiejaar) {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new SessieKalender(academiejaar, LocalDateTime.of(2023,Month.SEPTEMBER,21,19,30,40),
					LocalDateTime.of(2024,Month.SEPTEMBER,21,19,30,40));
		});
	}
}
