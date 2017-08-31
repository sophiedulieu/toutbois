package view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mainApp.Main;
import model.Company;
import model.Representative;
import type.TypeStreet;


public abstract class CompanyViewController {


	// ************* MAIN APP ************


	protected Main mainApp;

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}


	// ************* TABLE **********


	@FXML
	protected TableView<Company> companyTable;
	@FXML
	protected TableColumn<Company, Integer> idCompanyColumn;
	@FXML
	protected TableColumn<Company, String> companyNameColumn;
	@FXML
	protected TableColumn<Company, Long> siretColumn;
	@FXML
	protected TableColumn<Company, String> cityColumn;
	@FXML
	protected TableColumn<Company, String> representativeNameColumn;



	//**************   FORM  *************


	// Company
	@FXML
	protected TextField companyNameField;
	@FXML
	protected TextField siretField;

	// Contact
	@FXML
	protected TextField firstNameField;
	@FXML
	protected TextField lastNameField;
	@FXML
	protected TextField phoneNumField;
	@FXML
	protected TextField faxNumField;
	@FXML
	protected TextField emailField;

	// Address
	@FXML
	protected TextField numAddressField;
	@FXML
	protected ComboBox<TypeStreet> typeStreetBox;
	@FXML
	protected TextField nameStreetField;
	@FXML
	protected TextField complementAddressField;
	@FXML
	protected TextField cityField;
	@FXML
	protected TextField postalCodeField;

	// Representative
	@FXML
	protected ChoiceBox<Representative> representativeBox;
	@FXML
	protected TextField representativeFirstNameField;
	@FXML
	protected TextField representativeLastNameField;


	// Filling content
	protected void showDetails(Company company) {

		if (company != null) {
			// Company
			companyNameField.setText(company.getCompanyName());
			siretField.setText(company.getSiret().toString());
			// Contact
			firstNameField.setText(company.getContact().getFirstName());
			lastNameField.setText(company.getContact().getLastName());
			phoneNumField.setText(company.getContact().getPhoneNum());
			faxNumField.setText(company.getContact().getFaxNum());
			emailField.setText(company.getContact().getEmail());
			// Address
			numAddressField.setText(company.getAddress().getNumAddress());
			typeStreetBox.setValue(company.getAddress().getTypeStreet());
			nameStreetField.setText(company.getAddress().getNameStreet());
			complementAddressField.setText(
					company.getAddress().getComplementAddress());
			postalCodeField.setText(company.getAddress().getPostalCode());
			cityField.setText(company.getAddress().getCity());
			// Representative
			representativeBox.setValue(company.getRepresentative());
		}
		else {
			// Company
			companyNameField.setText(null);
			siretField.setText(null);
			// Contact
			firstNameField.setText(null);
			lastNameField.setText(null);
			phoneNumField.setText(null);
			faxNumField.setText(null);
			emailField.setText(null);
			// Address
			numAddressField.setText(null);
			typeStreetBox.setValue(null);
			nameStreetField.setText(null);
			complementAddressField.setText(null);
			postalCodeField.setText(null);
			cityField.setText(null);
			// Representative
			representativeBox.setValue(null);
		}
	}


	protected void showRepresentativeDetails(Representative representative) {
		if (representative != null) {
			representativeFirstNameField.setText(representative.getFirstName());
			representativeLastNameField.setText(representative.getLastName());
		}
		else {
			representativeFirstNameField.setText(null);
			representativeLastNameField.setText(null);
		}
	}



	// *********** INIT *********************


	@FXML
	protected void initialize() { 

		// Table
		idCompanyColumn.setCellValueFactory(cellData 
				-> cellData.getValue().idCompanyProperty().asObject());
		companyNameColumn.setCellValueFactory(cellData 
				-> cellData.getValue().companyNameProperty());
		siretColumn.setCellValueFactory(cellData 
				-> cellData.getValue().siretProperty().asObject());
		cityColumn.setCellValueFactory(cellData 
				-> cellData.getValue().getAddress().cityProperty());
		representativeNameColumn.setCellValueFactory(cellData 
				-> cellData.getValue().getRepresentative().lastNameProperty());

		// Form
		typeStreetBox.getItems().setAll(TypeStreet.values());
		representativeBox.getItems().setAll(
				Representative.getRepresentativeData());
		
		// Table <-> Form
		representativeBox.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showRepresentativeDetails(newValue));

		showDetails(null);
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
	}


	// btnEdit
	@FXML
	protected void handleEdit() {
		Company selectedCompany = companyTable.getSelectionModel().getSelectedItem();
		if (selectedCompany != null) {

			// company
			selectedCompany.setCompanyName(companyNameField.getText());
			selectedCompany.setSiret(Long.parseLong(siretField.getText()));
			// contact
			selectedCompany.getContact().setFirstName(firstNameField.getText());
			selectedCompany.getContact().setLastName(lastNameField.getText());
			selectedCompany.getContact().setPhoneNum(phoneNumField.getText());
			selectedCompany.getContact().setFaxNum(faxNumField.getText());
			selectedCompany.getContact().setEmail(emailField.getText());
			// address
			selectedCompany.getAddress().setNumAddress(numAddressField.getText());
			selectedCompany.getAddress().setTypeStreet(typeStreetBox.getValue());
			selectedCompany.getAddress().setNameStreet(nameStreetField.getText());
			selectedCompany.getAddress().setComplementAddress(
					complementAddressField.getText());
			selectedCompany.getAddress().setPostalCode(postalCodeField.getText());
			selectedCompany.getAddress().setCity(cityField.getText());
			// representative
			selectedCompany.setRepresentative(representativeBox.getValue());

			// TODO Fix representativeNameColumn :
			//		does not print the new name when the representative
			//		has been modified
		}
		else {
			// No selection
			// TODO new AlertDialog(TypeWarning.NO_COMPANY_SELECTED);
		}
	}


	// btnDelete
	@FXML
	protected void handleDelete() {
		int selectedIndex = companyTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			companyTable.getItems().remove(selectedIndex);
		}
		else {
			// No selection
			// TODO new AlertDialog(TypeWarning.NO_COMPANY_SELECTED);
		}
	}






} // public class CompanyViewController
