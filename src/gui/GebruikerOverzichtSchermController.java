package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Gebruiker;
import domein.GebruikerDTO;
import domein.GebruikerStatus;
import domein.GebruikerType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class GebruikerOverzichtSchermController extends SchermController implements Initializable, PropertyChangeListener{
	
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
		dc.addGebruikerListListener(this);
	}
	
	/**
	 * Selecteren van een gebruiker
	 */
	@FXML
    private void handleEditGegevensAction(MouseEvent event) throws IOException{
		Gebruiker gebruiker = (Gebruiker) tblView.getSelectionModel().getSelectedItem();
		getDC().setGeselecteerdeGebruiker(gebruiker);
    }
	
	/**
	 * Verwijderen van een gebruiker
	 */
	@FXML
    private void handleDeleteGebruikerAction(ActionEvent event){
    	Gebruiker gebruiker = (Gebruiker) tblView.getSelectionModel().getSelectedItem();
    	getDC().verwijderGebruiker(gebruiker);
    }
	
	/**
	 * Aanmaken nieuwe gebruiker
	 */
	@FXML
	private void handleCreateGebruikerAction(ActionEvent event) throws IOException{
		GebruikerDTO dto = new GebruikerDTO();
		
		dto.setNaam("Nieuwe gebruiker");
		dto.setNaamChamilo("Naam chamilo");
		dto.setEmailadres("nieuw.email@hogent.be");
		
		
		//dto.setWachtwoord("password");
		String pw = generatePassword();
		dto.setUnhashed(pw);
		dto.setWachtwoord(pw);
		
		dto.setStatus(GebruikerStatus.NIET_ACTIEF);
		dto.setType(GebruikerType.GewoneGebruiker);
		
		
		getDC().addGebruiker(dto);

    }
	
	/**
	 * Zoek gebruiker in lijst
	 */
	@FXML
	private void handleZoekGebruikerAction(ActionEvent event) {
		maakGebruikerTable(tblView, getDC().getGebruikersMetNaam(txtZoekGebruiker.getText()));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		maakGebruikerTable(tblView, getDC().getGebruikers());
	}
	
	/**
	 * Genereert een random 7 digit string
	 */
	private String generatePassword()
	{
		String mogelijks ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzkaas1234567890";
		int maxPasswordLength = 7;
		Random random = new Random();
		
        char[] text = new char[maxPasswordLength];
        for (int i = 0; i < maxPasswordLength; i++) {
            text[i] = mogelijks.charAt(random.nextInt(mogelijks.length()));
        }
        return new String(text);
	}
}
