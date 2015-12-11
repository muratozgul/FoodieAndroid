package app.com.example.foodie.foodieandroid.ModelSecondary;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import app.com.example.foodie.foodieandroid.Model.Dish;

public class OrderItem implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("orderId")
    private int order_id;

    @SerializedName("dishId")
    private int dish_id;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("price")
    private double totalPrice;

    //############################
    //Constructors
    //############################

    public OrderItem(int id, int orderId, int dish_id, int quantity) {
        this.id = id;
        this.order_id = orderId;
        this.dish_id = dish_id;
        this.quantity = quantity;
    }

    public OrderItem(int dish_id, int quantity, double totalPrice) {
        this.dish_id = dish_id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public OrderItem(Dish dish, int quantity, double totalPrice) {
        this.dish_id = dish.getDish_id();
        this.quantity = quantity;
        this.totalPrice = totalPrice;
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

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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
        out.writeInt(order_id);
        out.writeInt(dish_id);
        out.writeInt(quantity);
    }

    // This is used to regenerate orderItem object.
    // All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<OrderItem> CREATOR = new Parcelable.Creator<OrderItem>() {
        public OrderItem createFromParcel(Parcel in) {
            return new OrderItem(in);
        }

        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };

    // constructor that takes a Parcel and gives you an object populated with it's values
    private OrderItem(Parcel in) {
        //read in the same order as writes
        this.id = in.readInt();
        this.order_id = in.readInt();
        this.dish_id = in.readInt();
        this.quantity = in.readInt();
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
        return otherObj instanceof OrderItem && this.id == ((OrderItem) otherObj).id;
    }

    //############################
    //Other Methods
    //############################

    public String toString(){
        String result = "OrderItem id: " + Integer.toString(this.id);
        result += ", orderId: " + Integer.toString(this.order_id);
        result += ", quantity: " + Integer.toString(this.quantity);
        return result;
    }
}
