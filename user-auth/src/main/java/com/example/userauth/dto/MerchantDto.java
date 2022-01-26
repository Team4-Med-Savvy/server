package com.example.userauth.dto;

public class MerchantDto {
    private String id;
    private String name;
    private long total_sales;
    private long points;

    public MerchantDto(String id,String name, long total_sales, long points) {
        this.id=id;
        this.name = name;
        this.total_sales = total_sales;
        this.points = points;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(long total_sales) {
        this.total_sales = total_sales;
    }
}
