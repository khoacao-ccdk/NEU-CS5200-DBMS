package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Employees;

/**
 * Employee Dao - Data access class to interact with the database
 * 
 * @author Cody Cao & Xiaolin Zhan
 */
public class EmployeesDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
	private static EmployeesDao instance = null;

	protected EmployeesDao() {
		this.connectionManager = new ConnectionManager();
	}
	
	public static EmployeesDao getInstance() {
		if(instance == null) {
			instance = new EmployeesDao();
		}
		return instance;
	}

    /**
	 * Insert a new employee record into the database
	 * @param employee
	 * @return
	 * @throws SQLException
	 */
	public Employees create(Employees employee) throws SQLException{
        String insertEmployee = "INSERT INTO Employees(EmployeeId, FirstName, LastName, SSN, DOB, Email, Phone, Street1, Street2, City, State, Zip, Status, Role, Wage) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertEmployee);
			insertStmt.setInt(1, employee.getEmployeeId());
			insertStmt.setString(2, employee.getFirstName());
			insertStmt.setString(3, employee.getLastName());
			insertStmt.setObject(4, employee.getSSN(), Types.VARCHAR);
			insertStmt.setObject(5, employee.getDOB(), Types.DATE);
			insertStmt.setObject(6, employee.getEmail(), Types.VARCHAR);
			insertStmt.setObject(7, employee.getPhone(), Types.VARCHAR);
			insertStmt.setObject(8, employee.getStreet1(), Types.VARCHAR);
			insertStmt.setObject(9, employee.getStreet2(), Types.VARCHAR);
			insertStmt.setObject(10, employee.getCity(), Types.VARCHAR);
			insertStmt.setObject(11, employee.getState(), Types.VARCHAR);
			insertStmt.setObject(12, employee.getZip(), Types.VARCHAR);
			insertStmt.setObject(13, employee.getStatus(), Types.BOOLEAN);
			insertStmt.setObject(14, employee.getRole(), Types.VARCHAR);
			insertStmt.setObject(15, employee.getWage(), Types.DECIMAL);

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
	 * Retrieves all employee
	 * @return
	 * @throws SQLException
	 */
	public List<Employees> getAllEmployee() throws SQLException {
		List<Employees> employees = new ArrayList<>();
		String selectEmployee = "SELECT EmployeeId, FirstName, LastName, SSN, DOB, Email, Phone, Street1, Street2, City, State, Zip, Status, Role, Wage "
			+ "FROM Employees;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try{
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEmployee);
			
			results = selectStmt.executeQuery();
			while(results.next()){
				int resultEmployeeId = results.getInt("EmployeeId");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String ssn = results.getString("SSN");
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
				Employees employee = new Employees(resultEmployeeId, firstName, lastName, ssn, dob, email,
					phone, street1, street2, city, state, zip, status, role, wage);

				// Return the populated Employee object
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
	 * Retrieves the Employee info given the Id
	 * @param employeeId
	 * @return
	 * @throws SQLException
	 */
	public Employees getEmployeeById(int employeeId) throws SQLException {
		String selectEmployee = "SELECT EmployeeId, FirstName, LastName, SSN, DOB, Email, Phone, Street1, Street2, City, State, Zip, Status, Role, Wage "
			+ "FROM Employees WHERE EmployeeId=?";
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
				Employees employee = new Employees(resultEmployeeId, firstName, lastName, ssn, dob, email,
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
	public List<Employees> getEmployeeByFirstName(String firstName) throws SQLException {
		List<Employees> employees = new ArrayList<>();
		String selectEmployee = "SELECT EmployeeId, FirstName, LastName, SSN, DOB, Email, Phone, Street1, Street2, City, State, Zip, Status, Role, Wage "
			+ "FROM Employees WHERE FirstName LIKE \'%?%\'";
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
				Employees employee = new Employees(employeeId, resultFirstName, lastName, ssn, dob, email,
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
	 * Update an employee record in the database
	 * @param employee
	 * @param newFirstName
	 * @param newLastName
	 * @param newSSN
	 * @param newDOB
	 * @param newEmail
	 * @param newPhone
	 * @param newStreet1
	 * @param newStreet2
	 * @param newCity
	 * @param newState
	 * @param newZip
	 * @param newStatus
	 * @param newRole
	 * @param newWage
	 * @return
	 * @throws SQLException
	 */
	public Employees updateEmployee(
		Employees employee,
		String newFirstName,
		String newLastName, 
		String newSSN,
		Date newDOB,
		String newEmail,
		String newPhone,
		String newStreet1,
		String newStreet2,
		String newCity,
		String newState,
		String newZip, 
		boolean newStatus, 
		String newRole,
		int newWage
	) throws SQLException {
		String update = "UPDATE Employees SET FirstName=?, LastName=?, SSN=?, DOB=?, Email=?, Phone=?, Street1=?, Street2=?, City=?, State=?, Zip=?, Status=?, Role=?, Wage=? "
						+ "WHERE EmployeeId=?";

		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(update);
		
			updateStmt.setString(1, employee.getFirstName());
			updateStmt.setString(2, employee.getLastName());
			updateStmt.setObject(3, employee.getSSN(), Types.VARCHAR);
			updateStmt.setObject(4, employee.getDOB(), Types.DATE);
			updateStmt.setObject(5, employee.getEmail(), Types.VARCHAR);
			updateStmt.setObject(6, employee.getPhone(), Types.VARCHAR);
			updateStmt.setObject(7, employee.getStreet1(), Types.VARCHAR);
			updateStmt.setObject(8, employee.getStreet2(), Types.VARCHAR);
			updateStmt.setObject(9, employee.getCity(), Types.VARCHAR);
			updateStmt.setObject(10, employee.getState(), Types.VARCHAR);
			updateStmt.setObject(11, employee.getZip(), Types.VARCHAR);
			updateStmt.setObject(12, employee.getStatus(), Types.BOOLEAN);
			updateStmt.setObject(13, employee.getRole(), Types.VARCHAR);
			updateStmt.setObject(14, employee.getWage(), Types.DECIMAL);
			updateStmt.setInt(15, employee.getEmployeeId());

			//Execute statement
			int numAffectedRow = updateStmt.executeUpdate();
			if(numAffectedRow == 0){
				throw new SQLException(String.format(
					"No record updated for EmployeeId = %d",
					employee.getEmployeeId()
				));
			}

			//Update object to reflect changes
			employee.setFirstName(newFirstName);
			employee.setLastName(newLastName);
			employee.setSSN(newSSN);
			employee.setDOB(newDOB);
			employee.setEmail(newEmail);
			employee.setPhone(newPhone);
			employee.setStreet1(newStreet1);
			employee.setStreet2(newStreet2);
			employee.setCity(newCity);
			employee.setState(newState);
			employee.setZip(newZip);
			employee.setStatus(newStatus);
			employee.setRole(newRole);
			employee.setWage(newWage);

			return employee;
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
	 * Delete an employee record from database
	 * @param employee
	 * @return
	 * @throws SQLException
	 */
	public Employees delete(Employees employee) throws SQLException {
		String deleteEmployee = "DELETE FROM Employees WHERE EmployeeId=?;";

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
