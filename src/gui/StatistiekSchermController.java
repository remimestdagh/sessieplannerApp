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

public class StatistiekSchermController extends AnchorPane implements Initializable, SchermController{
	
	private DomeinController dc;
	
	@FXML
	private Button btnHoofdmenu;
	
	@FXML
	private Button btnGebruikers;
	
	@FXML
	private Button btnSessies;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void setDomainController(DomeinController dc) {
		this.dc = dc;
	}
	
	@FXML
    private void handleHoofdmenuAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/HoofdScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnHoofdmenu.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
    private void handleGebruikersAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GebruikersStatistiekScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnGebruikers.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
    private void handleSessiesAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SessiesStatistiekScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnSessies.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
}