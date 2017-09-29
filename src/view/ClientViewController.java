package view;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import dao.ClientDAO;
import dao.ProspectDAO;
import model.Client;
import model.Prospect;
import type.AlertDialog;
import type.TypeWarning;



/**
 * @author	Oupouwaout
 */
public class ClientViewController extends CompanyViewController {


	// ****************   TABLE   *****************


	@FXML
	protected TableView<Client> companyTable;
	@FXML
	protected TableColumn<Client, Integer> nbOrderColumn;



	// *****************   FORM   *****************


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



	// *****************   INIT   *****************


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



	// ***************   BUTTONS   ****************


	// btnReset (vider)
	@FXML
	protected void handleReset() {
		companyTable.getSelectionModel().clearSelection();
		showDetails(null);
	}


	// btnAdd
	@FXML
	protected void handleAdd() throws SQLException {
		if (true) { // TODO inputValidation

			// Object
			Client client = new Client (
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

			//Data
			super.handleAdd(client);
			ClientDAO.insertClientDAO(client);
			// TODO Rollback on duplicate siret exception
		}
	}


	// btnEdit
	@FXML
	protected void handleEdit() throws SQLException {
		super.handleEdit();

		Client selectedClient = companyTable.getSelectionModel().getSelectedItem();
		if (selectedClient != null) {
			// Object
			companyTable.refresh();
			// Data
			ClientDAO.editClientDAO(selectedClient);
		}
	}
	
	
	// btnMutate
	@FXML
	protected void handleMutate() throws SQLException {

		Client selectedClient = companyTable.getSelectionModel().getSelectedItem();
		if (selectedClient != null) {
			// Object
			Prospect prospect = new Prospect(selectedClient);
			// Data
			ProspectDAO.clientToProspect(prospect);
		}
		else {
			new AlertDialog(TypeWarning.NO_COMPANY_SELECTED);
		}
	}


	// btnDelete
	@FXML
	protected void handleDelete() throws SQLException {

		Client selectedClient = companyTable.getSelectionModel().getSelectedItem();
		if (selectedClient != null) {
			// Object
			companyTable.getItems().remove(selectedClient);
			// Data
			ClientDAO.deleteClient(selectedClient);
		}
		else {
			new AlertDialog(TypeWarning.NO_COMPANY_SELECTED);
		}
	}
	
	

} // public class ClientViewController
