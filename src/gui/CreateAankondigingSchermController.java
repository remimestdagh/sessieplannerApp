package gui;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import domein.Aankondiging;
import domein.DomeinController;
import domein.IGebruiker;
import domein.Sessie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateAankondigingSchermController extends SchermController implements Initializable {

	@FXML
	private Button btnCancel;

	@FXML
	private TextArea txaInhoud;

	@FXML
	private Button btnToevoegen;

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
	private void handleAankondigingToevoegenAction(ActionEvent event) throws IOException {

		// headermessage herinnering opbouwen
		String pattern = "dd MMMM yyyy - HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date d = Date.from(getDC().getGeselecteerdeSessie().getStartDatum().atZone(ZoneId.systemDefault()).toInstant());
		String datum = simpleDateFormat.format(d);

		String headerMessage = "Betreffende de sessie: " + getDC().getGeselecteerdeSessie().getTitel() + ",\n"
				+ "		Tijdstip : " + datum + ",\n" + "		Met gastspreker : "
				+ getDC().getGeselecteerdeSessie().getNaamGastspreker() + "\n\n";

		//
		String inhoud = txaInhoud.getText();

		try {

			// 1. herinneringen doormailen indien checkbox geselecteerd was
			if (cbVerstuurMail.isSelected() && getDC().getGebruikersFromGeselecteerdeSessie().size()>0) {
				lblMailText.setText("Het venster zal worden gesloten eens de mail verzonden werd...");
				
				
				String[] mailTo = new String[getDC().getGebruikersFromGeselecteerdeSessie().size()];
				int i = 0;
				for (IGebruiker gebruiker : getDC().getGebruikersFromGeselecteerdeSessie()) {
				    mailTo[i]=gebruiker.getEmailadres();
				    i++;
				}
				
				
				
				getDC().mailNaarGebruikers(headerMessage + inhoud, "aankondiging",mailTo);
			}

			getDC().addAankondigingToGeselecteerdeSessie(inhoud);

			// scherm sluiten
			sluitScherm(btnToevoegen);

		} catch (IllegalArgumentException e) {
			errorText.setText(e.getMessage());
		}

		sluitScherm(btnToevoegen);

	}

	@FXML
	private void handleHerinneringVersturenAction(ActionEvent event) throws IOException {

	}
}