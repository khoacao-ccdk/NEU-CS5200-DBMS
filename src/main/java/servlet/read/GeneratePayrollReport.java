package servlet.read;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.*;
import model.*;

@WebServlet("/generatepayrollreport")
public class GeneratePayrollReport extends HttpServlet{
	protected PaymentsDao paymentsDao;
	protected EmployeesDao employeesDao;
	protected TimeClocksDao timeClocksDao;
	protected ClockEditsDao clockEditsDao;
	
	@Override
	public void init() {
		paymentsDao = PaymentsDao.getInstance();
		employeesDao = EmployeesDao.getInstance();
		timeClocksDao = TimeClocksDao.getInstance();
		clockEditsDao = ClockEditsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Payments> payments = new ArrayList<>();
        List<Employees> employees = new ArrayList<>();
        List<TimeClocks> timeClocks = new ArrayList<>();
        List<ClockEdits> clockEdits = new ArrayList<>();
        
		// Retrieve and validate the given date Strings
        String startDateString = req.getParameter("start");
		String endDateString = req.getParameter("end");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			LocalDate start = LocalDate.parse(startDateString, formatter);
			LocalDate end = LocalDate.parse(endDateString, formatter);
			
			for (LocalDate currentDate = start; currentDate.isBefore(end.plusDays(1)); currentDate = currentDate.plusDays(1)) {
				timeClocks.addAll(timeClocksDao.getTimeClockByDate(currentDate));
			}
			
			
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
}
