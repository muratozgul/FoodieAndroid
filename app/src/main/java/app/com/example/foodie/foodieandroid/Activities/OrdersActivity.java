package app.com.example.foodie.foodieandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.DishMenuAdapter;
import app.com.example.foodie.foodieandroid.Adapters.OrderAdapter;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.R;

public class OrdersActivity extends AppCompatActivity {
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

        // specify an adapter
        ordersAdapter = new OrderAdapter(orders);
        ordersRecyclerView.setAdapter(ordersAdapter);
    }

//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.orderCard:
//                Toast.makeText(getApplicationContext(), "Order Card Clicked", Toast.LENGTH_LONG).show();
//                break;
//        }
//    }

    public List<Order> fetchOrders() {
        List<Order> orders = new ArrayList<Order>();

        orders.add(new Order(1, 10d));
        orders.add(new Order(2, 15d));
        orders.add(new Order(3, 12d));
        orders.add(new Order(4, 18d));
        orders.add(new Order(5, 22));

        return orders;
    }
}
