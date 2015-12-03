package app.com.example.foodie.foodieandroid.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jennifer on 11/11/15.
 */
public class Dish {
    private int dish_id;
    private String name;
    private Image main;
    private float rating;
    private double price;
    private List<String> tags;

    public Dish(String name, float rating, double price, List<String> tags) {
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.tags = tags;
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

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTags() {
        StringBuilder allTags = new StringBuilder();
        for(int i = 0; i < tags.size(); i++){
            allTags.append(tags.get(i));
            allTags.append(" | ");
        }
        return allTags.toString();
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
