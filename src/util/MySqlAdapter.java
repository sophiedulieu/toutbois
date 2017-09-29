package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.rowset.CachedRowSetImpl;

import dao.ClientDAO;
import dao.CompanyDAO;
import dao.ContactDAO;
import dao.ProspectDAO;
import dao.RepresentativeDAO;
import model.Client;
import model.Company;
import model.Contact;
import model.Prospect;
import model.Representative;



public abstract class MySqlAdapter {


	// **************   ATTRIBUTES   **************
	
	
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/toutbois";
    private static String login = "toutbois";
    private static String password = "";
    private static Connection cn = null;
    private static Statement st = null;
    private static ResultSet rs = null;
    private static CachedRowSetImpl crs = null;
    
    
    // Save
    public static void loadData() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	
    	ContactDAO.buildContactListDAO();
		Contact.getContactList().setAll(
				ContactDAO.getContactListDAO());
		
		Representative.getRepresentativeData().setAll(
				RepresentativeDAO.searchRepresentatives());
		
		ClientDAO.buildClientListDAO();
		Client.getClientList().setAll(
				ClientDAO.getClientListDAO());
		
		ProspectDAO.buildProspectListDAO();
		Prospect.getProspectList().setAll(
				ProspectDAO.getProspectListDAO());
		
		//Person.setAiPerson(PersonDAO.getAiPersonDAO());
		Company.setAiCompany(CompanyDAO.getAiCompanyDAO());
    }
	
    
    // Connection
    public static void dbConnect() {
    	try {
        	Class.forName(JDBC_DRIVER);
        	cn = DriverManager.getConnection(url, login, password);
    	}
		catch (SQLException e){
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    
    // Disconnection
    public static void dbDisconnect() {
        try {
            if (cn != null && !cn.isClosed()) {
                cn.close();
            }
        }
        catch (Exception e){
			e.printStackTrace();
        }
    }
    
    
    // Query
    public static ResultSet dbExecuteQuery(String querySt) throws SQLException {

        dbConnect();
        try {
			st = cn.createStatement();
			rs = st.executeQuery(querySt);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
		}
        catch (SQLException e) {
			e.printStackTrace();
		}
        finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            dbDisconnect();
        }
        
		return crs;
    }
    
    
    // Update
    public static int dbExecuteUpdate(String updateSt) throws SQLException {

        int genKey = -1;
        
        dbConnect();
        try {
            st = cn.createStatement();
            st.executeUpdate(updateSt, Statement.RETURN_GENERATED_KEYS);
            rs = st.getGeneratedKeys();
            
            while (rs.next()) {
            	genKey = rs.getInt(1);
            }
        }
        catch (SQLException e) {
			e.printStackTrace();
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            dbDisconnect();
        }

		return genKey;
    }
	

} // public class MySqlAdapter
