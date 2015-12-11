package app.com.example.foodie.foodieandroid.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private String TAG = "CHEF_FRAGMENT";
    private static final String KEY = "chef";
    int dish_id;
    int chef_id;
    private Chef chef = new Chef();
    private ImageView chefImg;
    private TextView chefName;
    private TextView chefMealsServed;

    private FragmentActivity listener;

    public FragmentChef() {
        // Required empty public constructor
    }

    public static FragmentChef newInstance(Chef chef) {
        FragmentChef fragment = new FragmentChef();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, chef);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        chef = (Chef) bundle.getSerializable(KEY);

        Log.e(TAG, chef.toString());
        dish_id = getActivity().getIntent().getIntExtra("dish_id", 23);
        chef_id = getActivity().getIntent().getIntExtra("chef_id", 23);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_chef, container, false);
        chefImg = (ImageView) view.findViewById(R.id.chefImg);
        chefName = (TextView) view.findViewById(R.id.chefName);
        chefMealsServed = (TextView) view.findViewById(R.id.chefMealsServed);

        //getChef(dish_id);

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
            chef = new Chef(1, "Chef Apple", "http://images.wisegeek.com/man-in-chef-uniform.jpg");
            chef.setNumOfDishes(10);
        }
        if(dish_id == 2) {
            chef = new Chef(2, "Chef Banana", "http://i.huffpost.com/gen/1282658/images/o-FEMALE-CHEF-facebook.jpg");
            chef.setNumOfDishes(20);
        }
        if(dish_id == 3) {
            chef = new Chef(3, "Chef Cherry", "http://images.wisegeek.com/linecook.jpg");
            chef.setNumOfDishes(30);
        }
        if(dish_id == 4) {
            chef = new Chef(4, "Chef Date", "http://previews.123rf.com/images/kurhan/kurhan1201/kurhan120100042/11861111-Chef-man--Stock-Photo-chef-cook-happy.jpg");
            chef.setNumOfDishes(40);
        }
        if(dish_id == 5) {
            chef = new Chef(5, "Chef Emily", "http://www.clipartbest.com/cliparts/pc5/oyE/pc5oyELxi.jpeg");
            chef.setNumOfDishes(50);
        }
        if(dish_id == 6) {
            chef = new Chef(6, "Chef Fungus", "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRaWNDwVB_uw6wtNTXKagFAEpnG5KtPJzlQ6JdCKbqyTfN_Zsr4");
            chef.setNumOfDishes(60);
            chef.setRating(2.5f);

        }
        // Chef ID is empty
        else if(dish_id == 23){
            chef = new Chef(1, "Chef Nobody", "http://vignette4.wikia.nocookie.net/icarly/images/3/37/Sad-face.png/revision/latest?cb=20120729221450");
            chef.setNumOfDishes(0);
            Log.e("Fragment Chef", "chef_id doesn't exist");
        }
    }

}
