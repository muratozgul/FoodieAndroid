package app.com.example.foodie.foodieandroid.Activities;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.OrderItemAdapter;
import app.com.example.foodie.foodieandroid.Adapters.ShoppingCartAdapter;
import app.com.example.foodie.foodieandroid.Application.FoodieApp;
import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;
import app.com.example.foodie.foodieandroid.R;
import app.com.example.foodie.foodieandroid.Sensors.ShakeDetector;

public class CheckoutActivity extends AppCompatActivity {
    private static final String TAG = "CheckoutActivity";
    private RecyclerView cartItemsRecyclerView;
    //private RecyclerView.Adapter cartItemsAdapter;
    private ShoppingCartAdapter cartItemsAdapter;
    private RecyclerView.LayoutManager cartItemsLayoutManager;
    private List<OrderItem> orderItems;

    private Button placeOrderButton;
    private TextView itemCountView;
    private TextView totalPriceView;
    private Toast currentToast;

    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                handleShakeEvent(count);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Checkout");
        //in manifest file -> set android:parentActivityName

        //intent data for order
        //Intent intent = getIntent();
        orderItems = FoodieApp.getCart().getItemsAsList();

        placeOrderButton = (Button) findViewById(R.id.placeOrderButton);
        itemCountView = (TextView) findViewById(R.id.cartItemCount);
        totalPriceView = (TextView) findViewById(R.id.cartTotalCost);

        cartItemsRecyclerView = (RecyclerView) findViewById(R.id.cartItemsRecyclerView);
        //enable optimizations if all item views are
        // of the same height and width for significantly smoother scrolling
        //ordersRecyclerView.setHasFixedSize(true);

        cartItemsLayoutManager = new LinearLayoutManager(this);
        cartItemsRecyclerView.setLayoutManager(cartItemsLayoutManager);

        // specify an adapter
        cartItemsAdapter = new ShoppingCartAdapter(orderItems, this);
        cartItemsRecyclerView.setAdapter(cartItemsAdapter);

    }

    @Override
    protected void onStart(){
        super.onStart();
        rePopulateAdapter();
        refreshSummary();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    public void rePopulateAdapter(){
        this.cartItemsAdapter.setOrderItems(FoodieApp.getCart().getItemsAsList());
        this.cartItemsAdapter.notifyDataSetChanged();
    }

    public void refreshSummary(){
        itemCountView.setText("Items in cart: " + FoodieApp.getCart().size());
        NumberFormat formatter = new DecimalFormat("#0.00");
        String priceString = formatter.format(FoodieApp.getCart().getTotalCost());
        totalPriceView.setText("Total price: $" + priceString);
    }

    //############################
    //ShakeDetector  methods
    //############################

    public void handleShakeEvent(int count){
        int threshold = 4;

        if(count < threshold) {
            String message = "Shake " + Integer.toString(threshold - count) + " more times to reset the cart";
            showToast(message);

        } else if(count == threshold) {
            showToast("Cart contents cleared!");
            FoodieApp.getInstance().getCart().empty();
            rePopulateAdapter();
            refreshSummary();
        } else if(count > threshold){
            showToast("Cart contents already cleared!");
        }
    }

    void showToast(String text) {
        if(currentToast == null) {
            currentToast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        }
        currentToast.setText(text);
        currentToast.setDuration(Toast.LENGTH_LONG);
        currentToast.show();
    }

    void cancelToast() {
        if(currentToast != null) {
            currentToast.cancel();
        }
    }

}
