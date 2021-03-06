package app.com.example.foodie.foodieandroid.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Model.Customer;
import app.com.example.foodie.foodieandroid.Model.Review;
import app.com.example.foodie.foodieandroid.Model.User;
import app.com.example.foodie.foodieandroid.R;

/**
 * Created by Jennifer on 11/30/15.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{
    private List<Review> reviews = new ArrayList<Review>();
    private Customer reviewer = new Customer();

    public static class ReviewViewHolder extends RecyclerView.ViewHolder{
        public CardView reviewCV;
        public ImageView reviewerImg;
        public TextView reviewerName;
        public RatingBar dishRating;
        public TextView reviewTime;
        public TextView reviewText;

        public ReviewViewHolder(View itemView){
            super(itemView);
            reviewCV = (CardView) itemView.findViewById(R.id.reviewCard);
            reviewerImg = (ImageView) itemView.findViewById(R.id.reviewerThumb);
            reviewerName = (TextView) itemView.findViewById(R.id.reviewerName);
            dishRating = (RatingBar) itemView.findViewById(R.id.dishRating);
            reviewTime = (TextView) itemView.findViewById(R.id.reviewTime);
            reviewText = (TextView) itemView.findViewById(R.id.reviewText);
        }
    }

    public ReviewAdapter (List<Review> reviews) {this.reviews = reviews;}

    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_review, parent, false);
        ReviewViewHolder rvh = new ReviewViewHolder(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder reviewViewHolder, int i){
        if (i >= reviews.size()) {
            return;
        }
        reviewer = reviews.get(i).getCustomer();
        Uri uri;
        Context context = reviewViewHolder.reviewerImg.getContext();
        if(reviewer.getProfile_img() != null) {
             uri = Uri.parse(reviewer.getProfile_img());
        } else {
            uri = Uri.parse("https://cdn2.iconfinder.com/data/icons/rcons-user/32/female-fill-circle-512.png");
        }

        Picasso.with(context).load(uri).into(reviewViewHolder.reviewerImg);
        reviewViewHolder.reviewerName.setText(reviewer.getName());
        reviewViewHolder.dishRating.setRating(reviews.get(i).getStar_rating());
        reviewViewHolder.reviewTime.setText(reviews.get(i).getReviewDateString());
        reviewViewHolder.reviewText.setText(reviews.get(i).getReview_text());
    }

    @Override
    public int getItemCount(){ return reviews.size(); }
}
