package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
import util.MySqlAdapter;

public abstract class ClientDAO extends CompanyDAO {
	
	
	// List
	public static ObservableList<Client> clientListDAO = 
			FXCollections.observableArrayList();
	
	
	// Get list
	public static ObservableList<Client> getClientListDAO() {
		return clientListDAO;
	}
	
	
	// Build list
    public static ObservableList<Client> buildClientListDAO() throws SQLException {

		String selectSt = "SELECT * FROM Client;";
		ResultSet rs = MySqlAdapter.dbExecuteQuery(selectSt);
		
        while (rs.next()) {
        	// TODO test
        	/*
        	System.out.println(rs.getString("idCompany"));
        	System.out.println(rs.getString("nbOrder"));
        	*/
        	
        	Client client = new Client();
        	client.setIdCompany(rs.getInt("idCompany"));
        	client.setNbOrder(rs.getInt("nbOrder"));
        	clientListDAO.add(client);
        }
        
        for (Client client : clientListDAO) {
        	getCompanyDAO(client);
        }
        
		return clientListDAO;
	}
	
	
	// Insert Client
	public static int insertClientDAO(Client client) throws SQLException {
		
		String insertSt = 
				"INSERT INTO Client (idCompany, nbOrder) "
				+ "VALUES ("
						+ "" + client.getIdCompany() + ", " 
						+ "" + client.getNbOrder() + "); ";
		
		return MySqlAdapter.dbExecuteUpdate(insertSt);
	}
	
	
	// Edit Client
	public static int editClientDAO(Client client) throws SQLException {

		CompanyDAO.editCompanyDAO(client);
		
		String updateSt = 
				"UPDATE Client "
				+ "SET "
					+ "nbOrder = " + client.getNbOrder() + " "
				+ "WHERE idCompany = " + client.getIdCompany() + "; ";
		
		return MySqlAdapter.dbExecuteUpdate(updateSt);
	}
	
	
	// Change Prospect to Client
	public static int prospectToClient(Client client) throws SQLException {
		
		String deleteSt = 
				"DELETE FROM Prospect "
				+ "WHERE idCompany = " + client.getIdCompany() + ";";
		
		MySqlAdapter.dbExecuteUpdate(deleteSt);
		
		String insertSt = 
				"INSERT INTO Client (idCompany, nbOrder) "
				+ "VALUES ("
						+ "" + client.getIdCompany() + ", "
						+ "'" + client.getNbOrder() + "'); ";
		
		return MySqlAdapter.dbExecuteUpdate(insertSt);
	}
	
	
	// Delete Client
	public static int deleteClient(Client client) {
		
		
		return -1;
	}
	

} // public abstract class ClientDAO
