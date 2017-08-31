package util;

import java.util.Hashtable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import model.Client;
import model.Contact;
import model.Prospect;
import model.Representative;


/**
 * Helper class : wrap a list of persons and saves it to XML
 */
@XmlRootElement(name = "entities")
@XmlType(propOrder = { "contactList", "representativeList", 
		"clientList", "prospectList", 
		"companyContactMap", "companyRepresentativeMap" })
public class DataWrapper {
	

	// ***************   CONTACT   ****************

	
    private List<Contact> contactList;

    @XmlElementWrapper(name="contactList")
    @XmlElement(name = "contact")
    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
    

    
	// ************   REPRESENTATIVE   ************

	
    private List<Representative> representativeList;

    @XmlElementWrapper(name="representativeList")
    @XmlElement(name = "representative")
    public List<Representative> getRepresentativeList() {
        return representativeList;
    }

    public void setRepresentativeList(List<Representative> representativeList) {
        this.representativeList = representativeList;
    }
    
    

	// ****************   CLIENT   ****************

    
    private List<Client> clientList;

    @XmlElementWrapper(name="clientList")
    @XmlElement(name = "client")
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
    
    
    
	// ***************   PROSPECT   ***************

    
    private List<Prospect> prospectList;

    @XmlElementWrapper(name="prospectList")
    @XmlElement(name = "prospect")
    public List<Prospect> getProspectList() {
        return prospectList;
    }

    public void setProspectList(List<Prospect> prospectList) {
        this.prospectList = prospectList;
    }
    
    

    // *************   ASSOCIATIONS   *************
    
    
	// Map idCompany with its contact's idPerson
	
    private Hashtable<Integer, Integer> companyContactMap;
    
    @XmlElement(name = "companyContactMap")
    public Hashtable<Integer, Integer> getCompanyContactMap() {
		return companyContactMap;
    }

    public void setCompanyContactMap(Hashtable<Integer, Integer> companyContactMap) {
        this.companyContactMap = companyContactMap;
    }
    
    
	// Map idCompany with its representative's idPerson
	
    private Hashtable<Integer, Integer> companyRepresentativeMap;
    
    @XmlElement(name = "companyRepresentativeMap")
    public Hashtable<Integer, Integer> getCompanyRepresentativeMap() {
		return companyRepresentativeMap;
    }

    public void setCompanyRepresentativeMap(Hashtable<Integer, Integer> clientRepMap) {
        this.companyRepresentativeMap = clientRepMap;
    }
    
    
    

} // public class DataWrapper
