package model;

import java.sql.Date;
import java.util.Objects;

/**
 * Employee class - represents an employee in the database 
 * 
 * @author Cody Cao & Xiaolin Zhan
 */
public class Employee {
    private int employeeId;
    private String firstName; 
    private String lastName; 
    private String SSN;
    private Date dOB;
    private String email;
    private String phone;
    private String Street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private boolean status;
    private String role;
    private int wage;

    /**
     * Constructor to access employee from database
     * @param employeeId
     * @param firstName
     * @param lastName
     * @param SSN
     * @param dOB
     * @param email
     * @param phone
     * @param Street1
     * @param street2
     * @param city
     * @param state
     * @param zip
     * @param status
     * @param role
     * @param wage
     */
    public Employee(int employeeId, String firstName, String lastName, String SSN, Date dOB, String email, String phone, String Street1, String street2, String city, String state, String zip, boolean status, String role, int wage) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        this.dOB = dOB;
        this.email = email;
        this.phone = phone;
        this.Street1 = Street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.status = status;
        this.role = role;
        this.wage = wage;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSSN() {
        return this.SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public Date getDOB() {
        return this.dOB;
    }

    public void setDOB(Date dOB) {
        this.dOB = dOB;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet1() {
        return this.Street1;
    }

    public void setStreet1(String Street1) {
        this.Street1 = Street1;
    }

    public String getStreet2() {
        return this.street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public boolean isStatus() {
        return this.status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getWage() {
        return this.wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public Employee employeeId(int employeeId) {
        setEmployeeId(employeeId);
        return this;
    }

    public Employee firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public Employee lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public Employee SSN(String SSN) {
        setSSN(SSN);
        return this;
    }

    public Employee dOB(Date dOB) {
        setDOB(dOB);
        return this;
    }

    public Employee email(String email) {
        setEmail(email);
        return this;
    }

    public Employee phone(String phone) {
        setPhone(phone);
        return this;
    }

    public Employee Street1(String Street1) {
        setStreet1(Street1);
        return this;
    }

    public Employee street2(String street2) {
        setStreet2(street2);
        return this;
    }

    public Employee city(String city) {
        setCity(city);
        return this;
    }

    public Employee state(String state) {
        setState(state);
        return this;
    }

    public Employee zip(String zip) {
        setZip(zip);
        return this;
    }

    public Employee status(boolean status) {
        setStatus(status);
        return this;
    }

    public Employee role(String role) {
        setRole(role);
        return this;
    }

    public Employee wage(int wage) {
        setWage(wage);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(SSN, employee.SSN) && Objects.equals(dOB, employee.dOB) && Objects.equals(email, employee.email) && Objects.equals(phone, employee.phone) && Objects.equals(Street1, employee.Street1) && Objects.equals(street2, employee.street2) && Objects.equals(city, employee.city) && Objects.equals(state, employee.state) && Objects.equals(zip, employee.zip) && status == employee.status && Objects.equals(role, employee.role) && wage == employee.wage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, SSN, dOB, email, phone, Street1, street2, city, state, zip, status, role, wage);
    }

    @Override
    public String toString() {
        return "{" +
            " employeeId='" + getEmployeeId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", SSN='" + getSSN() + "'" +
            ", dOB='" + getDOB() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", Street1='" + getStreet1() + "'" +
            ", street2='" + getStreet2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", zip='" + getZip() + "'" +
            ", status='" + isStatus() + "'" +
            ", role='" + getRole() + "'" +
            ", wage='" + getWage() + "'" +
            "}";
    }
    
}
