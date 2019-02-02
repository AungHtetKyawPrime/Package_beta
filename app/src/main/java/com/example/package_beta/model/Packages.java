package com.example.package_beta.model;

public class Packages {
    public int place_image;
    public String place_name;
    public String place_description;
    public String place_money;
    public String place_date;

    public Packages(int place_image, String place_name, String place_description, String place_money, String place_date) {
        this.place_image = place_image;
        this.place_name = place_name;
        this.place_description = place_description;
        this.place_money = place_money;
        this.place_date = place_date;
    }

    public int getPlace_image() {
        return place_image;
    }

    public void setPlace_image(int place_image) {
        this.place_image = place_image;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getPlace_description() {
        return place_description;
    }

    public void setPlace_description(String place_description) {
        this.place_description = place_description;
    }

    public String getPlace_money() {
        return place_money;
    }

    public void setPlace_money(String place_money) {
        this.place_money = place_money;
    }

    public String getPlace_date() {
        return place_date;
    }

    public void setPlace_date(String place_date) {
        this.place_date = place_date;
    }
}
