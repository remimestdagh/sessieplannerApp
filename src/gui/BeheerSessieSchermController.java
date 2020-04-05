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

public class BeheerSessieSchermController  extends SchermController implements Initializable{
	
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

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		
		maakAankondigingTable(tblView, getDC().getAankondigingenfromGeselecteerdeSessie());
		
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
        
        if(getDC().gebruikerIsHoofdverantwoordelijke()) {
        	verranderScherm(btnKalender, "SessieKalender");
        }else {
        	verranderScherm(btnKalender, "VerantwoordelijkeBeheerSessies");
        }
    }
	
	@FXML
    private void handleAankondigingAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnAankondiging, "CreateAankondiging");
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
		
		getDC().editGeselecteerdeSessie(titel, spreker, lokaal, capaciteit, startDatum, eindDatum, status);
        
        if(getDC().gebruikerIsHoofdverantwoordelijke()) {
        	verranderScherm(btnEditSessie, "SessieKalender");
        }else {
        	verranderScherm(btnEditSessie, "VerantwoordelijkeBeheerSessies");
        }
    }
}





