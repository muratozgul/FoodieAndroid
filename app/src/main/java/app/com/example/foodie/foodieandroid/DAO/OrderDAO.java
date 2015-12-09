package app.com.example.foodie.foodieandroid.DAO;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Application.FoodieApp;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;

/**
 * Created by muratozgul on 07/12/15.
 */
public class OrderDAO {
    private static final String TAG = "OrderDAO";
    private static String restApiBaseUrl = FoodieApp.getApiUrl();
    private static String orderUrl = "/order";

    //############################
    //API GET methods
    //############################

    // Return single Order by order-id
    public static void findById(int id, final IOrderCallback cbInterface) {
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
                cbInterface.findOrderByIdCb(response.toString());
                cbInterface.findOrderByIdCb(parseGsonObject(response));
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

    // Return all Orders
    public static void findAll(final IOrderCallback cbInterface) {
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
                cbInterface.findAllOrdersCb(response.toString());
                cbInterface.findAllOrdersCb(parseGsonArray(response));
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

    public static Order parseJsonObject(JSONObject jsonObject){
        Order order = null;

        try {
            int id = jsonObject.getInt("id");
            int customerId = jsonObject.getInt("customer_id");
            double price = jsonObject.getDouble("price");
            String createdAt = jsonObject.getString("createdAt");

            order = new Order(id, customerId, createdAt, price);
        } catch (JSONException e) {
            Log.d(TAG, "Order parseJsonObjectError: " + e.getMessage());
        }

        return order;
    }

    public static Order parseGsonObject(JSONObject jsonObject){
        Order order = null;

        Gson gson = new Gson();

        //convert the json string back to object
        order = gson.fromJson(jsonObject.toString(), Order.class);
        Log.d(TAG, "Order parseGsonObject: " + order.toString());

        return order;
    }

    public static ArrayList<Order> parseGsonArray(JSONArray jsonArray){
        //ArrayList<Order> orders = new ArrayList<Order>();

        Gson gson = new Gson();
        //ToDo
        Type listType = new TypeToken<List<Order>>() {}.getType();
        ArrayList<Order> orders = (ArrayList<Order>) gson.fromJson(jsonArray.toString(), listType);
        //orders = gson.fromJson(jsonArray, Order.class);

        Log.d(TAG, "Order parseGsonArray: size:" + orders.size());

        return orders;
    }
}
