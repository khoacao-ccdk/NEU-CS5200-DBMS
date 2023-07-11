package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.CheckItem;

/**
 * CheckItem Dao - Data access class to interact with the database
 * 
 * @author Cody Cao & Xiaolin Zhan
 */
public class CheckItemDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
	private static CheckItemDao instance = null;

	protected CheckItemDao() {
		this.connectionManager = new ConnectionManager();
	}
	
	public static CheckItemDao getInstance() {
		if(instance == null) {
			instance = new CheckItemDao();
		}
		return instance;
	}

    /**
	 * Insert a new menu item into the database
	 * @param item
	 * @return
	 * @throws SQLException
	 */
	public CheckItem create(CheckItem item) throws SQLException {
        String insertMenuItem = "INSERT INTO CheckItems(CheckId, Date, ItemId, TimeCreated, OrderModeId, Qty, RefundQty, EmployeeId, ParentModifierId) "
        + "VALUES(?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
		PreparedStatement insertStmt = null;    

        try {
            connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertMenuItem);
            insertStmt.setInt(1, item.getCheckId());
			insertStmt.setDate(2, item.getDate());
			insertStmt.setInt(3, item.getItemId());
			insertStmt.setTimestamp(4, item.getTimeCreated());
			insertStmt.setInt(5, item.getOrderModeId());
			insertStmt.setInt(6, item.getQuantity());
			insertStmt.setInt(7, item.getRefundQuantity());
			insertStmt.setInt(8, item.getEmployeeId());
			insertStmt.setInt(9, item.getParentModifierId());

			//Execute statement
			insertStmt.executeUpdate();

			//Update id for the menu item since it was auto-generated from the database
			ResultSet generatedKeys = insertStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
                item.setCheckItemId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating check item failed, no ID obtained.");
            }
			
			return item;
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
	 * Update check item data
	 * @param item
	 * @return
	 * @throws SQLException
	 */
	public CheckItem updateItem(CheckItem item) throws SQLException {
		String updateMenuItem = "UPDATE CheckItems "
			+ "SET CheckId=?, Date=?, ItemId=?, TimeCreated=?, OrderModeId=?, Qty=?, RefundQty=?, EmployeeId=?, ParentModifierId=? "
			+ "WHERE CheckItemId = ?;";
        Connection connection = null;
		PreparedStatement updateStmt = null;    

        try {
            connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateMenuItem);
		
            updateStmt.setInt(1, item.getCheckId());
			updateStmt.setDate(2, item.getDate());
			updateStmt.setInt(3, item.getItemId());
			updateStmt.setTimestamp(4, item.getTimeCreated());
			updateStmt.setInt(5, item.getOrderModeId());
			updateStmt.setInt(6, item.getQuantity());
			updateStmt.setInt(7, item.getRefundQuantity());
			updateStmt.setInt(8, item.getEmployeeId());
			updateStmt.setInt(9, item.getParentModifierId());
			updateStmt.setInt(10, item.getCheckItemId());

			//Execute statement
			int numAffectedRow = updateStmt.executeUpdate();

			System.out.println(String.format("%d row updated.", numAffectedRow));
			
			return item;
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
	 * Retrieve the specific row with the given data
	 * @param checkItemId
	 * @return
	 * @throws SQLException
	 */
	public CheckItem getItemById(int checkItemId) throws SQLException {
		String selectCheckItem = "SELECT CheckItemId, CheckId, Date, ItemId, TimeCreated, OrderModeId, Qty, RefundQty, EmployeeId, ParentModifierId "
				+ "FROM CheckItems WHERE CheckItemId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCheckItem);
			selectStmt.setInt(1, checkItemId);

			results = selectStmt.executeQuery();
			if(results.next()) {
				//Retrieve values
				int resultCheckItemId = results.getInt("CheckItemId");
				int checkId = results.getInt("CheckId");
				Date date = results.getDate("Date");
				int itemId = results.getInt("ItemId");
				Timestamp timeCreated = results.getTimestamp("TimeCreated");
				int quantity = results.getInt("Qty");
				int refundQuantity = results.getInt("RefundQty");
				int employeeId = results.getInt("EmployeeId");
				int parentModifierId = results.getInt("ParentModifierId");

				CheckItem item = new CheckItem(resultCheckItemId, 
					checkId, date, resultCheckItemId, timeCreated, itemId, quantity, 
					refundQuantity, employeeId, parentModifierId);

				return item;
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
	 * Retrieve the items given check id and date
	 * @param checkItemId
	 * @return
	 * @throws SQLException
	 */
	public List<CheckItem> getItemByCheck(int checkId, Date date) throws SQLException {
		List<CheckItem> items = new ArrayList<>();
		String selectCheckItem = "SELECT CheckItemId, CheckId, Date, ItemId, TimeCreated, OrderModeId, Qty, RefundQty, EmployeeId, ParentModifierId "
				+ "FROM CheckItems WHERE CheckId=? AND Date=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCheckItem);
			selectStmt.setInt(1, checkId);
			selectStmt.setDate(2, date);

			results = selectStmt.executeQuery();
			while(results.next()) {
				//Retrieve values
				int checkItemId = results.getInt("CheckItemId");
				int resultCheckId = results.getInt("CheckId");
				Date resultDate = results.getDate("Date");
				int itemId = results.getInt("ItemId");
				Timestamp timeCreated = results.getTimestamp("TimeCreated");
				int quantity = results.getInt("Qty");
				int refundQuantity = results.getInt("RefundQty");
				int employeeId = results.getInt("EmployeeId");
				int parentModifierId = results.getInt("ParentModifierId");

				CheckItem item = new CheckItem(checkItemId, 
					resultCheckId, resultDate, itemId, timeCreated, itemId, quantity, 
					refundQuantity, employeeId, parentModifierId);

				items.add(item);
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
		return items;
	}

	/**
	 * Delete a check item from database - do not use in production since we need to issue a refund
	 * @param item
	 * @return
	 * @throws SQLException
	 */
	public CheckItem delete(CheckItem item) throws SQLException{
		String deleteUser = "DELETE FROM CheckItem WHERE CheckItemId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, item.getCheckItemId());
			int affectedRows = deleteStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for CheckItemId = " + item.getCheckItemId());
			}

			// Return null so the caller can no longer operate on the instance.
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
