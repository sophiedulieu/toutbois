package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Representative;
import util.MySqlAdapter;

public class RepresentativeDAO {

	//Search a representative
	public static Representative searchRepresentative (String idPerson) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		//Declare a SELECT statement
        String selectStmt = "SELECT * FROM representative INNER join person on representative.idPerson = person.idPerson WHERE representative.idPerson="+idPerson;
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRep = MySqlAdapter.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getRepresentativeFromResultSet method and get representative object
            Representative representative = getRepresentativeFromResultSet(rsRep);
 
            //Return representative object
            return representative;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + idPerson + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
		
	
	//Use ResultSet from DB as parameter and set Representative Object's attributes and return representative object.
    private static Representative getRepresentativeFromResultSet(ResultSet rs) throws SQLException
    {
        Representative rep = null;
        if (rs.next()) {
            rep = new Representative();
            rep.setNumPerson(rs.getInt("idPerson"));
            rep.setFirstName(rs.getString("firstName"));
            rep.setLastName(rs.getString("lastName"));
            rep.setEmail(rs.getString("email"));
            rep.setPhoneNum(rs.getString("phoneNum"));
            rep.setFaxNum(rs.getString("faxNum"));
            rep.setCommissionRate(rs.getDouble("commissionRate"));
            rep.setBasicSalary(rs.getDouble("basicSalary"));
        }
        return rep;
    }
 
    
    //Select Representatives
    public static ObservableList<Representative> searchRepresentatives() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	 //Declare a SELECT statement
        String selectStmt = "SELECT * FROM representative INNER join person on representative.idPerson = person.idPerson";
        		//INNER JOIN person ON representative.idPerson";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsReps = MySqlAdapter.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getRepresentativeList method and get representative object
            ObservableList<Representative> repList = getRepresentativeList(rsReps);
 
            //Return representative object
            return repList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Select * from representatives operation
    private static ObservableList<Representative> getRepresentativeList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Representative> repList = FXCollections.observableArrayList();
 
        while (rs.next()) {
            Representative rep = new Representative();
            rep.setNumPerson(rs.getInt("idPerson"));
            rep.setFirstName(rs.getString("firstName"));
            rep.setLastName(rs.getString("lastName"));
            rep.setEmail(rs.getString("email"));
            rep.setPhoneNum(rs.getString("phoneNum"));
            rep.setFaxNum(rs.getString("faxNum"));
            rep.setCommissionRate(rs.getDouble("commissionRate"));
            rep.setBasicSalary(rs.getDouble("basicSalary"));
            //Add representative to the ObservableList
            repList.add(rep);
        }
        //return empList (ObservableList of Representatives)
        return repList;
    }
	
    
    //Delete Representative
    public static void deleteRepWithId (String numPerson) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	
    	//Declare delete statement
    	String updateStmt =
		
    			" DELETE FROM REPRESENTATIVE" +		
    			" WHERE IDPERSON ="+ numPerson +";\n" ;
    	String delstatmt = "DELETE FROM person WHERE idPerson ="+numPerson+";";
    			
    	
    	//Execute UPDATE operation
    	try {
    		MySqlAdapter.dbExecuteUpdate(updateStmt);
    		MySqlAdapter.dbExecuteUpdate(delstatmt);
    	} catch (SQLException e) {
    		System.out.println("Error" + e);
    		throw e;
    	}
    }
	
	
    //Insert a Representative
    public static void insertRep( String firstName, String lastName,
    		String email, String phoneNum, String faxNum, double commissionRate, double basicSalary)
    		throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException{
    	String test = "select idPerson from person where idPerson =(select max(idPerson)from person)";
    	
    	
    	//Declare an INSERT statement
    	String updateStmt =
    			
    			"INSERT INTO PERSON\n"+
    			"(firstName, lastName, email, phoneNum, faxNum) \n " +
    			"VALUES ('"+firstName+"', '"+lastName+"', '"+email+"', '"+phoneNum+"', '"+faxNum+"');";
    			
    	String updateRepr =
    			" INSERT INTO REPRESENTATIVE\n" +
    			" (idPerson, commissionRate, basicSalary) \n" +
    			"VALUES (("+test+"),'"+commissionRate+"','"+basicSalary+"');";
    		
    	//Execute INSERT operation
    	try {
    		MySqlAdapter.dbExecuteUpdate(updateStmt);   		
    		MySqlAdapter.dbExecuteUpdate(updateRepr);
    	} catch (SQLException e) {
    		System.out.println("Erreur :" + updateStmt + "\n" + e );
    		throw e;
    	}
    	
    }
    
    //Update a Representative
    public static void updateRep (String numPerson, String firstName, String lastName,
    		String email, String phoneNum, String faxNum, double commissionRate, double basicSalary) 
    		throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	
    	//Declare update statement
    	String updateStmt =
    		   			
    			"UPDATE person SET \n " +
    			" 	FIRSTNAME = '" + firstName +  "'\n" +
    			" 	,LASTNAME = '" + lastName + "'\n" +
    			" 	,EMAIL = '" + email + "'\n" +
    			" 	,PHONENUM = '" + phoneNum + "'\n" +
    			" 	,FAXNUM = '" + faxNum + "'\n" +
    			" WHERE IDPERSON = " + numPerson ;
    
    	String updateRepr =
    			
    			
    			"UPDATE representative SET \n " +
    			" 	 COMMISSIONRATE = '" + commissionRate + "'\n" +
    			" 	 ,BASICSALARY = '" + basicSalary + "'\n" +
    			" WHERE IDPERSON = " + numPerson ;
    	
    	
    	//Execute update
    	try {
    		MySqlAdapter.dbExecuteUpdate(updateStmt);
    		MySqlAdapter.dbExecuteUpdate(updateRepr);
    	} catch (SQLException e) {
    		System.out.println("Erreur " + updateStmt + "\n" + e);
    		throw e;
    	}
    	
    }
}
