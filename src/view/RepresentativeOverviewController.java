package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainApp.Main;
import model.Representative;

public class RepresentativeOverviewController {

		@FXML
	    private TableView<Representative> representativeTable;
	    @FXML
	    private TableColumn<Representative, String> firstNameColumn;
	    @FXML
	    private TableColumn<Representative, String> lastNameColumn;
	    @FXML
	    private TableColumn<Representative, String> idPersonColumn;
	    @FXML
	    private TableColumn<Representative,String>  phoneNumColumn;
	    @FXML
	    private TableColumn<Representative, Double> basicSalaryColumn;
	    @FXML
	    private TableColumn<Representative, Double> commissionRateColumn;
	    
		
		@FXML
	    private TextField firstNameField;
	    @FXML
	    private TextField lastNameField;
	    @FXML
	    private TextField idPersonField;
	    @FXML
	    private TextField phoneNumField;
	    @FXML
	    private TextField basicSalaryField;
	    @FXML
	    private TextField commissionRateField;
		
		private Representative representative;
		private Stage dialogStage;
		private Main main;
		
		
		//Constructor
	    public RepresentativeOverviewController() {    	
	    }
	    
	    
	    //Initialisation du controller
	    @FXML
	    private void initialize() {

	        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
	        idPersonColumn.setCellValueFactory(cellData -> cellData.getValue().idPersonProperty());
	        phoneNumColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumProperty());
	        basicSalaryColumn.setCellValueFactory(cellData -> cellData.getValue().basicSalaryProperty().asObject());
	        commissionRateColumn.setCellValueFactory(cellData -> cellData.getValue().commissionRateProperty().asObject());
	        
	        showRepresentativeDetails(null);
	        //setRepresentant(null);

	        representativeTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showRepresentativeDetails(newValue));
	    }
	    
	    
		public void setMain(Main main) {
			this.main = main;	
			// Add observable list data to the table
	        representativeTable.setItems(main.getRepresentativeData());		
		}
	    
	    
		//Fill with representative details
		private void showRepresentativeDetails(Representative representative) {

			if (representative != null) {
				//Fill lavels with representative informations
				firstNameField.setText(representative.getFirstName());
	            lastNameField.setText(representative.getLastName());           	            
				idPersonField.setText(representative.getIdPerson());
				phoneNumField.setText(representative.getPhoneNum());
	            basicSalary.setText(Double.toString(representative.getBasicSalary()));
	            commissionRate.setText(Double.toString(commissionRate.getTauxCom()));

	        } else {
	        	//Earase all text if representative is null
	        	firstNameField.setText("");
	            lastNameField.setText("");        	            
				idPersonField.setText("");
				phoneNumField.setText("");
	            basicSalary.setText("");
	            commissionRate.setText("");
	        }
		}
		
		//Manage Errors
		
		private boolean isInputValid() {
			String errorMessage = "" ;
			
			if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
	            errorMessage += "Pas de prénom !\n";
	        }
			
			if (firstNameField.getText().length() >= 15) {
				errorMessage += "Le prénom est trop long !";
			}
			
			if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
	            errorMessage += "Pas de nom !\n";
	        }
			if (lastNameField.getText().length() >= 20) {
				errorMessage += "Le nom est trop long !";
			}
			
			if (idPersonField.getText() == null || idPersonField.getText().length() == 0) {
	            errorMessage += "Pas de numéro !\n";
	        }
			if (idPersonField.getText().length() >= 10) {
				errorMessage += "Le numéro est trop long !";
			}
			
			
			if (phoneNumField.getText() == null || phoneNumField.getText().length() == 0) {
	            errorMessage += "Pas de téléphone !\n";
	        }
			if (phoneNumField.getText().length() >= 10) {
				errorMessage += "Le téléphone est trop long !";
			}
				
			
			if (commissionRateField.getText() == null || commissionRateField.getText().length() == 0) {
	            errorMessage += "Taux non valide !\n";
	        }
			if (commissionRateField.getText().length() >= 4) {
				errorMessage += "Le taux est trop long !";
			}
			
			if (basicSalaryField.getText() == null || basicSalaryField.getText().length() == 0) {
	            errorMessage += "Salaire non valide !\n";
	        }
			if (basicSalaryField.getText().length() >= 10000) {
				errorMessage += "Le salaire est trop grand !";
			}
			
			
			if (errorMessage.length() == 0) {
	            return true;
	        } else {
	        	//Show error message
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Erreur");
	            alert.setHeaderText(errorMessage);
	            alert.setContentText("Corrigez l'entrée incorrecte !");

	            alert.showAndWait();

	            return false;
		}
			
			}
		
		//Delete one Representative
		@FXML
		private void handleDeleteRepresentative() {
		    int selectedIndex = representativeTable.getSelectionModel().getSelectedIndex();
		    if (selectedIndex >= 0) {
		    representativeTable.getItems().remove(selectedIndex);
		    } else {
		    	// Rien n'est sélectionné
		        Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(main.getPrimaryStage());
		        alert.setTitle("Aucune sélection");
		        alert.setHeaderText("Aucun représentant sélectionné !");
		        alert.setContentText("Sélectionnez un représentant dans la table");

		        alert.showAndWait();
		    }
		}
	    
		
		//Add ans edit
		//Add a representative
		public boolean isAddClicked() {
	        return addClicked;
	    }
		boolean addClicked = false;
		
		@FXML
		private void handleAdd() {
			if (isInputValid()) {
				
				Representative represeTemp = new Representative(firstNameField.getText(), lastNameField.getText(), idPersonField.getText(), phoneNumField.getText(), Double.parseDouble(basicSalaryField.getText()), 
						Double.parseDouble(commissionRateField.getText()));
				addClicked = true;
			
		        
				if (addClicked) {
		            main.getRepresentativeData().add(represeTemp);
			}
				
			}
			
		}
		
		//Edit one representative
		public boolean isEditClicked() {
	        return editClicked;
	    }
		boolean editClicked = false;
		
		@FXML
		private void handleEdit() {
			
		if (isInputValid()) {			
			
			if (editClicked=true) {
			
				Representative selectedRepresentative = representativeTable.getSelectionModel().getSelectedItem();
				if (selectedRepresentative != null) {
		            
					selectedRepresentative.setFirstName(firstNameField.getText());
					selectedRepresentative.setLastName(lastNameField.getText());
					selectedRepresentative.setIdPerson(idPersonField.getText());
					selectedRepresentative.setPhoneNum(phoneNumField.getText());
					selectedRepresentative.setCommissionRate(Double.parseDouble(commissionRate.getText()));
					selectedRepresentative.setBasicSalary(Double.parseDouble(basicSalary.getText()));
		          
				}		
		        } 
		        } 
		
		}
				
				
			
		
	
}
