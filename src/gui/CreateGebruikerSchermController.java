	package gui;

	import java.io.IOException;
	import java.net.URL;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Map;
	import java.util.ResourceBundle;

	import domein.DomeinController;
	import javafx.application.Platform;
	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.fxml.Initializable;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
	import javafx.scene.control.ListView;
	import javafx.scene.control.PasswordField;
	import javafx.scene.control.TextField;
	import javafx.scene.input.MouseEvent;
	import javafx.scene.layout.AnchorPane;
	import javafx.scene.text.Text;
	import javafx.stage.Stage;

	public class CreateGebruikerSchermController extends AnchorPane implements Initializable, SchermController{
		
		private DomeinController dc;
		
		@FXML
		private Button btnCreate;
		
		@FXML
		private Button btnCancel;
		
		@FXML
		private TextField txNaam;
		
		@FXML
		private TextField txChamilo;
		
		@FXML
		private TextField txEmail;
		
		@FXML
		private TextField txPassword;
		
		@FXML
		private ChoiceBox cbType;
		
		@FXML
		private ChoiceBox cbStatus;
		

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
		}

		public void setDomainController(DomeinController dc) {
			this.dc = dc;
			
			cbStatus.getItems().addAll("ACTIEF","GEBLOKKEERD","NIET_ACTIEF");
			cbStatus.setValue("ACTIEF");
			
			cbType.getItems().addAll("HoofdVerantwoordelijke","Verantwoordelijke","Gewone_Gebruiker");
			cbType.setValue("Gewone_Gebruiker");
		}
		
		@FXML
		private void handleCreateGebruikerAction(ActionEvent event) throws IOException{
			String naam = txNaam.getText();
			String chamilo = txChamilo.getText();
			String email = txEmail.getText();
			String wachtwoord = txPassword.getText();
			String status = (String) cbStatus.getValue();
			String type = (String) cbType.getValue();
	    	dc.addGebruiker(naam, chamilo, email, wachtwoord, status, type);
	    	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BeheerGebruikersScherm.fxml"));
			Parent root = (Parent)loader.load();
			SchermController test = loader.getController();
	        test.setDomainController(dc);
	        Stage stage = (Stage) btnCreate.getScene().getWindow();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }
		
		@FXML
		private void handleCancelAction(ActionEvent event) throws IOException{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BeheerGebruikersScherm.fxml"));
			Parent root = (Parent)loader.load();
			SchermController test = loader.getController();
	        test.setDomainController(dc);
	        Stage stage = (Stage) btnCancel.getScene().getWindow();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    }
		
	}