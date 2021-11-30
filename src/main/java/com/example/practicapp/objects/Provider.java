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
    public String getOrgname() {
        return orgname;
    }
    public int getWheatflourprice() {
        return wheatflourprice;
    }
    public int getSaltprice() {
        return saltprice;
    }
    public int getRyeflourprice() {
        return ryeflourprice;
    }
    public int getYeastprice() {
        return yeastprice;
    }
    public int getStatus() {
        return status;
    }
    public Provider(int id, String orgname, int wheatflourprice, int saltprice, int ryeflourprice, int yeastprice, int status) {
        this.id = id;
        this.orgname = orgname;
        this.wheatflourprice = wheatflourprice;
        this.saltprice = saltprice;
        this.ryeflourprice = ryeflourprice;
        this.yeastprice = yeastprice;
        this.status = status;
    }
}
