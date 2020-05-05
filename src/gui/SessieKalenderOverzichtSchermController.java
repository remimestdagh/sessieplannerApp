package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.ISessieKalender;
import domein.SessieKalender;
import domein.SessieKalenderDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class SessieKalenderOverzichtSchermController extends SchermController
		implements Initializable, PropertyChangeListener {

	@FXML
	private Button btnEdit;

	@FXML
	private TableView tblView;

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		maakKalenderTable(tblView, getDC().getSessieKalenders());
		dc.addSessieKalenderListListener(this);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 * Het selecteren van een sessie
	 */
	@FXML
	private void handleSessieKalenderDetailAction(MouseEvent event) throws IOException {
		getDC().setGeselecteerdeSessieKalender((SessieKalender) tblView.getSelectionModel().getSelectedItem());
	}

	/**
	 * Het aanpassen van een sessiekalender
	 */
	@FXML
	private void handleBeheerSessiesAction(ActionEvent event) throws IOException {
		getDC().setGeselecteerdeSessieKalender((SessieKalender) tblView.getSelectionModel().getSelectedItem());

		verranderScherm(btnEdit, "SessieKalender");
	}

	/**
	 * Verwijderen van een sessiekalender
	 */
	@FXML
	private void handleDeleteSessiekalenderAction(ActionEvent event) {
		ISessieKalender kalender = (ISessieKalender) tblView.getSelectionModel().getSelectedItem();
		getDC().verwijderSessieKalender(kalender);
	}

	/**
	 * Aanmaken nieuwe sessiekalender
	 */
	@FXML
	private void handleCreateSessiekalenderAction(ActionEvent event) throws IOException {
		SessieKalenderDTO dto = new SessieKalenderDTO();
		dto.setAcademiejaar("Nieuw academiejaar");
		dto.setStartdatum(LocalDateTime.now());
		dto.setEinddatum(LocalDateTime.now());
		getDC().addSessieKalender(dto);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		maakKalenderTable(tblView, getDC().getSessieKalenders());
	}
}
