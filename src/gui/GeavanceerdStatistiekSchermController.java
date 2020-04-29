package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class GeavanceerdStatistiekSchermController extends SchermController implements Initializable{
	
	@FXML
	private ImageView imgViewPowerBI;
	
	@FXML
	private Button btnKeerTerug;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
	}
	
	/**
	 * Keer terug
	 */
	@FXML
    private void handleKeerTerugAction(ActionEvent event) throws IOException {
        verranderScherm(btnKeerTerug, "Statistiek");
    }
}