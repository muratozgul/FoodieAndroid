package app.com.example.foodie.foodieandroid.Activities;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.OrderAdapter;
import app.com.example.foodie.foodieandroid.DAO.IOrderCallback;
import app.com.example.foodie.foodieandroid.DAO.OrderDAO;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.R;

public class OrdersActivity extends AppCompatActivity implements IOrderCallback {

    private static final String TAG = "OrdersActivity";
    private RecyclerView ordersRecyclerView;
    private RecyclerView.Adapter ordersAdapter;
    private RecyclerView.LayoutManager ordersLayoutManager;
    private LinearLayout progressBarWrapper;

    private List<Order> orders;

    //############################
    //IOrderCallback Interface Methods
    //############################

    @Override
    public void findOrderByIdCb(Order order) {
        this.orders.add(order);
        // To dismiss the dialog
        progressBarWrapper.setVisibility(View.GONE);
        this.ordersAdapter.notifyDataSetChanged();
    }

    @Override
    public void findOrderByIdCb(String responseString) {
        Toast.makeText(this, responseString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void findAllOrdersCb(ArrayList<Order> orders) {
        this.orders.addAll(orders);
        // To dismiss the dialog
        progressBarWrapper.setVisibility(View.GONE);
        this.ordersAdapter.notifyDataSetChanged();
    }

    @Override
    public void findAllOrdersCb(String responseString) {
        Toast.makeText(this, responseString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void createOrderCb(String responseString) {

    }

    //############################
    //AppCompatActivity methods
    //############################

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_orders);

        //initialize orders to empty list
        orders = new ArrayList<Order>();
        //orders = fetchOrders();

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //in manifest file -> set android:parentActivityName
        getSupportActionBar().setTitle("Order History");

        ordersRecyclerView = (RecyclerView) findViewById(R.id.ordersRecyclerView);
        //enable optimizations if all item views are
        // of the same height and width for significantly smoother scrolling
        //ordersRecyclerView.setHasFixedSize(true);

        ordersLayoutManager = new LinearLayoutManager(this);
        ordersRecyclerView.setLayoutManager(ordersLayoutManager);

        // specify an adapter
        Log.v(TAG, "Creating order adapter");
        ordersAdapter = new OrderAdapter(orders, this); //not getApplicationContext()
        ordersRecyclerView.setAdapter(ordersAdapter);

        progressBarWrapper = (LinearLayout) findViewById(R.id.progressBarWrapper);
        progressBarWrapper.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart(){
        super.onStart();
        // after everything is loaded on the screen,
        // get the data and add it via adapter
        //OrderDAO.findAll(this);
        //OrderDAO.pFindById(1, this);
        OrderDAO.pFindAll(this);
    }

    //############################
    //Other methods
    //############################

}
