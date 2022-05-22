package com.example.ourflora.model;

public class Accessory {
    Integer accessId;
    String accessName;
    String accessSize;
    String accessPrice;
    Integer imageUrl;

    public Accessory(Integer accessId, String accessName, String accessSize, String accessPrice, Integer imageUrl) {
        this.accessId = accessId;
        this.accessName = accessName;
        this.accessSize = accessSize;
        this.accessPrice = accessPrice;
        this.imageUrl = imageUrl;
    }

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public String getAccessSize() {
        return accessSize;
    }

    public void setAccessSize(String accessSize) {
        this.accessSize = accessSize;
    }

    public String getAccessPrice() {
        return accessPrice;
    }

    public void setAccessPrice(String accessPrice) {
        this.accessPrice = accessPrice;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
