package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import type.TypeStreet;



/**
 * @author	Oupouwaout
 */
public class Address {


	// **************   ATTRIBUTES   **************
	

	private final StringProperty numAddress;
    private final ObjectProperty<TypeStreet> typeStreet;
    private final StringProperty nameStreet;
    private final StringProperty complementAddress;
    private final StringProperty city;
    private final StringProperty postalCode;
    
	

	// *************   CONSTRUCTORS   *************
    
    
    /**
     * Address contructor.
     * 
     * @param numAddress		the number in the street
     * @param typeStreet		the <code>TypeStreet</code> enum type
     * @param nameStreet		the name of the street
     * @param complementAddress	any complement, if needed
     * @param city				the city
     * @param postalCode		the ZIP code
     */
    public Address ( String numAddress, TypeStreet typeStreet, 
    		String nameStreet, String complementAddress,
    		String city, String postalCode ) {
    
    this.numAddress = new SimpleStringProperty(numAddress);
    this.typeStreet = new SimpleObjectProperty<TypeStreet>(typeStreet);
    this.nameStreet = new SimpleStringProperty(nameStreet);
    this.complementAddress = new SimpleStringProperty(complementAddress);
    this.city = new SimpleStringProperty(city);
    this.postalCode = new SimpleStringProperty(postalCode);
    }
    
    
    /**
     * Default Address constructor.
     * <p>
     * This constructor generates an empty address object used by 
     * the unmarshaller from <code>mainApp.loadData()</code>. The 
     * unmarshaller injects the datas from the .xml file into the 
     * empty address attributes using the setters, making it real.
     */
    public Address() {
    	this (null, null, null, null, null, null);
    }

    

	// *********   ACCESSORS / MUTATORS   *********
    
    
    // numAddress
    
    public String getNumAddress() {
        return numAddress.get();
    }

    public void setNumAddress(String numAddress) {
        this.numAddress.set(numAddress);
    }

    public StringProperty numAddressProperty() {
        return numAddress;
    }
    
    
    // typeStreet
    
    public TypeStreet getTypeStreet() {
        return typeStreet.get();
    }

    public void setTypeStreet(TypeStreet typeStreet) {
        this.typeStreet.set(typeStreet);
    }
    
    public void setTypeStreet(String typeStreet) {
        this.typeStreet.set(TypeStreet.valueOf(typeStreet)); 
    }

    public ObjectProperty<TypeStreet> typeStreetProperty() {
        return typeStreet;
    }
    
    
    // nameStreet
    
    public String getNameStreet() {
        return nameStreet.get();
    }

    public void setNameStreet(String nameStreet) {
        this.nameStreet.set(nameStreet);
    }

    public StringProperty nameStreetProperty() {
        return nameStreet;
    }
    
    
    // complementAddress
    
    public String getComplementAddress() {
        return complementAddress.get();
    }

    public void setComplementAddress(String complementAddress) {
        this.complementAddress.set(complementAddress);
    }

    public StringProperty complementAddressProperty() {
        return complementAddress;
    }
    
    
    // city
    
    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }
    
    
    // postalCode
    
    public String getPostalCode() {
        return postalCode.get();
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public StringProperty postalCodeProperty() {
        return postalCode;
    }
	
    
    
} // public class Address
