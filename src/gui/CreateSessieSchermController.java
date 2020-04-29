package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.SessieDTO;
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
	private Button btnCancel, btnToevoegen;
	
	@FXML
	private TextField txtTitel, txtGastspreker, txtMaxCapaciteit, txtLokaal;
	
	@FXML
	private DatePicker dateStartDatum, dateEindDatum;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
    private void handleCancelAction(ActionEvent event) throws IOException {
        sluitScherm(btnCancel);
    }
	
	@FXML
    private void handleSessieToevoegenAction(ActionEvent event) throws IOException {
		
		SessieDTO dto = new SessieDTO();

		dto.setTitel( txtTitel.getText() );
		dto.setStartDatum( dateStartDatum.getValue().atStartOfDay() );
		dto.setEindDatum( dateEindDatum.getValue().atStartOfDay() );
		dto.setNaamGastspreker( txtGastspreker.getText() );
		dto.setMAX_CAPACITEIT( Integer.parseInt(txtMaxCapaciteit.getText()) );
		dto.setLokaalCode( txtLokaal.getText() );
		
		getDC().addSessieToGeselecteerdeSessieKalender(dto);
        
        sluitScherm(btnToevoegen);
    }
}