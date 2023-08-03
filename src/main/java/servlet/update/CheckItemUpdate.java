package servlet.update;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.CheckItemDao;
import model.CheckItem;

@WebServlet("/checkitemupdate")
public class CheckItemUpdate extends HttpServlet {
	protected CheckItemDao checkItemDao;
	
	@Override
	public void init() throws ServletException {
		checkItemDao = CheckItemDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve employee and validate.
        try {
			int checkItemId = Integer.parseInt(req.getParameter("CheckItemId"));
			
    		CheckItem checkItem = checkItemDao.getItemById(checkItemId);
    		if(checkItem == null) {
    			messages.put("success", "checkItemId does not exist.");
    		} else {
    			req.setAttribute("CheckItem", checkItem);
    		}
  
		} catch (NumberFormatException e){
			messages.put("success", "Please enter a valid CheckItemId.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    
        req.getRequestDispatcher("/CheckItemUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		 // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve employee and validate.
        try {
			int checkItemId = Integer.parseInt(req.getParameter("CheckItemId"));
			
    		CheckItem checkItem = checkItemDao.getItemById(checkItemId);
    		if(checkItem == null) {
    			messages.put("success", "CheckItemId does not exist, no update to perform");
    		} else {
    			//Get new information from request
    			int newQty = Integer.parseInt(req.getParameter("Qty"));
    			int newRefundQty = Integer.parseInt(req.getParameter("RefundQty"));
    			
    			checkItem = checkItemDao.updateItem(checkItem, newQty, newRefundQty);
    			
    			req.setAttribute("CheckItem", checkItem);
    		}
		} catch (NumberFormatException e){
			messages.put("success", "Please enter a valid quantity/refund quantity number.");
		}
        catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        } 
        
        req.getRequestDispatcher("/update/CheckItemUpdate.jsp").forward(req, resp);
	}
}