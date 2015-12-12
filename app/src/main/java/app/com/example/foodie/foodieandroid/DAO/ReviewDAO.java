package app.com.example.foodie.foodieandroid.DAO;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Application.FoodieApp;
import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.Model.Review;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;

/**
 * Created by Jennifer on 12/10/15.
 */
public class ReviewDAO {
    private static final String TAG = "ReviewDAO";
    private static String restApiBaseUrl = FoodieApp.getApiUrl();
    private static String baseReviewUrl = "/reviews";
    private static String dishReviewUrl = "/reviews/dishes";
    private static String chefReviewUrl = "/reviews/chefs";

    //############################
    //URL building methods
    //############################

    public static String postReviewUrl(){
        StringBuilder sb = new StringBuilder();
        sb.append(restApiBaseUrl);
        sb.append(baseReviewUrl);
        return sb.toString();
    }


    //############################
    //API GET methods
    //############################

    // Return reviews by dishId
    public static void findById(int id, String type, final IReviewCallback cbInterface) {
        // Build url
        StringBuilder sb = new StringBuilder();
        sb.append(restApiBaseUrl);
        if(type == "dish"){
            sb.append(dishReviewUrl);
        } else if(type == "chef"){
            sb.append(chefReviewUrl);
        }
        sb.append("/");
        sb.append(Integer.toString(id));
        String url = sb.toString();

        // Create new response listener
        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                cbInterface.findReviewsByDishIdCb(parseGsonArray(response));
            }
        };

        // Create new response error listener
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Object findAll Error: " + error.getMessage());
            }
        };

        // Make JSON Array request
        JsonArrayRequest jsonArrReq = new JsonArrayRequest(url, responseListener, errorListener);

        // Add request to (global) request queue
        FoodieApp.getInstance().addToRequestQueue(jsonArrReq, TAG);
    }

    //############################
    //API POST methods
    //############################

    public static void create(Review review, final IReviewCallback cbInterface) throws JSONException {
        // Build url
        String url = postReviewUrl();

        // Build req
        JSONObject jsReview = new JSONObject();
        jsReview.put("starRating", review.getStar_rating());
        jsReview.put("reviewText", review.getReview_text());
        jsReview.put("dishId", review.getDish_id());
        jsReview.put("customerId", FoodieApp.getAppUserId());

        // Create new response listener
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Order created");
                Log.d(TAG, response.toString());
                cbInterface.createReviewCb(response.toString());
            }
        };

        // Create new response error listener
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Order create Error: " + error.getMessage());
            }
        };

        // Make POST request
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, url, jsReview, responseListener, errorListener);
        Log.d(TAG, "Json sent:");
        Log.d(TAG, jsReview.toString());

        // Add request to (global) request queue
        FoodieApp.getInstance().addToRequestQueue(jsonObjRequest, TAG);
    }

    //############################
    //JSON parsing methods
    //############################

    public static ArrayList<Review> parseGsonArray(JSONArray jsonArray){
        Gson gson = new Gson();

        Type listType = new TypeToken<List<Review>>() {}.getType();
        ArrayList<Review> reviews = (ArrayList<Review>) gson.fromJson(jsonArray.toString(), listType);
        Log.d(TAG, "Review parseGsonArray: size:" + reviews.size());

        return reviews;
    }
}
