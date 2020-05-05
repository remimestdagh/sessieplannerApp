package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Gebruiker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class GebruikerOverzichtSchermController extends SchermController implements Initializable{
	
	@FXML
	private TableView tblView;
	
	@FXML
	private Button btnCreate, btnEdit, btnZoekGebruiker;
	
	@FXML
	private TextField txtZoekGebruiker;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		maakGebruikerTable(tblView, getDC().getGebruikers());
	}
	
	/**
	 * Selecteren van een gebruiker
	 */
	@FXML
    private void handleEditGegevensAction(MouseEvent event) throws IOException{
		Gebruiker gebruiker = (Gebruiker) tblView.getSelectionModel().getSelectedItem();
		getDC().setGeselecteerdeGebruiker(gebruiker);
		System.out.println("click geregistreerd");
    }
	
	/**
	 * Verwijderen van een gebruiker
	 */
	@FXML
    private void handleDeleteGebruikerAction(ActionEvent event){
    	Gebruiker gebruiker = (Gebruiker) tblView.getSelectionModel().getSelectedItem();
    	getDC().verwijderGebruiker(gebruiker);
    	maakGebruikerTable(tblView, getDC().getGebruikers());
    }
	
	/**
	 * Aanmaken nieuwe gebruiker
	 */
	@FXML
	private void handleCreateGebruikerAction(ActionEvent event) throws IOException{
        creëerScherm("CreateGebruiker");
    }
	
	/**
	 * Zoek gebruiker in lijst
	 */
	@FXML
	private void handleZoekGebruikerAction(ActionEvent event) {
		maakGebruikerTable(tblView, getDC().getGebruikersMetNaam(txtZoekGebruiker.getText()));
	}
}
