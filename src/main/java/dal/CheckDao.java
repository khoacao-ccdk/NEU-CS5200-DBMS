package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.Check;

/**
 * Check Dao - Data access class to interact with the database
 * 
 * @author Cody Cao & Xiaolin Zhan
 */
public class CheckDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
	private static CheckDao instance = null;

	protected CheckDao() {
		this.connectionManager = new ConnectionManager();
	}
	
	public static CheckDao getInstance() {
		if(instance == null) {
			instance = new CheckDao();
		}
		return instance;
	}

    /**
	 * Insert a new menu item into the database
	 * @param item
	 * @return
	 * @throws SQLException
	 */
	public Check create(Check check) throws SQLException {
        String insertMenuItem = "INSERT INTO Checks(CheckId, Date, RevcenterId, EmployeeId, TableDesc, GuestCount, ItemCount, NetSales, Comps, Promo, Tax, TimeOpen, TimeClose) "
        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
		PreparedStatement insertStmt = null;    

        try {
            connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertMenuItem);
            
            insertStmt.setInt(1, check.getCheckId());
			insertStmt.setDate(2, check.getDate());
			insertStmt.setInt(3, check.getRevCenterId());
			insertStmt.setInt(4, check.getEmployeeId());
			insertStmt.setString(5, check.getTableDesc());
			insertStmt.setInt(6, check.getGuestCount());
			insertStmt.setInt(7, check.getItemCount());
			insertStmt.setDouble(8, check.getNetSales());
			insertStmt.setDouble(9, check.getComps());
			insertStmt.setDouble(10, check.getPromo());
			insertStmt.setDouble(11, check.getTax());
			insertStmt.setTime(12, check.getTimeOpen());
			insertStmt.setTime(13, check.getTimeClose());

			//Execute statement
			insertStmt.executeUpdate();
			
			return check;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
        }
    }

	/**
	 * Retrieves a check record by CheckId and Date since check has a compound primary key
	 * @param checkId
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public Check getCheckByIdDate(int checkId, Date date) throws SQLException {
		String selectCheck = "SELECT CheckId, Date, RevcenterId, EmployeeId, TableDesc, GuestCount, ItemCount, NetSales, Comps, Promo, Tax, TimeOpen, TimeClose "
		+ "FROM Checks WHERE CheckId=? AND DATE=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCheck);
			selectStmt.setInt(1, checkId);
			selectStmt.setDate(2, date);

			results = selectStmt.executeQuery();
			if(results.next()) {
				//Retrieve values
				int resultCheckId = results.getInt("CheckId");
				Date resultDate = results.getDate("Date");
				int revCenterId = results.getInt("RevcenterId");
				int employeeId = results.getInt("EmployeeId");
				String tableDesc = results.getString("TableDesc");
				int guestCount = results.getInt("GuestCount");
				int itemCount = results.getInt("ItemCount");
				double netSales = results.getDouble("NetSales");
				double comps = results.getDouble("Comps");
				double promo = results.getDouble("Promo");
				double tax = results.getDouble("Tax");
				Time timeOpen = results.getTime("TimeOpen");
				Time timeClose = results.getTime("TimeClose");

				Check check = new Check(resultCheckId, resultDate, revCenterId, 
					employeeId, tableDesc, guestCount, itemCount, netSales, comps, promo, tax, 
					timeOpen, timeClose);

				return check;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	/**
	 * Retrieves a check record by Date 
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public List<Check> getCheckByDate(int checkId, Date date) throws SQLException {
		List<Check> checks = new ArrayList<>();
		String selectCheck = "SELECT CheckId, Date, RevcenterId, EmployeeId, TableDesc, GuestCount, ItemCount, NetSales, Comps, Promo, Tax, TimeOpen, TimeClose "
		+ "FROM Checks WHERE DATE=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCheck);
			selectStmt.setDate(1, date);

			results = selectStmt.executeQuery();
			while(results.next()) {
				//Retrieve values
				int resultCheckId = results.getInt("CheckId");
				Date resultDate = results.getDate("Date");
				int revCenterId = results.getInt("RevcenterId");
				int employeeId = results.getInt("EmployeeId");
				String tableDesc = results.getString("TableDesc");
				int guestCount = results.getInt("GuestCount");
				int itemCount = results.getInt("ItemCount");
				double netSales = results.getDouble("NetSales");
				double comps = results.getDouble("Comps");
				double promo = results.getDouble("Promo");
				double tax = results.getDouble("Tax");
				Time timeOpen = results.getTime("TimeOpen");
				Time timeClose = results.getTime("TimeClose");

				Check check = new Check(resultCheckId, resultDate, revCenterId, 
					employeeId, tableDesc, guestCount, itemCount, netSales, comps, promo, tax, 
					timeOpen, timeClose);

				checks.add(check);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return checks;
	}

	/**
	 * Update Check information
	 * @param check
	 * @return
	 * @throws SQLException
	 */
	public Check updateCheck(Check check) throws SQLException {
		String update = "UPDATE Checks "
			+ "SET RevcenterId=?, EmployeeId=?, TableDesc=?, GuestCount=?, ItemCount=?, NetSales=?, Comps=?, Promo=?, Tax=?, TimeOpen=?, TimeClose=? "
			+ "WHERE CheckId=? AND Date=?;";

		Connection connection = null;
		PreparedStatement updateStmt = null;  
		try{ 
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(update);

			updateStmt.setInt(1, check.getRevCenterId());
			updateStmt.setInt(2, check.getEmployeeId());
			updateStmt.setString(3, check.getTableDesc());
			updateStmt.setInt(4, check.getGuestCount());
			updateStmt.setInt(5, check.getItemCount());
			updateStmt.setDouble(6, check.getNetSales());
			updateStmt.setDouble(7, check.getComps());
			updateStmt.setDouble(8, check.getPromo());
			updateStmt.setDouble(9, check.getTax());
			updateStmt.setTime(10, check.getTimeOpen());
			updateStmt.setTime(11, check.getTimeClose());
			updateStmt.setInt(12, check.getCheckId());
			updateStmt.setDate(13, check.getDate());

			int numAffectedRow = updateStmt.executeUpdate();

			System.out.println(String.format("%d row updated.", numAffectedRow));
			
			return check;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the record from the Check table
	 * @param check
	 * @return
	 * @throws SQLException
	 */
	public Check delete(Check check) throws SQLException {
		String deleteCheck = "DELETE FROM Check WHERE CheckId=? AND Date=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCheck);
			deleteStmt.setInt(1, check.getCheckId());
			deleteStmt.setDate(2, check.getDate());
			
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for CheckId = " + check.getCheckId());
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
