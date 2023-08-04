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

import dal.TimeClocksDao;
import model.TimeClocks;

@WebServlet("/findincorrecttimeclock")
public class FindIncorrectTimeClock extends HttpServlet {
	protected TimeClocksDao timeClocksDao;
	
	@Override
	public void init() {
		timeClocksDao = TimeClocksDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<TimeClocks> incorrectTimeClocks = new ArrayList<>();
        
        // Retrieve and validate the given date Strings
        String startDateString = req.getParameter("start");
        String endDateString = req.getParameter("end");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
        	//Try to convert the two provided string to SQL date format
        	Date start = new Date(sdf.parse(startDateString).getTime());
        	Date end = new Date(sdf.parse(endDateString).getTime());
        	
        	incorrectTimeClocks = timeClocksDao.getIncorrectTimeClock(start, end);
        	
        	messages.put("success", String.format("Displaying incorrect time clock for %s - %s", 
        			startDateString, endDateString));
        	
        	req.setAttribute("timeclocks", incorrectTimeClocks);
        	
        	req.setAttribute("previousStart", startDateString);
        	req.setAttribute("previousEnd", endDateString);
            
            req.getRequestDispatcher("/read/EmployeeTimeClock.jsp").forward(req, resp);
        } catch(ParseException e) {
        	messages.put("success", "Please provide a correct date format (yyyy-mm-dd)");
        } catch(SQLException e) {
        	throw new IOException(e);
        }
	}
}