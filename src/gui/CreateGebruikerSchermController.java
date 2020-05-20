package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import domein.DomeinController;
import domein.GebruikerDTO;
import domein.GebruikerStatus;
import domein.GebruikerType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateGebruikerSchermController extends SchermController implements Initializable {

	@FXML
	private Button btnCreate, btnCancel;

	@FXML
	private TextField txNaam, txChamilo, txEmail, txPassword;

	@FXML
	private ChoiceBox<GebruikerType> cbType;
	
	@FXML
	private ChoiceBox<GebruikerStatus> cbStatus;

	@FXML
	private Label errorText;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);

		cbStatus.getItems().setAll(GebruikerStatus.values());
		cbStatus.setValue(GebruikerStatus.ACTIEF);

		cbType.getItems().setAll(GebruikerType.values());
		cbType.setValue(GebruikerType.GewoneGebruiker);
	}

	@FXML
	private void handleCreateGebruikerAction(ActionEvent event) throws IOException {

		try {
			GebruikerDTO dto = new GebruikerDTO();

			dto.setNaam(txNaam.getText());
			dto.setNaamChamilo(txChamilo.getText());
			dto.setEmailadres(txEmail.getText());
			dto.setWachtwoord(txPassword.getText());
			dto.setStatus((GebruikerStatus) cbStatus.getValue());
			dto.setType((GebruikerType) cbType.getValue());

			getDC().addGebruiker(dto);
			sluitScherm(btnCreate);
		} catch (IllegalArgumentException e) {
			errorText.setText(e.getMessage());
		}

	}

	@FXML
	private void handleCancelAction(ActionEvent event) throws IOException {
		sluitScherm(btnCancel);
	}

}