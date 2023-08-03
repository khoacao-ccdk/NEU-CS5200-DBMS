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

import dal.MenuItemDao;
import model.CheckItem;
import model.MenuItem;

@WebServlet("/menuitemupdate")
public class MenuItemUpdate extends HttpServlet{
	protected MenuItemDao menuItemDao;
	
	@Override
	public void init() throws ServletException {
		menuItemDao = MenuItemDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve employee and validate.
        try {
			int checkItemId = Integer.parseInt(req.getParameter("MenuItemId"));
			
    		MenuItem menuItem = menuItemDao.getItemById(checkItemId);
    		if(menuItem == null) {
    			messages.put("success", "menuItemId does not exist.");
    		} else {
    			req.setAttribute("MenuItem", menuItem);
    		}
  
		} catch (NumberFormatException e){
			messages.put("success", "Please enter a valid MenuItemId.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    
        req.getRequestDispatcher("/MenuItemUpdate.jsp").forward(req, resp);
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
			
    		MenuItem menuItem = menuItemDao.getItemById(checkItemId);
    		if(menuItem == null) {
    			messages.put("success", "CheckItemId does not exist, no update to perform");
    		} else {
    			//Get new information from request
    			String newItemName = req.getParameter("ItemName");
    			double newItemPrice = Double.parseDouble(req.getParameter("ItemPrice"));
    			int newCategoryId = Integer.parseInt(req.getParameter("CategoryId"));
    			
    			if(newItemName == null || newItemName.trim().isEmpty()) {
    				messages.put("success", "ItemName cannot be empty");
    			}	
    			menuItem = menuItemDao.updateItem(menuItem, newItemName, newItemPrice, newCategoryId);
    			req.setAttribute("MenuItem", menuItem);
    		}
		} catch (NumberFormatException e){
			messages.put("success", "Please enter a valid price/categotyId number.");
		}
        catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        } 
        
        req.getRequestDispatcher("/MenuItemUpdate.jsp").forward(req, resp);
	}
}