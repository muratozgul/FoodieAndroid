package app.com.example.foodie.foodieandroid.Adapters;

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

import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.R;

/**
 * Created by muratozgul on 03/12/15.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private List<Order> orders;

    public static class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView orderCardView;

        public TextView orderIdView;
        public TextView orderDateView;
        public TextView orderChefView;
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
            orderChefView = (TextView) itemView.findViewById(R.id.orderCard_chef);
            orderContentsView = (TextView) itemView.findViewById(R.id.orderCard_contents);
            orderPriceView = (TextView) itemView.findViewById(R.id.orderCard_price);
            orderDetailsButton = (Button) itemView.findViewById(R.id.orderCard_detailsButton);

            orderDetailsButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v instanceof Button){
                //get adapter position for item represented by this view
                int position = this.getAdapterPosition();
                //Toast.makeText(v.getContext(), "Details Button Clicked " + Integer.toString(position),
                //        Toast.LENGTH_SHORT).show();
                mListener.onDetailsButtonClick((Button) v, position);
            } else {
                mListener.onCardClick(v);
            }
        }

        public static interface IOrderViewHolderClicks {
            public void onCardClick(View caller);
            public void onDetailsButtonClick(Button callerButton, int position);
        }
    }

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    public OrderAdapter() {
        this.orders = new ArrayList<Order>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //don't need final if not making a toast
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order, parent, false);

        OrderViewHolder ovh = new OrderViewHolder(view,
                new OrderAdapter.OrderViewHolder.IOrderViewHolderClicks() {
                    public void onCardClick(View caller) { Log.v("OrderAdapter", "Caller View"); }
                    public void onDetailsButtonClick(Button callerButton, int position) {
                        Log.v("OrderAdapter","Caller Button");
                        Order order = getOrder(position);
                        String orderId = Integer.toString(order.getOrder_id());
                        Toast.makeText(view.getContext(), "(Adapter) Details Button Clicked " + Integer.toString(position),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        return ovh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(OrderViewHolder orderViewHolder, int position) {
        Order order = orders.get(position);

        orderViewHolder.orderIdView.setText("Order: "+ Integer.toString(order.getOrder_id()));
        orderViewHolder.orderDateView.setText(order.getTime_stamp());
        orderViewHolder.orderChefView.setText("DummyChefName");
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
