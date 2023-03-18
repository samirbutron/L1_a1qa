package com.sbutron;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class GameData {
    private String name;
    private ArrayList platforms;
    private Date releaseDate;
    private Review review;
    private Double price;

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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
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

    @Override
    public int hashCode() {
        return Objects.hash(name, platforms, releaseDate, review, price);
    }
}
