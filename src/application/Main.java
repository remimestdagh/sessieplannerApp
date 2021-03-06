package application;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import domein.DomeinController;
import gui.LoginSchermController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException{
		
		
		//domeincontroller
		DomeinController dc = new DomeinController();
		//eerste scherm uit xml laden
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginScherm.fxml"));
		Parent root = (Parent)loader.load();
		//eerste scherm initialiseren en domeincontroller meegeven
        LoginSchermController test = loader.getController();
        test.setDomeinController(dc);
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image("/fonts/windowicon.jpg"));
        stage.setTitle("IT-Lab Sessieplanner");
        
        
        //Maximizing screen (best afblijven, wisselvallig gedrag)
        /*
        stage.setMaximized(true);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        */
        
        

        stage.setScene(scene);
        stage.show();
        
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
}

