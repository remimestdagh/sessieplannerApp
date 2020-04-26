package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Sessie;
import domein.SessieKalender;
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

public class SessieKalendersSchermController extends SchermController implements Initializable{
	
	@FXML
	private Button btnHoofdmenu, btnEdit;
	
	@FXML
	private TableView tblView;
	
	@FXML
	private BorderPane borderPane;
	


	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		
		maakKalenderTable(tblView, getDC().getSessieKalenders());


	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    private void handleHoofdmenuAction(ActionEvent event) throws IOException {
        
        verranderScherm(btnHoofdmenu, "Hoofd");
    }
	
	/**
	 * Het selecteren van een sessie
	 */
	@FXML
    private void handleBeheerSessieKalenderAction(MouseEvent event) throws IOException {
		getDC().setGeselecteerdeSessieKalender((SessieKalender)tblView.getSelectionModel().getSelectedItem());
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BeheerSessieKalenderScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController schermController = loader.getController();
        schermController.setDomeinController(getDC());
        
        borderPane.setCenter(root);
    }
	
	/**
	 * Het aanpassen van een sessiekalender
	 */
	@FXML
    private void handleBeheerSessiesAction(ActionEvent event) throws IOException {
		getDC().setGeselecteerdeSessieKalender((SessieKalender)tblView.getSelectionModel().getSelectedItem());
        
        verranderScherm(btnEdit, "SessieKalender");
    }
}
