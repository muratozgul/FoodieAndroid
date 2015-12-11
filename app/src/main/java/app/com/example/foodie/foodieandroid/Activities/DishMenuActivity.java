package app.com.example.foodie.foodieandroid.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.DishMenuAdapter;
import app.com.example.foodie.foodieandroid.Application.FoodieApp;
import app.com.example.foodie.foodieandroid.DAO.DishDAO;
import app.com.example.foodie.foodieandroid.DAO.IDishCallback;
import app.com.example.foodie.foodieandroid.Model.Chef;
import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.R;

/**
 * Created by Jennifer on 11/11/15.
 */

public class DishMenuActivity extends AppCompatActivity implements IDishCallback {
    private RecyclerView dishMenuRV;
    private RecyclerView.Adapter dishMenuAdapter;
    private RecyclerView.LayoutManager dishMenuLayoutManager;
    private List<Dish> dishes = new ArrayList<Dish>();
    private TextView checkoutButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu");

        context = this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dishMenuRV = (RecyclerView) findViewById(R.id.dishRecycler);
        dishMenuRV.setHasFixedSize(true);
        dishMenuLayoutManager = new LinearLayoutManager(this);
        dishMenuRV.setLayoutManager(dishMenuLayoutManager);
        dishMenuAdapter = new DishMenuAdapter(dishes, this);
        dishMenuRV.setAdapter(dishMenuAdapter);

        fetchDishesFromServer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checkout, menu);

        View checkout = menu.findItem(R.id.checkout).getActionView();
        checkoutButton = (TextView) checkout.findViewById(R.id.cart_qty);

        int cartSize = FoodieApp.getInstance().getCart().size();
        checkoutButton.setText(Integer.toString(cartSize));

        checkoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CheckoutActivity.class);
                context.startActivity(intent);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void updateCartIcon(){
        checkoutButton.setText(Integer.toString(FoodieApp.getInstance().getCart().size()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.checkout) {
            Intent checkoutIntent = new Intent(this, CheckoutActivity.class);
            startActivity(checkoutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fetchDishesFromServer() {
        DishDAO.findAll(this);
    }

    //############################
    //IDishCallback Interface Methods
    //############################

    @Override
    public void findDishByIdCb(Dish dish) {
        this.dishes.add(dish);
        this.dishMenuAdapter.notifyDataSetChanged();
    }

    @Override
    public void findDishByIdCb(String responseString) {
        Toast.makeText(this, responseString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void findAllDishesCb(ArrayList<Dish> dishes) {
        this.dishes.addAll(dishes);
        this.dishMenuAdapter.notifyDataSetChanged();
    }

    @Override
    public void findAllDishesCb(String responseString) {
        Toast.makeText(this, responseString, Toast.LENGTH_SHORT).show();
    }
}
