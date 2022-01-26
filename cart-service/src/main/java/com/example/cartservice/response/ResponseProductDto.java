package com.example.cartservice.response;

public class ResponseProductDto {
    private String title;
    private Long price;
    private String category;
    private int quantity;
    private String image;
//    private String description;
    private String name;
    private String email;
    private long points;
    private long total_sales;
    private String merchantId;



    public ResponseProductDto(String title, Long price, String email, int quantity, String category, String image, String name, long ranking, long total_sales, String merchantId) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.quantity=quantity;
        this.email=email;
        this.image = image;
//        this.description = description;
        this.name = name;
        this.points = ranking;
        this.total_sales = total_sales;
        this.merchantId = merchantId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public long getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(long total_sales) {
        this.total_sales = total_sales;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

}
