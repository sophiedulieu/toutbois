package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import type.TypeStreet;



/**
 * @author	Oupouwaout
 */
public class Client extends Company {


	// **************   ATTRIBUTES   **************
	

	private static ObservableList<Client> clientList = 
    		FXCollections.observableArrayList();

    private final IntegerProperty nbOrder;
	
	

	// *************   CONSTRUCTORS   *************
    
    
    /**
     * Client contructor.
     * 
     * @param companyName		the company name
     * @param siret				the siret, contains 14 digits
     * @param contact			the person to contact in the company
     * @param address			the address
     * @param representative	the representative following the company
     */
    public Client( 
    		// company
    		String companyName, Long siret, Contact contact, 
    		Address address, Representative representative ) {
    	
    	super (companyName, siret, contact, address, representative);
    	this.nbOrder = new SimpleIntegerProperty(1);

    	clientList.add(this);
    }
    
    
    /**
     * Client constructor.
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
     */
    public Client ( 
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
    		Representative representative ) {
    	
    	this(companyName, siret, 
    			new Contact(firstName, lastName, phoneNum, faxNum, email),
    			new Address(numAddress, typeStreet, nameStreet, 
    					complementAddress, city, postalCode),
    			representative);
    }
    
    
    /**
     * Default Client constructor.
     * <p>
     * This constructor generates an empty client object used by 
     * the unmarshaller from mainApp.loadData(). The unmarshaller 
     * injects the datas from the .xml file into the zombie client 
     * attributes using the setters.
     */
    public Client () {
    	this(null, 0L, null, null, null);
    }
    
    
    public Client( Prospect prospect) {
    	this ( prospect.getCompanyName(), prospect.getSiret(),
    			prospect.getContact(), prospect.getAddress(),
    			prospect.getRepresentative() );
    	this.setIdCompany(prospect.getIdCompany());
    	Prospect.getProspectList().remove(prospect);
    }
    

    
	// *********   ACCESSORS / MUTATORS   *********
    

    // nbOrder
    
    public Integer getNbOrder() {
    	return nbOrder.get();
    }
    
    public void setNbOrder(Integer nbOrder) {
    	this.nbOrder.set(nbOrder);
    }
    
    public IntegerProperty nbOrderProperty() {
    	return nbOrder;
    }


    // clientList
    
    public static ObservableList<Client> getClientList() {
        return clientList;
    }

    
	

} // public class Client
