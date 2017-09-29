package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Company;
import model.Contact;
import model.Representative;
import util.MySqlAdapter;

public abstract class CompanyDAO {
	
	
	public static void getCompanyDAO(Company company) throws SQLException {

		String selectSt = 
				"SELECT * "
				+ "FROM Company "
				+ "WHERE idCompany = " + company.getIdCompany() + ";";
		ResultSet rs = MySqlAdapter.dbExecuteQuery(selectSt);

        while (rs.next()) {
        	// TODO test
        	/*
        	System.out.println(rs.getString("idCompany"));
        	System.out.println(rs.getString("companyName"));
        	System.out.println(rs.getString("siret"));
        	System.out.println(rs.getString("idAddress"));
        	System.out.println(rs.getString("idContact"));
        	System.out.println(rs.getString("idRepresentative"));
        	*/
        	
        	company.setCompanyName(rs.getString("companyName"));
        	company.setSiret(rs.getLong("siret"));
        	company.setAddress(AddressDAO.getAddressDAO(rs.getInt("idAddress")));
        	
        	int idContact = rs.getInt("idContact");
        	for (Contact contact : Contact.getContactList()) {
        		if (contact.getNumPerson().equals(idContact)) {
        			company.setContact(contact);
        			break;
        		}
        	}
        	
        	int idRepresentative = rs.getInt("idRepresentative");
        	for (Representative representative : Representative.getRepresentativeData()) {
        		if (representative.getNumPerson().equals(idRepresentative)) {
        			company.setRepresentative(representative);
        			break;
        		}
        	}
        }
	}
	
	
	public static int insertCompanyDAO(Company company, int idAddress) throws SQLException {
		
		String insertSt = 
				"INSERT INTO Company ("
						+ "companyName, siret, "
						+ "idAddress, idContact, idRepresentative) "
				+ "VALUES ("
						+ "'" + company.getCompanyName() + "', " 
						+ "" + company.getSiret() + ", " 
						+ "" + idAddress + ", "
						+ "" + company.getContact().getNumPerson() + ", "
						+ "" + company.getRepresentative().getNumPerson() + ") "
						+ "ON DUPLICATE KEY UPDATE siret = siret";
		// TODO annuler increment du compteur company
		
		return MySqlAdapter.dbExecuteUpdate(insertSt);
	}
	
	
	
	public static int getAiCompanyDAO() throws SQLException {

		int aiCompanyDAO = 1;
		String selectSt = 
				"SELECT AUTO_INCREMENT " 
				+ "FROM  INFORMATION_SCHEMA.TABLES " 
				+ "WHERE TABLE_SCHEMA = 'toutbois' " 
				+ "AND TABLE_NAME = 'Company'; ";
		ResultSet rs = MySqlAdapter.dbExecuteQuery(selectSt);
		
		while (rs.next()) {
			aiCompanyDAO = rs.getInt("AUTO_INCREMENT");
		}
		
		return aiCompanyDAO;
	}
	
	
	// Edit Company
	public static int editCompanyDAO(Company company) throws SQLException {

		// TODO contact
		AddressDAO.editAddressDAO(company);
		ContactDAO.editContactDAO(company);
		
		String updateSt = 
				"UPDATE Company "
				+ "SET "
						+ "companyName = '" + company.getCompanyName() + "', "
						+ "siret = '" + company.getSiret() + "', "
						+ "idRepresentative = '" + company.getRepresentative().getNumPerson() + "' "
				+ "WHERE idCompany = " + company.getIdCompany() + "; ";
		
		return MySqlAdapter.dbExecuteUpdate(updateSt);
	}
	

} // public class CompanyDAO
