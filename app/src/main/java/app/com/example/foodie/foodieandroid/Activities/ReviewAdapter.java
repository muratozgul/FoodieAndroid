package app.com.example.foodie.foodieandroid.Activities;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import app.com.example.foodie.foodieandroid.Model.Review;
import app.com.example.foodie.foodieandroid.R;

/**
 * Created by Jennifer on 11/30/15.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{
    private List<Review> reviews;

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
        reviewViewHolder.reviewerImg.setImageResource(R.drawable.foodie);
        reviewViewHolder.reviewerName.setText(reviews.get(i).getReviewer());
        reviewViewHolder.dishRating.setRating(reviews.get(i).getStar_rating());
        reviewViewHolder.reviewText.setText(reviews.get(i).getTimestamp());
        reviewViewHolder.reviewText.setText(reviews.get(i).getReview_text());
    }

    @Override
    public int getItemCount(){ return reviews.size(); }
}
