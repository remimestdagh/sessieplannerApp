package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.Aankondiging;
import domein.DomeinController;
import domein.Gebruiker;
import domein.Media;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BeheerSessieSchermController  extends SchermController implements Initializable{
	
	@FXML
	private Button btnEditSessie, btnAankondiging, btnCreateMedia, btnDeleteMedia;
	
	@FXML
	private TableView tblAankondigingen, tblGebruikers, tblMedia;
	
	@FXML
	private TextField txtLokaal, txtCapaciteit, txtSpreker, txtEindDatum, txtStartDatum, txtVerantwoordelijke, txtTitel;
	
	@FXML
	private ChoiceBox<String> cbStatus;
	
	@FXML
	private Text txtGebruikers, txtAankondigingen;
	
	@FXML
	private Label lblError;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		
		maakAankondigingTable(tblAankondigingen, getDC().getAankondigingenfromGeselecteerdeSessie());
		maakMediaTable(tblMedia, getDC().getMediafromGeselecteerdeSessie());
		maakGebruikerTable(tblGebruikers, getDC().getGebruikersFromGeselecteerdeSessie());
		
		txtTitel.setText(dc.getGeselecteerdeSessieTitel());
		txtVerantwoordelijke.setText(dc.getGeselecteerdeSessieSessieAanmaker());
		txtStartDatum.setText(dc.getGeselecteerdeSessieStartDatum());
		txtEindDatum.setText(dc.getGeselecteerdeSessieEindDatum());
		txtSpreker.setText(dc.getGeselecteerdeSessieNaamGastspreker());
		txtCapaciteit.setText("" + dc.getGeselecteerdeSessieMAX_CAPACITEIT());
		txtLokaal.setText(dc.getGeselecteerdeSessieLokaalCode());
		
		cbStatus.getItems().addAll("AANGEMAAKT","GEOPEND","GESTART","GESLOTEN");
		cbStatus.setValue(dc.getGeselecteerdeSessieStatus());
	}
	
	@FXML
    private void handleCreateMediaAction(ActionEvent event) throws IOException {
        
        creëerScherm("CreateMedia");
    }
	
	@FXML
    private void handleDeleteMediaAction(ActionEvent event) throws IOException {
		Media media = (Media) tblMedia.getSelectionModel().getSelectedItem();
    	getDC().verwijderMediaFromGeselecteerdeSessie(media);
    	maakMediaTable(tblMedia, getDC().getMediafromGeselecteerdeSessie());
    }
	
	
	/**
	 * Button event dat het scherm ter aanmaak van aankondigingen opent
	 */
	@FXML
    private void handleAankondigingAction(ActionEvent event) throws IOException {
        
        creëerScherm("CreateAankondiging");
    }
	
	@FXML
    private void handleEditSessieAction(ActionEvent event) throws IOException {
try {
	String titel = txtTitel.getText();
	String startDatum = txtStartDatum.getText();
	String eindDatum = txtEindDatum.getText();
	String spreker = txtSpreker.getText();
	int capaciteit = Integer.parseInt(txtCapaciteit.getText());
	String lokaal = txtLokaal.getText();
	String status = (String) cbStatus.getValue();
	
	getDC().editGeselecteerdeSessie(titel, spreker, lokaal, capaciteit, startDatum, eindDatum, status);

	lblError.setText("");
	
}catch(IllegalArgumentException e)
{
	lblError.setText(e.getMessage());
}
    }
}





