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

import dal.CheckItemDao;
import model.CheckItem;

@WebServlet("/CheckItemRead")
public class CheckItemRead extends HttpServlet {
	protected CheckItemDao checkItemDao;
	
	@Override
	public void init() throws ServletException {
		this.checkItemDao = CheckItemDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<CheckItem> checkItems = new ArrayList<>();
        
        // Retrieve and validate the given date Strings
        String checkIdString = req.getParameter("checkId");
        String dateString = req.getParameter("date");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
        	//Try to convert the two provided string to SQL date format
        	int checkId = Integer.parseInt(checkIdString);
        	Date date = new Date(sdf.parse(dateString).getTime());
        	
        	checkItems = checkItemDao.getItemByCheck(checkId, date);
        	
        	messages.put("success", String.format("Displaying check item results for checkId %d at date  - %s", 
        			checkId, dateString));
        	
        	req.setAttribute("checkitems", checkItems);
            
            req.getRequestDispatcher("/read/FindCheckItems.jsp").forward(req, resp);
        } catch(NumberFormatException e) { 
        	messages.put("success", "Please provide a correct checkId format");
        } catch(ParseException e) {
        	messages.put("success", "Please provide a correct date format (yyyy-mm-dd)");
        } catch(SQLException e) {
        	throw new IOException(e);
        }
	}
}