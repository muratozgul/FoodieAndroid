package app.com.example.foodie.foodieandroid.Model;

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

    private float rating;
    private int num_of_dishes;
    private List<String> tags;
    private ArrayList<Dish> dishes;
    private ArrayList<Review> reviews;

    //NOT USED FIELD!!!
    private Menu menu;
    private Inventory inventory;
    private ArrayList<Order> orders;

    public Chef(int user_id, String name, String profile_img) {
        super(user_id, name, profile_img);
    }

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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
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
