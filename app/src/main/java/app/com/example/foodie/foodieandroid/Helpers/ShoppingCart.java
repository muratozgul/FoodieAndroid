package app.com.example.foodie.foodieandroid.Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;


public class ShoppingCart {
    public static final String TAG = "ShoppingCart";
    private static ShoppingCart singleton;

    // Key: Dish, Value: Quantity
    private HashMap<Dish, Integer> contents;
    private Double totalCost;

    //############################
    //Constructors
    //############################

    protected ShoppingCart(){
        this.contents = new HashMap<Dish, Integer>();
        this.totalCost = 0d;
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

    // return the number of items in the shopping cart
    public int size(){
        int sum = 0;

        for (int val : this.contents.values()) {
            sum += val;
        }
        return sum;
    }

    public ArrayList<OrderItem> getItemsAsList() {
        ArrayList<OrderItem> items = new ArrayList<OrderItem>();

        for (Map.Entry<Dish, Integer> entry : this.contents.entrySet()) {
            Dish dish = entry.getKey();
            int quantity = entry.getValue().intValue();

            double price = quantity*dish.getPrice();

            items.add(new OrderItem(0, 0, dish.getDish_id(), quantity, dish));
        }

        return items;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    //############################
    //Other Methods
    //############################

    public void empty(){
        this.totalCost = 0d;
        this.contents = new HashMap<Dish, Integer>();
    }

    public void addOne(Dish dish){
        this.totalCost += dish.getPrice();

        if(this.contents.containsKey(dish)){
            this.contents.put(dish, this.contents.get(dish)+1);
        } else {
            this.contents.put(dish, 1);
        }
    }

    public void removeOne(Dish dish){
        this.totalCost -= dish.getPrice();

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
            int quantity = this.contents.get(dish);
            this.totalCost -= (quantity*dish.getPrice());

            this.contents.remove(dish);
        }
    }

    public Order checkout(){
        Order order = new Order(this.contents);
        this.empty();
        return order;
    }

}
