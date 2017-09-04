package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mainApp.Main;

public class RootLayoutController {
	
	
	
	// ************* MAIN APP *************
	
	
	private Main mainApp;
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}
	

	
	// ************* MENU BAR *************
	
	
	// Manage Clients
	@FXML
	private void handleShowClientView() {
		mainApp.showClientView();
	}
	
	// Manage Prospects
	@FXML
	private void handleShowProspectView() {
		mainApp.showProspectView();
	}
	
	// Manage Representative
	@FXML
	private void handleShowRepresentativeView() {
		mainApp.showRepresentativeOverview();;
	}
	
	// Help
	// TODO Help
	
	// About
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		String content = "Logiciel de gestion d'un annuaire de clients " + 
				"et de prospects, et des repr�sentants de l'entreprise." + 
				"\n\nAuteur : Oupouwaout et Sophie";
		alert.setTitle("ToutBois");
		alert.setHeaderText("A propos");
		alert.setContentText(content);

		alert.showAndWait();
	}
	
	// Exit
	@FXML
	private void handleExit() {
		Platform.exit();
	}
	

} // public class RootLayoutController
