package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BeheerGebruikerSchermController extends SchermController implements Initializable{
	
	
	@FXML
	private Button btnTerug;
	
	@FXML
	private Button btnEdit;
	
	@FXML
	private TextField txtNaam;
	
	@FXML
	private TextField txtChamilo;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private ChoiceBox cbStatus;
	
	@FXML
	private ChoiceBox cbType;
	
	@FXML
	private TableView tblView;
	
	@FXML
	private Label lblIntro;
	
	

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		
		maakSessieTable(tblView, getDC().getSessiesfromGeselecteerdeGebruiker());
		
		cbStatus.getItems().addAll("ACTIEF","GEBLOKKEERD","NIET_ACTIEF");
		cbStatus.setValue(dc.getGeselecteerdeGebruikerStatus());
		
		cbType.getItems().addAll("HoofdVerantwoordelijke","Verantwoordelijke","Gewone_Gebruiker");
		cbType.setValue(dc.getGeselecteerdeGebruikerType());
		
		txtNaam.setText(dc.getGeselecteerdeGebruikerNaam());
		txtChamilo.setText(dc.getGeselecteerdeGebruikerNaamChamilo());
		txtEmail.setText(dc.getGeselecteerdeGebruikerEmailadres());
		
		lblIntro.setText("Sessies waarvoor "+ txtNaam.getText()+" aanwezig was:");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    private void handleEditAction(ActionEvent event) throws IOException {
		String naam = txtNaam.getText();
		String naamChamilo = txtChamilo.getText();
		String email = txtEmail.getText();
		String status = (String) cbStatus.getValue();
		String type = (String) cbType.getValue();
		
		getDC().editGeselecteerdeGebruiker(naam, naamChamilo, email, status, type);
    }
}
