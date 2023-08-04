package servlet.delete;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.ClockEditsDao;
import model.ClockEdits;

@WebServlet("/clockeditdelete")
public class ClockEditDelete extends HttpServlet {
	protected ClockEditsDao clockEditDao;
	
	@Override 
	public void init() throws ServletException {
		this.clockEditDao = ClockEditsDao.getInstance();
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		 // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        try {
        	int clockEditId = Integer.valueOf("clockeditid");
        	
        	// Delete the BlogUser.
	        ClockEdits edit = new ClockEdits(clockEditId);
        	edit = clockEditDao.delete(edit);
        	// Update the message.
	        if (edit == null) {
	            messages.put("title", "Successfully deleted Time Clock Edit ID: " + clockEditId);
	            messages.put("disableSubmit", "true");
	        } else {
	        	messages.put("title", "Failed to delete " + clockEditId);
	        	messages.put("disableSubmit", "false");
	        }
        
        } catch(NumberFormatException e) {
        	 messages.put("title", "Invalid clock edit id");
             messages.put("disableSubmit", "true");
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.getRequestDispatcher("/delete/PaymentDelete.jsp").forward(req, resp);
	}
}