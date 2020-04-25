package gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
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

public class LoginSchermController extends SchermController implements Initializable{
	
	@FXML
    private TextField emailVeld;
	
	@FXML
	private PasswordField wachtwoordVeld;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Text errorText;
	
	//CONSTRUCTOR
	public LoginSchermController() 
	{
		
	}
	
	
	//METHODS
    @FXML
    /*
     * LoginScherm -> Hoofdscherm
     */
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
    	try {
    		getDC().login(emailVeld.getText(), wachtwoordVeld.getText());
    		//dc.login("@", "p");
    		//vanuit dc.login kan verkeerdeInfo-error geworpen worden dus alles hieronder halen we niet noodzakelijk
    		verranderScherm(loginButton, "Hoofd");
    		
    	}catch(IllegalArgumentException e) { //errors vanuit persistentiedummy geworpen met corresponderende tekst.
    		errorText.setText(e.getMessage());
    	}catch(IllegalAccessError e){
    		errorText.setText(e.getMessage());
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
}