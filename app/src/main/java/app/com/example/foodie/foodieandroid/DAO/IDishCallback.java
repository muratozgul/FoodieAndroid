package app.com.example.foodie.foodieandroid.DAO;

import java.util.ArrayList;

import app.com.example.foodie.foodieandroid.Model.Chef;
import app.com.example.foodie.foodieandroid.Model.Dish;

/**
 * Created by muratozgul on 08/12/15.
 */
public interface IDishCallback {
    public void findDishByIdCb(Dish dish);
    public void findDishByIdCb(String responseString);

    public void findAllDishesCb(ArrayList<Dish> dishes);
    public void findAllDishesCb(String responseString);
}
