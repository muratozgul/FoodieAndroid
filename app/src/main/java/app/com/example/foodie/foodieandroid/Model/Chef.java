package app.com.example.foodie.foodieandroid.Model;

import java.util.ArrayList;

import app.com.example.foodie.foodieandroid.Collection.Inventory;
import app.com.example.foodie.foodieandroid.Collection.Menu;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.Utility.Rating;

/**
 * Created by Jennifer on 11/11/15.
 */
public class Chef extends User {

    private Rating rating;
    private int num_of_dishes;
    private ArrayList<Dish> dishes;
    private ArrayList<Review> reviews;

    //NOT USED FIELD!!!
    private Menu menu;
    private Inventory inventory;
    private ArrayList<Order> orders;

    public Chef(int user_id, String name, String profile_img) {
        super(user_id, name, profile_img);
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
