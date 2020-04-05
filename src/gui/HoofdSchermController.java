package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HoofdSchermController extends SchermController implements Initializable{
	
	@FXML
    private Text textName;
	
	@FXML
	private Button btnBeheerGebruikers;
	
	@FXML
	private Button btnStat;
	
	@FXML
	private Button btnKalender;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		textName.setText(dc.getNaamIngelogdeGebruiker());
	}
	
	@FXML
    private void handleBeheerGebruikersButtonAction(ActionEvent event) throws IOException {
		if(getDC().gebruikerIsHoofdverantwoordelijke()) {
        	
        	verranderScherm(btnBeheerGebruikers, "BeheerGebruikers");
		}
	}
	
	@FXML
    private void handleStatistiekButtonAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnStat, "Statistiek");
	}
	
	@FXML
    private void handleSessieKalenderButtonAction(ActionEvent event) throws IOException {
        
        if(getDC().gebruikerIsHoofdverantwoordelijke()) {
        	verranderScherm(btnKalender, "SessieKalenders");
        }else {
        	verranderScherm(btnKalender, "VerantwoordelijkeBeheerSessies");
        }
	}
	
}