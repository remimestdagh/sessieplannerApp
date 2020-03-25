package gui;

import java.net.URL;
import java.util.ResourceBundle;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HoofdScherm extends VBox{
	//PARAMETERS
	private Scene scene;
	private DomeinController dc;
	private Button plaatsAankondiging;
	
	
	
	
	//CONSTRUCTOR
	public HoofdScherm(DomeinController dc)
	{
		this.dc = dc;
		this.plaatsAankondiging = new Button("Plaats aankondiging");
		buildGui();
		buildButtons();
	}
	
	

	//METHODS
	/**
	 * Deze methode kent events toe aan het klikken op buttons
	 */
	private void buildButtons()
	{
		plaatsAankondiging.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Start plaatsen aankondiging... ");
				//dc.plaatsAankondiging();
				/*
				scene.getStylesheets().remove("application/application.css");
				scene.getStylesheets().add("application/loginScherm.css");
				*/
			}
		});
	}
	
	
	/**
	 * deze methode voegt alle ui componenten toe aan het scherm
	 */
	private void buildGui()
	{
		// add everything
		this.getChildren().addAll(plaatsAankondiging);
	}
	
	//GETTERS AND SETTERS
	public void setScene(Scene scene)
	{
		this.scene = scene;
	}

}
