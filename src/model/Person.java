package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	protected StringProperty idPerson;
	protected StringProperty firstName;
	protected StringProperty lastName;
	private final StringProperty phoneNum;
	private final StringProperty faxNum;
	private final StringProperty email;

	
	//Constructor
	public Person(String idPerson, String firstName, String lastName, String phoneNum, String faxNum, String email) {
		
		this.idPerson = new SimpleStringProperty(idPerson);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.phoneNum = new SimpleStringProperty(phoneNum);
		this.faxNum = new SimpleStringProperty(faxNum);
		this.email = new SimpleStringProperty(email);
	}

	
	//Default Constructor
	public Person() {
		this(null,null,null,null,null,null);
	}

	
	//Getters & Setters
	public String getIdPerson() {
		return idPerson.get();
	}
	public void setIdPerson(String idPerson) {
		this.idPerson.set(idPerson);
	}
	public StringProperty idPersonProperty() {
		return idPerson;
	}
	
	
	public String getFirstName() {
		return firstName.get();
	}
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	public StringProperty firstName() {
		return firstName;
	}
	
	
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public StringProperty lastName() {
		return lastName;
	}
	
	
	public String getPhoneNum() {
		return phoneNum.get();
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum.set(phoneNum);
	}
	public StringProperty phoneNum() {
		return phoneNum;
	}
	
	
	public String getFaxNum() {
		return faxNum.get();
	}
	public void SetFaxNum(String faxNum) {
		this.faxNum.set(faxNum);
	}
	public StringProperty faxNum() {
		return faxNum;
	}
	
	
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	public StringProperty email() {
		return email;
	}
}
