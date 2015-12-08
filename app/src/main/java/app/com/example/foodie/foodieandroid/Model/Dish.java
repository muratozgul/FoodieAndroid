package app.com.example.foodie.foodieandroid.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jennifer on 11/11/15.
 */
public class Dish {
    private int dish_id;
    private String name;
    private String dish_img;
    private float rating;
    private double price;
    private List<String> tags;
    private int chef_id;

    //############################
    //Constructors
    //############################

    public Dish(int dish_id, String name, float rating, double price, List<String> tags, String dish_img, int chef_id) {
        this.dish_id = dish_id;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.tags = tags;
        this.dish_img = dish_img;
        this.chef_id = chef_id;
    }

    public Dish(int dish_id, String name, double price) {
        this.dish_id = dish_id;
        this.name = name;
        this.rating = 4;
        this.price = price;
        this.tags = null;
        this.dish_img = "";
        this.chef_id = 0;
    }

    //############################
    //Getters & Setters
    //############################

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

    public String getDishImage() {
        return dish_img;
    }

    public void setDishImage(String dish_img) {
        this.dish_img = dish_img;
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

    public int getChef_id() {
        return chef_id;
    }

    public void setChef_id(int chef_id) {
        this.chef_id = chef_id;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDish_img() {
        return dish_img;
    }

    public void setDish_img(String dish_img) {
        this.dish_img = dish_img;
    }

    //############################
    //HashMap Comparison Methods
    //############################
    @Override
    public int hashCode(){
        return dish_id;
    }

    @Override
    public boolean equals(Object otherObj) {
        return otherObj instanceof Dish && this.dish_id == ((Dish) otherObj).dish_id;
    }
}
