package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Gebruiker;
import domein.Sessie;
import domein.SessieKalender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GebruikersStatistiekSchermController extends SchermController implements Initializable{
	
	@FXML
	private Button btnStatistiek;
	
	@FXML
	private Button btnAanwezigheden;
	
	@FXML
	private TableView tblView;
	
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
	
	/**
	 * Keer terug
	 */
	@FXML
    private void handleStatistiekAction(ActionEvent event) throws IOException {
        verranderScherm(btnStatistiek, "Statistiek");
    }
	
	
	/**
	 * Bekijk aanwezigheden op geselecteerde gebruiker d.m.v selectie
	 */
	@FXML
    private void handleAanwezighedenAction(MouseEvent event) throws IOException{
		Gebruiker gebruiker = (Gebruiker) tblView.getSelectionModel().getSelectedItem(); //haal geselecteerde gebruiker op
		getDC().setGeselecteerdeGebruiker(gebruiker);									 //speel door naar domeincontroller
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GebruikerStatistiekScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController schermController = loader.getController();
        schermController.setDomeinController(getDC());
        
        borderPane.setCenter(root);
        
    }
    

	
}

