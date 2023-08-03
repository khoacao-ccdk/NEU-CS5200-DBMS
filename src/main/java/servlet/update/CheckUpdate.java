package servlet.update;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.CheckDao;
import model.Check;

@WebServlet("/checkupdate")
public class CheckUpdate extends HttpServlet{
	protected CheckDao checkDao;
	
	@Override
	public void init() throws ServletException {
		checkDao = CheckDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve employee and validate.
        try {
			int checkId = Integer.parseInt(req.getParameter("CheckId"));
			Date date = Date.valueOf(req.getParameter("Date"));
			
    		Check check = checkDao.getCheckByIdDate(checkId, date);
    		if(check == null) {
    			messages.put("success", "CheckId and Date does not exist.");
    		} else {
    			req.setAttribute("Check", check);
    		}
  
		} catch (NumberFormatException e){
			messages.put("success", "Please enter a valid CheckId.");
		} catch (IllegalArgumentException e) {
			messages.put("success", "Please enter a valid date (yyyy-mm-dd).");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    
        req.getRequestDispatcher("/CheckUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		 // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve employee and validate.
        try {
        	int checkId = Integer.parseInt(req.getParameter("CheckId"));
			Date date = Date.valueOf(req.getParameter("Date"));
			
    		Check check = checkDao.getCheckByIdDate(checkId, date);
    		if(check == null) {
    			messages.put("success", "CheckId and Date does not exist, no update to perform");
    		} else {
    			//Get new information from request
    			String newTableDesc = req.getParameter("TableDesc");
    			int newGuestCount = Integer.parseInt(req.getParameter("GuestCount"));
    			int newItemCount = Integer.parseInt(req.getParameter("ItemCount"));
    			double newNetSales = Double.parseDouble(req.getParameter("NetSales"));
    			double newComps = Double.parseDouble(req.getParameter("Comps"));
    			double newPromo = Double.parseDouble(req.getParameter("Promo"));
    			double newTax = Double.parseDouble(req.getParameter("Tax"));
    			
    			check = checkDao.updateCheck(
    					check, 
    					newTableDesc, 
    					newGuestCount, 
    					newItemCount, 
    					newNetSales, 
    					newComps, 
    					newPromo, 
    					newTax);
    			
    			req.setAttribute("Check", check);
    		}
		} catch (NumberFormatException e){
			messages.put("success", "Please enter a valid number format for Guest Count/Item Count/Net Sales/Comps/Promo/Tax.");
		} catch (IllegalArgumentException e) {
			messages.put("success", "Please enter a valid date (yyyy-mm-dd).");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        } 
        
        req.getRequestDispatcher("/CheckUpdate.jsp").forward(req, resp);
	}
}