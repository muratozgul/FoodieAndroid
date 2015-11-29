package app.com.example.foodie.foodieandroid.Model;

import java.util.ArrayList;

import app.com.example.foodie.foodieandroid.Collection.Inventory;
import app.com.example.foodie.foodieandroid.Collection.Menu;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.ModelSecondary.Review;
import app.com.example.foodie.foodieandroid.Utility.Rating;

/**
 * Created by Jennifer on 11/11/15.
 */
public class Chef extends User {
    private Menu menu;
    private Inventory inventory;
    private Rating rating;
    private ArrayList<Review> reviews;
    private ArrayList<Order> orders;

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

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
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
}
