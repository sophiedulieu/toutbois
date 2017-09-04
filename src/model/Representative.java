package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Representative extends Person {
	
	private static ObservableList<Representative> representativeData = FXCollections.observableArrayList();
	
	public static ObservableList<Representative> getRepresentativeData() {
		return representativeData;
	}

	private final DoubleProperty commissionRate;
	private final DoubleProperty basicSalary;
	
	public Representative(String firstName, String lastName, /*Integer numPerson,*/ String phoneNum, String faxNum, String email, Double commissionRate, Double basicSalary) {	
		super(firstName, lastName, /*numPerson,*/ phoneNum, faxNum, email);
		this.commissionRate = new SimpleDoubleProperty(commissionRate);
		this.basicSalary = new SimpleDoubleProperty(basicSalary);
	}
	
	public Representative() {
		this(null,null,null,null, null, 0d,0d);
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
	
	
	
	public String toString() {
		return ( getLastName() + " " + getFirstName() );
	}
	
/*
	public static ObservableList<Representative>getRepresentativeList() {
		return getRepresentativeList();
	}*/
}
