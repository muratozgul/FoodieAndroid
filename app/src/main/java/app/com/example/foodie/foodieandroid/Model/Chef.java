package app.com.example.foodie.foodieandroid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Collection.Inventory;
import app.com.example.foodie.foodieandroid.Collection.Menu;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.Utility.Rating;

/**
 * Created by Jennifer on 11/11/15.
 */
public class Chef extends User {

    @SerializedName("score")
    private float rating;

    @SerializedName("numOfDishes")
    private int num_of_dishes;

    @SerializedName("tags")
    private List<String> tags;

    @SerializedName("dishes")
    private ArrayList<Dish> dishes;

//    private ArrayList<Review> reviews;
//
//    //TODO:NOT USED FIELD!!!
//    private Menu menu;
//    private Inventory inventory;
//    private ArrayList<Order> orders;

    //############################
    //Constructors
    //############################


    public Chef(int user_id, String name, String profile_img, float rating, int num_of_dishes, List<String> tags, ArrayList<Dish> dishes) {
        super(user_id, name, profile_img);
        this.rating = rating;
        this.num_of_dishes = num_of_dishes;
        this.tags = tags;
        this.dishes = dishes;
    }

    public Chef(int user_id, String name, String profile_img, ArrayList<Dish> dishes) {
        super(user_id, name, profile_img);
        this.dishes = dishes;
    }

    public Chef(int user_id, String name, String profile_img, float rating, int num_of_dishes, List<String> tags) {
        super(user_id, name, profile_img);
        this.rating = rating;
        this.num_of_dishes = num_of_dishes;
        this.tags = tags != null ? tags : new ArrayList<String>();
    }

    public Chef(int user_id, String name, String profile_img) {
        super(user_id, name, profile_img);
    }

    public Chef(){};

    //############################
    //Getters & Setters
    //############################

    public int getNum_of_dishes() {
        return num_of_dishes;
    }

    public void setNum_of_dishes(int num_of_dishes) {
        this.num_of_dishes = num_of_dishes;
    }

    public String getTags() {
        StringBuilder allTags = new StringBuilder();
        for(int i = 0; i < tags.size() - 1; i++){
            allTags.append(tags.get(i));
            allTags.append(" | ");
        }
        allTags.append(tags.get(tags.size()-1));
        return allTags.toString();
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNumOfDishes() {
        return num_of_dishes;
    }

    public void setNumOfDishes(int num_of_dishes) {
        this.num_of_dishes = num_of_dishes;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }
}
