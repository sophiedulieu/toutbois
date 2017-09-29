package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 * @author	Oupouwaout
 */
public class Contact extends Person {


	// **************   ATTRIBUTES   **************
	

    private static ObservableList<Contact> contactList = 
    		FXCollections.observableArrayList();
    
    

	// *************   CONSTRUCTORS   *************
    
    
    /**
     * Contact constructor.
     * 
     * @param firstName			the first name
     * @param lastName			the last name
     * @param phoneNum			the phone number
     * @param faxNum			the fax number
     * @param email				the e-mail
     */
    public Contact ( String firstName, String lastName, 
    		String phoneNum, String faxNum, String email ) {
    	
    	super(firstName, lastName, phoneNum, faxNum, email);
    	contactList.add(this);
    }
    
    
    /**
     * Default Contact constructor.
     * <p>
     * This constructor generates an empty contact object.
     */
    public Contact() {
    	this(null, null, null, null, null);
    }
    
    

	// *********   ACCESSORS / MUTATORS   *********
	
    
    // contactList
    public static ObservableList<Contact> getContactList() {
        return contactList;
    }
    
    

} // public class Contact
