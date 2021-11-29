package com.example.practicapp.objects;

public class Provider {
    private int id;
    private String orgname;
    private int wheatflourprice;
    private int saltprice;
    private int ryeflourprice;
    private int yeastprice;
    private int status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public int getWheatflourprice() {
        return wheatflourprice;
    }

    public void setWheatflourprice(int wheatflourprice) {
        this.wheatflourprice = wheatflourprice;
    }

    public int getSaltprice() {
        return saltprice;
    }

    public void setSaltprice(int saltprice) {
        this.saltprice = saltprice;
    }

    public int getRyeflourprice() {
        return ryeflourprice;
    }

    public void setRyeflourprice(int ryeflourprice) {
        this.ryeflourprice = ryeflourprice;
    }

    public int getYeastprice() {
        return yeastprice;
    }

    public void setYeastprice(int yeastprice) {
        this.yeastprice = yeastprice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public Provider(int id, String orgname, int wheatflourprice, int saltprice, int ryeflourprice, int yeastprice, int status) {
        this.id = id;
        this.orgname = orgname;
        this.wheatflourprice = wheatflourprice;
        this.saltprice = saltprice;
        this.ryeflourprice = ryeflourprice;
        this.yeastprice = yeastprice;
    }
}
