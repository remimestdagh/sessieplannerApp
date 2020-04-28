package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.GebruikerDTO;
import domein.IGebruiker;
import domein.Sessie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BeheerGebruikerSchermController extends SchermController implements Initializable {

	@FXML
	private Button btnTerug, btnEdit;

	@FXML
	private TextField txtNaam, txtChamilo, txtEmail;

	@FXML
	private ChoiceBox cbStatus, cbType;

	@FXML
	private TableView tblView;

	@FXML
	private Label lblIntro, lblError;

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);

		maakSessieTable(tblView, getDC().getSessiesfromGeselecteerdeGebruiker());

		IGebruiker gebruiker = dc.getGeselecteerdeGebruiker();

		cbStatus.getItems().addAll("ACTIEF", "GEBLOKKEERD", "NIET_ACTIEF");
		cbStatus.setValue(gebruiker.getStatus().toString());

		cbType.getItems().addAll("HoofdVerantwoordelijke", "Verantwoordelijke", "Gewone_Gebruiker");
		cbType.setValue(gebruiker.getType().toString());

		txtNaam.setText(gebruiker.getNaam());
		txtChamilo.setText(gebruiker.getNaamChamilo());
		txtEmail.setText(gebruiker.getEmailadres());

		lblIntro.setText("Sessies waarvoor " + txtNaam.getText() + " aanwezig was:");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void handleEditAction(ActionEvent event) throws IOException {
		try {
			GebruikerDTO dto = new GebruikerDTO();

			dto.setNaam(txtNaam.getText());
			dto.setNaamChamilo(txtChamilo.getText());
			dto.setEmailadres(txtEmail.getText());
			dto.setStatus((String) cbStatus.getValue());
			dto.setType((String) cbType.getValue());

			getDC().editGeselecteerdeGebruiker(dto);
			
		} catch (IllegalArgumentException e) {
			lblError.setText(e.getMessage());
		}
	}
}
