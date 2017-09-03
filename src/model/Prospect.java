package model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import type.LocalDateAdapter;
import type.TypeStreet;



/**
 * @author	Oupouwaout
 */
public class Prospect extends Company {
	

	// **************   ATTRIBUTES   **************
	
	
	private static ObservableList<Prospect> prospectList = 
			FXCollections.observableArrayList();

    private final ObjectProperty<LocalDate> lastVisit;
    
	

	// *************   CONSTRUCTORS   *************
    
    
    /**
     * Prospect constructor.
     * 
     * @param companyName		the company name
     * @param siret				the siret, contains 14 digits
     * @param contact			the person to contact in the company
     * @param address			the address
     * @param representative	the representative following the company
     * @param lastVisit			the locale date of the last prospective visit
     */
    public Prospect( 
    		// company
    		String companyName, Long siret, Contact contact, 
    		Address address, Representative representative, 
    		// prospect
    		LocalDate lastVisit ) {
    	
    	super (companyName, siret, contact, address, representative);
    	this.lastVisit = new SimpleObjectProperty<LocalDate>(lastVisit);
    	
    	prospectList.add(this);
    }
    
    
    /**
     * Prospect constructor.
     * 
     * @param companyName		the company name
     * @param siret				the siret, contains 14 digits
     * @param firstName			the first name of the contact in the company
     * @param lastName			the last name of the contact in the company
     * @param phoneNum			the phone number of the contact in the company
     * @param faxNum			the fax number of the contact in the company
     * @param email				the e-mail of the contact in the company
     * @param numAddress		the number in the street
     * @param typeStreet		the <code>TypeStreet</code> enum type
     * @param nameStreet		the name of the street
     * @param complementAddress	any complement, if needed
     * @param city				the city
     * @param postalCode		the ZIP code
     * @param representative	the representative following the company
     * @param lastVisit			the locale date of the last prospective visit
     */
    public Prospect ( 
    		// company
    		String companyName, Long siret, 
    		// contact
    		String firstName, String lastName, 
    		String phoneNum, String faxNum, String email, 
    		// address
    		String numAddress, TypeStreet typeStreet, 
    		String nameStreet, String complementAddress,
    		String city, String postalCode, 
    		// representative
    		Representative representative, 
    		// prospect
    		LocalDate lastVisit ) {
    	
    	this ( companyName, siret,
    			new Contact(firstName, lastName, phoneNum, faxNum, email),
    			new Address(numAddress, typeStreet, nameStreet, 
    					complementAddress, city, postalCode),
    			representative, lastVisit );
    }
    
    
    /**
     * Default Prospect constructor.
     * <p>
     * This constructor generates an empty prospect object used by 
     * the unmarshaller from mainApp.loadData(). The unmarshaller 
     * injects the datas from the .xml file into the ghost prospect 
     * attributes using the setters.
     */
    public Prospect() {
    	this (null, 0L, null, null, null, null);
    }
    
    
    public Prospect( Client client) {
    	this ( client.getCompanyName(), client.getSiret(),
    			client.getContact(), client.getAddress(),
    			client.getRepresentative(), LocalDate.now() );
    	this.setIdCompany(client.getIdCompany());
    	Client.getClientList().remove(client);
    }

    

	// *********   ACCESSORS / MUTATORS   *********


    // lastVisit

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getLastVisit() {
    	return lastVisit.get();
    }

    public void setLastVisit(LocalDate lastVisit) {
    	this.lastVisit.set(lastVisit);
    }

    public ObjectProperty<LocalDate> lastVisitProperty() {
    	return lastVisit;
    }
    
    
    // prospectList
    
    public static ObservableList<Prospect> getProspectList() {
        return prospectList;
    }
    
    
    

} // public class Prospect
