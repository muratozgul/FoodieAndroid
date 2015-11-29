package app.com.example.foodie.foodieandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.com.example.foodie.foodieandroid.Model.Chef;
import app.com.example.foodie.foodieandroid.R;

public class ChefActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.chef_zhang).setOnClickListener(this);
        findViewById(R.id.chef_liu).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chef_zhang:
                Intent chefDetailActivity1 = new Intent(this, ChefDetailActivity.class);
                startActivity(chefDetailActivity1);
                break;
            case R.id.chef_liu:
                Intent chefDetailActivity2 = new Intent(this, ChefDetailActivity.class);
                startActivity(chefDetailActivity2);
                break;
            // ...
        }
    }

}
