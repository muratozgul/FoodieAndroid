package app.com.example.foodie.foodieandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import app.com.example.foodie.foodieandroid.R;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.cat_chinese).setOnClickListener(this);
        findViewById(R.id.cat_vegetarian).setOnClickListener(this);
        findViewById(R.id.cat_desserts).setOnClickListener(this);
        findViewById(R.id.cat_meatlover).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent dishMenuActivity = new Intent(this, DishMenuActivity.class);
        dishMenuActivity.putExtra("source", "category");
        switch (v.getId()) {
            case R.id.cat_chinese:
                dishMenuActivity.putExtra("category", 1);
                startActivity(dishMenuActivity);
                break;
            case R.id.cat_vegetarian:
                dishMenuActivity.putExtra("category", 2);
                startActivity(dishMenuActivity);
                break;
            case R.id.cat_desserts:
                dishMenuActivity.putExtra("category", 3);
                startActivity(dishMenuActivity);
                break;
            case R.id.cat_meatlover:
                dishMenuActivity.putExtra("category", 4);
                startActivity(dishMenuActivity);
                break;
            default:
                Toast.makeText(this, getResources().getResourceEntryName(v.getId()) + " clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
