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
import model.ClockEdits;

@WebServlet("/clockeditcreate")
public class ClockEditCreate extends HttpServlet {
	protected ClockEditsDao clockEditDao;
	
	@Override
	public void init() throws ServletException {
		clockEditDao = ClockEditsDao.getInstance();
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
	    	System.out.println(req.getParameter("in"));
	    	Time in = Time.valueOf(req.getParameter("in"));
	    	Time out = Time.valueOf(req.getParameter("out"));
	    	Time breakStart = Time.valueOf(req.getParameter("breakstart"));
	    	Time breakEnd = Time.valueOf(req.getParameter("breakend"));
	    	
        	// Insert the ClockEdit into db
	    	ClockEdits clockEdit = new ClockEdits(timeClockId, in, out, breakStart, breakEnd);
        	clockEdit = clockEditDao.create(clockEdit);
        	messages.put("success", "Successfully created clock edit");
	         
        } catch(NumberFormatException e) {
        	messages.put("success", "Please enter a valid timeclockid number.");
        } catch(IllegalArgumentException e) {
        	messages.put("success", "Please enter a valid time format (hh:mm).");
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.getRequestDispatcher("/ClockEditCreate.jsp").forward(req, resp);
    }
}