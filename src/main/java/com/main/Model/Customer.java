package com.main.Model;

public class Customer {
    private String customerName;
    private String customerAddress;
    private String customerIdCard;
    private String customerPhone;
    private String username;
    private String password;
    private int roleId=3;

    public Customer(String customerName, String customerAddress, String customerIdCard, String customerPhone, String username, String password, int roleId) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerIdCard = customerIdCard;
        this.customerPhone = customerPhone;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerIdCard() {
        return customerIdCard;
    }

    public void setCustomerIdCard(String customerIdCard) {
        this.customerIdCard = customerIdCard;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
