package app.com.example.foodie.foodieandroid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jennifer on 11/11/15.
 */
public class Dish {

    @SerializedName("id")
    private int dish_id;

    @SerializedName("name")
    private String name;

    @SerializedName("dishPic")
    private String dish_img;

    @SerializedName("score")
    private float rating;

    @SerializedName("price")
    private double price;

    @SerializedName("tags")
    private List<String> tags;

    @SerializedName("chef")
    private Chef chef;

    //############################
    //Constructors
    //############################

    public Dish(int dish_id, String name, float rating, double price, List<String> tags, String dish_img, Chef chef) {
        this.dish_id = dish_id;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.tags = tags != null ? tags : new ArrayList<String>();
        this.dish_img = dish_img;
        this.chef = chef;
    }

    public Dish(int dish_id, String name, float rating, double price, String imageUrl, Chef chef) {
        this.dish_id = dish_id;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.tags =  new ArrayList<String>();
        this.dish_img = imageUrl;
        this.chef = chef;
    }

    public Dish(){}
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
        if (tags == null)
            return "";

        StringBuilder allTags = new StringBuilder();
        for(int i = 0; i < tags.size()-1; i++){
            allTags.append(tags.get(i));
            allTags.append(" | ");
        }
        allTags.append(tags.get(tags.size()-1));
        return allTags.toString();
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
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

    // For debugging
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\ndish_id: " + dish_id);
        sb.append("\nname: " + name);
        sb.append("\nrating: " + rating);
        sb.append("\npic: " + dish_img);
        sb.append("\nprice: " + price);
        sb.append("\ntags:" + tags);
        return sb.toString();
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
