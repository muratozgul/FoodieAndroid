package app.com.example.foodie.foodieandroid.DAO;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;


import java.util.ArrayList;

/**
 * Created by muratozgul on 06/12/15.
 */
public class OrderItemDAO {
    private static final String restApiBaseUrl = "http://www.foodie.com/";
    private static final String orderItemsUrl = "/orderitems";
    private static final String TAG = "OrderItemDAO";

    // Return all OrderItems associated to the user
    public static ArrayList<OrderItem> findAll() {


        return null;
    }

    // Return all OrderItems of an order
    public static ArrayList<OrderItem> findByOrderId(int orderId) {


        return null;
    }

    // Return single OrderItem by order-item-id
    public static void findById(int id) {
        // Build url
        StringBuilder sb = new StringBuilder();
        sb.append(restApiBaseUrl);
        sb.append(orderItemsUrl);
        sb.append("/");
        sb.append(Integer.toString(id));
        String url = sb.toString();

        // Make JSON Object request
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d(TAG, response.toString());


                }
            },new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "Error: " + error.getMessage());
                    //Toast.makeText(getApplicationContext(),
                    //        error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        );//jsonObjReq
    }

    public static OrderItem parseJson(JSONObject response) {
        OrderItem orderItem = null;
        try {
            int id = response.getInt("id");
            int orderId = response.getInt("orderId");
            int dishId = response.getInt("dishId");
            int quantity = response.getInt("quantity");

            orderItem = new OrderItem(id, orderId, dishId, quantity);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return orderItem;
    }
}

