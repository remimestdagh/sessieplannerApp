package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Gebruiker;
import domein.Sessie;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SessieKalenderSchermController extends SchermController implements Initializable{
	
	@FXML
	private TableView tblView;
	
	@FXML
	private Button btnHoofdmenu, btnSessieToevoegen;
	
	@FXML
	private BorderPane borderPane;
	
	@FXML
	private Text geselecteerdeKalender;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		
		if(getDC().gebruikerIsHoofdverantwoordelijke()) {
			maakSessieTable(tblView, getDC().getSessiesfromGeselecteerdeSessieKalender());
		}else {
			maakSessieTable(tblView, getDC().getSessiesFromVerantwoordelijke());
			btnHoofdmenu.setText("Terug naar hoofdmenu");
		}
		geselecteerdeKalender.setText("Sessies voor kalender: "+getDC().getGeselecteerdeSessieKalenderAcademiejaar());
	}
	
	@FXML
    private void handleHoofdmenuAction(ActionEvent event) throws IOException {

        if(getDC().gebruikerIsHoofdverantwoordelijke()) {
        	verranderScherm(btnHoofdmenu, "SessieKalenders");
        }else {
        	verranderScherm(btnHoofdmenu, "Hoofd");
        }
    }
	
	@FXML
    private void handleSessieToevoegenAction(ActionEvent event) throws IOException {
        creëerScherm("CreateSessie");
    }
	
	@FXML
    private void handleBeheerSessieAction(MouseEvent event) throws IOException {
		getDC().setGeselecteerdeSessie((Sessie)tblView.getSelectionModel().getSelectedItem());
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BeheerSessieScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController schermController = loader.getController();
        schermController.setDomeinController(getDC());
        
        borderPane.setCenter(root);
    }
}