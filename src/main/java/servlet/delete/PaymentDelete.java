package servlet.delete;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.*;

import dal.PaymentsDao;
import model.Payments;

@WebServlet("/paymentdelete")
public class PaymentDelete extends HttpServlet {
	protected PaymentsDao paymentsDao;
	
	@Override 
	public void init() throws ServletException{
		paymentsDao = PaymentsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Payment");        
        req.getRequestDispatcher("/PaymentDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String paymentIdString = req.getParameter("paymentId");
        try {
        	int paymentId = Integer.valueOf(paymentIdString);
        	
        	// Delete the BlogUser.
	        Payments payment = new Payments(paymentId);
        	payment = paymentsDao.delete(payment);
        	// Update the message.
	        if (payment == null) {
	            messages.put("title", "Successfully deleted " + paymentId);
	            messages.put("disableSubmit", "true");
	        } else {
	        	messages.put("title", "Failed to delete " + paymentId);
	        	messages.put("disableSubmit", "false");
	        }
        
        } catch(NumberFormatException e) {
        	 messages.put("title", "Invalid Paymentid");
             messages.put("disableSubmit", "true");
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
    }
}