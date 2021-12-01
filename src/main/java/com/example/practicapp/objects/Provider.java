package com.example.practicapp.objects;

public class Provider {
    private int id;
    private String orgname;
    private int price;
    private String ingredientname;



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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIngredientname() {
        return ingredientname;
    }

    public void setIngredientname(String ingredientname) {
        this.ingredientname = ingredientname;
    }



    public Provider(int id, String orgname, int price, String ingredientname) {
        this.id = id;
        this.orgname = orgname;
        this.price = price;
        this.ingredientname = ingredientname;
    }
}
