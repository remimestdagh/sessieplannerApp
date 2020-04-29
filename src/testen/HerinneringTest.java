package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Feedback;
import domein.Herinnering;

public class HerinneringTest {
private Herinnering herinnering1;
	
	@BeforeEach
	public void before() {
		herinnering1 = new Herinnering("Vergeet de kaas niet",30);	
	}
	
	@ParameterizedTest
	@ValueSource(strings= {" ","","    "})
	public void legeInhoudGeeftException(String inhoud) {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Herinnering(inhoud,30);
		});
		
	}

	@ParameterizedTest
	@ValueSource(ints= {-10,0,-10000})
	public void legeOfNegatiefTijdstipGeeftException(int tijdstip) {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Herinnering("Super interessant!",tijdstip);
		});
		
	}

}
