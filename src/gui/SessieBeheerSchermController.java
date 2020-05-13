package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import domein.DomeinController;
import domein.Gebruiker;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SessieBeheerSchermController extends SchermController implements Initializable{
	
	@FXML
	private Button btnHoofdmenu;
	
	@FXML
	private BorderPane borderPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void setDomeinController(DomeinController dc) {
		super.setDomeinController(dc);
		
		if(!getDC().gebruikerIsHoofdverantwoordelijke()) {
			btnHoofdmenu.setText("Terug naar hoofdmenu");
		}
		
		try {

			FXMLLoader overzichtloader = new FXMLLoader(getClass().getResource("/gui/SessieOverzichtScherm.fxml"));
			Parent overzicht = (Parent) overzichtloader.load();
			SchermController OverzichtSchermController = overzichtloader.getController();
			OverzichtSchermController.setDomeinController(getDC());
			borderPane.setLeft(overzicht);

			FXMLLoader detailloader = new FXMLLoader(getClass().getResource("/gui/SessieDetailScherm.fxml"));
			Parent detail = (Parent) detailloader.load();
			SchermController detailSchermController = detailloader.getController();
			detailSchermController.setDomeinController(getDC());
			borderPane.setCenter(detail);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    private void handleHoofdmenuAction(ActionEvent event) throws IOException {

        if(getDC().gebruikerIsHoofdverantwoordelijke()) {
        	verranderScherm(btnHoofdmenu, "SessieKalenderBeheer");
        }else {
        	verranderScherm(btnHoofdmenu, "Hoofd");
        }
    }

}