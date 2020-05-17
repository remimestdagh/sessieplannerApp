package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;


import domein.DomeinController;
import javafx.application.Platform;
import javafx.css.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HoofdSchermController extends SchermController implements Initializable{

	
	@FXML
    private Text textName;
	
	@FXML
	private Button btnBeheerGebruikers;
	
	@FXML
	private Button btnStat;
	
	@FXML
	private Button btnKalender;
	
	@FXML
	private Button btnLogUit;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeButtonTooltips();
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		textName.setText(dc.getNaamIngelogdeGebruiker());
	}
	
	/**
	 * Button event om naar beheer van gebruikers te gaan.
	 */
	@FXML
    private void handleBeheerGebruikersButtonAction(ActionEvent event) throws IOException {
		if(getDC().gebruikerIsHoofdverantwoordelijke()) {
        	
        	verranderScherm(btnBeheerGebruikers, "GebruikerBeheer");
		}
	}
	
	/**
	 * Button event om naar statistieken te gaan
	 */
	@FXML
    private void handleStatistiekButtonAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnStat, "Statistiek");
	}
	
	/**
	 * Button event om naar sessiekalenders te gaan
	 */
	@FXML
    private void handleSessieKalenderButtonAction(ActionEvent event) throws IOException {
        
        if(getDC().gebruikerIsHoofdverantwoordelijke()) {
        	verranderScherm(btnKalender, "SessieKalenderBeheer");
        }else {
        	//verranderScherm(btnKalender, "SessieKalender");
        	verranderScherm(btnKalender, "SessieKalenderBeheer");
        }
	}
	
	
	/**
	 * Button event om uit te loggen
	 */
	@FXML
    private void logUitButtonAction(ActionEvent event) throws IOException {
        	verranderScherm(btnLogUit, "Login");
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
		Tooltip tooltipBeheerGebruikers = new Tooltip("Hier kunt u de gegevens van bestaande gebruikers aanpassen.");
		btnBeheerGebruikers.setTooltip(tooltipBeheerGebruikers);
		tooltipBeheerGebruikers.setShowDelay(Duration.seconds(0.2));
		tooltipBeheerGebruikers.setStyle(tooltipStyling);
		
		//button voor beheer statistieken
		Tooltip tooltipStatistieken = new Tooltip("Hier kunt u gebruikers, aanwezigheden per sessie en een PowerBI overzicht bekijken.");
		btnStat.setTooltip(tooltipStatistieken);
		tooltipStatistieken.setShowDelay(Duration.seconds(0.2));
		tooltipStatistieken.setStyle(tooltipStyling);
		
		//button voor beheer gebruikers
		Tooltip tooltipKalender = new Tooltip("Hier kunt u sessiekalenders aanpassen, aanmaken en verwijderen alsook de daartoe horende sessies beheren.");
		btnKalender.setTooltip(tooltipKalender);
		tooltipKalender.setShowDelay(Duration.seconds(0.2));
		tooltipKalender.setStyle(tooltipStyling);
		

	}
	
}