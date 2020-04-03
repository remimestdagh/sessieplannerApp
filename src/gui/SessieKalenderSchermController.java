package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import domein.DomeinController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SessieKalenderSchermController extends SchermController implements Initializable{
	
	@FXML
	private TableView tblView;
	
	@FXML
	private Button btnHoofdmenu;
	
	@FXML
	private Button btnSessieToevoegen;
	
	@FXML
	private Button btnBeheerSessie;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		
		maakSessieTable(tblView, getDC().getSessiesfromGeselecteerdeSessieKalender());
	}
	
	@FXML
    private void handleHoofdmenuAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnHoofdmenu, "SessieKalenders"); //Hoofd?
    }
	
	@FXML
    private void handleSessieToevoegenAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnSessieToevoegen, "CreateSessie");
    }
	
	@FXML
    private void handleBeheerSessieAction(ActionEvent event) throws IOException {
		getDC().setGeselecteerdeSessie((Sessie)tblView.getSelectionModel().getSelectedItem());
        
        verranderScherm(btnBeheerSessie, "BeheerSessie");
    }
}