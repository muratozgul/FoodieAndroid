package app.com.example.foodie.foodieandroid.DAO;

import java.util.ArrayList;

import app.com.example.foodie.foodieandroid.Model.Chef;
import app.com.example.foodie.foodieandroid.Model.Dish;

/**
 * Created by Jennifer on 12/8/15.
 */
public interface IChefCallback {
    public void findChefByIdCb(Chef chef);
    public void findChefByIdCb(String responseString);

    public void findAllChefsCb(ArrayList<Chef> chefs);
    public void findAllChefsCb(String responseString);
}
