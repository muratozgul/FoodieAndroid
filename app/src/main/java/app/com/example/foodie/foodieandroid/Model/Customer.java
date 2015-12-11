package app.com.example.foodie.foodieandroid.Model;

import java.io.Serializable;
import java.util.ArrayList;

import app.com.example.foodie.foodieandroid.Helpers.ShoppingCart;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;

/**
 * Created by Jennifer on 11/11/15.
 */
public class Customer extends User implements Serializable {

    private ArrayList<Review> reviews;
    private ArrayList<Order> orders;
    private ShoppingCart shoppingCart;

    //############################
    //Constructors
    //############################

    public Customer(int user_id, String name, String profile_img) {
        super(user_id, name, profile_img);
    }

    public Customer(){}

    //############################
    //Getters & Setters
    //############################

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

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

}
