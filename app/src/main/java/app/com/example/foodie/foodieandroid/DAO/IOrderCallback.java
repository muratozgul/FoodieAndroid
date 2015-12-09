package app.com.example.foodie.foodieandroid.DAO;

import app.com.example.foodie.foodieandroid.ModelSecondary.Order;

/**
 * Created by muratozgul on 08/12/15.
 */
public interface IOrderCallback {

    public void findOrderByIdCb(Order order);
    public void findOrderByIdCb(String responseString);

}
