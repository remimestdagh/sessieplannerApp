package gui;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import domein.IGebruiker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CreateHerinneringSchermController extends SchermController implements Initializable {

	@FXML
	private Button btnCancel, btnToevoegen;

	@FXML
	private TextArea txaInhoud;

	@FXML
	private Label errorText, lblMailText;

	@FXML
	private CheckBox cbVerstuurMail;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	private void handleCancelAction(ActionEvent event) throws IOException {

		sluitScherm(btnCancel);
	}

	
	@FXML
	private void handleHerinneringVersturenAction(ActionEvent event) throws IOException {

		//headermessage herinnering opbouwen
		String pattern = "dd MMMM yyyy - HH:mm";
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
		Date d = Date.from(getDC().getGeselecteerdeSessie().getStartDatum().atZone(ZoneId.systemDefault()).toInstant());
		String datum = simpleDateFormat.format(d);

		String headerMessage = "Betreffende de sessie: " + getDC().getGeselecteerdeSessie().getTitel()
				+ ",\n" + "		Tijdstip : " + datum + ",\n"
				+ "		Met gastspreker : " + getDC().getGeselecteerdeSessie().getNaamGastspreker() + "\n\n";

		String herinnering = txaInhoud.getText();

		try {

			// 1. herinneringen doormailen indien checkbox geselecteerd was
			if (cbVerstuurMail.isSelected()) {
				lblMailText.setText("Het venster zal worden gesloten eens de mail verzonden werd...");
				
				String[] mailTo = new String[getDC().getGebruikersFromGeselecteerdeSessie().size()];
				int i = 0;
				for (IGebruiker gebruiker : getDC().getGebruikersFromGeselecteerdeSessie()) {
				    mailTo[i]=gebruiker.getEmailadres();
				    i++;
				}
				
				
				getDC().mailNaarGebruikers(headerMessage + herinnering, "herinnering", mailTo);
			}

			// 2. herinneringen storen
			getDC().addHerinneringToGeselecteerdeSessie(herinnering);
			// scherm sluiten
			sluitScherm(btnToevoegen);

		} catch (IllegalArgumentException e) {
			errorText.setText(e.getMessage());
		}

	}

}
