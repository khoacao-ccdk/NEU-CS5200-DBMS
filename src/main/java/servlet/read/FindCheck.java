package servlet.read;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.CheckDao;
import model.Check;

@WebServlet("/findcheck")
public class FindCheck extends HttpServlet {
	protected CheckDao checkDao;
	
	@Override
	public void init() {
		checkDao = CheckDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Check> checks = new ArrayList<Check>();
        
        // Retrieve and validate the given date Strings
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
        	//Try to convert the two provided string to SQL date format
        	Date startDate = new Date(sdf.parse(start).getTime());
        	Date endDate = new Date(sdf.parse(end).getTime());
        	
        	checks = checkDao.getCheckByDateRange(startDate, endDate);
        	
        	messages.put("success", String.format("Displaying results for date range %s - %s", 
        			start, end));
        	
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindCheck.jsp.
        	messages.put("previousStart", start);
        	messages.put("previousEnd", end);
        	
        	req.setAttribute("checks", checks);
            
            req.getRequestDispatcher("/FindCheck.jsp").forward(req, resp);
        } catch(ParseException e) {
        	messages.put("success", "Please provide a correct date format (yyyy-mm-dd)");
        } catch(SQLException e) {
        	throw new IOException(e);
        }
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Check> checks = new ArrayList<Check>();
        
        // Retrieve and validate the given date Strings
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
        	//Try to convert the two provided string to SQL date format
        	Date startDate = new Date(sdf.parse(start).getTime());
        	Date endDate = new Date(sdf.parse(end).getTime());
        	
        	checks = checkDao.getCheckByDateRange(startDate, endDate);
        	
        	messages.put("success", String.format("Displaying results for date range %s - %s", 
        			start, end));
        	
        	req.setAttribute("checks", checks);
            
            req.getRequestDispatcher("/FindCheck.jsp").forward(req, resp);
        } catch(ParseException e) {
        	messages.put("success", "Please provide a correct date format (yyyy-mm-dd)");
        } catch(SQLException e) {
        	throw new IOException(e);
        }
    }
}