package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import domein.Aankondiging;
import domein.DomeinController;
import domein.Gebruiker;
import domein.ISessie;
import domein.Media;
import domein.Sessie;
import domein.SessieDTO;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SessieDetailSchermController extends SchermController implements Initializable, PropertyChangeListener {

	@FXML
	private Button btnEditSessie, btnAankondiging, btnCreateMedia, btnDeleteMedia, btnHerinnering;

	@FXML
	private TableView tblAankondigingen, tblGebruikers, tblMedia, tblHerinnering;

	@FXML
	private TextField txtLokaal, txtCapaciteit, txtSpreker, txtEindDatum, txtStartDatum, txtVerantwoordelijke, txtTitel;

	@FXML
	private ChoiceBox<String> cbStatus;

	@FXML
	private Text txtGebruikers, txtAankondigingen, txtHerinnering,txtError;

	@FXML
	private Label lblError;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		dc.addSessieListener(this);
	}

	@FXML
	private void handleCreateMediaAction(ActionEvent event) throws IOException {
		if (getDC().getGeselecteerdeSessie() != null) {
			creëerScherm("CreateMedia");
		}
	}

	@FXML
	private void handleDeleteMediaAction(ActionEvent event) throws IOException {
		Media media = (Media) tblMedia.getSelectionModel().getSelectedItem();
		getDC().verwijderMediaFromGeselecteerdeSessie(media);
		maakMediaTable(tblMedia, getDC().getMediafromGeselecteerdeSessie());
	}

	/**
	 * Button event dat het scherm ter aanmaak van Herinneringen opent
	 */
	@FXML
	private void handleHerinneringAction(ActionEvent event) throws IOException {
		if (getDC().getGeselecteerdeSessie() != null) {
			creëerScherm("CreateHerinnering");
		}

	}

	/**
	 * Button event dat het scherm ter aanmaak van aankondigingen opent
	 */
	@FXML
	private void handleAankondigingAction(ActionEvent event) throws IOException {

		if (getDC().getGeselecteerdeSessie() != null) {
			creëerScherm("CreateAankondiging");
		}

	}

	/**
	 * Button event dat het scherm ter aanmaak van feedback opent
	 */
	@FXML
	private void handleFeedbackAction(ActionEvent event) throws IOException {
		// creëerScherm("CreateFeedback");
	}

	@FXML
	private void handleEditSessieAction(ActionEvent event) throws IOException {
		try {
			SessieDTO dto = new SessieDTO();

			dto.setTitel(txtTitel.getText());
			dto.setStartDatum(LocalDateTime.parse(txtStartDatum.getText()));
			dto.setEindDatum(LocalDateTime.parse(txtEindDatum.getText()));
			dto.setSessieAanmaker(txtVerantwoordelijke.getText());
			dto.setNaamGastspreker(txtSpreker.getText());
			dto.setMAX_CAPACITEIT(Integer.parseInt(txtCapaciteit.getText()));
			dto.setLokaalCode(txtLokaal.getText());
			dto.setStatus((String) cbStatus.getValue());

			try {
				getDC().editGeselecteerdeSessie(dto);
			} catch (IllegalAccessException e) {
				txtError.setText("U bent de eigenaar van deze sessie niet");
			}
			lblError.setText("");

		} catch (IllegalArgumentException e) {
			lblError.setText(e.getMessage());
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		ISessie sessie = (ISessie) evt.getNewValue();

		if (sessie != null) {
			maakAankondigingTable(tblAankondigingen, getDC().getAankondigingenfromGeselecteerdeSessie());
			maakMediaTable(tblMedia, getDC().getMediafromGeselecteerdeSessie());
			if(!sessie.getEindDatum().isAfter(LocalDateTime.now())) {
				maakFeedbackTable(tblHerinnering, getDC().getFeedbackFromGeselecteerdeSessie());
				txtHerinnering.setText("Feedback");
				btnHerinnering.setDisable(true);
			}
			else {
				maakHerinneringTable(tblHerinnering, getDC().getHerinneringenFromGeselecteerdeSessie());
				txtHerinnering.setText("Herinneringen");
				btnHerinnering.setDisable(false);
			}
			
			txtTitel.setText(sessie.getTitel());
			txtVerantwoordelijke.setText(sessie.getSessieAanmaker());
			txtStartDatum.setText(sessie.getStartDatum().toString());
			txtEindDatum.setText(sessie.getEindDatum().toString());
			txtSpreker.setText(sessie.getNaamGastspreker());
			txtCapaciteit.setText("" + sessie.getMAX_CAPACITEIT());
			txtLokaal.setText(sessie.getLokaalCode());
			cbStatus.getItems().addAll("AANGEMAAKT", "GEOPEND", "GESTART", "GESLOTEN");
			cbStatus.setValue(sessie.getStatus().toString());
			maakGebruikerTable(tblGebruikers,getDC().getGebruikersFromGeselecteerdeSessie());
			 
		} else {

		}
	}
}
