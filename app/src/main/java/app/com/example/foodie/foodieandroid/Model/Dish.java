package app.com.example.foodie.foodieandroid.Model;

import java.util.ArrayList;

/**
 * Created by Jennifer on 11/11/15.
 */
public class Dish {
    private int dish_id;
    private String name;
    private Image main;
    private ArrayList<Image> images;
    private ArrayList<Tag> tags;
    private float price;

    public Dish(String name, Image main) {
        this.name = name;
        this.main = main;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getMain() {
        return main;
    }

    public void setMain(Image main) {
        this.main = main;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
