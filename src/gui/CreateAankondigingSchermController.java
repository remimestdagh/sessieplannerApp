package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import domein.Aankondiging;
import domein.DomeinController;
import domein.Sessie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateAankondigingSchermController extends AnchorPane implements Initializable,SchermController{
	
	private DomeinController dc;
	
	@FXML
	private Button btnCancel;
	
	@FXML
	private TextArea txaInhoud;
	
	@FXML
	private Button btnToevoegen;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void setDomainController(DomeinController dc) {
		this.dc = dc;
	}
	
	@FXML
    private void handleCancelAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BeheerSessieScherm.fxml"));
		Parent root = (Parent)loader.load();
		SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
    private void handleAankondigingToevoegenAction(ActionEvent event) throws IOException {
		
		String inhoud = txaInhoud.getText();
		
		dc.addAankondigingToGeselecteerdeSessie(inhoud);
		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BeheerSessieScherm.fxml"));
		Parent root = (Parent)loader.load();
		SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}