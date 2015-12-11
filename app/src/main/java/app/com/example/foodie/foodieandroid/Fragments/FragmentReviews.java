package app.com.example.foodie.foodieandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.ReviewAdapter;
import app.com.example.foodie.foodieandroid.DAO.DishDAO;
import app.com.example.foodie.foodieandroid.DAO.IReviewCallback;
import app.com.example.foodie.foodieandroid.DAO.ReviewDAO;
import app.com.example.foodie.foodieandroid.Model.Review;
import app.com.example.foodie.foodieandroid.R;

public class FragmentReviews extends Fragment implements IReviewCallback {

    private List<Review> reviews = new ArrayList<Review>();
    private int dish_id;
    private int chef_id;
    // Chef review or dish review
    private String type;
    private RecyclerView reviewRV;
    private RecyclerView.Adapter reviewAdapter;
    private RecyclerView.LayoutManager reviewManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString("type");
        if(type == "dish") {
            dish_id = getArguments().getInt("dish_id");
            Log.e("REVIEWS: ", " DISH_ID: " + dish_id);
            fetchReviewsFromServer(dish_id, type);
        } else if(type == "chef"){
            chef_id = getArguments().getInt("chef_id");
            Log.e("REVIEWS: ", " CHEF_ID: " + chef_id);
            fetchReviewsFromServer(chef_id, type);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_fragment_reviews, container, false);

        reviewRV = (RecyclerView) view.findViewById(R.id.reviewRecycler);
        reviewRV.setHasFixedSize(false);
        reviewRV.setNestedScrollingEnabled(false);
        reviewRV.setMinimumHeight(1500);
        reviewRV.setFocusable(false);

        reviewManager = new LinearLayoutManager(view.getContext());
        reviewRV.setLayoutManager(reviewManager);
        reviewAdapter = new ReviewAdapter(reviews);
        reviewRV.setAdapter(reviewAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    public void fetchReviewsFromServer(int dish_id, String type){
        ReviewDAO.findById(dish_id, type, this);
    }

    //############################
    //IReviewCallback Interface Methods
    //############################

    @Override
    public void findReviewsByDishIdCb(ArrayList<Review> reviews) {
        this.reviews.addAll(reviews);
        this.reviewAdapter.notifyDataSetChanged();
        Log.e("REVIEWS: ", reviews.toString());
    }
}
