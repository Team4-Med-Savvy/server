package com.example.product.dto;

public class ResponseMerchantDto {
    private Double price;
    private String merchantId;
    private String prodcutId;
    private long stock;

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getProdcutId() {
        return prodcutId;
    }

    public void setProdcutId(String prodcutId) {
        this.prodcutId = prodcutId;
    }
}
