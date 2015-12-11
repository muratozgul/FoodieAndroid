package app.com.example.foodie.foodieandroid.Adapters;

import android.content.Context;
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

import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;
import app.com.example.foodie.foodieandroid.R;

/**
 * Created by muratozgul on 10/12/15.
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.CartItemViewHolder>{
    private List<OrderItem> orderItems;
    private Context context;
    private static final String TAG = "ShoppingCartAdapter";

    public static class CartItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cartItemCardView;

        public TextView quantityView;
        public TextView nameView;
        public TextView priceView;
        public ImageView imageView;
        public RatingBar ratingBar;
        public Button removeButton;

        public ICartItemViewHolderClicks mListener;

        public CartItemViewHolder(View itemView, ICartItemViewHolderClicks listener){
            super(itemView);
            mListener = listener;

            cartItemCardView = (CardView) itemView.findViewById(R.id.orderCard);

            quantityView = (TextView) itemView.findViewById(R.id.orderItemCard_quantity);
            nameView = (TextView) itemView.findViewById(R.id.orderItemCard_name);
            priceView = (TextView) itemView.findViewById(R.id.orderItemCard_price);
            imageView = (ImageView) itemView.findViewById(R.id.orderItemCard_image);
            ratingBar = (RatingBar) itemView.findViewById(R.id.orderItemCard_ratingBar);
            removeButton = (Button) itemView.findViewById(R.id.orderItemCard_commentButton);

            removeButton.setOnClickListener(this);
            //ratingBar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            if (v instanceof Button){
                mListener.onRemoveButtonClick((Button) v, position);
            } else if(v instanceof RatingBar) {
                mListener.onRatingBarClick((RatingBar) v, position);
            }
        }

        public static interface ICartItemViewHolderClicks {
            public void onRatingBarClick(RatingBar callerRatingBar, int position);
            public void onRemoveButtonClick(Button callerButton, int position);
        }
    }

    public ShoppingCartAdapter(List<OrderItem> orderItems, Context context) {
        this.orderItems = orderItems;
        this.context = context;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ShoppingCartAdapter.CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //don't need final if not making a toast
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_item, parent, false);

        CartItemViewHolder oivh = new CartItemViewHolder(view,
                new ShoppingCartAdapter.CartItemViewHolder.ICartItemViewHolderClicks() {
                    public void onRatingBarClick(RatingBar callerRatingBar, int position) {
                        //Toast.makeText(view.getContext(), "Rating Bar Clicked " + Integer.toString(position),
                        //        Toast.LENGTH_SHORT).show();
                    }
                    public void onRemoveButtonClick(Button callerButton, int position) {
                        Toast.makeText(view.getContext(), "Comment Button Clicked " + Integer.toString(position),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        return oivh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CartItemViewHolder civh, int position) {
        OrderItem oi = orderItems.get(position);

        civh.removeButton.setText("Remove");

        String quantity = Integer.toString(oi.getQuantity()) + "x";
        civh.quantityView.setText(quantity);
        civh.nameView.setText(oi.getDishObject().getName());
        civh.priceView.setText("$"+Double.toString(oi.getDishObject().getPrice()*oi.getQuantity()));
        civh.ratingBar.setRating(oi.getDishObject().getRating());

        Picasso.with(context).load(oi.getDishObject().getDish_img()).into(civh.imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.orderItems.size();
    }

}
