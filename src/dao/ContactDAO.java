package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Company;
import model.Contact;
import util.MySqlAdapter;

public abstract class ContactDAO extends PersonDAO {
	
	
	// List
	private static ObservableList<Contact> contactListDAO = 
			FXCollections.observableArrayList();
	
	
	// Get list
	public static ObservableList<Contact> getContactListDAO() {
		return contactListDAO;
	}
	
	
	// Build list
	public static ObservableList<Contact> buildContactListDAO() throws SQLException {
		
		contactListDAO.clear();

		String selectSt = 
				"SELECT * "
				+ "FROM Person "
				+ "LEFT JOIN Representative USING (idPerson) "
				+ "WHERE basicSalary IS NULL;";
		ResultSet rs = MySqlAdapter.dbExecuteQuery(selectSt);
		
        while (rs.next()) {
        	// TODO test
        	/*
        	System.out.println(rs.getString("idPerson"));
        	*/
        	
        	Contact contact = new Contact();
        	contact.setNumPerson(rs.getInt("idPerson"));
        	contactListDAO.add(contact);
        }
        
        for (Contact contact : contactListDAO) {
        	getPersonDAO(contact);
        }
        
		return contactListDAO;
	}
	
	
	// Insert Contact
	public static int insertContactDAO(Contact contact) throws SQLException {
		
		String insertSt = 
				"INSERT INTO Person ("
						+ "firstName, lastName, email, phoneNum, faxNum) "
				+ "VALUES ("
						+ "'" + contact.getFirstName() + "', " 
						+ "'" + contact.getLastName() + "', " 
						+ "'" + contact.getEmail() + "', " 
						+ "'" + contact.getPhoneNum() + "', " 
						+ "'" + contact.getFaxNum() + "'); ";
		
		return MySqlAdapter.dbExecuteUpdate(insertSt);
	}
	
	
	// Edit Contact
	public static int editContactDAO(Company company) throws SQLException {
		
		String updateSt = 
				"UPDATE Person "
				+ "SET "
						+ "firstName = '" + company.getContact().getFirstName() + "', "
						+ "lastName = '" + company.getContact().getLastName() + "', "
						+ "email = '" + company.getContact().getEmail() + "', "
						+ "phoneNum = '" + company.getContact().getPhoneNum() + "', "
						+ "faxNum = '" + company.getContact().getFaxNum() + "' "
				+ "WHERE idPerson = ("
					+ "SELECT idContact "
					+ "FROM Company "
					+ "WHERE idCompany = " + company.getIdCompany() + "); ";
		
		return MySqlAdapter.dbExecuteUpdate(updateSt);
	}
	

} // public class Contact
