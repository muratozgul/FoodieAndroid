package app.com.example.foodie.foodieandroid.Activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.OrderAdapter;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;
import app.com.example.foodie.foodieandroid.R;

public class OrdersActivity extends AppCompatActivity {
    private static final String TAG = "OrdersActivity";
    private RecyclerView ordersRecyclerView;
    private RecyclerView.Adapter ordersAdapter;
    private RecyclerView.LayoutManager ordersLayoutManager;
    private List<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //in manifest file -> set android:parentActivityName

        ordersRecyclerView = (RecyclerView) findViewById(R.id.ordersRecyclerView);
        //enable optimizations if all item views are
        // of the same height and width for significantly smoother scrolling
        //ordersRecyclerView.setHasFixedSize(true);

        ordersLayoutManager = new LinearLayoutManager(this);
        ordersRecyclerView.setLayoutManager(ordersLayoutManager);

        //orders = new ArrayList<Order>();
        orders = fetchOrders();
        Log.v(TAG, "Fetched order: " + orders.toString());

        // specify an adapter
        Log.v(TAG, "Creating order adapter");
        ordersAdapter = new OrderAdapter(orders, this); //not getApplicationContext()
        ordersRecyclerView.setAdapter(ordersAdapter);
    }

    public List<Order> fetchOrders() {
        List<Order> orders = new ArrayList<Order>();

        Order order1 = new Order(1, 10d);
        order1.addOrderItem(new OrderItem(1,order1.getId(),1,2));
        order1.addOrderItem(new OrderItem(2,order1.getId(),2,1));
        order1.addOrderItem(new OrderItem(3,order1.getId(),5,3));

        orders.add(order1);

        return orders;
    }
}
