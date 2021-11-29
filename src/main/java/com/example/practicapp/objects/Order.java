package com.example.practicapp.objects;
import java.sql.Date;
import java.util.List;

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

    public String getProduct() {
        return productid;
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
private String productid;
private Date date;
private String Contact;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    private String Status;
    public Order(int id, String organization, int quantity, String product, Date date, String contacts, int status) {
        this.id = id;
        this.organzition = organization;
        this.quantity = quantity;
        this.productid = product;
        this.date = date;
        this.Contact = contacts;
        this.Status = status==0?"Проверка": status ==1?"Ожидание выполнения": status == 2? "Выполнен":"N/A";
    }
}
