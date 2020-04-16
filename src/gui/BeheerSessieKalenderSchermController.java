package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.SessieKalender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BeheerSessieKalenderSchermController extends SchermController implements Initializable{
	
	@FXML
	private TextField txtJaar;
	
	@FXML
	private DatePicker dateStart, dateEind;
	
	@FXML
	private Button btnSave;


	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		
		txtJaar.setText(getDC().getGeselecteerdeSessieKalenderAcademiejaar());
		dateStart.setValue(getDC().getGeselecteerdeSessieKalenderStartdatum().toLocalDate());
		dateEind.setValue(getDC().getGeselecteerdeSessieKalenderEinddatum().toLocalDate());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    private void handleEditSessieKalenderAction(ActionEvent event) throws IOException {
		String academiejaar = txtJaar.getText();
		LocalDateTime startDate = dateStart.getValue().atStartOfDay();
		LocalDateTime eindDate = dateEind.getValue().atStartOfDay();
		
		getDC().editGeselecteerdeSessieKalender(academiejaar, startDate, eindDate);
    }
}
