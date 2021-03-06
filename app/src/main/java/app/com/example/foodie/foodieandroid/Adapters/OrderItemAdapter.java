package app.com.example.foodie.foodieandroid.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Activities.OrderDetailsActivity;
import app.com.example.foodie.foodieandroid.Application.FoodieApp;
import app.com.example.foodie.foodieandroid.DAO.ReviewDAO;
import app.com.example.foodie.foodieandroid.Model.Review;
import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;
import app.com.example.foodie.foodieandroid.R;
import app.com.example.foodie.foodieandroid.Utility.Rating;

/**
 * Created by muratozgul on 05/12/15.
 */
public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder>{
    private List<OrderItem> orderItems;
    private Context context;
    private static final String TAG = "OrderItemAdapter";

    public static class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView orderCardView;

        public TextView quantityView;
        public TextView nameView;
        public TextView priceView;
        public ImageView imageView;
        public RatingBar ratingBar;
        public Button commentButton;

        public IOrderItemViewHolderClicks mListener;

        public OrderItemViewHolder(View itemView, IOrderItemViewHolderClicks listener){
            super(itemView);
            mListener = listener;

            orderCardView = (CardView) itemView.findViewById(R.id.orderCard);

            quantityView = (TextView) itemView.findViewById(R.id.orderItemCard_quantity);
            nameView = (TextView) itemView.findViewById(R.id.orderItemCard_name);
            priceView = (TextView) itemView.findViewById(R.id.orderItemCard_price);
            imageView = (ImageView) itemView.findViewById(R.id.orderItemCard_image);
            ratingBar = (RatingBar) itemView.findViewById(R.id.orderItemCard_ratingBar);
            commentButton = (Button) itemView.findViewById(R.id.orderItemCard_commentButton);

            commentButton.setOnClickListener(this);
            ratingBar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            if (v instanceof Button){
                mListener.onCommentButtonClick((Button) v, position);
            } else if(v instanceof RatingBar) {
                mListener.onRatingBarClick((RatingBar) v, position);
            }
        }

        public static interface IOrderItemViewHolderClicks {
            public void onRatingBarClick(RatingBar callerRatingBar, int position);
            public void onCommentButtonClick(Button callerButton, int position);
        }
    }

    public OrderItemAdapter(List<OrderItem> orderItems, Context context) {
        this.orderItems = orderItems;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public OrderItemAdapter.OrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //don't need final if not making a toast
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_item, parent, false);

        OrderItemViewHolder oivh = new OrderItemViewHolder(view,
                new OrderItemAdapter.OrderItemViewHolder.IOrderItemViewHolderClicks() {
                    public void onRatingBarClick(RatingBar callerRatingBar, int position) {
                        Toast.makeText(view.getContext(), "Rating Bar Clicked " + Integer.toString(position),
                                Toast.LENGTH_SHORT).show();
                    }
                    public void onCommentButtonClick(Button callerButton, int position) {
                        Toast.makeText(view.getContext(), "Comment Button Clicked " + Integer.toString(position),
                                Toast.LENGTH_SHORT).show();
                        showReviewDialog(position);
                    }
                });

        return oivh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(OrderItemViewHolder oivh, int position) {
        OrderItem oi = orderItems.get(position);

        String quantity = Integer.toString(oi.getQuantity()) + "x";

        oivh.quantityView.setText(quantity);
        oivh.nameView.setText(oi.getDishObject().getName());
        oivh.priceView.setText("$"+Double.toString(oi.getDishObject().getPrice()*oi.getQuantity()));
        oivh.ratingBar.setRating(oi.getDishObject().getRating());

        Picasso.with(context).load(oi.getDishObject().getDish_img()).into(oivh.imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.orderItems.size();
    }

    public OrderItem getOrderItem(int position){
        return this.orderItems.get(position);
    }

    protected void showReviewDialog(final int position) {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.dialog_write_review, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);

        final RatingBar ratingBar = (RatingBar) promptView.findViewById(R.id.reviewRating);
        final EditText editText = (EditText) promptView.findViewById(R.id.reviewTextEdit);

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(context, "Review you left: \n" + ratingBar.getRating() + " stars\n" + editText.getText().toString(), Toast.LENGTH_LONG).show();
                        Review review = new Review(ratingBar.getRating(), editText.getText().toString(),
                                FoodieApp.getAppUserId(), getOrderItem(position).getDish_id());
                        postReview(review);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected void postReview(Review review){
        try {
            ReviewDAO.create(review, (OrderDetailsActivity)this.context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
