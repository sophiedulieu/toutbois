package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;

public class Representative extends Person {

	private final DoubleProperty commissionRate;
	private final DoubleProperty basicSalary;
	
	public Representative(Double commissionRate, Double basicSalary) {
		
		this.commissionRate = new SimpleDoubleProperty(commissionRate);
		this.basicSalary = new SimpleDoubleProperty(basicSalary);
	}
	
	public Representative() {
		this(0d,0d);
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
	public DoubleProperty basicSalary() {
		return basicSalary;
	}
	
	public String toString() {
		return ( getLastName() + " " + getFirstName() );
	}
	

	public static ObservableList<Representative>getRepresentativeList() {
		return getRepresentativeList();
	}
}
