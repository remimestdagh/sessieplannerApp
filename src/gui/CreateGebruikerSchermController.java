package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import domein.DomeinController;
import domein.GebruikerDTO;
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
	private ChoiceBox<String> cbType, cbStatus;

	@FXML
	private Label errorText;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);

		cbStatus.getItems().addAll("ACTIEF", "GEBLOKKEERD", "NIET_ACTIEF");
		cbStatus.setValue("ACTIEF");

		cbType.getItems().addAll("HoofdVerantwoordelijke", "Verantwoordelijke", "Gewone_Gebruiker");
		cbType.setValue("Gewone_Gebruiker");
	}

	@FXML
	private void handleCreateGebruikerAction(ActionEvent event) throws IOException {

		try {
			GebruikerDTO dto = new GebruikerDTO();

			dto.setNaam(txNaam.getText());
			dto.setNaamChamilo(txChamilo.getText());
			dto.setEmailadres(txEmail.getText());
			dto.setWachtwoord(txPassword.getText());
			dto.setStatus((String) cbStatus.getValue());
			dto.setType((String) cbType.getValue());

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