package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StatistiekSchermController extends SchermController implements Initializable{
	
	@FXML
	private Button btnHoofdmenu;
	
	@FXML
	private Button btnGebruikers;
	
	@FXML
	private Button btnGeavanceerd;
	
	@FXML
	private Button btnSessies;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeButtonTooltips();
	}
	
	@FXML
    private void handleHoofdmenuAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnHoofdmenu, "Hoofd");
    }
	
	@FXML
    private void handleGebruikersAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnGebruikers, "GebruikersStatistiek");
    }
	
	@FXML
    private void handleSessiesAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnSessies, "SessiesStatistiek");
    }
	
	private void initializeButtonTooltips()
	{
		//styling
		String tooltipStyling = "-fx-background: rgba(200,200,200,1);\r\n" + 
				"    -fx-text-fill: white;\r\n" + 
				"    -fx-background-color: rgba(255,255,255,0.1);\r\n" + 
				"    -fx-background-radius: 0px;\r\n" + 
				"	 -fx-border-color: white;\r\n" + 
				"    -fx-border-width: 1px;\r\n"+
				"    -fx-padding: 0.667em 0.75em 0.667em 0.75em; /* 10px */\r\n" + 
				"    -fx-font-size: 0.85em;";
		
		//button voor beheer gebruikers
		Tooltip tooltipGeavanceerd = new Tooltip("Hier kunt u powerBI statistieken raadplegen");
		btnGeavanceerd.setTooltip(tooltipGeavanceerd);
		tooltipGeavanceerd.setShowDelay(Duration.seconds(0.2));
		tooltipGeavanceerd.setStyle(tooltipStyling);
		
		//button voor beheer statistieken
		Tooltip tooltipGebruiker = new Tooltip("Hier kunt u per gebruiker de bijgewoonde sessies bekijken");
		btnGebruikers.setTooltip(tooltipGebruiker);
		tooltipGebruiker.setShowDelay(Duration.seconds(0.2));
		tooltipGebruiker.setStyle(tooltipStyling);
		
		//button voor beheer gebruikers
		Tooltip tooltipSessies = new Tooltip("Hier kunt u per sessie de gebruikers die ze bijwoonden bekijken.");
		btnSessies.setTooltip(tooltipSessies);
		tooltipSessies.setShowDelay(Duration.seconds(0.2));
		tooltipSessies.setStyle(tooltipStyling);
		

	}
}