package servlet.update;

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
import model.ClockEdits;

@WebServlet("/clockeditupdate")
public class ClockEditsUpdate extends HttpServlet{
	protected ClockEditsDao clockEditDao;
	
	@Override
	public void init() throws ServletException {
		clockEditDao = ClockEditsDao.getInstance();
	}
	
	@Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
    
        try {
        	//Read all information from the request
	    	int clockEditId = Integer.parseInt(req.getParameter("clockeditid"));
	    	
	    	ClockEdits edit = clockEditDao.getClockEditById(clockEditId);
	    	if(edit == null) {
	    		messages.put("success", "The given clockEditId does not correlate to any edit in the database");
	    	}
	    	else {
		    	Time newClockIn = Time.valueOf(req.getParameter("newin"));
		    	Time newClockOut = Time.valueOf(req.getParameter("newout"));
		    	Time newBreakStart = Time.valueOf(req.getParameter("newbreakstart"));
		    	Time newBreakEnd = Time.valueOf(req.getParameter("newbreakend"));
		    	
		    	//Performs the update
		    	edit = clockEditDao.update(edit, newClockIn, newClockOut, newBreakStart, newBreakEnd);
		    	messages.put("success", "Successfully update ClockEdit with id: " + clockEditId);
	    	}  
        } catch(NumberFormatException e) {
        	messages.put("success", "Please enter a valid timeclockid number.");
        } catch(IllegalArgumentException e) {
        	messages.put("success", "Please enter a valid time format (hh:mm) for every field.");
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.getRequestDispatcher("/ClockEditUpdate.jsp").forward(req, resp);
    }
}