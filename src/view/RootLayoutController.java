package view;

import javafx.fxml.FXML;
import mainApp.Main;

public class RootLayoutController {
	
	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}
	
	//Leave program
	@FXML
	private void handleExit() {
		System.exit(0);
	}

}
