package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Prospect;
import util.MySqlAdapter;

public abstract class ProspectDAO extends CompanyDAO {
	
	
	// List
	public static ObservableList<Prospect> prospectListDAO = 
			FXCollections.observableArrayList();
	
	
	// Get list
	public static ObservableList<Prospect> getProspectListDAO() {
		return prospectListDAO;
	}
	
	
	// Build list
	public static ObservableList<Prospect> buildProspectListDAO() throws SQLException {
		
		prospectListDAO.clear();
		
		String selectSt = "SELECT * FROM Prospect;";
		ResultSet rs = MySqlAdapter.dbExecuteQuery(selectSt);
		
        while (rs.next()) {
        	// TODO test
        	/*
        	System.out.println(rs.getString("idCompany"));
        	System.out.println(rs.getString("lastVisit"));
        	*/
        	
        	Prospect prospect = new Prospect();
        	prospect.setIdCompany(rs.getInt("idCompany"));
        	prospect.setLastVisit(rs.getDate("lastVisit"));
        	prospectListDAO.add(prospect);
        }
        
        for (Prospect prospect : prospectListDAO) {
        	getCompanyDAO(prospect);
        }
        
		return prospectListDAO;
	}
	
	
	// Insert Prospect
	public static int insertProspectDAO(Prospect prospect) throws SQLException {
		
		String insertSt = 
				"INSERT INTO Prospect (idCompany, lastVisit) "
				+ "VALUES ("
						+ "" + prospect.getIdCompany() + ", " 
						+ "'" + prospect.getLastVisit() + "'); ";
		
		return MySqlAdapter.dbExecuteUpdate(insertSt);
	}
	
	
	// Edit Prospect
	public static int editProspectDAO(Prospect prospect) throws SQLException {

		CompanyDAO.editCompanyDAO(prospect);
		
		String updateSt = 
				"UPDATE Prospect "
				+ "SET "
					+ "lastVisit = '" + prospect.getLastVisit() + "' "
				+ "WHERE idCompany = " + prospect.getIdCompany() + "; ";
		
		return MySqlAdapter.dbExecuteUpdate(updateSt);
	}
	
	
	// Change Client to Prospect
	public static int clientToProspect(Prospect prospect) throws SQLException {
		
		String deleteSt = 
				"DELETE FROM Client "
				+ "WHERE idCompany = " + prospect.getIdCompany() + ";";
		
		MySqlAdapter.dbExecuteUpdate(deleteSt);
		
		String insertSt = 
				"INSERT INTO Prospect (idCompany, lastVisit) "
				+ "VALUES ("
						+ "" + prospect.getIdCompany() + ", "
						+ "'" + prospect.getLastVisit() + "'); ";
		
		return MySqlAdapter.dbExecuteUpdate(insertSt);
	}
	
	
	// Delete Prospect
	public static int deleteProspect(Prospect prospect) throws SQLException {
		
		// TODO company
		
		String deleteSt = 
				"DELETE FROM Prospect "
				+ "WHERE idCompany = " + prospect.getIdCompany() + ";";
		
		return MySqlAdapter.dbExecuteUpdate(deleteSt);
	}
	
	
} // public abstract class ProspectDAO
