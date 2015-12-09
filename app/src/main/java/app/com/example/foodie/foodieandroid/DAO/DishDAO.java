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
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;

/**
 * Created by muratozgul on 08/12/15.
 */
public class DishDAO {
    private static final String TAG = "DishDAO";
    private static String restApiBaseUrl = FoodieApp.getApiUrl();
    private static String orderUrl = "/dish";

    //############################
    //API GET methods
    //############################

    // Return single Dish by dish-id
    public static void findById(int id, final IDishCallback cbInterface) {
        // Build url
        StringBuilder sb = new StringBuilder();
        sb.append(restApiBaseUrl);
        sb.append(orderUrl);
        sb.append("/");
        sb.append(Integer.toString(id));
        String url = sb.toString();

        // Create new response listener
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                cbInterface.findDishByIdCb(response.toString());
                cbInterface.findDishByIdCb(parseGsonObject(response));
            }
        };

        // Create new response error listener
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Order findById Error: " + error.getMessage());
            }
        };

        // Make JSON Object request
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                responseListener, errorListener);

        // Add request to (global) request queue
        FoodieApp.getInstance().addToRequestQueue(jsonObjReq, TAG);
    }

    // Return all Dishes
    public static void findAll(final IDishCallback cbInterface) {
        // Build url
        StringBuilder sb = new StringBuilder();
        sb.append(restApiBaseUrl);
        sb.append(orderUrl);
        String url = sb.toString();

        // Create new response listener
        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                cbInterface.findAllDishesCb(response.toString());
                cbInterface.findAllDishesCb(parseGsonArray(response));
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

    public static Dish parseGsonObject(JSONObject jsonObject){
        Dish dish  = null;
        Gson gson = new Gson();

        //convert the json string back to object
        dish = gson.fromJson(jsonObject.toString(), Dish.class);
        Log.d(TAG, "Order parseGsonObject: " + dish.toString());

        return dish;
    }

    public static ArrayList<Dish> parseGsonArray(JSONArray jsonArray){
        Gson gson = new Gson();

        Type listType = new TypeToken<List<Dish>>() {}.getType();
        ArrayList<Dish> dishes = (ArrayList<Dish>) gson.fromJson(jsonArray.toString(), listType);
        Log.d(TAG, "Order parseGsonArray: size:" + dishes.size());

        return dishes;
    }
}
