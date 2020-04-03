package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StatistiekSchermController extends SchermController implements Initializable{
	
	@FXML
	private Button btnHoofdmenu;
	
	@FXML
	private Button btnGebruikers;
	
	@FXML
	private Button btnSessies;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
    private void handleHoofdmenuAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnHoofdmenu, "Hoofd");
    }
	
	@FXML
    private void handleGebruikersAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnGebruikers, "GebruikersStatistiek");
    }
	
	@FXML
    private void handleSessiesAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnSessies, "SessiesStatistiek");
    }
	
}