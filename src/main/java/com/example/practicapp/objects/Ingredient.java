package com.example.practicapp.objects;

public class Ingredient {
    private String provider;
    private String name;
    private int ing_id;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;
    public int getIng_id() {
        return ing_id;
    }

    public void setIng_id(int ing_id) {
        this.ing_id = ing_id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
    public Ingredient(String name, int ing_id) {
        //this.provider = provider;String provider
        this.name = name;
        this.ing_id = ing_id;
    }
    public Ingredient(String name, int ing_id, int quantity) {
        //this.provider = provider;String provider
        this.name = name;
        this.ing_id = ing_id;
        this.quantity = quantity;
    }
}
