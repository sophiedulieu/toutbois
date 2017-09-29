package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import type.TypeStreet;



/**
 * @author	Oupouwaout
 */
public abstract class Company {


	// **************   ATTRIBUTES   **************
	

    private static ObservableList<Company> companyList = 
    		FXCollections.observableArrayList();
    private static int aiCompany;

	protected final IntegerProperty idCompany;
	protected final StringProperty companyName;
	protected final LongProperty siret;
	protected final ObjectProperty<Representative> representative;
	protected final ObjectProperty<Contact> contact;
	protected final ObjectProperty<Address> address;
    
	

	// *************   CONSTRUCTORS   *************
    
    
    /**
     * Company constructor.
     * 
     * @param companyName		the company name
     * @param siret				the siret, contains 14 digits
     * @param contact			the person to contact in the company
     * @param address			the address
     * @param representative	the representative following the company
     */
    public Company ( String companyName, Long siret, Contact contact, 
    		Address address, Representative representative ) {

    	this.idCompany = new SimpleIntegerProperty(aiCompany);
    	this.companyName = new SimpleStringProperty(companyName);
    	this.siret = new SimpleLongProperty(siret);
    	this.contact = new SimpleObjectProperty<Contact>(contact);
    	this.address = new SimpleObjectProperty<Address>(address);
    	this.representative = new SimpleObjectProperty<Representative>(representative);
    	
    	companyList.add(this);
    	aiCompany++;
    }
    

    /**
     * Company constructor.
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
    public Company ( 
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
    	
    	this ( companyName, siret,
    			new Contact (
        				firstName, lastName, phoneNum, faxNum, email ),
    			new Address (
    					numAddress, typeStreet, nameStreet,
    					complementAddress, city, postalCode),
    			representative );
    }
    
    
    /**
     * Default Company constructor.
     * <p>
     * This constructor generates an empty company object.
     */
    public Company() {
    	this(null, 0L, null, null, null);
    }
    
    

	// *********   ACCESSORS / MUTATORS   *********
    
    
    // idCompany

    public Integer getIdCompany() {
    	return idCompany.get();
    }
    
    public void setIdCompany(Integer idCompany) {
        this.idCompany.set(idCompany);
    }
    
    public IntegerProperty idCompanyProperty() {
    	return idCompany;
    }
    

    // siret
    
    public Long getSiret() {
    	return siret.get();
    }
    
    public void setSiret(Long siret) {
    	this.siret.set(siret);
    }
    
    public LongProperty siretProperty() {
    	return siret;
    }
    

    // companyName
    
    public String getCompanyName() {
        return companyName.get();
    }
    
    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
    
    public StringProperty companyNameProperty() {
        return companyName;
    }

    
    // contact

	public Contact getContact() {
		return contact.get();
	}

    public void setContact(Contact contact) {
        this.contact.set(contact);
    }

    public ObjectProperty<Contact> contactProperty() {
        return contact;
    }

    
    // address
    
	public Address getAddress() {
		return address.get();
	}

    public void setAddress(Address address) {
        this.address.set(address);
    }

    public ObjectProperty<Address> addressProperty() {
        return address;
    }


    // representative

    public Representative getRepresentative() {
    	return representative.get();
    }

    public void setRepresentative(Representative representative) {
    	this.representative.set(representative);
    }

    public ObjectProperty<Representative> representativeProperty() {
    	return representative;
    }
	
    
    // companyList
    
    public static ObservableList<Company> getCompanyList() {
        return companyList;
    }
	
    
    // aiCompany
    
    public static int getAiCompany() {
        return aiCompany;
    }

    public static void setAiCompany(int ai) {
        aiCompany = ai;
    }
    
    

} // public class Company
