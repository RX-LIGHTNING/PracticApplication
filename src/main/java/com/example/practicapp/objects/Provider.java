package com.example.practicapp.objects;

public class Provider implements Comparable<Provider> {
    private int id;
    private String orgname;
    private int price;
    private String ingredientname;
    private int ing_id;

    public boolean isStatus() {
        return status;
    }

    private boolean status;
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getIng_id() {
        return ing_id;
    }
    public void setIng_id(int ing_id) {
        this.ing_id = ing_id;
    }
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
    @Override
    public int compareTo(Provider o) {
        return this.getPrice() - o.getPrice();
    }


    public Provider(int id,int ing_id, String orgname, int price, String ingredientname, boolean status) {
        this.id = id;
        this.ing_id = ing_id;
        this.orgname = orgname;
        this.price = price;
        this.ingredientname = ingredientname;
        this.status = status;
    }
}
