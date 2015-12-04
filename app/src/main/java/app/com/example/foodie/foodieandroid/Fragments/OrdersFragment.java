package app.com.example.foodie.foodieandroid.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Adapters.OrderAdapter;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.R;

public class OrdersFragment extends Fragment {
    private RecyclerView ordersRecyclerView;
    private RecyclerView.Adapter ordersAdapter;
    private RecyclerView.LayoutManager ordersLayoutManager;
    private List<Order> orders;

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

        ordersRecyclerView = (RecyclerView) view.findViewById(R.id.ordersRecyclerView);
        //ordersRecyclerView.setHasFixedSize(false);
        //ordersRecyclerView.setNestedScrollingEnabled(false);
        //ordersRecyclerView.setMinimumHeight(1500);

        ordersLayoutManager = new LinearLayoutManager(view.getContext());
        ordersRecyclerView.setLayoutManager(ordersLayoutManager);

        orders = new ArrayList<Order>();

        // specify an adapter
        ordersAdapter = new OrderAdapter(orders);
        ordersRecyclerView.setAdapter(ordersAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
}