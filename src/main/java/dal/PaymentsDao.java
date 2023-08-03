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

import model.Payments;

public class PaymentsDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static PaymentsDao instance = null;

	protected PaymentsDao() {
		this.connectionManager = new ConnectionManager();
	}

	public static PaymentsDao getInstance() {
		if (instance == null) {
			instance = new PaymentsDao();
		}
		return instance;
	}

	/**
	 * Insert a new payment item into the database.
	 * 
	 * @param item
	 * @return
	 * @throws SQLException
	 */
	public Payments create(Payments item) throws SQLException {
		String insertPayment = "INSERT INTO Payments(CheckId, Date, Time, PaymentMethod, CCNumber, AuthNumber, PaymentAmount, Tips, EmployeeId) "
				+ "VALUES(?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPayment, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, item.getCheckId());
			insertStmt.setDate(2, item.getDate());
			insertStmt.setTime(3, item.getTime());
			insertStmt.setString(4, item.getPaymentMethod());
			insertStmt.setString(5, item.getCcNumber());
			insertStmt.setString(6, item.getAuthNumber());
			insertStmt.setFloat(7, item.getPaymentAmount());
			insertStmt.setFloat(8, item.getTips());
			insertStmt.setInt(9, item.getEmployeeId());

			// Execute statement
			insertStmt.executeUpdate();

			// Update id for the menu item since it was auto-generated from the database
			ResultSet generatedKeys = insertStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				item.setPaymentId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Creating payment item failed, no ID obtained.");
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
	 * Retrieve a payment item by payment id.
	 * @param paymentId
	 * @return
	 * @throws SQLException
	 */
	public Payments getPaymentByPaymentId(int paymentId) throws SQLException {
		String selectPayment = "SELECT PaymentId, CheckId, Date, Time, PaymentMethod, CCNumber, AuthNumber, PaymentAmount, Tips, EmployeeId "
				+ "FROM Payments " + "WHERE PaymentId=?;";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPayment);
			selectStmt.setInt(1, paymentId);

			results = selectStmt.executeQuery();
			if (results.next()) {
				// Retrieve values
				int resultPaymentId = results.getInt("PaymentId");
				int checkId = results.getInt("CheckId");
				Date date = results.getDate("Date");
				Time time = results.getTime("Time");
				String paymentMethod = results.getString("PaymentMethod");
				String ccNumber = results.getString("CCNumber");
				String authNumber = results.getString("AuthNumber");
				float paymentAmount = results.getFloat("PaymentAmount");
				float tips = results.getFloat("Tips");
				int employeeId = results.getInt("EmployeeId");

				Payments item = new Payments(resultPaymentId, checkId, date, time, paymentMethod, ccNumber, authNumber,
						paymentAmount, tips, employeeId);

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
	 * Retrieve payments from a check, where check id and date are known.
	 * @param checkId
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public List<Payments> getPaymentsByCheck(int checkId, Date date) throws SQLException {
		List<Payments> items = new ArrayList<>();
		String selectCheckItem = "SELECT PaymentId, CheckId, Date, Time, PaymentMethod, CCNumber, AuthNumber, PaymentAmount, Tips, EmployeeId "
				+ "FROM Payments " + "WHERE CheckId=? AND Date=?;";
		
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
				int paymentId = results.getInt("PaymentId");
				int resultCheckId = results.getInt("CheckId");
				Date resultDate = results.getDate("Date");
				Time time = results.getTime("Time");
				String paymentMethod = results.getString("PaymentMethod");
				String ccNumber = results.getString("CCNumber");
				String authNumber = results.getString("AuthNumber");
				float paymentAmount = results.getFloat("PaymentAmount");
				float tips = results.getFloat("Tips");
				int employeeId = results.getInt("EmployeeId");

				Payments item = new Payments(paymentId, resultCheckId, resultDate, time, paymentMethod, ccNumber, authNumber,
						paymentAmount, tips, employeeId);

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
	 * Retrieve a list of payment by date.
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public List<Payments> getPaymentsByDate(LocalDate date) throws SQLException {
		List<Payments> items = new ArrayList<>();
		String selectCheckItem = "SELECT PaymentId, CheckId, Date, Time, PaymentMethod, CCNumber, AuthNumber, PaymentAmount, Tips, EmployeeId "
				+ "FROM Payments " + "WHERE Date=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCheckItem);
			selectStmt.setDate(1, Date.valueOf(date));

			results = selectStmt.executeQuery();
			while(results.next()) {
				//Retrieve values
				int paymentId = results.getInt("PaymentId");
				int checkId = results.getInt("CheckId");
				Date resultDate = results.getDate("Date");
				Time time = results.getTime("Time");
				String paymentMethod = results.getString("PaymentMethod");
				String ccNumber = results.getString("CCNumber");
				String authNumber = results.getString("AuthNumber");
				float paymentAmount = results.getFloat("PaymentAmount");
				float tips = results.getFloat("Tips");
				int employeeId = results.getInt("EmployeeId");

				Payments item = new Payments(paymentId, checkId, resultDate, time, paymentMethod, ccNumber, authNumber,
						paymentAmount, tips, employeeId);

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
	
	public Payments delete(Payments item) throws SQLException{
		String deletePayments = "DELETE FROM Payments WHERE PaymentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePayments);
			deleteStmt.setInt(1, item.getPaymentId());
			int affectedRows = deleteStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for PaymentId = " + item.getPaymentId());
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
