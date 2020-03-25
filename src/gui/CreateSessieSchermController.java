package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateSessieSchermController extends AnchorPane implements Initializable,SchermController{
	
	private DomeinController dc;
	
	@FXML
	private Button btnCancel;
	
	@FXML
	private TextField txtTitel;
	
	@FXML
	private TextField txtGastspreker;
	
	@FXML
	private TextField txtMaxCapaciteit;
	
	@FXML
	private TextField txtLokaal;
	
	@FXML
	private TextField txtStartDatum;
	
	@FXML
	private TextField txtEindDatum;
	
	@FXML
	private Button btnToevoegen;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void setDomainController(DomeinController dc) {
		this.dc = dc;
	}
	
	@FXML
    private void handleCancelAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SessieKalenderScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
    private void handleSessieToevoegenAction(ActionEvent event) throws IOException {
		String titel = txtTitel.getText();
		String gastspreker = txtGastspreker.getText();
		int maxCapaciteit = Integer.parseInt(txtMaxCapaciteit.getText());
		String lokaal = txtLokaal.getText();
		String startDatum = txtStartDatum.getText();
		String eindDatum = txtEindDatum.getText();
		
		dc.addSessieToGeselecteerdeSessieKalender(titel, gastspreker, lokaal, maxCapaciteit, startDatum, eindDatum);
		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SessieKalenderScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnToevoegen.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}