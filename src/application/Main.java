package application;
	

import java.io.IOException;

import domein.DomeinController;
import gui.LoginScherm;
import gui.LoginSchermController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException{
		
		DomeinController dc = new DomeinController();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginScherm.fxml"));
		Parent root = (Parent)loader.load();
        LoginSchermController test = loader.getController();
        test.setDomainController(dc);
		
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
}

/*
try {
			//scherm maken
			LoginScherm root = new LoginScherm(new DomeinController());
			//scherm aan scene toevoegen
			Scene scene = new Scene(root,800,630);
			//stylesheets opzetten
			scene.getStylesheets().add(getClass().getResource("loginScherm.css").toExternalForm());
			//scene aan stage toevoegen
			primaryStage.setScene(scene);
			//stage tonen
			primaryStage.show();
			
			 root.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
*/