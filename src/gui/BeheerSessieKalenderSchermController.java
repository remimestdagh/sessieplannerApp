package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.ISessieKalender;
import domein.SessieKalender;
import domein.SessieKalenderDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BeheerSessieKalenderSchermController extends SchermController implements Initializable {

	@FXML
	private TextField txtJaar;

	@FXML
	private DatePicker dateStart, dateEind;

	@FXML
	private Button btnSave;

	@FXML
	private Label lblError;

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);

		ISessieKalender kalender = dc.getGeselecteerdeSessieKalender();

		txtJaar.setText(kalender.getAcademiejaar());
		dateStart.setValue(kalender.getStartdatum().toLocalDate());
		dateEind.setValue(kalender.getEinddatum().toLocalDate());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void handleEditSessieKalenderAction(ActionEvent event) throws IOException {
		try {

			SessieKalenderDTO dto = new SessieKalenderDTO();

			dto.setAcademiejaar(txtJaar.getText());
			dto.setStartdatum(dateStart.getValue().atStartOfDay());
			dto.setEinddatum(dateEind.getValue().atStartOfDay());

			getDC().editGeselecteerdeSessieKalender(dto);
		} catch (IllegalArgumentException e) {
			lblError.setText(e.getMessage());
		}

	}
}
