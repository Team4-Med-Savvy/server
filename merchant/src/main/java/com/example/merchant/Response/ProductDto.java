package com.example.merchant.Response;

import java.util.List;

public class ProductDto {
    private String id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private List<String> merchant;
    private String image;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getMerchant() {
        return merchant;
    }

    public void setMerchant(List<String> merchant) {
        this.merchant = merchant;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
