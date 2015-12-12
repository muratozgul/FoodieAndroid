package app.com.example.foodie.foodieandroid.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.com.example.foodie.foodieandroid.Activities.DishDetailActivity;
import app.com.example.foodie.foodieandroid.Activities.DishMenuActivity;
import app.com.example.foodie.foodieandroid.Activities.OrderDetailsActivity;
import app.com.example.foodie.foodieandroid.Application.FoodieApp;
import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.R;

/**
 * Created by Jennifer on 11/29/15.
 */

public class DishMenuAdapter extends RecyclerView.Adapter<DishMenuAdapter.DishViewHolder> {
    private List<Dish> dishes;
    private Context context;

    public static class DishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CardView cv;
        public ImageView dishImage;
        public TextView dishName;
        public TextView dishPrice;
        public RatingBar dishRating;
        public TextView dishTags;
        public Button addButton;
        public IDishViewHolderClicks mListener;

        public DishViewHolder(View itemView, IDishViewHolderClicks listener) {
            super(itemView);
            mListener = listener;

            cv = (CardView) itemView.findViewById(R.id.dishCard);
            dishImage = (ImageView) itemView.findViewById(R.id.dishImage);
            dishName = (TextView) itemView.findViewById(R.id.dishName);
            dishPrice = (TextView) itemView.findViewById(R.id.dishPrice);
            dishRating = (RatingBar) itemView.findViewById(R.id.dishRating);
            dishTags = (TextView) itemView.findViewById(R.id.dishTags);
            addButton = (Button) itemView.findViewById(R.id.buyButton);

            dishImage.setOnClickListener(this);
            addButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            if (v instanceof Button){
                //get adapter position for item represented by this view
                mListener.onAddButtonClick((Button) v, position);
            } else if (v instanceof ImageView){
                mListener.onImageClick((ImageView) v, position);
            }
        }

        public static interface IDishViewHolderClicks {
            public void onAddButtonClick(Button callerButton, int position);
            public void onImageClick(ImageView imageView, int position);
        }
    }

    public DishMenuAdapter(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public DishMenuAdapter(List<Dish> dishes, Context context) {
        this.context = context;
        this.dishes = dishes;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DishMenuAdapter.DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dish_menu, parent, false);

        DishViewHolder dvh = new DishViewHolder(view,
                new DishMenuAdapter.DishViewHolder.IDishViewHolderClicks() {

                    public void onAddButtonClick(Button callerButton, int position) {
                        Dish dish = getDish(position);
                        FoodieApp.getInstance().getCart().addOne(dish);
                        ((DishMenuActivity)context).updateCartIcon();
                        Toast.makeText(view.getContext(), dish.getName() + " added to cart",
                                Toast.LENGTH_SHORT).show();
                    }

                    public void onImageClick(ImageView imageView, int position) {
                        Intent detailIntent = new Intent(view.getContext(), DishDetailActivity.class);
                        detailIntent.putExtra("dish_id", dishes.get(position).getDish_id());
                        view.getContext().startActivity(detailIntent);
                    }
                });

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
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public Dish getDish(int position) {
        return this.dishes.get(position);
    }
}