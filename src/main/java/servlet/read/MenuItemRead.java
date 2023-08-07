package servlet.read;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.MenuItemDao;
import model.MenuItem;

@WebServlet("/menuitemread")
public class MenuItemRead extends HttpServlet {
	protected MenuItemDao menuItemDao;
	
	@Override
	public void init() throws ServletException {
		this.menuItemDao = MenuItemDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<MenuItem> menuItems = new ArrayList<>();
     
        try {
        	menuItems = menuItemDao.getAllItem();
        	req.setAttribute("menuitems", menuItems);    
            req.getRequestDispatcher("/read/MenuItemRead.jsp").forward(req, resp);
        } catch(SQLException e) {
        	throw new IOException(e);
        }
	}
}