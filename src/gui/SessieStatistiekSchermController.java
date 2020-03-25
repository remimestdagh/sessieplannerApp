package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Gebruiker;
import domein.Sessie;
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

public class SessieStatistiekSchermController extends AnchorPane implements Initializable,SchermController{
	
	private DomeinController dc;
	
	@FXML
	private Button btnSessies;
	
	@FXML
	private TableView tblView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void setDomainController(DomeinController dc) {
		this.dc = dc;
		
		TableColumn<Gebruiker, String> naamColumn = new TableColumn<>("Naam");
		naamColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
		
		TableColumn<Gebruiker, String> chamiloColumn = new TableColumn<>("Naam Chamilo");
		chamiloColumn.setCellValueFactory(new PropertyValueFactory<>("naamChamilo"));
		
		TableColumn<Gebruiker, String> emailColumn = new TableColumn<>("E-mail adres");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailadres"));
		
		TableColumn<Gebruiker, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		tblView.setItems(dc.getGebruikersFromGeselecteerdeSessie());
		tblView.getColumns().addAll(naamColumn, chamiloColumn, emailColumn, statusColumn);
	}
	
	@FXML
    private void handleSessiesAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SessiesStatistiekScherm.fxml"));
		Parent root = (Parent)loader.load();
        SchermController test = loader.getController();
        test.setDomainController(dc);
        Stage stage = (Stage) btnSessies.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}