package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.TimeClocks;

public class TimeClocksDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static TimeClocksDao instance = null;

	protected TimeClocksDao() {
		this.connectionManager = new ConnectionManager();
	}

	public static TimeClocksDao getInstance() {
		if (instance == null) {
			instance = new TimeClocksDao();
		}
		return instance;
	}
	
	public TimeClocks create(TimeClocks item) throws SQLException {
		String insertTimeClock = "INSERT INTO TimeClocks(EmployeeId, Date, In, Out, UnpaidBreak) "
				+ "VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTimeClock, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, item.getEmployeeId());
			insertStmt.setDate(2, Date.valueOf(item.getDate()));
			insertStmt.setTime(3, item.getClockInTime());
			insertStmt.setTime(4, item.getClockOutTime());
			insertStmt.setInt(5, item.getUnpaidBreakMin());

			// Execute statement
			insertStmt.executeUpdate();

			// Update id for the menu item since it was auto-generated from the database
			ResultSet generatedKeys = insertStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				item.setTimeClockId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating Time Clock item failed, no ID obtained.");
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
	
	public TimeClocks getTimeClockById(int timeClockId) throws SQLException {
		String selectTimeClock = "SELECT TimeClockId, EmployeeId, Date, ClockInTime, ClockOutTime, UnpaidBreakMin "
				+ "FROM TimeClocks " + "WHERE TimeClockId=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTimeClock);
			selectStmt.setInt(1, timeClockId);

			results = selectStmt.executeQuery();
			if (results.next()) {
				// Retrieve values
				int resultTimeClockId = results.getInt("TimeClockId");
				int employeeId = results.getInt("EmployeeId");
				LocalDate date = results.getDate("Date").toLocalDate();
				Time in = results.getTime("ClockInTime");
				Time out = results.getTime("ClockOutTime");
				int unpaidBreak = results.getInt("UnpaidBreakMin");

				TimeClocks item = new TimeClocks(resultTimeClockId, employeeId, date, in, out, unpaidBreak);

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
	
	public List<TimeClocks> getTimeClockByDate(LocalDate date) throws SQLException {
		List<TimeClocks> items = new ArrayList<>();
		String selectTimeClock = "SELECT TimeClockId, EmployeeId, Date, ClockInTime, ClockOutTime, UnpaidBreakMin "
				+ "FROM TimeClocks " + "WHERE Date=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTimeClock);
			selectStmt.setDate(1, Date.valueOf(date));

			results = selectStmt.executeQuery();
			while(results.next()) {
				//Retrieve values
				int timeClockId = results.getInt("TimeClockId");
				int employeeId = results.getInt("EmployeeId");
				LocalDate resultDate = results.getDate("Date").toLocalDate();
				Time in = results.getTime("ClockInTime");
				Time out = results.getTime("ClockOutTime");
				int unpaidBreak = results.getInt("UnpaidBreakMin");

				TimeClocks item = new TimeClocks(timeClockId, employeeId, resultDate, in, out, unpaidBreak);

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
	
	public List<TimeClocks> getIncorrectTimeClock (Date start, Date end) throws SQLException {
		List<TimeClocks> timeClocks = new ArrayList<>();
		String findIncorrectTimeClock = "SELECT INVALID.TimeClockId, INVALID.EmployeeId, INVALID.Date, INVALID.ClockInTime, INVALID.ClockOutTime, INVALID.UnpaidBreakMin "
				+ "FROM "
				+ "(SELECT T.* "
				+ "FROM Employees E INNER JOIN Timeclocks T "
				+ "ON E.EmployeeId = T.EmployeeId "
				+ "WHERE (ClockInTime < '08:00:00' "
				+ "OR ClockInTime > '23:00:00' "
				+ "OR ClockOutTime > '23:00:00' "
				+ "OR ClockOutTime < '08:00:00') "
				+ "AND E.Status = true "
				+ "AND T.DATE BETWEEN ? AND ?) AS INVALID "
				+ "LEFT OUTER JOIN ClockEdits Edit "
				+ "ON INVALID.TimeClockId = Edit.TimeClockId "
				+ "WHERE Edit.ClockEditId IS NULL;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(findIncorrectTimeClock);
			selectStmt.setDate(1, start);
			selectStmt.setDate(2, end);

			results = selectStmt.executeQuery();
			while(results.next()) {
				//Retrieve values
				int timeClockId = results.getInt("TimeClockId");
				int employeeId = results.getInt("EmployeeId");
				LocalDate resultDate = results.getDate("Date").toLocalDate();
				Time clockInTime = results.getTime("ClockInTime");
				Time clockOutTime = results.getTime("ClockOutTime");
				int unpaidBreak = results.getInt("UnpaidBreakMin");

				TimeClocks item = new TimeClocks(timeClockId, employeeId, resultDate, clockInTime, clockOutTime, unpaidBreak);

				timeClocks.add(item);
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
		return timeClocks;
	}
	
	public TimeClocks delete(TimeClocks item) throws SQLException{
		String deletePayments = "DELETE FROM TimeClocks WHERE TimeClockId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePayments);
			deleteStmt.setInt(1, item.getTimeClockId());
			int affectedRows = deleteStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for TimeClockId = " + item.getTimeClockId());
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
