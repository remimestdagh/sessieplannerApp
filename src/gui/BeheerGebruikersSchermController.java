package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Gebruiker;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BeheerGebruikersSchermController extends AnchorPane implements Initializable,SchermController{
	
	private DomeinController dc;
	
	@FXML
	private TableView tblView;
	
	@FXML
	private Button btnHoofmenu;
	
	@FXML
	private Button btnEdit;
	
	@FXML
	private Button btnCreate;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void setDomainController(DomeinController dc) {
		this.dc = dc;
		updateTable();
	}
	
	private void updateTable() {
		tblView.getColumns().clear();
		
		TableColumn<Gebruiker, String> naamColumn = new TableColumn<>("Naam");
		naamColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
		
		TableColumn<Gebruiker, String> chamiloColumn = new TableColumn<>("Naam Chamilo");
		chamiloColumn.setCellValueFactory(new PropertyValueFactory<>("naamChamilo"));
		
		TableColumn<Gebruiker, String> emailColumn = new TableColumn<>("E-mail adres");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailadres"));
		
		TableColumn<Gebruiker, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		tblView.setItems(dc.getGebruikers());
		tblView.getColumns().addAll(naamColumn, chamiloColumn, emailColumn, statusColumn);
	}
	
	@FXML
    private void handleHoofdmenuAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/HoofdScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnHoofmenu.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
    private void handleEditGegevensAction(ActionEvent event) throws IOException{
		Gebruiker gebruiker = (Gebruiker) tblView.getSelectionModel().getSelectedItem();
		dc.setGeselecteerdeGebruiker(gebruiker);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BeheerGebruikerScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnHoofmenu.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
    private void handleDeleteGebruikerAction(ActionEvent event){
    	Gebruiker gebruiker = (Gebruiker) tblView.getSelectionModel().getSelectedItem();
    	dc.verwijderGebruiker(gebruiker.getNaam());
    	updateTable();
    }
	
	@FXML
	private void handleCreateGebruikerAction(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CreateGebruikerScherm.fxml"));
		Parent root = (Parent)loader.load();
		SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnCreate.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
}