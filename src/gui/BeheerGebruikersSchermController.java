package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Gebruiker;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BeheerGebruikersSchermController extends SchermController implements Initializable{
	
	@FXML
	private TableView tblView;
	
	@FXML
	private Button btnHoofmenu, btnCreate, btnEdit;
	
	@FXML
	private BorderPane borderPane;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		maakGebruikerTable(tblView, getDC().getGebruikers());
	}
	
	@FXML
    private void handleHoofdmenuAction(ActionEvent event) throws IOException {

        verranderScherm(btnHoofmenu, "Hoofd");
    }
	
	@FXML
    private void handleEditGegevensAction(MouseEvent event) throws IOException{
		Gebruiker gebruiker = (Gebruiker) tblView.getSelectionModel().getSelectedItem();
		getDC().setGeselecteerdeGebruiker(gebruiker);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BeheerGebruikerScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController schermController = loader.getController();
        schermController.setDomeinController(getDC());
        
        borderPane.setCenter(root);
        
    }
	
	@FXML
    private void handleDeleteGebruikerAction(ActionEvent event){
    	Gebruiker gebruiker = (Gebruiker) tblView.getSelectionModel().getSelectedItem();
    	getDC().verwijderGebruiker(gebruiker);
    	maakGebruikerTable(tblView, getDC().getGebruikers());
    }
	
	@FXML
	private void handleCreateGebruikerAction(ActionEvent event) throws IOException{
        creëerScherm("CreateGebruiker");
    }
	
}