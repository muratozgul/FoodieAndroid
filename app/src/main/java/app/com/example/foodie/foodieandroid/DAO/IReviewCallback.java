package app.com.example.foodie.foodieandroid.DAO;

import java.util.ArrayList;

import app.com.example.foodie.foodieandroid.Model.Review;

/**
 * Created by Jennifer on 12/10/15.
 */

public interface IReviewCallback {

    public void findReviewsByDishIdCb(ArrayList<Review> reviews);

    public void createReviewCb(String responseString);

}
