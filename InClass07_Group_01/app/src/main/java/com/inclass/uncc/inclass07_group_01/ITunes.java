package com.inclass.uncc.inclass07_group_01;

/**
 * Created by Rishi on 23/10/17.
 */

public class ITunes {
    String image,name;long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    String price;

    @Override
    public String toString() {
        return "ITunes{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
