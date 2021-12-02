package com.example.practicapp.objects;

import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Product {
    private String name;
    private int price;
    private Image image;
    private String description;
    private int id;
    private int recipe;
    private byte[] imageBytes;

    public byte[] getImageBytes() {
        return imageBytes;
    }
    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
    public int getRecipe() {
        return recipe;
    }
    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }
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
    private Image getImageFromBytes(byte[] rs) {
        Image img = new Image(new ByteArrayInputStream(rs));
        return img;
    }

    public Product(String name, int price, byte[] image, String description, int id, int recipe) {
        this.name = name;
        this.price = price;
        this.imageBytes = image;
        this.image = getImageFromBytes(image);
        this.description = description;
        this.id =id;
        this.recipe = recipe;
    }
}
