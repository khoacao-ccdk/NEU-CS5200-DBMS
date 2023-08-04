import java.sql.SQLException;

import dal.EmployeesDao;
import model.Employees;

public class Test {
	public static void main(String[] args) throws SQLException {
		EmployeesDao employeeDao = EmployeesDao.getInstance();
		for(Employees e : employeeDao.getAllEmployee()) {
			System.out.println(e.getFirstName());
		}
	}
}