package app.com.example.foodie.foodieandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.DishMenuAdapter;
import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.R;
/**
 * Created by Jennifer on 11/11/15.
 */
public class DishMenuActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView dishMenuRV;
    private RecyclerView.Adapter dishMenuAdapter;
    private RecyclerView.LayoutManager dishMenuLayoutManager;
    private List<Dish> dishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dishMenuRV = (RecyclerView)findViewById(R.id.dishRecycler);
        dishMenuRV.setHasFixedSize(true);

        // use a linear layout manager
        dishMenuLayoutManager = new LinearLayoutManager(this);
        dishMenuRV.setLayoutManager(dishMenuLayoutManager);

        addDish();

        // specify an adapter (see also next example)
        dishMenuAdapter = new DishMenuAdapter(dishes);
        dishMenuRV.setAdapter(dishMenuAdapter);

        //findViewById(R.id.dishImage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dishImage:
                Intent dishDetailActivity1 = new Intent(this, DishDetailActivity.class);
                startActivity(dishDetailActivity1);
                break;
        }
    }

    public void addDish(){
        dishes = new ArrayList<Dish>();
        List<String> tags = new ArrayList<String>();
        tags.add("Chinese");
        tags.add("Vege");
        dishes.add(new Dish("Mini Raspeberry Pavlovas", 4.5f, 3.99, tags, "http://www.freefoodphotos.com/imagelibrary/confectionery/thumbs/mini_raspeberry_pavlovas.jpg"));
        dishes.add(new Dish("Cake", 4, 8.99, tags, "http://www.freefoodphotos.com/imagelibrary/cooking/thumbs/cake_making.jpg"));
        dishes.add(new Dish("Strawberries", 3.5f, 4.99, tags, "http://www.freefoodphotos.com/imagelibrary/fruit/thumbs/three_strawberries.jpg"));
        dishes.add(new Dish("Dessert Merigues", 3, 7.99, tags, "http://www.freefoodphotos.com/imagelibrary/confectionery/thumbs/dessert_meringues.jpg"));
        dishes.add(new Dish("Bread", 2, 6.99, tags, "http://www.freefoodphotos.com/imagelibrary/bread/thumbs/bread.jpg"));
        dishes.add(new Dish("Fresh Salmon", 1, 5.99, tags, "http://www.freefoodphotos.com/imagelibrary/seafood/thumbs/fresh_salmon_snack.jpg"));
    }
}
