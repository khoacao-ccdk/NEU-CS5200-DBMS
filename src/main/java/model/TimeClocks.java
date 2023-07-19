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
	private Time in;
	private Time out;
	private int unpaidBreak;
	
	public TimeClocks(int employeeId, Date date, Time in, Time out, int unpaidBreak) {
		super();
		this.employeeId = employeeId;
		this.date = date;
		this.in = in;
		this.out = out;
		this.unpaidBreak = unpaidBreak;
	}

	public TimeClocks(int timeClockId, int employeeId, Date date, Time in, Time out, int unpaidBreak) {
		super();
		this.timeClockId = timeClockId;
		this.employeeId = employeeId;
		this.date = date;
		this.in = in;
		this.out = out;
		this.unpaidBreak = unpaidBreak;
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

	public Time getIn() {
		return in;
	}

	public void setIn(Time in) {
		this.in = in;
	}

	public Time getOut() {
		return out;
	}

	public void setOut(Time out) {
		this.out = out;
	}

	public int getUnpaidBreak() {
		return unpaidBreak;
	}

	public void setUnpaidBreak(int unpaidBreak) {
		this.unpaidBreak = unpaidBreak;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, employeeId, in, out, timeClockId, unpaidBreak);
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
		return Objects.equals(date, other.date) && employeeId == other.employeeId && Objects.equals(in, other.in)
				&& Objects.equals(out, other.out) && timeClockId == other.timeClockId
				&& unpaidBreak == other.unpaidBreak;
	}

	@Override
	public String toString() {
		return "TimeClocks [timeClockId=" + timeClockId + ", employeeId=" + employeeId + ", date=" + date + ", in=" + in
				+ ", out=" + out + ", unpaidBreak=" + unpaidBreak + "]";
	}
	
	
}
