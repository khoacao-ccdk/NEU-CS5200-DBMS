package model;

import java.sql.Time;
import java.util.Objects;

/** 
 * Time clocks class
 * Represents a clock record
 * @author Cody Cao & Xiaolin Zhan
 */

public class ClockEdits {
	
	private int editId;
	private int timeClockId;
	private Time in;
	private Time out;
	private Time breakStart;
	private Time breakEnd;
	
	public ClockEdits(int editId, int timeClockId, Time in, Time out, Time breakStart, Time breakEnd) {
		super();
		this.editId = editId;
		this.timeClockId = timeClockId;
		this.in = in;
		this.out = out;
		this.breakStart = breakStart;
		this.breakEnd = breakEnd;
	}

	public ClockEdits(int timeClockId, Time in, Time out, Time breakStart, Time breakEnd) {
		super();
		this.timeClockId = timeClockId;
		this.in = in;
		this.out = out;
		this.breakStart = breakStart;
		this.breakEnd = breakEnd;
	}
	
	/**
	 * Constructor used when deleting a time clock edit
	 * @param timeClockId
	 */
	public ClockEdits(int timeClockId) {
		this.timeClockId = timeClockId;
	}

	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		this.editId = editId;
	}

	public int getTimeClockId() {
		return timeClockId;
	}

	public void setTimeClockId(int timeClockId) {
		this.timeClockId = timeClockId;
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

	public Time getBreakStart() {
		return breakStart;
	}

	public void setBreakStart(Time breakStart) {
		this.breakStart = breakStart;
	}

	public Time getBreakEnd() {
		return breakEnd;
	}

	public void setBreakEnd(Time breakEnd) {
		this.breakEnd = breakEnd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(breakEnd, breakStart, editId, in, out, timeClockId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClockEdits other = (ClockEdits) obj;
		return Objects.equals(breakEnd, other.breakEnd) && Objects.equals(breakStart, other.breakStart)
				&& editId == other.editId && Objects.equals(in, other.in) && Objects.equals(out, other.out)
				&& timeClockId == other.timeClockId;
	}

	@Override
	public String toString() {
		return "ClockEdits [editId=" + editId + ", timeClockId=" + timeClockId + ", in=" + in + ", out=" + out
				+ ", breakStart=" + breakStart + ", breakEnd=" + breakEnd + "]";
	}
	
	
}
