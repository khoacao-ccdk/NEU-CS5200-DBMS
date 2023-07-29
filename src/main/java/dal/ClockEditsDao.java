package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.ClockEdits;

public class ClockEditsDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static ClockEditsDao instance = null;

	protected ClockEditsDao() {
		this.connectionManager = new ConnectionManager();
	}

	public static ClockEditsDao getInstance() {
		if (instance == null) {
			instance = new ClockEditsDao();
		}
		return instance;
	}

	public ClockEdits create(ClockEdits item) throws SQLException {
		String insertClockEdit = "INSERT INTO ClockEdits(TimeClockId, ClockIn, ClockOut, BreakStart, BreakEnd) "
				+ "VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertClockEdit, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, item.getTimeClockId());
			insertStmt.setTime(2, item.getIn());
			insertStmt.setTime(3, item.getOut());
			insertStmt.setTime(4, item.getBreakStart());
			insertStmt.setTime(5, item.getBreakEnd());

			// Execute statement
			insertStmt.executeUpdate();

			// Update id for the menu item since it was auto-generated from the database
			ResultSet generatedKeys = insertStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				item.setEditId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating Clock Edit item failed, no ID obtained.");
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
	 * Retrieve a clock edit record by ID.
	 * @param paymentId
	 * @return
	 * @throws SQLException
	 */
	public ClockEdits getClockEditById(int editId) throws SQLException {
		String selectClockEdit = "SELECT EditId, TimeClockId, In, Out, BreakStart, BreakEnd "
				+ "FROM ClockEdits " + "WHERE EditId=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectClockEdit);
			selectStmt.setInt(1, editId);

			results = selectStmt.executeQuery();
			if (results.next()) {
				// Retrieve values
				int resultClockEditId = results.getInt("EditId");
				int timeClockId = results.getInt("TimeClockId");
				Time in = results.getTime("In");
				Time out = results.getTime("Out");
				Time breakStart = results.getTime("BreakStart");
				Time breakEnd = results.getTime("BreakEnd");

				ClockEdits item = new ClockEdits(resultClockEditId, timeClockId, in, out, breakStart, breakEnd);

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
 * Retrieve a list of clock edit by date
 * @param checkId
 * @param date
 * @return
 * @throws SQLException
 */
	public List<ClockEdits> getClockEditssByTimeClockId(int timeClockId) throws SQLException {
		List<ClockEdits> items = new ArrayList<>();
		String selectClockEdits = "SELECT EditId, TimeClockId, In, Out, BreakStart, BreakEnd "
				+ "FROM ClockEdits " + "WHERE TimeClockId=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectClockEdits);
			selectStmt.setInt(1, timeClockId);

			results = selectStmt.executeQuery();
			while (results.next()) {
				// Retrieve values
				int clockEditId = results.getInt("EditId");
				int resultTimeClockId = results.getInt("TimeClockId");
				Time in = results.getTime("In");
				Time out = results.getTime("Out");
				Time breakStart = results.getTime("BreakStart");
				Time breakEnd = results.getTime("BreakEnd");

				ClockEdits item = new ClockEdits(clockEditId, resultTimeClockId, in, out, breakStart, breakEnd);

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
	 * Delete a Clock Edit
	 * @param item
	 * @return
	 * @throws SQLException
	 */
	public ClockEdits delete(ClockEdits item) throws SQLException {
		String deleteClockEdit = "DELETE FROM ClockEdits WHERE editId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteClockEdit);
			deleteStmt.setInt(1, item.getEditId());
			int affectedRows = deleteStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for EditId = " + item.getEditId());
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
