package app.com.example.foodie.foodieandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.ReviewAdapter;
import app.com.example.foodie.foodieandroid.Model.Review;
import app.com.example.foodie.foodieandroid.R;

public class FragmentReviews extends Fragment {

    private List<Review> reviews;

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

        addReviews();

        // specify an adapter (see also next example)
        reviewAdapter = new ReviewAdapter(reviews);
        reviewRV.setAdapter(reviewAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }


    public void addReviews(){
        reviews = new ArrayList<Review>();

        reviews.add(new Review(1, 4.5f, "This is really really delicious 1", "03/05/2015", 1, 1));
        reviews.add(new Review(2, 4.0f, "This is really really delicious 2", "03/05/2015", 2, 2));
        reviews.add(new Review(3, 3.5f, "This is really really delicious 3", "03/05/2015", 1, 3));
        reviews.add(new Review(4, 2.5f, "This is really really delicious 4", "03/05/2015", 2, 4));
        reviews.add(new Review(5, 1.0f, "This is really really delicious 5", "03/05/2015", 1, 5));
        reviews.add(new Review(6, 5.0f, "This is really really delicious 6", "03/05/2015", 2, 6));
    }
}
