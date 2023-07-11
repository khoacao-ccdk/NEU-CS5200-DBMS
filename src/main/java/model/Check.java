package model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 * Check class - represents a Check
 * 
 * @author Cody Cao & Xiaolin Zhan
 */
public class Check {
    private int checkId;
    private Date date;
    private int revCenterId;
    private int employeeId;
    private String tableDesc;
    private int guestCount;
    private double netSales;
    private double comps;
    private double promo; 
    private double tax;
    private int paymentId;
    private Time timeOpen;
    private Time timeClose;

    /**
     * Constructor to access check from the database
     * @param checkId
     * @param date
     * @param revCenterId
     * @param employeeId
     * @param tableDesc
     * @param guestCount
     * @param netSales
     * @param comps
     * @param promo
     * @param tax
     * @param paymentId
     * @param timeOpen
     * @param timeClose
     */
    public Check(int checkId, Date date, int revCenterId, int employeeId, String tableDesc, int guestCount, double netSales, double comps, double promo, double tax, int paymentId, Time timeOpen, Time timeClose) {
        this.checkId = checkId;
        this.date = date;
        this.revCenterId = revCenterId;
        this.employeeId = employeeId;
        this.tableDesc = tableDesc;
        this.guestCount = guestCount;
        this.netSales = netSales;
        this.comps = comps;
        this.promo = promo;
        this.tax = tax;
        this.paymentId = paymentId;
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
    }

    public int getCheckId() {
        return this.checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRevCenterId() {
        return this.revCenterId;
    }

    public void setRevCenterId(int revCenterId) {
        this.revCenterId = revCenterId;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getTableDesc() {
        return this.tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    public int getGuestCount() {
        return this.guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public double getNetSales() {
        return this.netSales;
    }

    public void setNetSales(double netSales) {
        this.netSales = netSales;
    }

    public double getComps() {
        return this.comps;
    }

    public void setComps(double comps) {
        this.comps = comps;
    }

    public double getPromo() {
        return this.promo;
    }

    public void setPromo(double promo) {
        this.promo = promo;
    }

    public double getTax() {
        return this.tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getPaymentId() {
        return this.paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Time getTimeOpen() {
        return this.timeOpen;
    }

    public void setTimeOpen(Time timeOpen) {
        this.timeOpen = timeOpen;
    }

    public Time getTimeClose() {
        return this.timeClose;
    }

    public void setTimeClose(Time timeClose) {
        this.timeClose = timeClose;
    }

    public Check checkId(int checkId) {
        setCheckId(checkId);
        return this;
    }

    public Check date(Date date) {
        setDate(date);
        return this;
    }

    public Check revCenterId(int revCenterId) {
        setRevCenterId(revCenterId);
        return this;
    }

    public Check employeeId(int employeeId) {
        setEmployeeId(employeeId);
        return this;
    }

    public Check tableDesc(String tableDesc) {
        setTableDesc(tableDesc);
        return this;
    }

    public Check guestCount(int guestCount) {
        setGuestCount(guestCount);
        return this;
    }

    public Check netSales(double netSales) {
        setNetSales(netSales);
        return this;
    }

    public Check comps(double comps) {
        setComps(comps);
        return this;
    }

    public Check promo(double promo) {
        setPromo(promo);
        return this;
    }

    public Check tax(double tax) {
        setTax(tax);
        return this;
    }

    public Check paymentId(int paymentId) {
        setPaymentId(paymentId);
        return this;
    }

    public Check timeOpen(Time timeOpen) {
        setTimeOpen(timeOpen);
        return this;
    }

    public Check timeClose(Time timeClose) {
        setTimeClose(timeClose);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Check)) {
            return false;
        }
        Check check = (Check) o;
        return checkId == check.checkId && Objects.equals(date, check.date) && revCenterId == check.revCenterId && employeeId == check.employeeId && Objects.equals(tableDesc, check.tableDesc) && guestCount == check.guestCount && netSales == check.netSales && comps == check.comps && promo == check.promo && tax == check.tax && paymentId == check.paymentId && Objects.equals(timeOpen, check.timeOpen) && Objects.equals(timeClose, check.timeClose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkId, date, revCenterId, employeeId, tableDesc, guestCount, netSales, comps, promo, tax, paymentId, timeOpen, timeClose);
    }

    @Override
    public String toString() {
        return "{" +
            " checkId='" + getCheckId() + "'" +
            ", date='" + getDate() + "'" +
            ", revCenterId='" + getRevCenterId() + "'" +
            ", employeeId='" + getEmployeeId() + "'" +
            ", tableDesc='" + getTableDesc() + "'" +
            ", guestCount='" + getGuestCount() + "'" +
            ", netSales='" + getNetSales() + "'" +
            ", comps='" + getComps() + "'" +
            ", promo='" + getPromo() + "'" +
            ", tax='" + getTax() + "'" +
            ", paymentId='" + getPaymentId() + "'" +
            ", timeOpen='" + getTimeOpen() + "'" +
            ", timeClose='" + getTimeClose() + "'" +
            "}";
    }
    
}