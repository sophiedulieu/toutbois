package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Client;
import model.Prospect;



/**
 * @author	Oupouwaout
 */
public class ClientViewController extends CompanyViewController {



	// ************* TABLE **********


	@FXML
	protected TableView<Client> companyTable;
	@FXML
	protected TableColumn<Client, Integer> nbOrderColumn;



	// **************   FORM  *************


	// Client
	@FXML
	protected TextField nbOrderField;
	

	// Filling content
	protected void showDetails(Client client) {

		if (client != null) {
			nbOrderField.setText(client.getNbOrder().toString());
		}
		else {
			nbOrderField.setText(null);
		}
		super.showDetails(client);
	}



	// *********** INIT *********************


	@FXML
	protected void initialize() {

		// Table
		companyTable.setItems(Client.getClientList());
		nbOrderColumn.setCellValueFactory(cellData 
				-> cellData.getValue().nbOrderProperty().asObject());

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

			new Client (
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
					representativeBox.getValue()
			);
		}
	}


	// btnEdit
	@FXML
	protected void handleEdit() {
		super.handleEdit();
		
		Client selectedClient = companyTable.getSelectionModel().getSelectedItem();
		if (selectedClient != null) {
			companyTable.refresh();
		}
	}
	
	
	// btnMutate
	@FXML
	protected void handleMutate() {

		Client selectedClient = companyTable.getSelectionModel().getSelectedItem();
		if (selectedClient != null) {
			new Prospect(selectedClient);
		}
	}
	






} // public class ClientViewController
