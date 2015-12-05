package app.com.example.foodie.foodieandroid.Model;

/**
 * Created by Jennifer on 11/11/15.
 */

public class Review {
    private int review_id;
    private float star_rating;
    private String review_text;
    private String timestamp;
    private int customer_id;
    private int dish_id;

    private int orderitem_id;

    public Review(int review_id, float star_rating, String review_text, String timestamp, int customer_id, int dish_id) {
        this.review_id = review_id;
        this.star_rating = star_rating;
        this.review_text = review_text;
        this.timestamp = timestamp;
        this.customer_id = customer_id;
        this.dish_id = dish_id;
    }

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

    public int getOrderitem_id() {
        return orderitem_id;
    }

    public void setOrderitem_id(int orderitem_id) {
        this.orderitem_id = orderitem_id;
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
}
