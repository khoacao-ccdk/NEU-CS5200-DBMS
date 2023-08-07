package servlet.update;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.CategoriesDao;
import dal.MenuItemDao;
import model.Categories;
import model.CheckItem;
import model.MenuItem;

@WebServlet("/menuitemupdate")
public class MenuItemUpdate extends HttpServlet{
	protected MenuItemDao menuItemDao;
	protected List<Categories> categories;
	
	@Override
	public void init() throws ServletException {
		menuItemDao = MenuItemDao.getInstance();
		try {
			categories = CategoriesDao.getInstance().getAllCategories();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// Retrieve the list of categories from the database
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
    		
    		// Inside your MenuItemUpdate servlet's doGet method
    		
    		req.setAttribute("categories", categories);  
		} catch (NumberFormatException e){
			messages.put("success", "Please enter a valid MenuItemId.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    
        req.getRequestDispatcher("/update/MenuItemUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		 // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve employee and validate.
        try {
			int menuItemId = Integer.parseInt(req.getParameter("MenuItemId"));
			
    		MenuItem menuItem = menuItemDao.getItemById(menuItemId);
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
    			req.setAttribute("categories", categories);  
    			messages.put("success", "Successfully updated menu item with id: " + menuItemId);
    		}
		} catch (NumberFormatException e){
			messages.put("success", "Please enter a valid price/categotyId number.");
		}
        catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        } 
        
        req.getRequestDispatcher("/update/MenuItemUpdate.jsp").forward(req, resp);
	}
}