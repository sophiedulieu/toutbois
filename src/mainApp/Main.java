package mainApp;

import model.Person;
import model.Representative;
import view.RepresentativeOverviewController;
import view.RootLayoutController;
import model.ListWrapper;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	BorderPane rootLayout;

	public Main() {}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ToutBois");     
        initRootLayout();      		
	}
	
	
	public void initRootLayout() {
        try {
        	// Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
	
	
	//Shows the representant overview inside the root layout.
	public void showRepresentativeOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RepresentativeView.fxml"));
            AnchorPane representativeOverviewController = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(representativeOverviewController);
            
         // Give the controller access to the main app.
            RepresentativeOverviewController controller = loader.getController();
            controller.setMain(this);          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	public Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
	

}
