package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Address;
import model.Company;
import util.MySqlAdapter;

public abstract class AddressDAO {

	
	
	public static Address getAddressDAO(int idAddress) throws SQLException {

		String selectSt = 
				"SELECT * "
				+ "FROM Address "
				+ "INNER JOIN city USING (postalCode) "
				+ "INNER JOIN TypeStreet USING (idTypeStreet)"
				+ "WHERE idAddress = " + idAddress + ";";
		ResultSet rs = MySqlAdapter.dbExecuteQuery(selectSt);

    	Address address = new Address();
        while (rs.next()) {
        	// TODO test
        	/*
        	System.out.println(rs.getString("numAddress"));
        	System.out.println(rs.getString("nameStreet"));
        	System.out.println(rs.getString("complementAddress"));
        	System.out.println(rs.getString("postalCode"));
        	System.out.println(rs.getString("city"));
        	System.out.println(rs.getString("nameTypeStreet"));
        	*/
        	
        	address.setNumAddress(rs.getString("numAddress"));
        	address.setNameStreet(rs.getString("nameStreet"));
        	address.setComplementAddress(rs.getString("complementAddress"));
        	address.setPostalCode(rs.getString("postalCode"));
        	address.setCity(rs.getString("city"));
        	address.setTypeStreet(rs.getString("nameTypeStreet"));
        }
		
        return address;
	}
	
	
	public static int insertAddressDAO(Company company) throws SQLException {
		
		insertCityDAO(company.getAddress());
		
		String insertSt = 
				"INSERT INTO Address ("
						+ "numAddress, nameStreet, complementAddress, "
						+ "postalCode, idTypeStreet, idCompany) "
				+ "VALUES ("
						+ "'" + company.getAddress().getNumAddress() + "', " 
						+ "'" + company.getAddress().getNameStreet() + "', " 
						+ "'" + company.getAddress().getComplementAddress() + "', " 
						+ "'" + company.getAddress().getPostalCode() + "', " 
						+ "" + (company.getAddress().getTypeStreet().ordinal() + 1) + ", " 
						+ "" + company.getIdCompany() + "); ";
		
		return MySqlAdapter.dbExecuteUpdate(insertSt);
	}
	
	
	public static int insertCityDAO(Address address) throws SQLException {
		
		String insertSt = 
				"INSERT INTO City (postalCode, city) "
				+ "VALUES ( "
						+ "" + address.getPostalCode() + ", " 
						+ "'" + address.getCity() + "') "
				+ "ON DUPLICATE KEY UPDATE postalCode = postalCode; ";
		
		return MySqlAdapter.dbExecuteUpdate(insertSt);
	}
	
	
	// Edit Address
	public static int editAddressDAO(Company company) throws SQLException {
		
		insertCityDAO(company.getAddress());
		
		String updateSt = 
				"UPDATE Address "
				+ "SET "
					+ "numAddress = '" + company.getAddress().getNumAddress() + "', "
					+ "nameStreet = '" + company.getAddress().getNameStreet() + "', "
					+ "complementAddress = '" + company.getAddress().getComplementAddress() + "', "
					+ "postalCode = '" + company.getAddress().getPostalCode() + "', "
					+ "idTypeStreet = '" + (company.getAddress().getTypeStreet().ordinal() + 1) + "' "
				+ "WHERE idCompany = " + company.getIdCompany() + "; ";
		
		return MySqlAdapter.dbExecuteUpdate(updateSt);
	}
	
	
	
} // public class AddressDAO
