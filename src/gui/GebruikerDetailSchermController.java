package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.concurrent.Flow.Subscriber;

import domein.DomeinController;
import domein.GebruikerDTO;
import domein.GebruikerStatus;
import domein.GebruikerType;
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

public class GebruikerDetailSchermController extends SchermController implements Initializable, PropertyChangeListener {

	@FXML
	private Button btnTerug, btnEdit;

	@FXML
	private TextField txtNaam, txtChamilo, txtEmail;

	@FXML
	private ChoiceBox<GebruikerType> cbType;
	
	@FXML
	private ChoiceBox<GebruikerStatus> cbStatus;

	@FXML
	private TableView tblView;

	@FXML
	private Label lblIntro, lblError;

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		dc.addGebruikerListener(this);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


	}

	/**
	 * Veranderen van een geselecteerde gebruiker's data.
	 */
	@FXML
	private void handleEditAction(ActionEvent event) throws IOException {
		try {
			GebruikerDTO dto = new GebruikerDTO();

			dto.setNaam(txtNaam.getText());
			dto.setNaamChamilo(txtChamilo.getText());
			dto.setEmailadres(txtEmail.getText());
			dto.setStatus((GebruikerStatus) cbStatus.getValue());
			dto.setType((GebruikerType) cbType.getValue());


			String bericht = String.format(
					"Uw gegevens, op email-adres: %s werden aangepast.%n%nWe geven u graag een overzicht van de huidige toestand van uw profiel:%n     Naam : %s%n     Naam Chamillo : %s%n     Email-adres : %s%n     Wachtwoord : %s%n",
					dto.getEmailadres(), dto.getNaam(), dto.getNaamChamilo(),dto.getEmailadres(), getDC().getGeselecteerdeGebruiker().getUnhashed());

			getDC().editGeselecteerdeGebruiker(dto);
			String[] recipient = {dto.getEmailadres()};
			getDC().mailNaarGebruikers(bericht, "update", recipient);

		} catch (IllegalArgumentException e) {
			lblError.setText(e.getMessage());
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		IGebruiker gebruiker = (IGebruiker) evt.getNewValue();

		if (gebruiker != null) {
			cbStatus.getItems().setAll(GebruikerStatus.values());
			cbStatus.setValue(gebruiker.getStatus());

			cbType.getItems().setAll(GebruikerType.values());
			cbType.setValue(gebruiker.getType());

			txtNaam.setText(gebruiker.getNaam());
			txtChamilo.setText(gebruiker.getNaamChamilo());
			txtEmail.setText(gebruiker.getEmailadres());

			lblIntro.setText("Sessies waarvoor " + txtNaam.getText() + " aanwezig was:");

			maakSessieTable(tblView, getDC().getSessiesfromGeselecteerdeGebruiker());
		} else {
			cbStatus.getItems().clear();
			cbType.getItems().clear();
			txtNaam.clear();
			txtChamilo.clear();
			txtEmail.clear();
			lblIntro.setText("");
			tblView.getItems().clear();
		}
	}
}
