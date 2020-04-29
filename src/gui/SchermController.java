package gui;

import java.io.IOException;
import domein.DomeinController;
import domein.IAankondiging;
import domein.IGebruiker;
import domein.IMedia;
import domein.ISessie;
import domein.ISessieKalender;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
	
	public void sluitScherm(Button button) {
		Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
	}
	
	/*
	 * Super method waarvan elk scherm erft. elk scherm dat een ander scherm oproept, roept deze methode aan.
	 */
	public void creëerScherm(String scherm) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/" + scherm + "Scherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController schermController = loader.getController();
        schermController.setDomeinController(getDC());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/fonts/windowicon.jpg"));
        //Nieuwe scherm een gepaste titel geven
        switch (scherm)
        {
        case "CreateSessie" : stage.setTitle("Sessie Toevoegen"); break;
        case "CreateGebruiker" : stage.setTitle("Gebruiker Toevoegen");
        case "CreateAankondiging" : stage.setTitle("Aankondiging Toevoegen"); break;
        case "CreateMedia" : stage.setTitle("Media Toevoegen"); break;
        }
        stage.show();
	}
	
	/*
	 * Super method waarvan elk scherm erft. elk scherm dat zichzelf van scherm doet veranderen roept dit aan.
	 */
	public void verranderScherm(Button button, String scherm) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/" + scherm + "Scherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController schermController = loader.getController();
        schermController.setDomeinController(dc);
        Stage stage = (Stage) button.getScene().getWindow();
        Scene scene = new Scene(root);
        //stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
	}
	
	public void maakSessieTable(TableView tblView, ObservableList<ISessie> list) {
		tblView.getColumns().clear();
		
		TableColumn<ISessie, String> verantwoordelijkeColumn = new TableColumn<>("Naam verantwoordelijke");
		verantwoordelijkeColumn.setCellValueFactory(new PropertyValueFactory<>("sessieAanmaker"));
		
		TableColumn<ISessie, String> titelColumn = new TableColumn<>("Titel");
		titelColumn.setCellValueFactory(new PropertyValueFactory<>("titel"));
		
		TableColumn<ISessie, String> startDatumColumn = new TableColumn<>("Start datum");
		startDatumColumn.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
		
		TableColumn<ISessie, String> eindDatumColumn = new TableColumn<>("Eind datum");
		eindDatumColumn.setCellValueFactory(new PropertyValueFactory<>("eindDatum"));
		
		TableColumn<ISessie, String> plaatsenColumn = new TableColumn<>("Aanwezigen / Vrije plaatsen");
		plaatsenColumn.setCellValueFactory(new PropertyValueFactory<>("aanwezigenOrVrijePlaatsen"));
		
		tblView.setItems(list);
		tblView.getColumns().addAll(verantwoordelijkeColumn, titelColumn, startDatumColumn, eindDatumColumn, plaatsenColumn);
		tblView.getSortOrder().addAll(startDatumColumn,verantwoordelijkeColumn);
		tblView.sort();
	}
	
	public void maakGebruikerTable(TableView tblView, ObservableList<IGebruiker> list) {
		tblView.getColumns().clear();
		
		TableColumn<IGebruiker, String> naamColumn = new TableColumn<>("Naam");
		naamColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
		
		TableColumn<IGebruiker, String> chamiloColumn = new TableColumn<>("Naam Chamilo");
		chamiloColumn.setCellValueFactory(new PropertyValueFactory<>("naamChamilo"));
		
		
		TableColumn<IGebruiker, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		TableColumn<IGebruiker,String> typeColumn = new TableColumn<>("Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		
		tblView.setItems(list);
		tblView.getColumns().addAll(naamColumn, chamiloColumn, statusColumn,typeColumn);
	}
	
	public void maakAankondigingTable(TableView tblView, ObservableList<IAankondiging> list) {
		tblView.getColumns().clear();
		
		TableColumn<IAankondiging, String> inhoudColumn = new TableColumn<>("Inhoud");
		inhoudColumn.setCellValueFactory(new PropertyValueFactory<>("inhoud"));
		
		TableColumn<IAankondiging, String> auteurColumn = new TableColumn<>("Auteur");
		auteurColumn.setCellValueFactory(new PropertyValueFactory<>("auteurNaam"));
		
		TableColumn<IAankondiging, String> publicatieDatumColumn = new TableColumn<>("Publicatie Datum");
		publicatieDatumColumn.setCellValueFactory(new PropertyValueFactory<>("publicatieDatum"));
		
		tblView.setItems(list);
		tblView.getColumns().addAll(inhoudColumn, auteurColumn, publicatieDatumColumn);
	}
	
	public void maakMediaTable(TableView tblView, ObservableList<IMedia> list) {
		tblView.getColumns().clear();
		
		TableColumn<IMedia, String> typeColumn = new TableColumn<>("Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		tblView.setItems(list);
		tblView.getColumns().addAll(typeColumn);
	}
	
	public void maakKalenderTable(TableView tblView, ObservableList<ISessieKalender> list) {
		tblView.getColumns().clear();
		
		TableColumn<ISessieKalender, String> academiejaarColumn = new TableColumn<>("Academiejaar");
		academiejaarColumn.setCellValueFactory(new PropertyValueFactory<>("academiejaar"));
		
		TableColumn<ISessieKalender, String> startdatumColumn = new TableColumn<>("Start Datum");
		startdatumColumn.setCellValueFactory(new PropertyValueFactory<>("startdatum"));
		
		TableColumn<ISessieKalender, String> einddatumColumn = new TableColumn<>("Eind Datum");
		einddatumColumn.setCellValueFactory(new PropertyValueFactory<>("einddatum"));
		
		tblView.setItems(list);
		tblView.getColumns().addAll(academiejaarColumn, startdatumColumn, einddatumColumn);
	}
	
	

}
