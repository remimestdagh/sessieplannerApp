package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.SessieKalenderDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateSessieKalenderSchermController extends SchermController implements Initializable {

	@FXML
	private Button btnTerug, btnCreate;

	@FXML
	private TextField txtAcademiejaar;
	
	@FXML
	private DatePicker dateBegin, dateEinde;

	@FXML
	private Label lblWarning;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	private void handleCancelAction(ActionEvent event) throws IOException {
		sluitScherm(btnTerug);
	}

	@FXML
	private void handleCreateSessieKalenderAction(ActionEvent event) throws IOException {
		SessieKalenderDTO dto = new SessieKalenderDTO();
		
		dto.setAcademiejaar( txtAcademiejaar.getText() );
		dto.setStartdatum( dateBegin.getValue().atStartOfDay() );
		dto.setEinddatum( dateEinde.getValue().atStartOfDay() );
		
		try {
			getDC().addSessieKalender(dto);
			sluitScherm(btnCreate);
		} catch (IllegalArgumentException e) {
			lblWarning.setText(e.getMessage());
		}

	}
}