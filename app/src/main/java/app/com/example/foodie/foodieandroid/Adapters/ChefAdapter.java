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

import app.com.example.foodie.foodieandroid.Activities.ChefDetailActivity;
import app.com.example.foodie.foodieandroid.Activities.DishDetailActivity;
import app.com.example.foodie.foodieandroid.Model.Chef;
import app.com.example.foodie.foodieandroid.R;

/**
 * Created by Jennifer on 12/8/15.
 */
public class ChefAdapter extends RecyclerView.Adapter<ChefAdapter.ChefViewHolder> {
    private List<Chef> chefs;

    public static class ChefViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public ImageView chefImage;
        public TextView chefName;
        public TextView mealsServed;
        public RatingBar chefRating;
        public TextView chefTags;

        public ChefViewHolder (View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.chefCard);
            chefImage = (ImageView) itemView.findViewById(R.id.chefImg);
            chefName = (TextView) itemView.findViewById(R.id.chefName);
            mealsServed = (TextView) itemView.findViewById(R.id.chefMealsServed);
            chefRating = (RatingBar) itemView.findViewById(R.id.chefRating);
            chefTags = (TextView) itemView.findViewById(R.id.chefTags);
        }
    }

    public ChefAdapter(List<Chef> chefs) { this.chefs = chefs; }

    @Override
    public ChefAdapter.ChefViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_chef, parent, false);
        ChefViewHolder cvh = new ChefViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ChefViewHolder chefViewHolder, final int position) {

        Context context = chefViewHolder.chefImage.getContext();
        Uri uri = Uri.parse(chefs.get(position).getProfile_img());
        Picasso.with(context).load(uri).into(chefViewHolder.chefImage);

        chefViewHolder.chefName.setText(chefs.get(position).getName());
        chefViewHolder.mealsServed.setText(Integer.toString(chefs.get(position).getNumOfDishes()) + " meals served");
        chefViewHolder.chefRating.setRating(chefs.get(position).getRating());
        chefViewHolder.chefTags.setText(chefs.get(position).getTags());
        chefViewHolder.cv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent detailIntent = new Intent(view.getContext(), ChefDetailActivity.class);
                detailIntent.putExtra("chef_id", chefs.get(position).getUser_id());
                view.getContext().startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chefs.size();
    }

}
