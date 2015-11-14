package app.com.example.foodie.foodieandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.com.example.foodie.foodieandroid.R;

public class DishMenuActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.imageView6).setOnClickListener(this);
        findViewById(R.id.imageView7).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView6:
                Intent dishDetailActivity1 = new Intent(this, DishDetailActivity.class);
                startActivity(dishDetailActivity1);
                break;
            case R.id.imageView7:
                Intent dishDetailActivity2 = new Intent(this, DishDetailActivity.class);
                startActivity(dishDetailActivity2);
                break;
            // ...
        }
    }

}
