package model;
import java.util.Objects;

/**
 * MenuItem class - represents an item in the menu 
 * 
 * @author Cody Cao & Xiaolin Zhan
 */
public class MenuItem {
    private int itemId;
    private String itemName;
    private double itemPrice;
    private int categoryId;

    /**
     * Constructor used when inserting a new Item to the menu
     * @param itemName
     * @param itemPrice
     * @param categoryId
     */
    public MenuItem(String itemName, double itemPrice, int categoryId) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.categoryId = categoryId;
    }

    /**
     * Constructor used when retrieving an item from database
     * @param itemId
     * @param itemName
     * @param itemPrice
     * @param categoryId
     */
    public MenuItem(int itemId, String itemName, double itemPrice, int categoryId) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.categoryId = categoryId;
    }

    public int getItemId() {
        return this.itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public MenuItem itemId(int itemId) {
        setItemId(itemId);
        return this;
    }

    public MenuItem itemName(String itemName) {
        setItemName(itemName);
        return this;
    }

    public MenuItem itemPrice(double itemPrice) {
        setItemPrice(itemPrice);
        return this;
    }

    public MenuItem categoryId(int categoryId) {
        setCategoryId(categoryId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MenuItem)) {
            return false;
        }
        MenuItem menuItem = (MenuItem) o;
        return itemId == menuItem.itemId && Objects.equals(itemName, menuItem.itemName) && itemPrice == menuItem.itemPrice && categoryId == menuItem.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, itemPrice, categoryId);
    }

    @Override
    public String toString() {
        return "{" +
            " itemId='" + getItemId() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", itemPrice='" + getItemPrice() + "'" +
            ", categoryId='" + getCategoryId() + "'" +
            "}";
    }
}