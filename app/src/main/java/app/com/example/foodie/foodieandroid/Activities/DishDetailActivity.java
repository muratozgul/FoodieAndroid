package app.com.example.foodie.foodieandroid.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Model.Chef;
import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.Model.Review;
import app.com.example.foodie.foodieandroid.R;

public class DishDetailActivity extends AppCompatActivity {
    private Dish dish;
    private ImageView dishImage;
    private TextView dishPrice;
    private RatingBar ratingBar;
    private TextView dishTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addDish();
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void addDish(){
        List<String> tags = new ArrayList<String>();
        tags.add("Low fat");
        tags.add("American");
        dish = new Dish("Salmon Crispbread", 4.5f, 10.99, tags, "http://www.freefoodphotos.com/imagelibrary/seafood/thumbs/salmon_crispbread.jpg");
    }
}
