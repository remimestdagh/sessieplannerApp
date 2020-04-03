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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SessiesStatistiekSchermController extends SchermController implements Initializable{
	
	@FXML
	private Button btnStatistiek;
	
	@FXML
	private TableView tblView;
	
	@FXML
	private Button btnAanwezigheden;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		
		maakSessieTable(tblView, getDC().getSessies());
	}
	
	@FXML
    private void handleStatistiekAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnStatistiek, "Statistiek");
    }
	
	@FXML
    private void handleAanwezighedenAction(ActionEvent event) throws IOException {
		getDC().setGeselecteerdeSessie((Sessie)tblView.getSelectionModel().getSelectedItem());
        
        verranderScherm(btnAanwezigheden, "SessieStatistiek");
    }
}










