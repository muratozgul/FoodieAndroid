package app.com.example.foodie.foodieandroid.Activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.OrderItemAdapter;
import app.com.example.foodie.foodieandroid.Application.FoodieApp;
import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;
import app.com.example.foodie.foodieandroid.R;

public class CheckoutActivity extends AppCompatActivity {
    private static final String TAG = "CheckoutActivity";
    private RecyclerView cartItemsRecyclerView;
    private RecyclerView.Adapter cartItemsAdapter;
    private RecyclerView.LayoutManager cartItemsLayoutManager;
    private List<OrderItem> orderItems;

    private Button placeOrderButton;
    private TextView itemCountView;
    private TextView totalPriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //in manifest file -> set android:parentActivityName

        //intent data for order
        //Intent intent = getIntent();
        orderItems = new ArrayList<OrderItem>();

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
        cartItemsAdapter = new OrderItemAdapter(orderItems, this);
        cartItemsRecyclerView.setAdapter(cartItemsAdapter);

    }

    @Override
    protected void onStart(){
        super.onStart();
        rePopulateAdapter();
        itemCountView.setText("Items in cart: " + FoodieApp.getInstance().getCart().size());
        totalPriceView.setText("Total price: $" + Double.toString(FoodieApp.getInstance().getCart().getTotalCost()));
    }

    protected void rePopulateAdapter(){
        this.orderItems = FoodieApp.getInstance().getCart().getItemsAsList();
        this.cartItemsAdapter.notifyDataSetChanged();
    }
}
