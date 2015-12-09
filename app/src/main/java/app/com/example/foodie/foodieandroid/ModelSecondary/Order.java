package app.com.example.foodie.foodieandroid.ModelSecondary;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.com.example.foodie.foodieandroid.Model.Dish;


public class Order implements Parcelable{
    private static final String TAG = "Order";

    @SerializedName("id")
    private int id;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("chef_id")
    private int chef_id;

    @SerializedName("createdAt")
    private String time_stamp;

    @SerializedName("orderItems")
    private ArrayList<OrderItem> orderItems;

    @SerializedName("price")
    private double total_price;

    //############################
    //Constructors
    //############################

    public Order(){
        this.id = 123;
        this.user_id = 123;
        this.chef_id = 123;
        this.time_stamp = "TODAY";
        this.orderItems = new ArrayList<OrderItem>();
        this.total_price = 20d;
    }

    public Order(int id, double total_price){
        this.id = id;
        this.user_id = 123;
        this.chef_id = 123;
        this.time_stamp = "TODAY";
        this.orderItems = new ArrayList<OrderItem>();
        this.total_price = total_price;
    }

    public Order(int id, int customerId, String createdAt, double price){
        this.id = id;
        this.user_id  = customerId;
        this.time_stamp = createdAt;
        this.total_price = price;
        this.orderItems = new ArrayList<OrderItem>();
    }

    public Order(int id, int userId, int chefId, String time, ArrayList<OrderItem> list, double totalPrice){
        this.id = id;
        this.user_id = userId;
        this.chef_id = chefId;
        this.time_stamp = time;
        this.orderItems = list;
        this.total_price = totalPrice;
    }

    public Order(HashMap<Dish, Integer> dishes){
        this.orderItems = new ArrayList<OrderItem>();
        this.total_price = 0d;

        for (Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            Dish dish = entry.getKey();
            int quantity = entry.getValue().intValue();

            double price = quantity*dish.getPrice();

            this.orderItems.add(new OrderItem(dish.getDish_id(), quantity, price));
            this.total_price += price;
        }
    }

    //############################
    //Getters & Setters
    //############################

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getChef_id() {
        return chef_id;
    }

    public void setChef_id(int chef_id) {
        this.chef_id = chef_id;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    //############################
    //Parcelable Interface Methods
    //############################

    //Parcelable used for passing objects via intents
    //Parcelable is faster than serializable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeInt(user_id);
        out.writeInt(chef_id);
        out.writeString(time_stamp);
        out.writeDouble(total_price);
        out.writeTypedList(orderItems); //writeTypedList
    }

    // This is used to regenerate order object.
    // All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    // constructor that takes a Parcel and gives you an object populated with it's values
    private Order(Parcel in) {
        //read in the same order as writes
        this.id = in.readInt();
        this.user_id = in.readInt();
        this.chef_id = in.readInt();
        this.time_stamp = in.readString();
        this.total_price = in.readDouble();

        // readTypeList() needs an existing List<> to load.
        ArrayList<OrderItem> tempList = new ArrayList<OrderItem>();
        in.readTypedList(tempList, OrderItem.CREATOR); // OrderItem.class.getClassLoader()
        this.orderItems = tempList;
    }

    //############################
    //HashMap Comparison Methods
    //############################

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public boolean equals(Object otherObj) {
        return otherObj instanceof Order && this.id == ((Order) otherObj).id;
    }

    //############################
    //Other Methods
    //############################

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
    }

    public OrderItem getOrderItem(int index){
        return this.orderItems.get(index);
    }

    public String toString(){
        String output = "Order id: " + Integer.toString(this.id);
        output += " has " + Integer.toString(this.orderItems.size());
        output += " order items.";
        return output;
    }
}
