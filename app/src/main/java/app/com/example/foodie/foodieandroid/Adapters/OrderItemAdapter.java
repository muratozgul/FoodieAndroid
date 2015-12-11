package app.com.example.foodie.foodieandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;
import app.com.example.foodie.foodieandroid.R;

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
        oivh.nameView.setText(Integer.toString(oi.getDish_id()));
        oivh.priceView.setText("$"+Double.toString(oi.getTotalPrice()));

        oivh.ratingBar.setRating(3);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.orderItems.size();
    }
}
