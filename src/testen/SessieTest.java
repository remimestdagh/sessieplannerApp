package testen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Sessie;

public class SessieTest {

	private Sessie sessies;
	
	@ParameterizedTest
	@ValueSource(strings = {"       ",""," "})
	public void sessieAanmakenLegeTitelGeeftException(String titel) {
		Assertions.assertThrows(IllegalArgumentException.class,()
				->new Sessie(titel,"gastspreker","lokaalcode",100,LocalDateTime.now(),LocalDateTime.now(),"aanmaker"));
		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {})
	public void sessieAanmakenGeenDatumGeeftException(String date) {
		 //todo
	}
	@ParameterizedTest
	@ValueSource(strings = {"       ",""," "})
	public void sessieAanmakenLegeLokaalCodeGeeftException(String lokaalcode) {
		Assertions.assertThrows(IllegalArgumentException.class,()
				->new Sessie("titel","gastspreker",lokaalcode,100,LocalDateTime.now(),LocalDateTime.now(),"aanmaker"));
		
	}
	@ParameterizedTest
	@ValueSource(strings = {"       ",""," "})
	public void sessieAanmakenLegeAanmakerGeeftException(String aanmaker) {
		Assertions.assertThrows(IllegalArgumentException.class,()
				->new Sessie("titel","gastspreker","lokaalcode",100,LocalDateTime.now(),LocalDateTime.now(),aanmaker));
		
	}
	
	
	@ParameterizedTest
	@ValueSource(ints= {-1,0,-100})
	public void sessieAanmakenLegeMaxCapacitietGeeftException(int getal) {
		Assertions.assertThrows(IllegalArgumentException.class,()
				->new Sessie("titel","gastspreker","lokaalcode",getal,LocalDateTime.now(),LocalDateTime.now(),"aanmaker"));
		
	}
	
	
	
}
