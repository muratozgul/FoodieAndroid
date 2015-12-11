package app.com.example.foodie.foodieandroid.DAO;

import java.util.ArrayList;

import app.com.example.foodie.foodieandroid.ModelSecondary.Order;

/**
 * Created by muratozgul on 08/12/15.
 */
public interface IOrderCallback {

    public void findOrderByIdCb(Order order);
    public void findOrderByIdCb(String responseString);

    public void findAllOrdersCb(ArrayList<Order> orders);
    public void findAllOrdersCb(String responseString);

    public void createOrderCb(String responseString);

}
