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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Fragments.FragmentChef;
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

        Intent dishInent = getIntent();
        int dish_id = dishInent.getIntExtra("dish_id", 1);

        // Get Dish Object
        findDish(dish_id);

        dishImage = (ImageView) findViewById(R.id.dishImage);
        dishPrice = (TextView) findViewById(R.id.dishPrice);
        ratingBar = (RatingBar) findViewById(R.id.dishRating);
        dishTags = (TextView) findViewById(R.id.dishTags);

        getSupportActionBar().setTitle(dish.getName());
        dishPrice.setText("$" + dish.getPrice());
        dishTags.setText(dish.getTags());
        ratingBar.setRating(dish.getRating());

        Uri dishUri = Uri.parse(dish.getDishImage());
        Picasso.with(this)
                .load(dishUri)
                .into(dishImage);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void findDish(int id){
        List<Dish> dishes = new ArrayList<Dish>();
        List<String> tags = new ArrayList<String>();
        tags.add("Chinese");
        tags.add("Vege");
        dishes.add(new Dish(1, "Mini Raspeberry Pavlovas", 4.5f, 3.99, tags, "http://www.freefoodphotos.com/imagelibrary/confectionery/thumbs/mini_raspeberry_pavlovas.jpg", 11));
        dishes.add(new Dish(2, "Cake", 4, 8.99, tags, "http://www.freefoodphotos.com/imagelibrary/cooking/thumbs/cake_making.jpg", 12));
        dishes.add(new Dish(3, "Strawberries", 3.5f, 4.99, tags, "http://www.freefoodphotos.com/imagelibrary/fruit/thumbs/three_strawberries.jpg", 13));
        dishes.add(new Dish(4, "Dessert Merigues", 3, 7.99, tags, "http://www.freefoodphotos.com/imagelibrary/confectionery/thumbs/dessert_meringues.jpg", 11));
        dishes.add(new Dish(5, "Bread", 2, 6.99, tags, "http://www.freefoodphotos.com/imagelibrary/bread/thumbs/bread.jpg", 12));
        dishes.add(new Dish(6, "Fresh Salmon", 1, 5.99, tags, "http://www.freefoodphotos.com/imagelibrary/seafood/thumbs/fresh_salmon_snack.jpg", 13));

        //TODO: CHECK EXCEPTION
        for(int i = 0; i < dishes.size(); i++){
            if(dishes.get(i).getDish_id() == id){
                dish = dishes.get(i);
            }
        }
    }
}
