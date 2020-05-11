package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Feedback;

public class FeedbackTest {
	private Feedback feedback1;
	/*
	@BeforeEach
	public void before() {
		feedback1 = new Feedback("Boris Bever","Super interessante sessie");	
	}
	@ParameterizedTest
	@ValueSource(strings= {" ","","    "})
	public void legeInhoudGeeftException(String inhoud) {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Feedback("Boris Bever",inhoud);
		});
		
	}

	@ParameterizedTest
	@ValueSource(strings= {" ","","    "})
	public void legeAuteurGeeftException(String auteur) {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Feedback(auteur,"Super interessant!");
		});
		
	}*/
}
