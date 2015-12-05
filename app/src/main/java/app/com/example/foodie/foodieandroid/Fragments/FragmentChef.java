package app.com.example.foodie.foodieandroid.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.com.example.foodie.foodieandroid.Activities.ChefDetailActivity;
import app.com.example.foodie.foodieandroid.Model.Chef;
import app.com.example.foodie.foodieandroid.R;

public class FragmentChef extends Fragment {
    int dish_id;
    private Chef chef;
    private ImageView chefImg;
    private TextView chefName;
    private TextView chefMealsServed;

    private FragmentActivity listener;

    public FragmentChef() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dish_id = getActivity().getIntent().getIntExtra("dish_id", 23);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_chef, container, false);
        chefImg = (ImageView) view.findViewById(R.id.chefImg);
        chefName = (TextView) view.findViewById(R.id.chefName);
        chefMealsServed = (TextView) view.findViewById(R.id.chefMealsServed);


        getChef(dish_id);

        Uri imgUri = Uri.parse(chef.getProfile_img());
        Picasso.with(getActivity()).load(imgUri).into(chefImg);

        chefName.setText(chef.getName());
        chefMealsServed.setText(chef.getNumOfDishes() + " meals served");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chefIntent = new Intent(v.getContext(), ChefDetailActivity.class);
                chefIntent.putExtra("chef_id", chef.getUser_id());
                v.getContext().startActivity(chefIntent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    public void getChef(int dish_id){
        if(dish_id == 1) {
            chef = new Chef(1, "Chef 1", "http://images.clipartpanda.com/pizza-clip-art-pizza-chef-food-pizza-chef-clipart-83865965.jpg");
            chef.setNumOfDishes(10);
        }
        if(dish_id == 2) {
            chef = new Chef(1, "Chef 2", "http://images.clipartpanda.com/pizza-clip-art-pizza-chef-food-pizza-chef-clipart-83865965.jpg");
            chef.setNumOfDishes(20);
        }
        if(dish_id == 3) {
            chef = new Chef(1, "Chef 3", "http://images.clipartpanda.com/pizza-clip-art-pizza-chef-food-pizza-chef-clipart-83865965.jpg");
            chef.setNumOfDishes(30);
        }
        if(dish_id == 4) {
            chef = new Chef(1, "Chef 4", "http://images.clipartpanda.com/pizza-clip-art-pizza-chef-food-pizza-chef-clipart-83865965.jpg");
            chef.setNumOfDishes(40);
        }
        if(dish_id == 5) {
            chef = new Chef(1, "Chef 5", "http://images.clipartpanda.com/pizza-clip-art-pizza-chef-food-pizza-chef-clipart-83865965.jpg");
            chef.setNumOfDishes(50);
        }
        if(dish_id == 6) {
            chef = new Chef(1, "Chef 6", "http://images.clipartpanda.com/pizza-clip-art-pizza-chef-food-pizza-chef-clipart-83865965.jpg");
            chef.setNumOfDishes(60);
        }
        // Chef ID is empty
        else if(dish_id == 23){
            chef = new Chef(1, "Chef Nobody", "http://vignette4.wikia.nocookie.net/icarly/images/3/37/Sad-face.png/revision/latest?cb=20120729221450");
            chef.setNumOfDishes(0);
            Log.e("Fragment Chef", "chef_id doesn't exist");
        }
    }

}
