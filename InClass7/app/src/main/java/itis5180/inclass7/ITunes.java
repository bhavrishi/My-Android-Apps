package itis5180.inclass7;

/**
 * Assignment: InClass07
 * Name: ITunes.java
 * Date: 10/24/2017
 * Bradley Wright
 * Anvesh Kottapelli
 * Bhavya Gedi
 */

class ITunes {
    public String image;
    public String name;
    private long id;

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

    String getImage() {
        return image;
    }

    void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getPrice() {
        return price;
    }

    void setPrice(String price) {
        this.price = price;
    }
}