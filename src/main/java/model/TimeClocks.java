package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/** 
 * Time clocks class
 * Represents a clock record
 * @author Cody Cao & Xiaolin Zhan
 */

public class TimeClocks {
	
	private int timeClockId;
	private int employeeId;
	private Date date;
	private Time clockInTime;
	private Time clockOutTime;
	private int unpaidBreakMin;
	
	public TimeClocks(int employeeId, Date date, Time clockInTime, Time clockOutTime, int unpaidBreakMin) {
		super();
		this.employeeId = employeeId;
		this.date = date;
		this.clockInTime = clockInTime;
		this.clockOutTime = clockOutTime;
		this.unpaidBreakMin = unpaidBreakMin;
	}

	public TimeClocks(int timeClockId, int employeeId, Date date, Time clockInTime, Time clockOutTime, int unpaidBreakMin) {
		super();
		this.timeClockId = timeClockId;
		this.employeeId = employeeId;
		this.date = date;
		this.clockInTime = clockInTime;
		this.clockOutTime = clockOutTime;
		this.unpaidBreakMin = unpaidBreakMin;
	}

	public int getTimeClockId() {
		return timeClockId;
	}

	public void setTimeClockId(int timeClockId) {
		this.timeClockId = timeClockId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getClockInTime() {
		return clockInTime;
	}

	public void setClockInTime(Time clockInTime) {
		this.clockInTime = clockInTime;
	}

	public Time getClockOutTime() {
		return clockOutTime;
	}

	public void setClockOutTime(Time clockOutTime) {
		this.clockOutTime = clockOutTime;
	}

	public int getUnpaidBreakMin() {
		return unpaidBreakMin;
	}

	public void setUnpaidBreakMin(int unpaidBreakMin) {
		this.unpaidBreakMin = unpaidBreakMin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, employeeId, clockInTime, clockOutTime, timeClockId, unpaidBreakMin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeClocks other = (TimeClocks) obj;
		return Objects.equals(date, other.date) && employeeId == other.employeeId && Objects.equals(clockInTime, other.clockInTime)
				&& Objects.equals(clockOutTime, other.clockOutTime) && timeClockId == other.timeClockId
				&& unpaidBreakMin == other.unpaidBreakMin;
	}

	@Override
	public String toString() {
		return "TimeClocks [timeClockId=" + timeClockId + ", employeeId=" + employeeId + ", date=" + date + ", in=" + clockInTime
				+ ", out=" + clockOutTime + ", unpaidBreak=" + unpaidBreakMin + "]";
	}
	
	
}
