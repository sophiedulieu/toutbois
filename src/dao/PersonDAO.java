package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Person;
import util.MySqlAdapter;

public abstract class PersonDAO {
	
	
	
	public static void getPersonDAO(Person person) throws SQLException {

		String selectSt = 
				"SELECT * "
				+ "FROM Person "
				+ "WHERE idPerson = " + person.getNumPerson() + ";";
		ResultSet rs = MySqlAdapter.dbExecuteQuery(selectSt);

        while (rs.next()) {
        	// TODO test
        	/*
        	System.out.println(rs.getString("firstName"));
        	System.out.println(rs.getString("lastName"));
        	System.out.println(rs.getString("phoneNum"));
        	System.out.println(rs.getString("faxNum"));
        	System.out.println(rs.getString("email"));
        	*/
        	
        	person.setFirstName(rs.getString("firstName"));
        	person.setLastName(rs.getString("lastName"));
        	person.setPhoneNum(rs.getString("phoneNum"));
        	person.setFaxNum(rs.getString("faxNum"));
        	person.setEmail(rs.getString("email"));
        }
		
	}
	
	
	
	public static int getAiPersonDAO() throws SQLException {

		int aiPersonDAO = 1;
		String selectSt = 
				"SELECT AUTO_INCREMENT " 
				+ "FROM  INFORMATION_SCHEMA.TABLES " 
				+ "WHERE TABLE_SCHEMA = 'toutbois' " 
				+ "AND TABLE_NAME = 'Person'; ";
		ResultSet rs = MySqlAdapter.dbExecuteQuery(selectSt);
		
		while (rs.next()) {
			aiPersonDAO = rs.getInt("AUTO_INCREMENT");
		}
		
		return aiPersonDAO;
	}
	
	
	

} // public class PersonDAO
