package app.com.example.foodie.foodieandroid.Application;

import android.app.Application;
import android.content.res.Configuration;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import app.com.example.foodie.foodieandroid.Helpers.ShoppingCart;

/**
 * Created by muratozgul on 06/12/15.
 */
public class FoodieApp extends Application {
    public static final String TAG = "FoodieApp";
    private static FoodieApp singleton;
    private static RequestQueue mRequestQueue;

    //private static final String restApiUrl = "http://ec2-52-90-154-59.compute-1.amazonaws.com:8080";
    private static final String restApiUrl = "http://54.67.14.75:8080/foodie/";
    //############################
    //Getters
    //############################

    public static synchronized FoodieApp getInstance(){
        return singleton;
    }

    public static ShoppingCart getCart(){
        return ShoppingCart.getInstance();
    }

    public static String getApiUrl() {
        return restApiUrl;
    }

    //############################
    //Override
    //############################

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    //############################
    //Volley methods
    // http://www.androidhive.info/2014/09/android-json-parsing-using-volley/
    //############################

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // tag is used for cancelling (a group of reqs) if needed
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
