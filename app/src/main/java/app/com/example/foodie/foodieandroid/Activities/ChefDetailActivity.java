package app.com.example.foodie.foodieandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.ChefAdapter;
import app.com.example.foodie.foodieandroid.Adapters.DishMenuAdapter;
import app.com.example.foodie.foodieandroid.DAO.ChefDAO;
import app.com.example.foodie.foodieandroid.DAO.DishDAO;
import app.com.example.foodie.foodieandroid.DAO.IChefCallback;
import app.com.example.foodie.foodieandroid.Fragments.FragmentChef;
import app.com.example.foodie.foodieandroid.Fragments.FragmentReviews;
import app.com.example.foodie.foodieandroid.Model.Chef;
import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.R;

public class ChefDetailActivity extends AppCompatActivity implements IChefCallback {
    private String TAG = "CHEF_DETAIL";
    private Chef chef = new Chef();
    private List<Dish> chefDishes = new ArrayList<Dish>();
    private RecyclerView chefDishRV;
    private RecyclerView.Adapter chefDishAdapter;
    private RecyclerView.LayoutManager chefDishLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent dishIntent = getIntent();
        int chef_id = dishIntent.getIntExtra("chef_id", 1);
        Log.e("ChefDetaiActivity", "CHEF_ID: " + Integer.toString(chef_id));

        //findChef(chef_id);
        //addDish();

        chefDishRV = (RecyclerView) findViewById(R.id.chefDishRecycler);
        //chefDishRV.setHasFixedSize(true);

        chefDishLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        chefDishRV.setLayoutManager(chefDishLayoutManager);

        chefDishAdapter = new DishMenuAdapter(chefDishes);
        chefDishRV.setAdapter(chefDishAdapter);

        fetchChefFromServer(chef_id);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void updateUI(){

        getSupportActionBar().setTitle(chef.getName());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = FragmentChef.newInstance(chef);
        ft.add(R.id.chef_fragment_container, fragment, "CHEF");

        Bundle bundle = new Bundle();
        bundle.putString("type", "chef");
        bundle.putInt("chef_id", chef.getUser_id());
        Fragment review_fragment = new FragmentReviews();
        review_fragment.setArguments(bundle);
        ft.add(R.id.review_fragment_container, review_fragment, "REVIEWS");
        ft.commit();
    }

    public void fetchChefFromServer(int id) {
        ChefDAO.findById(id, this);
    }

    //############################
    //IChefCallback Interface Methods
    //############################

    @Override
    public void findChefByIdCb(Chef chef) {
        this.chef = chef;
        if (chef != null && chef.getDishes() != null) {
            this.chefDishes.addAll(chef.getDishes());
        }
        updateUI();
        this.chefDishAdapter.notifyDataSetChanged();
    }

    @Override
    public void findChefByIdCb(String responseString) {
        Toast.makeText(this, responseString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void findAllChefsCb(ArrayList<Chef> chefs) {

    }

    @Override
    public void findAllChefsCb(String responseString) {

    }

    public void findChef(int id){
        List<Chef> chefs = new ArrayList<Chef>();
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

        Chef c5 = new Chef(5, "Chef Emily", "http://www.clipartbest.com/cliparts/pc5/oyE/pc5oyELxi.jpeg");
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

        //TODO: CHECK EXCEPTION
        for(int i = 0; i < chefs.size(); i++){
            if(chefs.get(i).getUser_id() == id){
                chef = chefs.get(i);
            }
        }
    }

//    public void addDish(){
//        chefDishes = new ArrayList<Dish>();
//        List<String> tags = new ArrayList<String>();
//        tags.add("Chinese");
//        tags.add("Vege");
//        chefDishes.add(new Dish(1, "Mini Raspeberry Pavlovas", 4.5f, 3.99, tags, "http://www.freefoodphotos.com/imagelibrary/confectionery/thumbs/mini_raspeberry_pavlovas.jpg", 11));
//        chefDishes.add(new Dish(2, "Cake", 4, 8.99, tags, "http://www.freefoodphotos.com/imagelibrary/cooking/thumbs/cake_making.jpg",12));
//        chefDishes.add(new Dish(6, "Fresh Salmon", 1, 5.99, tags, "http://www.freefoodphotos.com/imagelibrary/seafood/thumbs/fresh_salmon_snack.jpg", 13));
//    }

}
