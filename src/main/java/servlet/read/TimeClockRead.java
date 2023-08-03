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

import dal.TimeClocksDao;
import model.TimeClocks;

@WebServlet("/timeclocksread")
public class TimeClockRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected TimeClocksDao timeClocksDao;

	@Override
	public void init() {
		timeClocksDao = TimeClocksDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<TimeClocks> timeClocks = new ArrayList<>();

		// Retrieve and validate the given date Strings
		String startDateString = req.getParameter("start");
		String endDateString = req.getParameter("end");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			// Try to convert the two provided string to SQL date format
			LocalDate start = LocalDate.parse(startDateString, formatter);
			LocalDate end = LocalDate.parse(endDateString, formatter);

			for (LocalDate currentDate = start; currentDate.isBefore(end.plusDays(1)); currentDate = currentDate.plusDays(1)) {
				timeClocks.addAll(timeClocksDao.getTimeClockByDate(currentDate));
			}

			messages.put("success",
					String.format("Displaying time clock for %s - %s", startDateString, endDateString));

			req.setAttribute("timeClocks", timeClocks);

			req.setAttribute("previousStart", startDateString);
			req.setAttribute("previousEnd", endDateString);

			req.getRequestDispatcher("/read/EmployeeTimeClock.jsp").forward(req, res);
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

}
