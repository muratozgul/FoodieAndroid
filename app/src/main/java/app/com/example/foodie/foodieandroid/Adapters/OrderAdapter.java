package app.com.example.foodie.foodieandroid.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.R;

/**
 * Created by muratozgul on 03/12/15.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private List<Order> orders;

    public static class OrderViewHolder extends RecyclerView.ViewHolder{
        public CardView orderCardView;
        public TextView orderIdView;
        public TextView orderTimeView;

        public OrderViewHolder(View itemView){
            super(itemView);
            orderCardView = (CardView) itemView.findViewById(R.id.orderCard);
            orderIdView = (TextView) itemView.findViewById(R.id.orderCard_orderId);
            orderTimeView = (TextView) itemView.findViewById(R.id.orderCard_orderTime);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order, parent, false);
        OrderViewHolder ovh = new OrderViewHolder(view);
        return ovh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(OrderViewHolder orderViewHolder, int position) {
        orderViewHolder.orderIdView.setText(Integer.toString(orders.get(position).getOrder_id()));
        orderViewHolder.orderTimeView.setText(orders.get(position).getTime_stamp());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.orders.size();
    }
}
