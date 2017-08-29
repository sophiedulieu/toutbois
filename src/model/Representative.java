package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import mainApp.Main;


public class Representative extends Person {
	
	private Main main;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	private ObservableList<Representative> representativeData = FXCollections.observableArrayList();
	
	public ObservableList<Representative> getRepresentativeData() {
		return representativeData;
	}

	private final DoubleProperty commissionRate;
	private final DoubleProperty basicSalary;
	
	public Representative(String idPerson, String firstName, String lastName, String phoneNum, Double commissionRate, Double basicSalary) {	
		super();
		this.commissionRate = new SimpleDoubleProperty(commissionRate);
		this.basicSalary = new SimpleDoubleProperty(basicSalary);
	}
	
	public Representative() {
		this(null,null,null,null,0d,0d);
	}
	
	
	public Double getCommissionRate() {
		return commissionRate.get();
	}
	public void setCommissionRate(Double commissionRate) {
		this.commissionRate.set(commissionRate);
	}
	public DoubleProperty commissionRateProperty() {
		return commissionRate;
	}
	
	
	public Double getBasicSalary() {
		return basicSalary.get();
	}
	public void setBasicSalary(Double basicSalary) {
		this.basicSalary.set(basicSalary);
	}
	public DoubleProperty basicSalaryProperty() {
		return basicSalary;
	}
	
	
	//Show Representative
	@FXML
	private void handleShowRepresentant() {		               
        main.showRepresentativeOverview();
	}
	
	
	public String toString() {
		return ( getLastName() + " " + getFirstName() );
	}
	

	public static ObservableList<Representative>getRepresentativeList() {
		return getRepresentativeList();
	}
}
