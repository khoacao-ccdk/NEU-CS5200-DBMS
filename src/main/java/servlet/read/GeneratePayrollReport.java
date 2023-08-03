package servlet.read;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.*;

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
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String startDateString = req.getParameter("start");
		String endDateString = req.getParameter("end");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	}
}
