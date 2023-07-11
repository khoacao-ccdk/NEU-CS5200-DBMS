package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MenuItem;

/**
 * MenuItem Dao - Data access class to interact with the database
 * 
 * @author Cody Cao & Xiaolin Zhan
 */
public class MenuItemDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
	private static MenuItemDao instance = null;

	protected MenuItemDao() {
		this.connectionManager = new ConnectionManager();
	}
	
	public static MenuItemDao getInstance() {
		if(instance == null) {
			instance = new MenuItemDao();
		}
		return instance;
	}

    /**
	 * Insert a new menu item into the database
	 * @param item
	 * @return
	 * @throws SQLException
	 */
	public MenuItem create(MenuItem item) throws SQLException {
        String insertMenuItem = "INSERT INTO MenuItems(ItemName, ItemPrice, CategoryId) VALUES(?,?,?);";
        Connection connection = null;
		PreparedStatement insertStmt = null;    

        try {
            connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertMenuItem);
		
            insertStmt.setString(1, item.getItemName());
			insertStmt.setDouble(2, item.getItemPrice());
			insertStmt.setInt(3, item.getCategoryId());

			//Execute statement
			insertStmt.executeUpdate();

			//Update id for the menu item since it was auto-generated from the database
			ResultSet generatedKeys = insertStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
                item.setItemId(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating menu item failed, no ID obtained.");
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
	 * Update menu item data
	 * @param item
	 * @return
	 * @throws SQLException
	 */
	public MenuItem updateItem(MenuItem item) throws SQLException {
		String updateMenuItem = "UPDATE MenuItems "
			+ "SET ItemName = ?, ItemPrice = ?, CategoryId = ? "
			+ "WHERE ItemId = ?;";
        Connection connection = null;
		PreparedStatement updateStmt = null;    

        try {
            connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateMenuItem);
		
            updateStmt.setString(1, item.getItemName());
			updateStmt.setDouble(2, item.getItemPrice());
			updateStmt.setInt(3, item.getCategoryId());
			updateStmt.setInt(4, item.getItemId());

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
	 * Retrieves a all menu item from database
	 * 
	 * @return a list of menu items
	 * @throws SQLException
	 */
	public List<MenuItem> getAllItem() throws SQLException {
		List<MenuItem> items = new ArrayList<>();
		String selectMenuItem = "SELECT ItemId, ItemName, ItemPrice, CategoryId "
				+ "FROM MenuItem;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMenuItem);

			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultItemId = results.getInt("ItemId");
				String itemName = results.getString("ItemName");
				double itemPrice = results.getDouble("ItemPrice");
				int categoryId = results.getInt("CategoryId");

				MenuItem item = new MenuItem(resultItemId, itemName, itemPrice, categoryId);
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
	 * Retrieves a specific menu item from database
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public MenuItem getItemById(int itemId) throws SQLException {
		String selectMenuItem = "SELECT ItemId, ItemName, ItemPrice, CategoryId "
				+ "FROM MenuItem WHERE ItemId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMenuItem);
			selectStmt.setInt(1, itemId);

			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultItemId = results.getInt("ItemId");
				String itemName = results.getString("ItemName");
				double itemPrice = results.getDouble("ItemPrice");
				int categoryId = results.getInt("CategoryId");

				MenuItem item = new MenuItem(resultItemId, itemName, itemPrice, categoryId);
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
	 * Retrieves menu items with name like the one provided from database
	 * 
	 * @return a list of menu items
	 * @throws SQLException
	 */
	public List<MenuItem> getItemFromName(String itemName) throws SQLException {
		List<MenuItem> items = new ArrayList<>();
		String selectMenuItem = "SELECT ItemId, ItemName, ItemPrice, CategoryId "
				+ "FROM MenuItem "
				+ "WHERE ItemName LIKE \'%?%\'";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMenuItem);
			selectStmt.setString(1, itemName);

			results = selectStmt.executeQuery();
			while(results.next()) {
				int itemId = results.getInt("ItemId");
				String resultItemName = results.getString("ItemName");
				double itemPrice = results.getDouble("ItemPrice");
				int categoryId = results.getInt("CategoryId");

				MenuItem item = new MenuItem(itemId, resultItemName, itemPrice, categoryId);
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
	 * Delete an item from MenuItem table, given the item's information
	 * @param item
	 * @return
	 * @throws SQLException
	 */
	public MenuItem delete(MenuItem item) throws SQLException {
		String deleteUser = "DELETE FROM MenuItem WHERE ItemId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, item.getItemId());
			int affectedRows = deleteStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for ItemId = " + item.getItemId());
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
