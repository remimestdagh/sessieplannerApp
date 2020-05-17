package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.ISessie;
import domein.Sessie;
import domein.SessieDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class SessieOverzichtSchermController extends SchermController implements Initializable, PropertyChangeListener{
	
	@FXML
	private TableView tblView;
	
	@FXML
	private Button btnSessieToevoegen;
	
	@FXML
	private Text geselecteerdeKalender;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		
		dc.addSessieListListener(this);
		
		if(getDC().gebruikerIsHoofdverantwoordelijke()) {
			maakSessieTable(tblView, getDC().getSessies());
		}else {
			maakSessieTable(tblView, getDC().getSessies());
		}
		
		geselecteerdeKalender.setText("Sessies voor kalender: " + getDC().getGeselecteerdeSessieKalender().getAcademiejaar() );
	}
	
	@FXML
    private void handleSessieToevoegenAction(ActionEvent event) throws IOException {
		SessieDTO dto = new SessieDTO();
		dto.setTitel( "Nieuwe sessie" );
		dto.setStartDatum( LocalDateTime.now() );
		dto.setEindDatum( LocalDateTime.now() );
		dto.setNaamGastspreker( "Naam gastspreker" );
		dto.setSessieAanmaker(getDC().getNaamIngelogdeGebruiker());
		dto.setMAX_CAPACITEIT( 1 );
		dto.setLokaalCode( "Lokaal code" );
		
		getDC().addSessieToGeselecteerdeSessieKalender(dto);
		
    }
	
	@FXML
    private void handleSessieVerwijderenAction(ActionEvent event) throws IOException {
        getDC().verwijderSessieFromGeselecteerdeSessieKalender((ISessie) tblView.getSelectionModel().getSelectedItem());
    }
	
	@FXML
    private void handleBeheerSessieAction(MouseEvent event) throws IOException {
		getDC().setGeselecteerdeSessie((Sessie)tblView.getSelectionModel().getSelectedItem());
    }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if(getDC().gebruikerIsHoofdverantwoordelijke()) {
			maakSessieTable(tblView, getDC().getSessiesfromGeselecteerdeSessieKalender());
		}else {
			maakSessieTable(tblView, getDC().getSessiesFromVerantwoordelijke());
		}
	}
}
