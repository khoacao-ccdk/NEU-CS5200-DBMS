package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * CheckItem class - represents an item in the check 
 * 
 * @author Cody Cao & Xiaolin Zhan
 */
public class CheckItem {
    private int checkItemId;
    private int checkId;
    private Date date;
    private int itemId;
    private Timestamp timeCreated;
    private int orderModeId;
    private int quantity;
    private int refundQuantity;
    private int employeeId;
    private int parentModifierId;

    /**
     * Constructor used when inserting a new check item into the database
     * @param checkId
     * @param date
     * @param itemId
     * @param timeCreated
     * @param orderModeId
     * @param quantity
     * @param refundQuantity
     * @param employeeId
     * @param parentModifierId
     */
    public CheckItem(int checkId, Date date, int itemId, Timestamp timeCreated, int orderModeId, int quantity, int refundQuantity, int employeeId, int parentModifierId) {
        this.checkId = checkId;
        this.date = date;
        this.itemId = itemId;
        this.timeCreated = timeCreated;
        this.orderModeId = orderModeId;
        this.quantity = quantity;
        this.refundQuantity = refundQuantity;
        this.employeeId = employeeId;
        this.parentModifierId = parentModifierId;
    }

    /**
     * Constructor used when retrieving a check item from the database
     * @param checkItemId
     * @param checkId
     * @param date
     * @param itemId
     * @param timeCreated
     * @param orderModeId
     * @param quantity
     * @param refundQuantity
     * @param employeeId
     * @param parentModifierId
     */
    public CheckItem(int checkItemId, int checkId, Date date, int itemId, Timestamp timeCreated, int orderModeId, int quantity, int refundQuantity, int employeeId, int parentModifierId) {
        this.checkItemId = checkItemId;
        this.checkId = checkId;
        this.date = date;
        this.itemId = itemId;
        this.timeCreated = timeCreated;
        this.orderModeId = orderModeId;
        this.quantity = quantity;
        this.refundQuantity = refundQuantity;
        this.employeeId = employeeId;
        this.parentModifierId = parentModifierId;
    }

    public int getCheckItemId() {
        return this.checkItemId;
    }

    public void setCheckItemId(int checkItemId) {
        this.checkItemId = checkItemId;
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

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Timestamp getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getOrderModeId() {
        return this.orderModeId;
    }

    public void setOrderModeId(int orderModeId) {
        this.orderModeId = orderModeId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRefundQuantity() {
        return this.refundQuantity;
    }

    public void setRefundQuantity(int refundQuantity) {
        this.refundQuantity = refundQuantity;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getParentModifierId() {
        return this.parentModifierId;
    }

    public void setParentModifierId(int parentModifierId) {
        this.parentModifierId = parentModifierId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CheckItem)) {
            return false;
        }
        CheckItem checkItem = (CheckItem) o;
        return checkItemId == checkItem.checkItemId && checkId == checkItem.checkId && Objects.equals(date, checkItem.date) && itemId == checkItem.itemId && Objects.equals(timeCreated, checkItem.timeCreated) && orderModeId == checkItem.orderModeId && quantity == checkItem.quantity && refundQuantity == checkItem.refundQuantity && employeeId == checkItem.employeeId && parentModifierId == checkItem.parentModifierId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkItemId, checkId, date, itemId, timeCreated, orderModeId, quantity, refundQuantity, employeeId, parentModifierId);
    }

    @Override
    public String toString() {
        return "{" +
            " checkItemId='" + getCheckItemId() + "'" +
            ", checkId='" + getCheckId() + "'" +
            ", date='" + getDate() + "'" +
            ", itemId='" + getItemId() + "'" +
            ", timeCreated='" + getTimeCreated() + "'" +
            ", orderModeId='" + getOrderModeId() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", refundQuantity='" + getRefundQuantity() + "'" +
            ", employeeId='" + getEmployeeId() + "'" +
            ", parentModifierId='" + getParentModifierId() + "'" +
            "}";
    }
    
}
