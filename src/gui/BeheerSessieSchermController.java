package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.Aankondiging;
import domein.DomeinController;
import domein.Sessie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BeheerSessieSchermController  extends AnchorPane implements Initializable,SchermController{
	
	private DomeinController dc;
	
	@FXML
	private Button btnKalender;
	
	@FXML
	private TableView tblView;
	
	@FXML
	private Button btnAankondiging;
	
	@FXML
	private Button btnEditSessie;
	
	@FXML
	private TextField txtLokaal;
	
	@FXML
	private TextField txtCapaciteit;
	
	@FXML
	private TextField txtSpreker;
	
	@FXML
	private TextField txtEindDatum;
	
	@FXML
	private TextField txtStartDatum;
	
	@FXML
	private TextField txtVerantwoordelijke;
	
	@FXML
	private TextField txtTitel;
	
	@FXML
	private ChoiceBox cbStatus;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void setDomainController(DomeinController dc) {
		this.dc = dc;
		
		TableColumn<Aankondiging, String> inhoudColumn = new TableColumn<>("Inhoud");
		inhoudColumn.setCellValueFactory(new PropertyValueFactory<>("inhoud"));
		
		TableColumn<Aankondiging, String> auteurColumn = new TableColumn<>("Auteur");
		auteurColumn.setCellValueFactory(new PropertyValueFactory<>("auteurNaam"));
		
		TableColumn<Aankondiging, String> publicatieDatumColumn = new TableColumn<>("Publicatie Datum");
		publicatieDatumColumn.setCellValueFactory(new PropertyValueFactory<>("publicatieDatum"));
		
		tblView.setItems(dc.getAankondigingenfromGeselecteerdeSessie());
		tblView.getColumns().addAll(inhoudColumn, auteurColumn, publicatieDatumColumn);
		
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
    private void handleKalenderAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SessieKalenderScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnKalender.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
    private void handleAankondigingAction(ActionEvent event) throws IOException {
		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CreateAankondigingScherm.fxml"));
		Parent root = (Parent)loader.load();
		SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnAankondiging.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
    private void handleEditSessieAction(ActionEvent event) throws IOException {

		String titel = txtTitel.getText();
		String startDatum = txtStartDatum.getText();
		String eindDatum = txtEindDatum.getText();
		String spreker = txtSpreker.getText();
		int capaciteit = Integer.parseInt(txtCapaciteit.getText());
		String lokaal = txtLokaal.getText();
		String status = (String) cbStatus.getValue();
		
		dc.editGeselecteerdeSessie(titel, spreker, lokaal, capaciteit, startDatum, eindDatum, status);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SessieKalenderScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnEditSessie.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}





