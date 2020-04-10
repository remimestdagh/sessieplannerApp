package testen;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Aankondiging;

public class AankondigingTest {
	private Aankondiging aankondiging;
	
	
	@BeforeEach
	public void before() {
		aankondiging = new Aankondiging("Welkom in de sessie","Kurt Koebeest", new Date());
		
	}
	
	public void nieuweAankondigingAanmakenZonderErrors() {
		Assertions.assertDoesNotThrow(()->{
			new Aankondiging("Hallo! Welkom op de sessie yeah!","Hank Horse",new Date());
		});
	}
	@ParameterizedTest
	@ValueSource(strings= {"  ",""," ","       "})
	public void legeInhoudGeeftException(String inhoud) {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			aankondiging.setInhoud(inhoud);
		});
		
	}
	@ParameterizedTest
	@ValueSource(strings= {"  ",""," ","       "})
	public void legeAuteurGeeftException(String auteur) {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			aankondiging.setAuteur(auteur);
		});
		
	}

}
