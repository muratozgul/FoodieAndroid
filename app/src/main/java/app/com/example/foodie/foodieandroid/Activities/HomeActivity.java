package app.com.example.foodie.foodieandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import app.com.example.foodie.foodieandroid.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.chef_list).setOnClickListener(this);
        findViewById(R.id.dish_list).setOnClickListener(this);
        findViewById(R.id.categories).setOnClickListener(this);
        findViewById(R.id.location_map).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chef_list:
                Intent chefActivity = new Intent(this, ChefActivity.class);
                startActivity(chefActivity);
                break;
            case R.id.dish_list:
                Intent dishMenuActivity = new Intent(this, DishMenuActivity.class);
                startActivity(dishMenuActivity);
                break;
            case R.id.categories:
                Intent categoryActivity = new Intent(this, CategoryActivity.class);
                startActivity(categoryActivity);
                break;
            case R.id.location_map:
                Intent locationMapActivity = new Intent(this, MapsActivity.class);
                startActivity(locationMapActivity);
                break;
            // ...
        }
    }
}
