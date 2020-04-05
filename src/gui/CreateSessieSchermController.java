package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateSessieSchermController extends SchermController implements Initializable{
	
	@FXML
	private Button btnCancel;
	
	@FXML
	private TextField txtTitel;
	
	@FXML
	private TextField txtGastspreker;
	
	@FXML
	private TextField txtMaxCapaciteit;
	
	@FXML
	private TextField txtLokaal;
	
	@FXML
	private DatePicker dateStartDatum;
	
	@FXML
	private DatePicker dateEindDatum;
	
	@FXML
	private Button btnToevoegen;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
    private void handleCancelAction(ActionEvent event) throws IOException {
        
        if(getDC().gebruikerIsHoofdverantwoordelijke()) {
        	verranderScherm(btnCancel, "SessieKalender");
        }else {
        	verranderScherm(btnCancel, "VerantwoordelijkeBeheerSessies");
        }
    }
	
	@FXML
    private void handleSessieToevoegenAction(ActionEvent event) throws IOException {
		String titel = txtTitel.getText();
		String gastspreker = txtGastspreker.getText();
		int maxCapaciteit = Integer.parseInt(txtMaxCapaciteit.getText());
		String lokaal = txtLokaal.getText();
		LocalDateTime startDatum = dateStartDatum.getValue().atStartOfDay();
		LocalDateTime eindDatum = dateEindDatum.getValue().atStartOfDay();
		
		getDC().addSessieToGeselecteerdeSessieKalender(titel, gastspreker, lokaal, maxCapaciteit, startDatum, eindDatum);
        
        if(getDC().gebruikerIsHoofdverantwoordelijke()) {
        	verranderScherm(btnToevoegen, "SessieKalender");
        }else {
        	verranderScherm(btnToevoegen, "VerantwoordelijkeBeheerSessies");
        }
    }
}