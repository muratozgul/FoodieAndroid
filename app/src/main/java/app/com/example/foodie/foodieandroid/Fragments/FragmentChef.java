package app.com.example.foodie.foodieandroid.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_chef, container, false);
        chefImg = (ImageView) view.findViewById(R.id.chefImg);
        chefName = (TextView) view.findViewById(R.id.chefName);
        chefMealsServed = (TextView) view.findViewById(R.id.chefMealsServed);

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
}
