package com.example.product.dto;

import java.util.List;

public class ReponseDto {
    private String id;
    private String title;
    private Double price;
    private String category;
    private List<MerchantDto> merchantdto;
    private String image;

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

    public List<MerchantDto> getMerchantdto() {
        return merchantdto;
    }

    public void setMerchantdto(List<MerchantDto> merchantdto) {
        this.merchantdto = merchantdto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
