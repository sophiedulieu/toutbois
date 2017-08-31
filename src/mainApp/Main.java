package mainApp;

import view.ClientViewController;
import view.ProspectViewController;
import view.RepresentativeOverviewController;
import view.RootLayoutController;

import java.io.IOException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Address;
import model.Client;
import model.Contact;
import model.Prospect;
import model.Representative;
import type.TypeStreet;

public class Main extends Application {



	// ***********   MAIN APPLICATION   ***********
	

	// Main
    public static void main(String[] args) {
        launch(args);
    }
	public Main() {
		// TODO data test
		Representative r1 = new Representative( "Roger", "Dupont", 
				"456", "456", "lkj@h.com", 0.25, 1500.0 );
		Representative r2 = new Representative( "Stan", "Smith", 
				"456", "456", "lkj@h.com", 0.25, 1500.0 );
		Contact m1 = new Contact( "Francis", "Kuntz", 
				"123", "123", "aze@c.com");
		Contact m2 = new Contact( "Marc", "O'Polo", 
				"123", "123", "aze@c.com");
		Address a1 = new Address ("1", TypeStreet.ROUTE, "des Chênes", 
				"NA", "Paris", "75001");
		Address a2 = new Address ("2", TypeStreet.ROUTE, "des Bouleaux", 
				"NA", "Colombes", "92700");
		new Client ("Tchoutchou", 123L, m1, a1, r1);
		new Prospect("Mattel", 345L, m2, a2, r2, LocalDate.now());
		}
	

	// Init
	
	/**
	 * Calls the <code>loadData()</code> function before the GUI starts.
	 */
	@Override
	public void init() {
		 //loadData();
	}
	

	// Start
	
	private Stage primaryStage;
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	

	/**
	 * Opens the main application window and launches the first scene
	 * to be displayed.
	 * <p>
	 * The icon and title for the application are defined here.
	 * The method launches an empty JavaFX root layout which will 
	 * contain the other JavaFX scenes. The welcome page with 
	 * login funtionality will be launched from here when/if implemented.
	 * 
	 * @param	primaryStage	the stage to be launched
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ToutBois");     
        initRootLayout();      		
	}
	
	
	// Stop
	
	/**
	 * Calls the <code>saveData()</code> function on application exit.
	 */
	@Override
	public void stop() {
		//saveData();
	}



	// *************   ROOT LAYOUT   **************


	private BorderPane rootLayout;

	/**
	 * Launches an empty JavaFX scene with a menu bar.
	 * <p>
	 * The other scenes will be displayed in the center of this one.
	 */
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
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }



	// **********   REPRESENTATIVE VIEW   *********


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



	// *************   CLIENT VIEW   **************


	/**
	 * Launches the client management window.
	 * <p>
	 * This method displays the client management window in the
	 * center of the root layout.
	 */
	public void showClientView() {
		try {
			// Load client view
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/ClientView.fxml"));
			AnchorPane clientView = (AnchorPane) loader.load();

			// Set client view into root layout
			rootLayout.setCenter(clientView);

			// Give the controller access to the main app
			ClientViewController controller = loader.getController();
			controller.setMainApp(this);
		}
		catch (IOException e) {
			// TODO Error
			e.printStackTrace();
		}
	}



	// ************   PROSPECT VIEW   *************


	/**
	 * Launches the prospect management window.
	 * <p>
	 * This method displays the prospect management window in the
	 * center of the root layout.
	 */
	public void showProspectView() {
		try {
			// Load prospect view
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/ProspectView.fxml"));
			AnchorPane prospectView = (AnchorPane) loader.load();

			// Set prospect view into root layout
			rootLayout.setCenter(prospectView);

			// Give the controller access to the main app
			ProspectViewController controller = loader.getController();
			controller.setMainApp(this);
		}
		catch (IOException e) {
			// TODO Error
			e.printStackTrace();
		}
	}
	

}
