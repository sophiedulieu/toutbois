package view;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Client;
import model.Prospect;

public class ProspectViewController extends CompanyViewController {



	// ************* TABLE **********


	@FXML
	protected TableView<Prospect> companyTable;
	@FXML
	protected TableColumn<Prospect, LocalDate> lastVisitColumn;



	// **************   FORM  *************


	// Prospect
	@FXML
	protected DatePicker lastVisitPicker;


	// Filling content
	protected void showDetails(Prospect prospect) {

		if (prospect != null) {
			lastVisitPicker.setValue(prospect.getLastVisit());
		}
		else {
			lastVisitPicker.setValue(null);
		}
		super.showDetails(prospect);
	}



	// *********** INIT *********************


	@FXML
	protected void initialize() {

		// Table
		companyTable.setItems(Prospect.getProspectList());
		lastVisitColumn.setCellValueFactory(cellData 
				-> cellData.getValue().lastVisitProperty());

		// Form

		// Super
		super.initialize();
		showDetails(null);

		// Table <-> Form
		companyTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showDetails(newValue));
	}



	// ************* BUTTONS *************


	// btnReset (vider)
	@FXML
	protected void handleReset() {
		companyTable.getSelectionModel().clearSelection();
		showDetails(null);
	}


	// btnAdd
	@FXML
	protected void handleAdd() {
		if (true) { // TODO inputValidation

			new Prospect (
					// company
					companyNameField.getText(),
					Long.parseLong(siretField.getText()), 

					// contact
					firstNameField.getText(), lastNameField.getText(), 
					phoneNumField.getText(), faxNumField.getText(), 
					emailField.getText(), 

					// address
					numAddressField.getText(), typeStreetBox.getValue(), 
					nameStreetField.getText(), complementAddressField.getText(),
					cityField.getText(), postalCodeField.getText(), 

					// representative
					representativeBox.getValue(),

					// prospect
					lastVisitPicker.getValue());
		}
	}


	// btnEdit
	@FXML
	protected void handleEdit() {
		super.handleEdit();
		
		Prospect selectedProspect = companyTable.getSelectionModel().getSelectedItem();
		if (selectedProspect != null) {
			selectedProspect.setLastVisit(lastVisitPicker.getValue());
			companyTable.refresh();
		}
	}
	
	
	// btnMutate
	@FXML
	protected void handleMutate() {

		Prospect selectedProspect = companyTable.getSelectionModel().getSelectedItem();
		if (selectedProspect != null) {
			new Client(selectedProspect);
		}
	}






} // public class ProspectViewController
