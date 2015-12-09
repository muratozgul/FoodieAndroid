package app.com.example.foodie.foodieandroid.Helpers;

import java.util.HashMap;
import java.util.Map;

import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;

/**
 * Created by Jennifer on 11/11/15.
 */
public class ShoppingCart {
    public static final String TAG = "ShoppingCart";
    private static ShoppingCart singleton;

    // Key: Dish, Value: Quantity
    private HashMap<Dish, Integer> contents;

    //############################
    //Constructors
    //############################

    protected ShoppingCart(){
        this.contents = new HashMap<Dish, Integer>();
    }

    //############################
    //Getters & Setters
    //############################

    public static synchronized ShoppingCart getInstance(){
        if(singleton == null) {
            singleton = new ShoppingCart();
        }
        return singleton;
    }

    public Map<Dish, Integer> getContents(){
        return this.contents;
    }

    //############################
    //Other Methods
    //############################

    public void empty(){
        this.contents = new HashMap<Dish, Integer>();
    }

    public void addOne(Dish dish){
        if(this.contents.containsKey(dish)){
            this.contents.put(dish, this.contents.get(dish)+1);
        } else {
            this.contents.put(dish, 1);
        }
    }

    public void removeOne(Dish dish){
        if(this.contents.containsKey(dish)){
            int amount = this.contents.get(dish);
            if(amount <= 1){
                this.contents.remove(dish);
            } else {
                this.contents.put(dish, amount-1);
            }
        }
    }

    public void removeAll(Dish dish){
        if(this.contents.containsKey(dish)){
            this.contents.remove(dish);
        }
    }

    public Order checkout(){
        Order order = new Order(this.contents);
        this.empty();
        return order;
    }

}
