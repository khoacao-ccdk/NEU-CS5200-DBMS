package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

/**
 * Employee Dao - Data access class to interact with the database
 * 
 * @author Cody Cao & Xiaolin Zhan
 */
public class EmployeeDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
	private static EmployeeDao instance = null;

	protected EmployeeDao() {
		this.connectionManager = new ConnectionManager();
	}
	
	public static EmployeeDao getInstance() {
		if(instance == null) {
			instance = new EmployeeDao();
		}
		return instance;
	}

    /**
	 * Insert a new employee record into the database
	 * @param employee
	 * @return
	 * @throws SQLException
	 */
	public Employee create(Employee employee) throws SQLException{
        String insertEmployee = "INSERT INTO Employee(EmployeeId, FirstName, LastName, SSN, DOB, Email, Phone, Street1, Street2, City, State, Zip, Status, Role, Wage) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertEmployee);
			insertStmt.setInt(1, employee.getEmployeeId());
			insertStmt.setString(2, employee.getFirstName());
			insertStmt.setString(3, employee.getLastName());
			insertStmt.setString(4, employee.getSSN());
			insertStmt.setDate(5, employee.getDOB());
			insertStmt.setString(6, employee.getEmail());
			insertStmt.setString(7, employee.getPhone());
			insertStmt.setString(8, employee.getStreet1());
			insertStmt.setString(9, employee.getStreet2());
			insertStmt.setString(10, employee.getCity());
			insertStmt.setString(11, employee.getState());
			insertStmt.setString(12, employee.getZip());
			insertStmt.setBoolean(13, employee.getStatus());
			insertStmt.setString(14, employee.getRole());
			insertStmt.setInt(15, employee.getWage());

			insertStmt.executeUpdate();

			return employee;
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
	 * Retrieves the Employee info given the Id
	 * @param employeeId
	 * @return
	 * @throws SQLException
	 */
	public Employee getEmployeeById(int employeeId) throws SQLException {
		String selectEmployee = "SELECT EmployeeId, FirstName, LastName, SSN, DOB, Email, Phone, Street1, Street2, City, State, Zip, Status, Role, Wage "
			+ "FROM Employee WHERE EmployeeId=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try{
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEmployee);
			selectStmt.setInt(1, employeeId);

			results = selectStmt.executeQuery();
			if(results.next()){
				int resultEmployeeId = results.getInt("EmployeeId");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String ssn = results.getString("SSN");
				// Retrieve other columns as needed
				Date dob = results.getDate("DOB");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				String zip = results.getString("Zip");
				boolean status = results.getBoolean("Status");
				String role = results.getString("Role");
				int wage = results.getInt("Wage");

				// Create a new Employee object using the retrieved data
				Employee employee = new Employee(resultEmployeeId, firstName, lastName, ssn, dob, email,
					phone, street1, street2, city, state, zip, status, role, wage);

				// Return the populated Employee object
				return employee;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
		}
		return null;
	}
	
	/**
	 * Retrieves the Employee info given the firstName
	 * @param firstName
	 * @return
	 * @throws SQLException
	 */
	public List<Employee> getEmployeeByFirstName(String firstName) throws SQLException {
		List<Employee> employees = new ArrayList<>();
		String selectEmployee = "SELECT EmployeeId, FirstName, LastName, SSN, DOB, Email, Phone, Street1, Street2, City, State, Zip, Status, Role, Wage "
			+ "FROM Employee WHERE FirstName LIKE \'%?%\'";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try{
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEmployee);
			selectStmt.setString(1, firstName);

			results = selectStmt.executeQuery();
			if(results.next()){
				int employeeId = results.getInt("EmployeeId");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String ssn = results.getString("SSN");
				// Retrieve other columns as needed
				Date dob = results.getDate("DOB");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				String zip = results.getString("Zip");
				boolean status = results.getBoolean("Status");
				String role = results.getString("Role");
				int wage = results.getInt("Wage");

				// Create a new Employee object using the retrieved data
				Employee employee = new Employee(employeeId, resultFirstName, lastName, ssn, dob, email,
					phone, street1, street2, city, state, zip, status, role, wage);

				employees.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
		}
		return employees;
	}

	/**
	 * Delete an employee record from database
	 * @param employee
	 * @return
	 * @throws SQLException
	 */
	public Employee delete(Employee employee) throws SQLException {
		String deleteEmployee = "DELETE FROM Employee WHERE EmployeeId=?;";

		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteEmployee);

			deleteStmt.setInt(1, employee.getEmployeeId());

			int affectedRows = deleteStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for EmployeeId = " + employee.getEmployeeId());
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
