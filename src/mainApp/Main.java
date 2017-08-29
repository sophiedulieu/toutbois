package mainApp;

import model.Person;
import model.Representative;
import model.ListWrapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main extends Application {

	
	private ObservableList<Representative> representativeData = FXCollections.observableArrayList();
	
	public ObservableList<Representative> getRepresentative() {
		return representativeData;
	}
	
	public Main() {}
}
