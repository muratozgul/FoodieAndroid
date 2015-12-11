package app.com.example.foodie.foodieandroid.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.Activities.OrderDetailsActivity;
import app.com.example.foodie.foodieandroid.Activities.OrdersActivity;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;
import app.com.example.foodie.foodieandroid.R;

/**
 * Created by muratozgul on 03/12/15.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private static final String TAG = "OrderAdapter";
    private List<Order> orders;
    private Context context;

    public static class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView orderCardView;

        public TextView orderIdView;
        public TextView orderDateView;
        public TextView orderContentsView;
        public TextView orderPriceView;
        public Button orderDetailsButton;

        public IOrderViewHolderClicks mListener;


        public OrderViewHolder(View itemView, IOrderViewHolderClicks listener){
            super(itemView);
            mListener = listener;

            orderCardView = (CardView) itemView.findViewById(R.id.orderCard);
            orderIdView = (TextView) itemView.findViewById(R.id.orderCard_id);
            orderDateView = (TextView) itemView.findViewById(R.id.orderCard_date);
            orderContentsView = (TextView) itemView.findViewById(R.id.orderCard_contents);
            orderPriceView = (TextView) itemView.findViewById(R.id.orderCard_price);
            orderDetailsButton = (Button) itemView.findViewById(R.id.orderCard_detailsButton);

            orderDetailsButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            if (v instanceof Button){
                //get adapter position for item represented by this view
                mListener.onDetailsButtonClick((Button) v, position);
            } else {
                mListener.onCardClick(v, position);
            }
        }

        public static interface IOrderViewHolderClicks {
            public void onCardClick(View caller, int position);
            public void onDetailsButtonClick(Button callerButton, int position);
        }
    }

    public OrderAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //don't need final if not making a toast
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order, parent, false);

        OrderViewHolder ovh = new OrderViewHolder(view,
                new OrderAdapter.OrderViewHolder.IOrderViewHolderClicks() {
                    public void onCardClick(View caller, int position) {
                        Order order = getOrder(position);
                        //Toast.makeText(view.getContext(), "(Adapter) Card Clicked: " + order.toString(),
                        //        Toast.LENGTH_SHORT).show();
                    }
                    public void onDetailsButtonClick(Button callerButton, int position) {
                        Order order = getOrder(position);
                        //Toast.makeText(view.getContext(), "(Adapter) Details Button Clicked " + Integer.toString(position),
                        //        Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context, OrderDetailsActivity.class);
                        intent.putExtra("order", order);
                        context.startActivity(intent);
                    }
                });

        return ovh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(OrderViewHolder orderViewHolder, int position) {
        Order order = orders.get(position);

        orderViewHolder.orderIdView.setText("Order#: "+ Integer.toString(order.getId()));
        orderViewHolder.orderDateView.setText(order.getOrderDateString());
        orderViewHolder.orderContentsView.setText("Order Contents");
        orderViewHolder.orderPriceView.setText("Total price: " + Double.toString(order.getTotal_price()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.orders.size();
    }

    public Order getOrder(int position) {
        return this.orders.get(position);
    }
}
