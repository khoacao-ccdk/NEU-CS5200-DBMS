package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.*;
/**
 * Category Dao - Data access class to interact with the database
 * 
 * @author Cody Cao & Xiaolin Zhan
 */
public class CategoriesDao {

	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static CategoriesDao instance = null;

	protected CategoriesDao() {
			this.connectionManager = new ConnectionManager();
		}

	public static CategoriesDao getInstance() {
		if (instance == null) {
			instance = new CategoriesDao();
		}
		return instance;
	}

	/**
	 * Insert a new category item into the database
	 * 
	 * @param item
	 * @return
	 * @throws SQLException
	 */
	public Categories create(Categories item) throws SQLException {
		String insertItem = "INSERT INTO Categories(Name) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertItem, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, item.getName());

			// Execute statement
			insertStmt.executeUpdate();

			// Update id for the menu item since it was auto-generated from the database
			ResultSet generatedKeys = insertStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				item.setCategoryId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating menu item failed, no ID obtained.");
			}

			return item;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Update category data
	 * 
	 * @param item
	 * @return
	 * @throws SQLException
	 */
	public Categories updateCategory(Categories item, String newName) throws SQLException {
		String updateItem = "UPDATE Categories " + "SET Name = ? " + "WHERE CategoryId = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;

		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateItem);

			updateStmt.setString(1, newName);
			updateStmt.setInt(2, item.getCategoryId());

			// Execute statement
			int numAffectedRow = updateStmt.executeUpdate();
			if (numAffectedRow == 0) {
				throw new SQLException(String.format("No record updated for ItemId = %d", item.getCategoryId()));
			}

			// Update object to reflect changes
			item.setName(newName);

			return item;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Retrieves a all category items from database
	 * 
	 * @return a list of category items
	 * @throws SQLException
	 */
	public List<Categories> getAllCategories() throws SQLException {
		List<Categories> items = new ArrayList<>();
		String selectCategory = "SELECT CategoryId, Name " + "FROM Categories;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCategory);

			results = selectStmt.executeQuery();
			while (results.next()) {
				int resultCategoryId = results.getInt("CategoryId");
				String name = results.getString("Name");

				Categories item = new Categories(resultCategoryId, name);
				items.add(item);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return items;
	}

	/**
	 * Retrieves a specific category item from database with id
	 * 
	 * @param categoryId
	 * @return
	 * @throws SQLException
	 */
	public Categories getCategoryById(int categoryId) throws SQLException {
		String selectCategory = 
				"SELECT CategoryId, Name " 
				+ "FROM Categories " 
				+ "WHERE CateogryId=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCategory);
			selectStmt.setInt(1, categoryId);

			results = selectStmt.executeQuery();
			if (results.next()) {
				int resultCategoryId = results.getInt("CategoryId");
				String name = results.getString("Name");

				Categories item = new Categories(resultCategoryId, name);
				return item;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}

	/**
	 * Retrieves categories from the database with name.
	 * 
	 * @return a list of category items
	 * @throws SQLException
	 */
	public List<Categories> getCategoriesByName(String name) throws SQLException {
		List<Categories> items = new ArrayList<>();
		String selectCategories = 
				"SELECT CategoriesId, Name " 
				+ "FROM Categories "
				+ "WHERE Name LIKE \'%?%\'";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCategories);
			selectStmt.setString(1, name);

			results = selectStmt.executeQuery();
			while (results.next()) {
				int categoryId = results.getInt("CategoryId");
				String resultName = results.getString("Name");

				Categories item = new Categories(categoryId, resultName);
				items.add(item);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return items;
	}

	/**
	 * Delete a category from Category table
	 * 
	 * @param category
	 * @return
	 * @throws SQLException
	 */
	public Categories delete(Categories item) throws SQLException {
		String deleteCategory = "DELETE FROM Categories WHERE CategoryId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCategory);
			deleteStmt.setInt(1, item.getCategoryId());
			int affectedRows = deleteStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for ItemId = " + item.getCategoryId());
			}

			// Return null so the caller can no longer operate on the instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
