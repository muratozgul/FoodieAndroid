package app.com.example.foodie.foodieandroid.DAO;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.example.foodie.foodieandroid.Application.FoodieApp;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;

/**
 * Created by muratozgul on 07/12/15.
 */
public class OrderDAO {
    private static final String TAG = "OrderDAO";
    private static String restApiBaseUrl = FoodieApp.getApiUrl();
    private static String orderUrl = "/order";

    // Return single OrderItem by order-item-id
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
                cbInterface.findOrderByIdCb(parseJson(response));
            }
        };

        // Create new response error listener
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.getMessage());
                //Toast.makeText(getApplicationContext(),
                //        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        // Make JSON Object request
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                responseListener, errorListener);

        // Add request to (global) request queue
        FoodieApp.getInstance().addToRequestQueue(jsonObjReq, TAG);
    }

    public static Order parseJson(JSONObject jsonObject){
        Order order = null;

        try {
            int id = jsonObject.getInt("id");
            int customerId = jsonObject.getInt("customer_id");
            double price = jsonObject.getDouble("price");
            String createdAt = jsonObject.getString("createdAt");

            order = new Order(id, customerId, createdAt, price);
        } catch (JSONException e) {
            Log.d(TAG, "Order parseJsonError: " + e.getMessage());
        }

        return order;
    }
}
