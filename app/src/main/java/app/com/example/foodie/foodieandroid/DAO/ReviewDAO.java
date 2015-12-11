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
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Application.FoodieApp;
import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.Model.Review;

/**
 * Created by Jennifer on 12/10/15.
 */
public class ReviewDAO {
    private static final String TAG = "ReviewDAO";
    private static String restApiBaseUrl = FoodieApp.getApiUrl();
    private static String reviewUrl = "/reviews/dishes";

    //############################
    //API GET methods
    //############################

    // Return reviews by dishId
    public static void findById(int id, final IReviewCallback cbInterface) {
        // Build url
        StringBuilder sb = new StringBuilder();
        sb.append(restApiBaseUrl);
        sb.append(reviewUrl);
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
