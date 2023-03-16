package com.sbutron;

import java.util.ArrayList;
import java.util.Date;

public class GameData {
    private String name;
    private ArrayList platforms;
    private Date releaseDate;
    private Review review;
    private int price;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList getPlatforms() {
        return platforms;
    }
    public void setPlatforms(ArrayList platforms) {
        this.platforms = platforms;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public Review getReview() {
        return review;
    }
    public void setReview(Review review) {
        this.review = review;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "GameData{" +
                "name='" + name +
                ", platforms=" + platforms +
                ", releaseDate=" + releaseDate +
                ", review=" + review +
                ", price=" + price +
                '}';
    }

}
