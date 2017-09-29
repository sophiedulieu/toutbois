package view;

import java.sql.SQLException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import dao.ClientDAO;
import dao.ProspectDAO;
import model.Client;
import model.Prospect;
import type.AlertDialog;
import type.TypeWarning;



/**
 * @author	Oupouwaout
 */
public class ProspectViewController extends CompanyViewController {



	// ****************   TABLE   *****************


	@FXML
	protected TableView<Prospect> companyTable;
	@FXML
	protected TableColumn<Prospect, LocalDate> lastVisitColumn;



	// *****************   FORM   *****************


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



	// *****************   INIT   *****************


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

			//Object
			Prospect prospect = new Prospect (
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
			
			//Data
			super.handleAdd(prospect);
			ProspectDAO.insertProspectDAO(prospect);
			// TODO Rollback on duplicate siret exception
		}
		
	}


	// btnEdit
	@FXML
	protected void handleEdit() throws SQLException {
		super.handleEdit();
		
		Prospect selectedProspect = companyTable.getSelectionModel().getSelectedItem();
		if (selectedProspect != null) {
			// Object
			selectedProspect.setLastVisit(lastVisitPicker.getValue());
			companyTable.refresh();
			// Data
			ProspectDAO.editProspectDAO(selectedProspect);
		}
	}
	
	
	// btnMutate
	@FXML
	protected void handleMutate() throws SQLException {

		Prospect selectedProspect = companyTable.getSelectionModel().getSelectedItem();
		if (selectedProspect != null) {
			// Object
			Client client = new Client(selectedProspect);
			// Data
			ClientDAO.prospectToClient(client);
		}
		else {
			new AlertDialog(TypeWarning.NO_COMPANY_SELECTED);
		}
	}


	// btnDelete
	@FXML
	protected void handleDelete() throws SQLException {

		// Object
		Prospect selectedProspect = companyTable.getSelectionModel().getSelectedItem();
		if (selectedProspect != null) {
			// Object
			companyTable.getItems().remove(selectedProspect);
			// Data
			ProspectDAO.deleteProspect(selectedProspect);
		}
		else {
			new AlertDialog(TypeWarning.NO_COMPANY_SELECTED);
		}
	}

	

} // public class ProspectViewController
