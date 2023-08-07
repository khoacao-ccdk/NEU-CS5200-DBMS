package servlet.create;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.ClockEditsDao;
import dal.TimeClocksDao;
import model.ClockEdits;
import model.Employees;
import model.TimeClocks;

@WebServlet("/clockeditcreate")
public class ClockEditCreate extends HttpServlet {
	protected ClockEditsDao clockEditDao;
	
	@Override
	public void init() throws ServletException {
		clockEditDao = ClockEditsDao.getInstance();
	}
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        try {
        	int timeClockId = Integer.parseInt(req.getParameter("timeclockid"));
        	TimeClocks timeClock = TimeClocksDao.getInstance().getTimeClockById(timeClockId);
        	req.setAttribute("timeclock", timeClock);
        	req.setAttribute("start", req.getParameter("start"));
        	req.setAttribute("end", req.getParameter("end"));
        } catch(NumberFormatException e) {
        	messages.put("success", "Please enter a valid timeclock id");
        } catch(SQLException e) {
        	throw new IOException(e);
        }
		
        req.getRequestDispatcher("/create/ClockEditCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
    
        try {
        	//Read all information from the request
	    	int timeClockId = Integer.parseInt(req.getParameter("timeclockid"));
	    	Time in = Time.valueOf(req.getParameter("in"));
	    	Time out = Time.valueOf(req.getParameter("out"));
	    	Time breakStart = Time.valueOf(req.getParameter("breakstart"));
	    	Time breakEnd = Time.valueOf(req.getParameter("breakend"));
	    	
        	// Insert the ClockEdit into db
	    	ClockEdits clockEdit = new ClockEdits(timeClockId, in, out, breakStart, breakEnd);
        	clockEdit = clockEditDao.create(clockEdit);
        	messages.put("success", "Successfully created clock edit");
        	req.getRequestDispatcher("/read/EmployeeTimeClock.jsp").forward(req, resp);
	        return;
        } catch(NumberFormatException e) {
        	messages.put("success", "Please enter a valid timeclockid number.");
        } catch(IllegalArgumentException e) {
        	messages.put("success", "Please enter a valid time format (hh:mm).");
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.getRequestDispatcher("/create/ClockEditCreate.jsp").forward(req, resp);
    }
}