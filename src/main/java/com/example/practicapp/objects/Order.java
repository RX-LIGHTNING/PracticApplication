package com.example.practicapp.objects;

import java.sql.Date;

public class Order {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrganzition() {
        return organzition;
    }

    public void setOrganzition(String organzition) {
        this.organzition = organzition;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    private int id;
private String organzition;
private int quantity;
private int productid;
private Date date;
private String Contact;

    public Order(int id, String organization, int quantity, int productid, Date date, String contacts) {
        this.id = id;
        this.organzition = organzition;
        this.quantity = quantity;
        this.productid = productid;
        this.date = date;
        this.Contact = contacts;

    }
}
