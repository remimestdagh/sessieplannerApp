package gui;

import java.io.IOException;

import domein.Aankondiging;
import domein.DomeinController;
import domein.Gebruiker;
import domein.Sessie;
import domein.SessieKalender;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public abstract class SchermController extends AnchorPane {
	
	private DomeinController dc;
	
	public void setDomeinController(DomeinController dc) {
		this.dc = dc;
	}
	
	public DomeinController getDC() {
		return dc;
	}
	
	public void verranderScherm(Button button, String scherm) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/" + scherm + "Scherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController schermController = loader.getController();
        schermController.setDomeinController(dc);
        Stage stage = (Stage) button.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	public void maakSessieTable(TableView tblView, ObservableList<Sessie> list) {
		tblView.getColumns().clear();
		
		TableColumn<Sessie, String> verantwoordelijkeColumn = new TableColumn<>("Naam verantwoordelijke");
		verantwoordelijkeColumn.setCellValueFactory(new PropertyValueFactory<>("sessieAanmaker"));
		
		TableColumn<Sessie, String> titelColumn = new TableColumn<>("Titel");
		titelColumn.setCellValueFactory(new PropertyValueFactory<>("titel"));
		
		TableColumn<Sessie, String> startDatumColumn = new TableColumn<>("Start datum");
		startDatumColumn.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
		
		TableColumn<Sessie, String> eindDatumColumn = new TableColumn<>("Eind datum");
		eindDatumColumn.setCellValueFactory(new PropertyValueFactory<>("eindDatum"));
		
		TableColumn<Sessie, String> plaatsenColumn = new TableColumn<>("Aanwezigen / Vrije plaatsen");
		plaatsenColumn.setCellValueFactory(new PropertyValueFactory<>("aanwezigenOrVrijePlaatsen"));
		
		tblView.setItems(list);
		tblView.getColumns().addAll(verantwoordelijkeColumn, titelColumn, startDatumColumn, eindDatumColumn, plaatsenColumn);
		tblView.getSortOrder().addAll(startDatumColumn,verantwoordelijkeColumn);
		tblView.sort();
	}
	
	public void maakGebruikerTable(TableView tblView, ObservableList<Gebruiker> list) {
		tblView.getColumns().clear();
		
		TableColumn<Gebruiker, String> naamColumn = new TableColumn<>("Naam");
		naamColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
		
		TableColumn<Gebruiker, String> chamiloColumn = new TableColumn<>("Naam Chamilo");
		chamiloColumn.setCellValueFactory(new PropertyValueFactory<>("naamChamilo"));
		
		TableColumn<Gebruiker, String> emailColumn = new TableColumn<>("E-mail adres");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailadres"));
		
		TableColumn<Gebruiker, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		tblView.setItems(list);
		tblView.getColumns().addAll(naamColumn, chamiloColumn, emailColumn, statusColumn);
	}
	
	public void maakAankondigingTable(TableView tblView, ObservableList<Aankondiging> list) {
		tblView.getColumns().clear();
		
		TableColumn<Aankondiging, String> inhoudColumn = new TableColumn<>("Inhoud");
		inhoudColumn.setCellValueFactory(new PropertyValueFactory<>("inhoud"));
		
		TableColumn<Aankondiging, String> auteurColumn = new TableColumn<>("Auteur");
		auteurColumn.setCellValueFactory(new PropertyValueFactory<>("auteurNaam"));
		
		TableColumn<Aankondiging, String> publicatieDatumColumn = new TableColumn<>("Publicatie Datum");
		publicatieDatumColumn.setCellValueFactory(new PropertyValueFactory<>("publicatieDatum"));
		
		tblView.setItems(list);
		tblView.getColumns().addAll(inhoudColumn, auteurColumn, publicatieDatumColumn);
	}
	
	public void maakKalenderTable(TableView tblView, ObservableList<SessieKalender> list) {
		tblView.getColumns().clear();
		
		TableColumn<SessieKalender, String> academiejaarColumn = new TableColumn<>("Academiejaar");
		academiejaarColumn.setCellValueFactory(new PropertyValueFactory<>("academiejaar"));
		
		TableColumn<SessieKalender, String> startdatumColumn = new TableColumn<>("Start Datum");
		startdatumColumn.setCellValueFactory(new PropertyValueFactory<>("startdatum"));
		
		TableColumn<SessieKalender, String> einddatumColumn = new TableColumn<>("Eind Datum");
		einddatumColumn.setCellValueFactory(new PropertyValueFactory<>("einddatum"));
		
		tblView.setItems(list);
		tblView.getColumns().addAll(academiejaarColumn, startdatumColumn, einddatumColumn);
	}
}
