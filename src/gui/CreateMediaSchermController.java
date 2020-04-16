package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateMediaSchermController extends SchermController implements Initializable{
	
	@FXML
	private Button btnCancel, btnCreate;
	
	@FXML
	private TextField txtType;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
    private void handleCancelAction(ActionEvent event) throws IOException {
        
        sluitScherm(btnCancel);
    }
	
	@FXML
    private void handleCreateMediaAction(ActionEvent event) throws IOException {
		
		String type = txtType.getText();
		
		getDC().addMediaToGeselecteerdeSessie(type);
        
        sluitScherm(btnCreate);
    }
}