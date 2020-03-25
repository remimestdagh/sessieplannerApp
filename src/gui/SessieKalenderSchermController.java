package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Sessie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SessieKalenderSchermController extends AnchorPane implements Initializable, SchermController{
	
	private DomeinController dc;
	
	@FXML
	private TableView tblView;
	
	@FXML
	private Button btnHoofdmenu;
	
	@FXML
	private Button btnSessieToevoegen;
	
	@FXML
	private Button btnBeheerSessie;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void setDomainController(DomeinController dc) {
		this.dc = dc;
		
		
		TableColumn<Sessie, String> verantwoordelijkeColumn = new TableColumn<>("Naam verantwoordelijke");
		verantwoordelijkeColumn.setCellValueFactory(new PropertyValueFactory<>("naamVerantwoordelijke"));
		
		TableColumn<Sessie, String> titelColumn = new TableColumn<>("Titel");
		titelColumn.setCellValueFactory(new PropertyValueFactory<>("titel"));
		
		TableColumn<Sessie, String> startDatumColumn = new TableColumn<>("Start datum");
		startDatumColumn.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
		
		TableColumn<Sessie, String> eindDatumColumn = new TableColumn<>("Eind datum");
		eindDatumColumn.setCellValueFactory(new PropertyValueFactory<>("eindDatum"));
		
		TableColumn<Sessie, String> plaatsenColumn = new TableColumn<>("Aanwezigen / Vrije plaatsen");
		plaatsenColumn.setCellValueFactory(new PropertyValueFactory<>("aanwezigenOrVrijePlaatsen"));
		
		tblView.setItems(dc.getSessiesfromGeselecteerdeSessieKalender());
		tblView.getColumns().addAll(verantwoordelijkeColumn, titelColumn, startDatumColumn, eindDatumColumn, plaatsenColumn);
		
	}
	
	@FXML
    private void handleHoofdmenuAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SessieKalendersScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnHoofdmenu.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
    private void handleSessieToevoegenAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CreateSessieScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnSessieToevoegen.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
    private void handleBeheerSessieAction(ActionEvent event) throws IOException {
		dc.setGeselecteerdeSessie((Sessie)tblView.getSelectionModel().getSelectedItem());
		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BeheerSessieScherm.fxml"));
		Parent root = (Parent)loader.load();
		SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnBeheerSessie.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}