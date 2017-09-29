package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Person;
import util.MySqlAdapter;



/**
 * @author	Oupouwaout
 */
public abstract class PersonDAO {
	

	// Set Person's attributes
	/**
	 * Gets the person's details from the database and 
	 * sets its attributes.
	 * 
	 * @param person the person whom to set attributes
	 * @throws SQLException
	 */
	public static void getPersonDAO(Person person) throws SQLException {

		// Get the person's details
		String selectSt = 
				"SELECT * "
				+ "FROM Person "
				+ "WHERE idPerson = " + person.getNumPerson() + ";";
		ResultSet rs = MySqlAdapter.dbExecuteQuery(selectSt);

		// Set the person's details
        while (rs.next()) {
        	person.setFirstName(rs.getString("firstName"));
        	person.setLastName(rs.getString("lastName"));
        	person.setPhoneNum(rs.getString("phoneNum"));
        	person.setFaxNum(rs.getString("faxNum"));
        	person.setEmail(rs.getString("email"));
        }
	}
	

	// Get Person auto_increment
	/**
	 * Requests the actual auto_increment for the table <code>Person</code>.
	 * 
	 * @return the Person auto_increment
	 * @throws SQLException
	 */
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
