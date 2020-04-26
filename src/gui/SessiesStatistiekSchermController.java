package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Gebruiker;
import domein.Sessie;
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

public class SessiesStatistiekSchermController extends SchermController implements Initializable{
	
	@FXML
	private Button btnStatistiek;
	
	@FXML
	private TableView tblView;
	
	@FXML
	private Button btnAanwezigheden;
	
	@FXML
	private BorderPane borderPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		
		maakSessieTable(tblView, getDC().getSessies());
	}

	
	/**
	 * Selecteren van een sessie
	 */
	@FXML
    private void handleGebruikersAction(MouseEvent event) throws IOException{
		Sessie sessie = (Sessie) tblView.getSelectionModel().getSelectedItem();
		getDC().setGeselecteerdeSessie(sessie);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SessieStatistiekScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController schermController = loader.getController();
        schermController.setDomeinController(getDC());
        
        borderPane.setCenter(root);
        
    }
	

	/**
	 * Terugkeren naar statistiek scherm
	 */
	@FXML
    private void handleStatistiekAction(ActionEvent event) throws IOException {
        verranderScherm(btnStatistiek, "Statistiek");
    }
}










