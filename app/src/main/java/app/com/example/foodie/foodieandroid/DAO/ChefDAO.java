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
import app.com.example.foodie.foodieandroid.Model.Chef;

/**
 * Created by Jennifer on 12/8/15.
 */


public class ChefDAO {
    public static final String TAG = "ChefDAO";
    private static String restApiBaseUrl = FoodieApp.getApiUrl();
    private static String chefUrl = "/chefs";

    //############################
    //API GET methods
    //############################

    public static void findById(int id, final IChefCallback cbInterface) {
        //Build url
        StringBuilder sb = new StringBuilder();
        sb.append(restApiBaseUrl);
        sb.append(chefUrl);
        sb.append("/");
        sb.append(Integer.toString(id));
        String url = sb.toString();

        //Create new response listener
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                cbInterface.findChefByIdCb(parseGsonObject(response));
            }
        };

        // Create new response error listener
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Chef findById Error: " + error.getMessage());
            }
        };

        // Make JSON Object request
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                responseListener, errorListener);

        // Add request to (global) request queue
        FoodieApp.getInstance().addToRequestQueue(jsonObjReq, TAG);
    }

    public static void findAll(final IChefCallback cbInterface) {
        //Build url
        StringBuilder sb = new StringBuilder();
        sb.append(restApiBaseUrl);
        sb.append(chefUrl);
        String url = sb.toString();

        //Create new response listener
        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                cbInterface.findAllChefsCb(parseGsonArray(response));
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

    public static Chef parseGsonObject(JSONObject jsonObject){
        Chef chef = null;
        Gson gson = new Gson();

        //convert the json string back to object
        chef = gson.fromJson(jsonObject.toString(), Chef.class);
        Log.d(TAG, "Chef parseGsonObject: " + chef.toString());

        return chef;
    }

    public static ArrayList<Chef> parseGsonArray(JSONArray jsonArray){
        Gson gson = new Gson();

        Type listType = new TypeToken<List<Chef>>() {}.getType();
        ArrayList<Chef> chefs = (ArrayList<Chef>) gson.fromJson(jsonArray.toString(), listType);
        Log.d(TAG, "Chef parseGsonArray: size:" + chefs.size());

        return chefs;
    }
}