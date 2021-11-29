package com.example.practicapp.objects;

import javafx.scene.image.Image;

public class Product {
    private String name;
    private int price;
    private Image image;
    private String description;
    private int id;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Product(String name, int price, String image, String description, int id) {
        this.name = name;
        this.price = price;
        //this.image = new Image(String.valueOf(image));
        this.description = description;
        this.id =id;
    }
}
