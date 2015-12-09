package app.com.example.foodie.foodieandroid;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.example.foodie.foodieandroid.Helpers.ShoppingCart;
import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;

import static org.junit.Assert.assertEquals;

/**
 * Created by muratozgul on 07/12/15.
 */
public class OrderUnitTest {
    static Dish dish1;
    static Dish dish2;

    @BeforeClass
    public static void initialize() {
        //System.out.println("@BeforeClass: initializing...");
        dish1 = new Dish(1, "dish1", 2d);
        dish2 = new Dish(2, "dish2", 3d);
    }

    @Before
    public void clearPrevTest() {
        //System.out.println("@Before: cleaning...");

    }

    @Test
    public void shouldCreateOrderFromHashMap() throws Exception {
        HashMap<Dish, Integer> hashmap = new HashMap<Dish, Integer>();
        hashmap.put(dish1, 1);
        hashmap.put(dish2, 3);

        Order order = new Order(hashmap);
        assertEquals(2, order.getOrderItems().size());

        assertEquals(1, order.getOrderItem(0).getDish_id());
        assertEquals(1, order.getOrderItem(0).getQuantity());

        assertEquals(2, order.getOrderItem(1).getDish_id());
        assertEquals(3, order.getOrderItem(1).getQuantity());
    }
}
