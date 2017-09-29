package mainApp;

import view.ClientViewController;
import view.ProspectViewController;
import view.RepresentativeOverviewController;
import view.RootLayoutController;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.MySqlAdapter;
import type.AlertDialog;
import type.TypeError;

public class Main extends Application {



	// ***********   MAIN APPLICATION   ***********
	

	// Main
    public static void main(String[] args) {
        launch(args);
    }


	// Main Constructor
	
	/**
	 * Class constructor.
	 */
    public Main() {
    }
	

	// Init
	
	/**
	 * Called before the GUI starts.
	 * <p>
	 * This method calls the <code>loadData()</code> 
	 * function.
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
    @Override
    public void init() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	loadData();
    	// TODO test
    	/*
    	for (Representative representative : RepresentativeDAO.getRepresentativeListDAO()) {
    		System.out.println(
    				representative.getIdPerson()
    				+ " " + representative.getFirstName()
    				+ " " + representative.getBasicSalary());
    	}
    	for (Representative representative : Representative.getRepresentativeList()) {
    		System.out.println(
    				representative.getIdPerson()
    				+ " " + representative.getFirstName()
    				+ " " + representative.getBasicSalary());
    	}
    	*/
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
	 * Called on application exit.
	 */
	@Override
	public void stop() {
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
            loader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
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
			new AlertDialog(TypeError.INIT_ROOT_LAYOUT_ERROR);
        }        
    }



	// **********   REPRESENTATIVE VIEW   *********


	//Shows the representant overview inside the root layout.
	public void showRepresentativeOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/RepresentativeView.fxml"));
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
			loader.setLocation(Main.class.getResource("/view/ClientView.fxml"));
			AnchorPane clientView = (AnchorPane) loader.load();

			// Set client view into root layout
			rootLayout.setCenter(clientView);

			// Give the controller access to the main app
			ClientViewController controller = loader.getController();
			controller.setMainApp(this);
		}
		catch (IOException e) {
			e.printStackTrace();
			new AlertDialog(TypeError.SHOW_CLIENT_VIEW_ERROR);
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
			loader.setLocation(Main.class.getResource("/view/ProspectView.fxml"));
			AnchorPane prospectView = (AnchorPane) loader.load();

			// Set prospect view into root layout
			rootLayout.setCenter(prospectView);

			// Give the controller access to the main app
			ProspectViewController controller = loader.getController();
			controller.setMainApp(this);
		}
		catch (IOException e) {
			e.printStackTrace();
			new AlertDialog(TypeError.SHOW_PROSPECT_VIEW_ERROR);
		}
	}
	


	// ***********   DATA PERSISTENCE   ***********


	// Data loader
	
	/**
	 * Calls the <code>loadData()</code> from <code>MySqlAdapter</code>.
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public void loadData() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		MySqlAdapter.loadData();
	}
	

}
