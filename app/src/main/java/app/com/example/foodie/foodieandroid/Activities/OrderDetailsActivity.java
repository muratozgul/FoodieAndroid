package app.com.example.foodie.foodieandroid.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.OrderItemAdapter;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;
import app.com.example.foodie.foodieandroid.R;

public class OrderDetailsActivity extends AppCompatActivity {
    private static final String TAG = "OrderDetailsActivity";
    private RecyclerView orderItemsRecyclerView;
    private RecyclerView.Adapter orderItemsAdapter;
    private RecyclerView.LayoutManager orderItemsLayoutManager;
    private int orderId;
    private Order order;
    private List<OrderItem> orderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        //intent data for order
        Intent intent = getIntent();
        order = (Order) intent.getParcelableExtra("order");
        orderItems = order.getOrderItems();


        //order = fetchOrder();
        //orderItems = fetchOrderItems();

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //in manifest file -> set android:parentActivityName

        orderItemsRecyclerView = (RecyclerView) findViewById(R.id.ordersItemsRecyclerView);
        //enable optimizations if all item views are
        // of the same height and width for significantly smoother scrolling
        //ordersRecyclerView.setHasFixedSize(true);

        orderItemsLayoutManager = new LinearLayoutManager(this);
        orderItemsRecyclerView.setLayoutManager(orderItemsLayoutManager);

        // specify an adapter
        orderItemsAdapter = new OrderItemAdapter(orderItems, this);
        orderItemsRecyclerView.setAdapter(orderItemsAdapter);
    }

    public Order fetchOrder() {
        return new Order(this.orderId, 10d);
    }

    public List<OrderItem> fetchOrderItems() {
        List<OrderItem> orderItems = new ArrayList<OrderItem>();

        orderItems.add(new OrderItem(1, this.orderId, 1, 2));
        orderItems.add(new OrderItem(2, this.orderId, 5, 1));

        return orderItems;
    }
}
