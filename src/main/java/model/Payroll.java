package model;

import java.util.Objects;

public class Payroll {
	
	private double hours;
	private double tips;
	private double overtime;
	
	public Payroll(double hours, double tips, double overtime) {
		super();
		this.hours = hours;
		this.tips = tips;
		this.overtime = overtime;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public double getTips() {
		return tips;
	}

	public void setTips(double tips) {
		this.tips = tips;
	}

	public double getOvertime() {
		return overtime;
	}

	public void setOvertime(double overtime) {
		this.overtime = overtime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hours, overtime, tips);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payroll other = (Payroll) obj;
		return Double.doubleToLongBits(hours) == Double.doubleToLongBits(other.hours)
				&& Double.doubleToLongBits(overtime) == Double.doubleToLongBits(other.overtime)
				&& Double.doubleToLongBits(tips) == Double.doubleToLongBits(other.tips);
	}

	@Override
	public String toString() {
		return "Payroll [hours=" + hours + ", tips=" + tips + ", overtime=" + overtime + "]";
	}

	
	
}
