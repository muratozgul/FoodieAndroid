package app.com.example.foodie.foodieandroid.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.com.example.foodie.foodieandroid.Activities.DishDetailActivity;
import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.R;

/**
 * Created by Jennifer on 11/29/15.
 */

public class DishMenuAdapter extends RecyclerView.Adapter<DishMenuAdapter.DishViewHolder> {
    private List<Dish> dishes;

    public static class DishViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public ImageView dishImage;
        public TextView dishName;
        public TextView dishPrice;
        public RatingBar dishRating;
        public TextView dishTags;

        public DishViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.dishCard);
            dishImage = (ImageView) itemView.findViewById(R.id.dishImage);
            dishName = (TextView) itemView.findViewById(R.id.dishName);
            dishPrice = (TextView) itemView.findViewById(R.id.dishPrice);
            dishRating = (RatingBar) itemView.findViewById(R.id.dishRating);
            dishTags = (TextView) itemView.findViewById(R.id.dishTags);
        }
    }

    public DishMenuAdapter(List<Dish> dishes) {
        this.dishes = dishes;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DishMenuAdapter.DishViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dish_menu, parent, false);
        DishViewHolder dvh = new DishViewHolder(view);
        return dvh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DishViewHolder dishViewHolder, final int position) {
        Context context = dishViewHolder.dishImage.getContext();
        Uri dishUri = Uri.parse(dishes.get(position).getDishImage());
        Picasso.with(context).load(dishUri).into(dishViewHolder.dishImage);

        dishViewHolder.dishName.setText(dishes.get(position).getName());
        dishViewHolder.dishPrice.setText("$" + Double.toString(dishes.get(position).getPrice()));
        dishViewHolder.dishRating.setRating(dishes.get(position).getRating());
        dishViewHolder.dishTags.setText(dishes.get(position).getTags());
        dishViewHolder.cv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent detailIntent = new Intent(view.getContext(), DishDetailActivity.class);
                detailIntent.putExtra("dish_id", dishes.get(position).getDish_id());
                view.getContext().startActivity(detailIntent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dishes.size();
    }
}