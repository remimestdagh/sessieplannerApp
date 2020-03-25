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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BeheerGebruikerSchermController extends AnchorPane implements Initializable,SchermController{
	
	private DomeinController dc;
	
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

	@Override
	public void setDomainController(DomeinController dc) {
		this.dc = dc;
		
		TableColumn<Sessie, String> verantwoordelijkeColumn = new TableColumn<>("Naam verantwoordelijke");
		verantwoordelijkeColumn.setCellValueFactory(new PropertyValueFactory<>("naamVerantwoordelijke"));
		
		TableColumn<Sessie, String> titelColumn = new TableColumn<>("Titel");
		titelColumn.setCellValueFactory(new PropertyValueFactory<>("titel"));
		
		TableColumn<Sessie, String> startDatumColumn = new TableColumn<>("Start datum");
		startDatumColumn.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
		
		TableColumn<Sessie, String> eindDatumColumn = new TableColumn<>("Eind datum");
		eindDatumColumn.setCellValueFactory(new PropertyValueFactory<>("eindDatum"));
		
		TableColumn<Sessie, String> plaatsenColumn = new TableColumn<>("Aanwezigen / Vrije plaatsen");
		plaatsenColumn.setCellValueFactory(new PropertyValueFactory<>("aanwezigenOrVrijePlaatsen"));
		
		tblView.setItems(dc.getSessiesfromGeselecteerdeGebruiker());
		tblView.getColumns().addAll(verantwoordelijkeColumn, titelColumn, startDatumColumn, eindDatumColumn, plaatsenColumn);
		tblView.getSortOrder().addAll(startDatumColumn,verantwoordelijkeColumn);
		tblView.sort();
		
		cbStatus.getItems().addAll("ACTIEF","GEBLOKKEERD","NIET_ACTIEF");
		cbStatus.setValue(dc.getGeselecteerdeGebruikerStatus());
		
		cbType.getItems().addAll("HoofdVerantwoordelijke","Verantwoordelijke","Gewone_Gebruiker");
		cbType.setValue(dc.getGeselecteerdeGebruikerType());
		
		txtNaam.setText(dc.getGeselecteerdeGebruikerNaam());
		txtChamilo.setText(dc.getGeselecteerdeGebruikerNaamChamilo());
		txtEmail.setText(dc.getGeselecteerdeGebruikerEmailadres());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    private void handleTerugAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BeheerGebruikersScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnTerug.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
    private void handleEditAction(ActionEvent event) throws IOException {
		String naam = txtNaam.getText();
		String naamChamilo = txtChamilo.getText();
		String email = txtEmail.getText();
		String status = (String) cbStatus.getValue();
		String type = (String) cbType.getValue();
		
		dc.editGeselecteerdeGebruiker(naam, naamChamilo, email, status, type);
		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BeheerGebruikersScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnEdit.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
