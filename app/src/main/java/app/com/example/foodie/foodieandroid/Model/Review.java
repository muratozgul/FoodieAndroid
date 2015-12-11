package app.com.example.foodie.foodieandroid.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Jennifer on 11/11/15.
 */

public class Review implements Serializable {

    @SerializedName("id")
    private int review_id;

    @SerializedName("starRating")
    private float star_rating;

    @SerializedName("reviewText")
    private String review_text;

    @SerializedName("updatedAt")
    private String timestamp;

    @SerializedName("customerId")
    private int customer_id;

    @SerializedName("dishId")
    private int dish_id;

    @SerializedName("customer")
    private Customer customer;

    //############################
    //Constructors
    //############################

    public Review(int review_id, float star_rating, String review_text, String timestamp, int customer_id, int dish_id) {
        this.review_id = review_id;
        this.star_rating = star_rating;
        this.review_text = review_text;
        this.timestamp = timestamp;
        this.customer_id = customer_id;
        this.dish_id = dish_id;
    }

    public Review(int review_id, float star_rating, String review_text, String timestamp, int customer_id, int dish_id, Customer customer) {
        this.review_id = review_id;
        this.star_rating = star_rating;
        this.review_text = review_text;
        this.timestamp = timestamp;
        this.customer_id = customer_id;
        this.dish_id = dish_id;
        this.customer = customer;
    }

    public Review(Customer customer) {
        this.customer = customer;
    }

    public Review(){}
//############################
    //Getters & Setters
    //############################

    public void setStar_rating(float star_rating) {
        this.star_rating = star_rating;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public float getStar_rating() {
        return star_rating;
    }

    public void setStar_rating(int star_rating) {
        this.star_rating = star_rating;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // For debugging
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nid: " + review_id);
        sb.append("\nrating: " + star_rating);
        sb.append("\ntext: " + review_text);
        sb.append("\ntimestamp: " + timestamp);
        sb.append("\ncustomer_id: " + customer_id);
        sb.append("\ndish_id:" + dish_id);
        sb.append("\nCUSTOMER: \n" + customer.toString());
        return sb.toString();
    }

    //############################
    //HashMap Comparison Methods
    //############################

    @Override
    public int hashCode(){
        return review_id;
    }

    @Override
    public boolean equals(Object otherObj) {
        return otherObj instanceof Review && this.review_id == ((Review) otherObj).review_id;
    }

    //############################
    //Other Methods
    //############################

    public String getReviewDateString(){
        //timestamp is in milliseconds
        return DateFormat.getDateTimeInstance().format(new Date(Long.parseLong(timestamp)));
    }
}
