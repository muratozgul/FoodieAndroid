package app.com.example.foodie.foodieandroid.Activities;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import app.com.example.foodie.foodieandroid.Model.Chef;
import app.com.example.foodie.foodieandroid.Model.Image;
import app.com.example.foodie.foodieandroid.R;

public class FragmentChef extends Fragment {
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_chef, container, false);
        chefImg = (ImageView) view.findViewById(R.id.chefImg);
        chefName = (TextView) view.findViewById(R.id.chefName);
        chefMealsServed = (TextView) view.findViewById(R.id.chefMealsServed);

        addChef();

        chefImg.setImageResource(R.drawable.chef);
        chefName.setText(chef.getName());
        chefMealsServed.setText(chef.getNumOfDishes() + " meals served");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    public void addChef(){
        chef = new Chef();
        chef.setName("Chef Lee");
        chef.setNumOfDishes(30);
    }

}
