package app.com.example.foodie.foodieandroid.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Application.FoodieApp;
import app.com.example.foodie.foodieandroid.DAO.ChefDAO;
import app.com.example.foodie.foodieandroid.DAO.DishDAO;
import app.com.example.foodie.foodieandroid.DAO.IDishCallback;
import app.com.example.foodie.foodieandroid.Fragments.FragmentChef;
import app.com.example.foodie.foodieandroid.Fragments.FragmentReviews;
import app.com.example.foodie.foodieandroid.Model.Chef;
import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.Model.Review;
import app.com.example.foodie.foodieandroid.R;

public class DishDetailActivity extends AppCompatActivity implements IDishCallback{
    private static String TAG = "DISH_DETAIL";
    private Dish dish = new Dish();
    private Chef chef = new Chef();
    private ImageView dishImage;
    private TextView dishPrice;
    private RatingBar ratingBar;
    private TextView dishTags;
    private TextView checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent dishInent = getIntent();
        int dish_id = dishInent.getIntExtra("dish_id", 1);

        // Get Dish Object
        //findDish(dish_id);
        fetchDishFromServer(dish_id);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void updateUI(){
        getSupportActionBar().setTitle(dish.getName());

        dishImage = (ImageView) findViewById(R.id.dishImage);
        dishPrice = (TextView) findViewById(R.id.dishPrice);
        ratingBar = (RatingBar) findViewById(R.id.dishRating);
        dishTags = (TextView) findViewById(R.id.dishTags);
        dishPrice.setText("$" + dish.getPrice());
        dishTags.setText(dish.getTags());
        ratingBar.setRating(dish.getRating());
        Uri dishUri = Uri.parse(dish.getDishImage());
        Picasso.with(this).load(dishUri).into(dishImage);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment chef_fragment = FragmentChef.newInstance(chef);
        ft.add(R.id.fragment_container, chef_fragment, "CHEF");

        Bundle bundle = new Bundle();
        bundle.putString("type", "dish");
        bundle.putInt("dish_id", dish.getDish_id());
        Fragment review_fragment = new FragmentReviews();
        review_fragment.setArguments(bundle);
        ft.add(R.id.fragment_container, review_fragment, "REVIEWS");
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checkout, menu);

        final View checkout = menu.findItem(R.id.checkout).getActionView();
        checkoutButton = (TextView) checkout.findViewById(R.id.cart_qty);

        checkoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(checkout.getContext(), CheckoutActivity.class);
                checkout.getContext().startActivity(intent);
                int cartSize = FoodieApp.getInstance().getCart().size();
                checkoutButton.setText(Integer.toString(cartSize));
            }
        });

        return super.onCreateOptionsMenu(menu);
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

    public void fetchDishFromServer(int id) {
        DishDAO.findById(id, this);
    }

    //############################
    //IChefCallback Interface Methods
    //############################

    @Override
    public void findDishByIdCb(Dish dish) {
        this.dish = dish;
        this.chef = dish.getChef();
        updateUI();
    }

    @Override
    public void findDishByIdCb(String responseString) {

    }

    @Override
    public void findAllDishesCb(ArrayList<Dish> dishes) {

    }

    @Override
    public void findAllDishesCb(String responseString) {

    }

    @Override
    public void findDishesByCategory(ArrayList<Dish> dishes) {

    }
}
