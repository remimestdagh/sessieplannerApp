package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Gebruiker;
import domein.Sessie;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GebruikerStatistiekSchermController extends SchermController implements Initializable{
	
	@FXML
	private Button btnGebruikers;
	
	@FXML
	private TableView tblView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		
		maakSessieTable(tblView, getDC().getSessiesfromGeselecteerdeGebruiker());
	}
	
	@FXML
    private void handleGebruikersAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnGebruikers, "GebruikersStatistiek");
    }
}