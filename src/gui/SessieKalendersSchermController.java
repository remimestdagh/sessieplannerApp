package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Sessie;
import domein.SessieKalender;
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
import javafx.stage.Stage;

public class SessieKalendersSchermController extends AnchorPane implements Initializable,SchermController{
	
	private DomeinController dc;
	
	@FXML
	private Button btnHoofdmenu;
	
	@FXML
	private Button btnEdit;
	
	@FXML
	private TableView tblView;

	@Override
	public void setDomainController(DomeinController dc) {
		this.dc = dc;
		
		TableColumn<SessieKalender, String> academiejaarColumn = new TableColumn<>("Academiejaar");
		academiejaarColumn.setCellValueFactory(new PropertyValueFactory<>("academiejaar"));
		
		TableColumn<SessieKalender, String> startdatumColumn = new TableColumn<>("Start Datum");
		startdatumColumn.setCellValueFactory(new PropertyValueFactory<>("startdatum"));
		
		TableColumn<SessieKalender, String> einddatumColumn = new TableColumn<>("Eind Datum");
		einddatumColumn.setCellValueFactory(new PropertyValueFactory<>("einddatum"));
		
		tblView.setItems(dc.getSessieKalenders());
		tblView.getColumns().addAll(academiejaarColumn, startdatumColumn, einddatumColumn);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    private void handleHoofdmenuAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/HoofdScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnHoofdmenu.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
	
	@FXML
    private void handleEditSessieKalenderAction(ActionEvent event) throws IOException {
		dc.setGeselecteerdeSessieKalender((SessieKalender)tblView.getSelectionModel().getSelectedItem());
		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SessieKalenderScherm.fxml"));
		Parent root = (Parent)loader.load();
		SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnEdit.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
