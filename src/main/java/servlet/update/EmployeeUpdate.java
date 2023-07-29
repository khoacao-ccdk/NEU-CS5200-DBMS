package servlet.update;

import dal.EmployeesDao;
import model.Employees;

import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/employeeupdate")
public class EmployeeUpdate extends HttpServlet{
    protected EmployeesDao employeeDao;

    @Override
	public void init() throws ServletException {
		employeeDao = EmployeesDao.getInstance();
	}

    @Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve employee and validate.
        try {
			int employeeId = Integer.parseInt(req.getParameter("EmployeeId"));
			
    		Employees employee = employeeDao.getEmployeeById(employeeId);
    		if(employee == null) {
    			messages.put("success", "EmployeeId does not exist.");
    		} else {
    			req.setAttribute("Employee", employee);
    		}
  
		} catch (NumberFormatException e){
			messages.put("success", "Please enter a valid EmployeeId.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    
        req.getRequestDispatcher("/EmployeeUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve employee and validate.
        try {
			int employeeId = Integer.parseInt(req.getParameter("EmployeeId"));
			
    		Employees employee = employeeDao.getEmployeeById(employeeId);
    		if(employee == null) {
    			messages.put("success", "EmployeeId does not exist, no update to perform");
    		} else {
    			//Get new information from request
    			String newFirstName = req.getParameter("Firstname");
    			String newLastName = req.getParameter("LastName");
    			String newSSN = req.getParameter("SSN");
    			String DOBString = req.getParameter("DOB");
    			Date newDOB = DOBString != null ? Date.valueOf(DOBString) : null;
    			String newEmail = req.getParameter("Email");
    			String newPhone = req.getParameter("Phone");
    			String newStreet1 = req.getParameter("Street1");
    			String newStreet2 = req.getParameter("Street2");
    			String newCity = req.getParameter("City");
    			String newState = req.getParameter("State");
    			String newZip = req.getParameter("Zip");
    			boolean newStatus = Boolean.valueOf(req.getParameter("Status"));
    			String role = req.getParameter("Role");
    			int newWage = Integer.valueOf(req.getParameter("wage"));
    			
    			if(newFirstName == null || newFirstName.trim().isEmpty()) {
    				messages.put("success", "FirstName cannot be empty");
    			}
    			else if(newLastName == null || newLastName.trim().isEmpty()) {
    				messages.put("success", "LastName cannot be empty");
    			}
    			else {
    				//Perform update
    				employee = employeeDao.updateEmployee(employee, 
    						newFirstName, 
    						newLastName, 
    						newSSN, 
    						newDOB, 
    						newEmail, 
    						newPhone, 
    						newStreet1, 
    						newStreet2, 
    						newCity, 
    						newState, 
    						newZip, 
    						newStatus, 
    						role, 
    						newWage);
    				messages.put("success", "Successfully updated employee with id: " + employeeId);
    			}
    			req.setAttribute("Employee", employee);
    		}
		} catch (NumberFormatException e){
			messages.put("success", "Please enter a valid wage number.");
		} catch (IllegalArgumentException e) {
			messages.put("success", "Please enter a valid Date of birth (yyyy-mm-dd)");
		}
        catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        } 
        
        req.getRequestDispatcher("/EmployeeUpdate.jsp").forward(req, resp);
    }
}
