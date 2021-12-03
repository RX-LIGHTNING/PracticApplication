package com.example.practicapp.objects;

import java.util.List;

public class Recipe {
    private List<Integer> quantity;
    private List<Integer> ing_id;
    private List<String> ing_name;
    private int recipeid;
    private String name;

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public List<Integer> getIng_id() {
        return ing_id;
    }

    public void setIng_id(List<Integer> ing_id) {
        this.ing_id = ing_id;
    }

    public List<String> getIng_name() {
        return ing_name;
    }

    public void setIng_name(List<String> ing_name) {
        this.ing_name = ing_name;
    }

    public int getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(int recipeid) {
        this.recipeid = recipeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe(int recipeid, String name) {
        this.recipeid = recipeid;
        this.name = name;
    }
}
