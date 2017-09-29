package view;

import java.sql.SQLException;
import java.text.ParseException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainApp.Main;
import model.Company;
import model.Representative;
import dao.RepresentativeDAO;

public class RepresentativeOverviewController {

		@FXML
	    private TableView<Representative> representativeTable;
	    @FXML
	    private TableColumn<Representative, String> firstNameColumn;
	    @FXML
	    private TableColumn<Representative, String> lastNameColumn;
	    @FXML
	    private TableColumn<Representative, Integer> numPersonColumn;
	    @FXML
	    private TableColumn<Representative, String>  phoneNumColumn;
	    @FXML
	    private TableColumn<Representative, String>  faxNumColumn;
	    @FXML
	    private TableColumn<Representative, String>  emailColumn;
	    @FXML
	    private TableColumn<Representative, Double> basicSalaryColumn;
	    @FXML
	    private TableColumn<Representative, Double> commissionRateColumn;
	    
		
		@FXML
	    private TextField firstNameField;
	    @FXML
	    private TextField lastNameField;
	    @FXML
	    private Label numPersonField;
	    @FXML
	    private TextField phoneNumField;
	    @FXML
	    private TextField faxNumField;
	    @FXML
	    private TextField emailField;
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
	    
	    
	    //Initialize controller
	    @FXML
	    private void initialize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

	    	//representativeTable.setItems(Representative.getRepresentativeData());
	    	firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
	        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());	        
	        numPersonColumn.setCellValueFactory(cellData -> cellData.getValue().numPersonProperty().asObject());
	        phoneNumColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumProperty());
	        faxNumColumn.setCellValueFactory(cellData -> cellData.getValue().faxNumProperty());
	        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
	        basicSalaryColumn.setCellValueFactory(cellData -> cellData.getValue().basicSalaryProperty().asObject());
	        commissionRateColumn.setCellValueFactory(cellData -> cellData.getValue().commissionRateProperty().asObject());
	        searchRepresentatives();
	        showRepresentativeDetails(null);

	        representativeTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showRepresentativeDetails(newValue));
	        
	        
	    }
	    
	    //Search all representatives
	    public void searchRepresentatives() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
	        try {
	            //Get all Employees information
	            ObservableList<Representative> rpData = RepresentativeDAO.searchRepresentatives ();
	            //Populate Employees on TableView
	            populateRepresentatives (rpData);
	        } catch (SQLException e){
	            System.out.println("Error occurred while getting clients information from DB.\n" + e);
	            throw e;
	        }
	    }
	    public void  populateRepresentatives (ObservableList<Representative> repData) throws ClassNotFoundException {
	        //Set items to the clientsTable
	    	representativeTable.setItems(repData);
	    }
		public void setMain(Main main) {
			this.main = main;	
			// Add observable list data to the table
	        //representativeTable.setItems(Representative.getRepresentativeData());		
		}
	    
	    
		//Fill with representative details
		private void showRepresentativeDetails(Representative representative) {

			if (representative != null) {
				//Fill labels with representative informations
				firstNameField.setText(representative.getFirstName());
	            lastNameField.setText(representative.getLastName());           	            
				numPersonField.setText(Integer.toString(representative.getNumPerson()));
				phoneNumField.setText(representative.getPhoneNum());
				faxNumField.setText(representative.getFaxNum());
				emailField.setText(representative.getEmail());
	            basicSalaryField.setText(Double.toString(representative.getBasicSalary()));
	            commissionRateField.setText(Double.toString(representative.getCommissionRate()));

	        } else {
	        	//Earase all text if representative is null
	        	firstNameField.setText("");
	            lastNameField.setText("");        	            
				numPersonField.setText("");
				phoneNumField.setText("");
				faxNumField.setText("");
				emailField.setText("");
	            basicSalaryField.setText("");
	            commissionRateField.setText("");
	        }
		}
		
		//Manage Errors	
		private boolean isInputValid() {
			String errorMessage = "" ;
			
			if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
	            errorMessage += "Pas de prénom !\n";
	        }
			
			if (firstNameField.getText().length() >= 20) {
				errorMessage += "Le prénom est trop long !";
			}
			
			if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
	            errorMessage += "Pas de nom !\n";
	        }
			if (lastNameField.getText().length() >= 20) {
				errorMessage += "Le nom est trop long !";
			}
			
			/* if (idPersonField.getText() == null || idPersonField.getText().length() == 0) {
	            errorMessage += "Pas de numéro !\n";
	        }
			if (idPersonField.getText().length() >= 10) {
				errorMessage += "Le numéro est trop long !";
			} */
			
			
			if (phoneNumField.getText() == null || phoneNumField.getText().length() == 0) {
	            errorMessage += "Pas de téléphone !\n";
	        }
			if (phoneNumField.getText().length() >= 18) {
				errorMessage += "Le téléphone est trop long !";
			}
				
			
			if (basicSalaryField.getText() == null || basicSalaryField.getText().length() == 0) {
	            errorMessage += "Salaire non valide !\n";
	        }
			if (basicSalaryField.getText().length() >= 10) {
				errorMessage += "Le salaire est trop grand !";
			}
			
			
			if (commissionRateField.getText() == null || commissionRateField.getText().length() == 0) {
	            errorMessage += "Taux non valide !\n";
	        }
			if (commissionRateField.getText().length() >= 4) {
				errorMessage += "Le taux est trop long !";
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
		private void handleDeleteRepresentative(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
			if (isInputValid()) {
				for (Company company : Company.getCompanyList()) {
					if (company.getRepresentative().getNumPerson() == Integer.parseInt(numPersonField.getText())) {
						System.out.println("Impossible de supprimer le représentant ");
						break;
					}
					else {
						try {
							RepresentativeDAO.deleteRepWithId(numPersonField.getText());
							System.out.println("Représentant supprimé !");
						} catch (SQLException e) {
							System.out.println("Impossible de supprimer le représentant " + e);
							throw e;
						}
						break;
					}
				}
				searchRepresentatives();
			}
		}
		
	    
		
		//Add and edit
		//Add a representative
		@FXML 
		private void handleAdd(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {

			if (isInputValid()) {
				try {
					RepresentativeDAO.insertRep(
							firstNameField.getText(),
							lastNameField.getText(),
							emailField.getText(),
							phoneNumField.getText(),
							faxNumField.getText(),
							Double.parseDouble(commissionRateField.getText()),
							Double.parseDouble(basicSalaryField.getText())
							);
					System.out.println("Représentant ajouté");

				} catch (SQLException e) {
					System.out.println("Problème lors de l'ajout du représentant " + e);
					throw e;
				}
				searchRepresentatives();
			}
		}

		
		//Edit a representative
		@FXML
		private void handleEdit(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException  {

			if (isInputValid()) {
				try {
					RepresentativeDAO.updateRep(
							numPersonField.getText(),
							firstNameField.getText(),
							lastNameField.getText(),
							emailField.getText(),
							phoneNumField.getText(),
							faxNumField.getText(),
							Double.parseDouble(commissionRateField.getText()),
							Double.parseDouble(basicSalaryField.getText())
							);
					System.out.println("Représentant édité");

				} catch (SQLException e) {
					System.out.println("Le représentant n'a pu être édité " + e);
					throw e;
				}
				searchRepresentatives();
			}
		}
					
}
