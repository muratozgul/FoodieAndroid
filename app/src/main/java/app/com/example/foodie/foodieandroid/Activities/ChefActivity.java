package app.com.example.foodie.foodieandroid.Activities;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.ChefAdapter;
import app.com.example.foodie.foodieandroid.Model.Chef;
import app.com.example.foodie.foodieandroid.R;

public class ChefActivity extends AppCompatActivity{
    private RecyclerView chefRV;
    private RecyclerView.Adapter chefAdapter;
    private RecyclerView.LayoutManager chefLayoutManager;
    private List<Chef> chefs;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chefs");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chefRV = (RecyclerView) findViewById(R.id.chefRecycler);
        chefRV.setHasFixedSize(true);

        chefLayoutManager = new LinearLayoutManager(this);
        chefRV.setLayoutManager(chefLayoutManager);

        addChef();

        chefAdapter = new ChefAdapter(chefs);
        chefRV.setAdapter(chefAdapter);
    }

    public void addChef(){
        chefs = new ArrayList<Chef>();
        List<String> tags1 = new ArrayList<String>();
        tags1.add("Vegetarian");
        List<String> tags2 = new ArrayList<String>();
        tags2.add("Cantonese");
        tags2.add("Dim Sum");

        Chef c1 = new Chef(1, "Chef Apple", "http://images.wisegeek.com/man-in-chef-uniform.jpg");
        c1.setNumOfDishes(10);
        c1.setRating(3.0f);
        c1.setTags(tags1);

        Chef c2 = new Chef(2, "Chef Banana", "http://i.huffpost.com/gen/1282658/images/o-FEMALE-CHEF-facebook.jpg");
        c2.setNumOfDishes(20);
        c2.setRating(4.0f);
        c2.setTags(tags2);

        Chef c3 = new Chef(3, "Chef Cherry", "http://images.wisegeek.com/linecook.jpg");
        c3.setNumOfDishes(30);
        c3.setRating(5.0f);
        c3.setTags(tags1);

        Chef c4 = new Chef(4, "Chef Date", "http://previews.123rf.com/images/kurhan/kurhan1201/kurhan120100042/11861111-Chef-man--Stock-Photo-chef-cook-happy.jpg");
        c4.setNumOfDishes(40);
        c4.setRating(3.5f);
        c4.setTags(tags2);

        Chef c5 = new Chef(5, "Chef Emily", "http://previews.123rf.com/images/kurhan/kurhan1304/kurhan130400322/19124470-Chef-woman--Stock-Photo-kitchen.jpg");
        c5.setNumOfDishes(60);
        c5.setRating(4.5f);
        c5.setTags(tags2);

        Chef c6 = new Chef(6, "Chef Fungus", "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRaWNDwVB_uw6wtNTXKagFAEpnG5KtPJzlQ6JdCKbqyTfN_Zsr4");
        c6.setNumOfDishes(60);
        c6.setRating(2.5f);
        c6.setTags(tags2);

        chefs.add(c1);
        chefs.add(c2);
        chefs.add(c3);
        chefs.add(c4);
        chefs.add(c5);
        chefs.add(c6);

    }

}
