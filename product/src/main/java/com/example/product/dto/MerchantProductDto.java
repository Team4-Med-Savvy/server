package com.example.product.dto;

public class MerchantProductDto {
    private long id;
    private long price;
    private long stock;
    private String productId;
    private String merchantId;

    public MerchantProductDto(long price, long stock, String productId, String merchantId) {
        this.price = price;
        this.stock = stock;
        this.productId = productId;
        this.merchantId = merchantId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
