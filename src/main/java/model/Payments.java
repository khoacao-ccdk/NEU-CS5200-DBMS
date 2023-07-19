package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Payments class
 * Represents a payment that is applied to a check
 * @author Cody Cao & Xiaolin Zhan
 */
public class Payments {
	
	private int paymentId;
	private int checkId;
	private Date date;
	private Time time;
	private String paymentMethod;
	private String ccNumber;
	private String authNumber;
	private float paymentAmount;
	private float tips;
	private int employeeId;
	
	/**
	 * Constructor used when retrieving a category in the database where all parameters are known.
	 * @param paymentId
	 * @param checkId
	 * @param timestamp
	 * @param paymentMethod
	 * @param ccNumber
	 * @param authNumber
	 * @param paymentAmount
	 * @param tips
	 * @param employeeId
	 */
	public Payments(int paymentId, int checkId, Date date, Time time, String paymentMethod, String ccNumber,
			String authNumber, float paymentAmount, float tips, int employeeId) {
		super();
		this.paymentId = paymentId;
		this.checkId = checkId;
		this.date = date;
		this.time = time;
		this.paymentMethod = paymentMethod;
		this.ccNumber = ccNumber;
		this.authNumber = authNumber;
		this.paymentAmount = paymentAmount;
		this.tips = tips;
		this.employeeId = employeeId;
	}

	/**
	 * Constructor used when creating a new category in the database, where categoryId is unknown.
	 * @param checkId
	 * @param timestamp
	 * @param paymentMethod
	 * @param ccNumber
	 * @param authNumber
	 * @param paymentAmount
	 * @param tips
	 * @param employeeId
	 */
	public Payments(int checkId, Date date, Time time, String paymentMethod, String ccNumber, String authNumber,
			float paymentAmount, float tips, int employeeId) {
		super();
		this.checkId = checkId;
		this.date = date;
		this.time = time;
		this.paymentMethod = paymentMethod;
		this.ccNumber = ccNumber;
		this.authNumber = authNumber;
		this.paymentAmount = paymentAmount;
		this.tips = tips;
		this.employeeId = employeeId;
	}
	
	/**
	 * Constructor used when deleting a payment record from the database
	 * @param paymentId an Integer represents the paymentId
	 */
	public Payments(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getCheckId() {
		return checkId;
	}

	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getAuthNumber() {
		return authNumber;
	}

	public void setAuthNumber(String authNumber) {
		this.authNumber = authNumber;
	}

	public float getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public float getTips() {
		return tips;
	}

	public void setTips(float tips) {
		this.tips = tips;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authNumber, ccNumber, checkId, date, employeeId, paymentAmount, paymentId, paymentMethod,
				time, tips);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payments other = (Payments) obj;
		return Objects.equals(authNumber, other.authNumber) && Objects.equals(ccNumber, other.ccNumber)
				&& checkId == other.checkId && Objects.equals(date, other.date) && employeeId == other.employeeId
				&& Float.floatToIntBits(paymentAmount) == Float.floatToIntBits(other.paymentAmount)
				&& paymentId == other.paymentId && Objects.equals(paymentMethod, other.paymentMethod)
				&& Objects.equals(time, other.time) && Float.floatToIntBits(tips) == Float.floatToIntBits(other.tips);
	}

	@Override
	public String toString() {
		return "Payments [paymentId=" + paymentId + ", checkId=" + checkId + ", date=" + date + ", time=" + time
				+ ", paymentMethod=" + paymentMethod + ", ccNumber=" + ccNumber + ", authNumber=" + authNumber
				+ ", paymentAmount=" + paymentAmount + ", tips=" + tips + ", employeeId=" + employeeId + "]";
	}
	
	
}
