package gui;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScherm extends VBox{
	//PARAMETERS
	private Scene scene;
	private DomeinController dc;
	private final Text schermTitel;
	private TextField emailVeld;
	private PasswordField wachtwoordVeld;
	private Button loginButton;
	
	//CONSTRUCTOR
	public LoginScherm(DomeinController dc)
	{
		this.dc = dc;
		this.schermTitel = new Text("Welkom op de HoGent sessieplanner!");
		this.emailVeld = new TextField("voornaam.famillienaam@student.hogent.be");
		this.wachtwoordVeld = new PasswordField();
		this.loginButton = new Button("Login");
		buildGui();
		buildButtons();
	}
	//METHODS
	private void buildGui()
	{
		
		this.schermTitel.getStyleClass().add("titel");
		// textfields
		VBox emailVBox = new VBox(new Label("Email-address"), emailVeld);
		VBox wachtwoordVBox = new VBox(new Label("Wachtwoord"), wachtwoordVeld);
		emailVBox.getStyleClass().add("vboxtextfield");
		wachtwoordVBox.getStyleClass().add("vboxtextfield");
		// add everything
		this.getChildren().addAll(schermTitel, emailVBox, wachtwoordVBox, loginButton);
		
	}
	
	private void buildButtons()
	{
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Clicked");
				scene.getStylesheets().clear();
				/*
				scene.getStylesheets().remove("application/application.css");
				scene.getStylesheets().add("application/loginScherm.css");
				*/
			}
		});
	}
	//GETTERS AND SETTERS

	public void setScene(Scene scene)
	{
		this.scene = scene;
	}
}
