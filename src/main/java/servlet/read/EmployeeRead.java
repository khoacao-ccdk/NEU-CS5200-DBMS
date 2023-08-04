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

import dal.EmployeesDao;
import model.Employees;
import model.TimeClocks;

@WebServlet("/employeeread")
public class EmployeeRead extends HttpServlet{
	protected EmployeesDao employeeDao;
	
	@Override
	public void init() throws ServletException {
		this.employeeDao = EmployeesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		try {
			List<Employees> employees = employeeDao.getAllEmployee();
			messages.put("success",
					String.format("Displaying all employee"));

			req.setAttribute("employees", employees);
			req.getRequestDispatcher("/read/EmployeeRead.jsp").forward(req, res);
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
}