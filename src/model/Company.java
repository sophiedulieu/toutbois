package model;

import java.util.Hashtable;

import javax.xml.bind.annotation.XmlTransient;

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
     * This constructor generates an empty company object used by 
     * the unmarshaller from <code>mainApp.loadData()</code>. The 
     * unmarshaller injects the datas from the .xml file into the 
     * ghost company attributes using the <code>setters</code>, making it alive.
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

    @XmlTransient
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

    @XmlTransient
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
    
    
    // companyContactMap
    
    /**
     * Maps the Company's Id with its Contact's NumPerson.
     * <p>
     * This method creates a map, and then iterates the <code>companyList</code>. 
     * For each company in the list, it writes a set in the map where the 
     * <code>idCompany</code> is the key and the contact's <code>numPerson</code> 
     * is the value. 
     * This map will be used by the unmarshaller from <code>main.loadData()</code> 
     * to set the <code>Contact</code> object back in its <code>Company</code> object.
     * The map is marshalled by <code>main.saveData()</code> along with the other
     * objects.
     * 
     * @return	the constructed map
     */
    public static Hashtable<Integer, Integer> getCompanyContactMap() {
    	Hashtable<Integer, Integer> companyContactMap = 
    			new Hashtable<Integer, Integer>();
		for ( Company company : companyList ) {
			companyContactMap.put(company.getIdCompany(), 
					company.getContact().getNumPerson());
		}
		return companyContactMap;
    }
    
    
    // companyRepresentativeMap
    
    /**
     * Maps the Company's Id with its Representative NumPerson.
     * <p>
     * This method creates a map, and then iterates the <code>companyList</code>. 
     * For each company in the list, it writes a set in the map where the 
     * <code>idCompany</code> is the key and the representative's 
     * <code>numPerson</code> is the value. 
     * This map will be used by the unmarshaller from <code>main.loadData()</code> 
     * to set the <code>Representative</code> object back in its <code>Company</code>
     *  object.
     * The map is marshalled by <code>main.saveData()</code> along with the other
     * objects.
     * 
     * @return	the constructed map
     */
    public static Hashtable<Integer, Integer> getCompanyRepresentativeMap() {
    	Hashtable<Integer, Integer> companyRepresentativeMap = 
    			new Hashtable<Integer, Integer>();
		for ( Company company : companyList ) {
			companyRepresentativeMap.put(company.getIdCompany(), 
					company.getRepresentative().getNumPerson());
		}
		return companyRepresentativeMap;
    }
	
    
    // aiCompany
    
    public static int getAiCompany() {
        return aiCompany;
    }

    public static void setAiCompany(int ai) {
        aiCompany = ai;
    }
    
    

} // public class Company
