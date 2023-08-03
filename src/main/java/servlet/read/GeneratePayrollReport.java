package servlet.read;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.*;
import model.*;

@WebServlet("/generatepayrollreport")
public class GeneratePayrollReport extends HttpServlet {

	protected PaymentsDao paymentsDao;
	protected EmployeesDao employeesDao;
	protected TimeClocksDao timeClocksDao;
	protected ClockEditsDao clockEditsDao;
	private static final double SERVER_TIP_MODIFIER = 0.8;
	private static final double COOK_TIP_MODIFIER = 0.2;

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
		
		List<String> listOfDates = new ArrayList<>();
		List<Payroll> payrolls = new ArrayList<>();
		Set<Employees> employeesSet = new HashSet<Employees>();
		Map<String, Map<Employees, Payroll>> payrollMap = new HashMap<>();
		List<Payments> payments = new ArrayList<>();
		Employees employee;
		List<TimeClocks> timeClocks = new ArrayList<>();
		ClockEdits clockEdit;
		Time clockIn;
		Time clockOut;
		int breakMin;

		// Retrieve and validate the given date Strings
		String startDateString = req.getParameter("start");
		String endDateString = req.getParameter("end");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try {
			LocalDate start = LocalDate.parse(startDateString, formatter);
			LocalDate end = LocalDate.parse(endDateString, formatter);

			for (LocalDate currentDate = start; currentDate
					.isBefore(end.plusDays(1)); currentDate = currentDate.plusDays(1)) {
				listOfDates.add(currentDate.toString());
				Map<Employees, Double> hoursMapByDay = new HashMap<>();
				Map<Employees, Payroll> payrollMapByDay = new HashMap<>();

				timeClocks = timeClocksDao.getTimeClockByDate(currentDate);
				payments = paymentsDao.getPaymentsByDate(currentDate);
				float totalTips = 0;
				for (Payments payment : payments) {
					totalTips += payment.getTips();
				}
				double totalHoursFOH = 0;
				double totalHoursBOH = 0;
				for (TimeClocks timeClock : timeClocks) {
					employee = employeesDao.getEmployeeById(timeClock.getEmployeeId());
					// ignore employee is not active such as Xiaolin, POS, etc.
					if (!employee.getStatus()) {
						continue;
					}
					if(!employeesSet.contains(employee)) {
						employeesSet.add(employee);
					}
					clockEdit = clockEditsDao.getClockEditssByTimeClockId(timeClock.getTimeClockId());
					if (clockEdit == null) {
						clockIn = timeClock.getClockInTime();
						clockOut = timeClock.getClockOutTime();
						breakMin = timeClock.getUnpaidBreakMin();
					} else {
						clockIn = clockEdit.getIn();
						clockOut = clockEdit.getOut();
						breakMin = Duration
								.between(clockEdit.getBreakStart().toLocalTime(), clockEdit.getBreakEnd().toLocalTime())
								.toMinutesPart();
					}
					double hours = (Duration.between(clockIn.toLocalTime(), clockOut.toLocalTime()).toMinutes()
							- breakMin) / 60.0;

					// calculating the total hours for the FOH and BOH
					if (employee.getRole().equals("Server")) {
						totalHoursFOH += hours;
					} else if (employee.getRole().equals("Cook") || employee.getRole().equals("Prep")) {
						totalHoursBOH += hours;
					}

					// Putting hours into a Employees, Hours map
					hoursMapByDay.put(employee, hoursMapByDay.getOrDefault(employee, 0.0) + hours);

				}
				double fohTipsPerHour = totalTips * 0.95 * SERVER_TIP_MODIFIER / totalHoursFOH;
				double bohTipsPerHour = totalTips * 0.95 * COOK_TIP_MODIFIER / totalHoursBOH;
				for (Map.Entry<Employees, Double> entry : hoursMapByDay.entrySet()) {
					double tips = ((entry.getKey().getRole().equals("Server")) ? entry.getValue() * fohTipsPerHour
							: entry.getValue() * bohTipsPerHour);
					Payroll payroll = new Payroll(entry.getValue(), tips, 0);
					payrollMapByDay.put(entry.getKey(), payroll);
				}
				payrollMap.put(currentDate.toString(), payrollMapByDay);
			}
			req.setAttribute("listOfDates", listOfDates);
			req.setAttribute("listOfEmployees", employeesSet.toArray());
			req.setAttribute("payrollMap", payrollMap);
			req.getRequestDispatcher("/GeneratePayrollReport.jsp").forward(req, res);
			
		} catch (

		SQLException e) {
			throw new IOException(e);
		}
	}
}
