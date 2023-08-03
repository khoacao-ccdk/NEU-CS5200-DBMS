package servlet.create;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.EmployeesDao;
import model.ClockEdits;
import model.Employees;

@WebServlet("/employeecreate")
public class EmployeeCreate extends HttpServlet {
	protected EmployeesDao employeeDao;
	
	@Override()
	public void init() throws ServletException {
		this.employeeDao = EmployeesDao.getInstance();
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
    
        try {
        	//Read all information from the request
	    	int id = Integer.parseInt(req.getParameter("employeeid"));
	    	String firstName = req.getParameter("firstname");
	    	String lastName = req.getParameter("lastname");
	    	String ssn = req.getParameter("ssn");
	    	String dobString = req.getParameter("dob");
	    	String email = req.getParameter("email");
	    	String phone = req.getParameter("phone");
	    	String street1 = req.getParameter("street1");
	    	String street2 = req.getParameter("street2");
	    	String city = req.getParameter("city");
	    	String state = req.getParameter("state");
	    	String zip = req.getParameter("zip");
	    	boolean status = Boolean.valueOf(req.getParameter("status"));
	    	String role = req.getParameter("role");
			int wage = Integer.parseInt(req.getParameter("wage"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	    	Date dob = new Date(sdf.parse(dobString).getTime());
			
			if(firstName == null || firstName.trim().isEmpty()) {
				messages.put("success", "FirstName cannot be empty");
			}
			else if(lastName == null || lastName.trim().isEmpty()) {
				messages.put("success", "LastName cannot be empty");
			}
			else {
				Employees e = new Employees(
						id, 
						firstName,
						lastName,
						ssn,
						dob,
						email,
						phone,
						street1,
						street2,
						city,
						state,
						zip,
						status,
						role,
						wage);
				employeeDao.create(e);
				messages.put("success", "Successfully created employee");
			} 
        } catch(NumberFormatException e) {
        	messages.put("success", "Please enter a valid timeclockid number.");
        } catch(IllegalArgumentException e) {
        	messages.put("success", "Please enter a valid time format (hh:mm).");
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        } catch (ParseException e) {
			messages.put("success", "Please enter a valid date format (yyyy-dd-mm)");
		}
        
        req.getRequestDispatcher("/create/EmployeeCreate.jsp").forward(req, resp);
    }
}