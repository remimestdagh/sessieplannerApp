package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HoofdSchermController extends AnchorPane implements Initializable, SchermController{
	
	private DomeinController dc;
	
	@FXML
    private Text textName;
	
	@FXML
	private Button btnBeheerGebruikers;
	
	@FXML
	private Button btnStat;
	
	@FXML
	private Button btnKalender;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void setDomainController(DomeinController dc) {
		this.dc = dc;
		textName.setText(dc.getNaamIngelogdeGebruiker());
	}
	
	@FXML
    private void handleBeheerGebruikersButtonAction(ActionEvent event) throws IOException {
		if(dc.gebruikerIsHoofdverantwoordelijke()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BeheerGebruikersScherm.fxml"));
			Parent root = (Parent)loader.load();
			SchermController scherm = loader.getController();
			scherm.setDomainController(dc);
			Stage stage = (Stage) btnBeheerGebruikers.getScene().getWindow();
			Scene scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
		}
	}
	
	@FXML
    private void handleStatistiekButtonAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/StatistiekScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController scherm = loader.getController();
        scherm.setDomainController(dc);
        Stage stage = (Stage) btnStat.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
    private void handleSessieKalenderButtonAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SessieKalendersScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController scherm = loader.getController();
        scherm.setDomainController(dc);
        Stage stage = (Stage) btnKalender.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
}