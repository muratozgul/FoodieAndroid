package app.com.example.foodie.foodieandroid;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import app.com.example.foodie.foodieandroid.Helpers.ShoppingCart;
import app.com.example.foodie.foodieandroid.Model.Chef;
import app.com.example.foodie.foodieandroid.Model.Dish;
import app.com.example.foodie.foodieandroid.ModelSecondary.Order;
import app.com.example.foodie.foodieandroid.ModelSecondary.OrderItem;

import static org.junit.Assert.*;
/**
 * Created by muratozgul on 07/12/15.
 */
public class ShoppingCartUnitTest {
    static ArrayList<Dish> dishes;
    static Dish dish1;
    static Dish dish2;
    static Dish dish3;
    static ShoppingCart cart;

    @BeforeClass
    public static void initialize() {
        //System.out.println("@BeforeClass: initializing...");
        dish1 = new Dish(1, "dish1", 5f, 2d, "", null);
        dish2 = new Dish(2, "dish2", 4f, 3.99d, "", null);
        dish3 = new Dish(3, "dish3", 3f, 5d, "", null);

        cart = ShoppingCart.getInstance();
    }

    @Before
    public void clearPrevTest() {
        //System.out.println("@Before: cleaning...");
        cart.empty();
    }

    @Test
    public void shouldAddSingleDish() throws Exception {
        cart.addOne(dish1);
        int quantity = cart.getContents().get(dish1);
        assertEquals(1, quantity);
    }

    @Test
    public void shouldAddMultipleSameDishes() throws Exception {
        cart.addOne(dish1);
        cart.addOne(dish1);
        int quantity = cart.getContents().get(dish1);
        assertEquals(2, quantity);
    }

    @Test
    public void shouldAddMultipleDifferentDishes() throws Exception {
        cart.addOne(dish1);
        cart.addOne(dish2);
        int q1 = cart.getContents().get(dish1);
        int q2 = cart.getContents().get(dish2);
        assertEquals(1, q1);
        assertEquals(1, q2);
    }

    @Test
    public void shouldAddAnyCombination() throws Exception {
        cart.addOne(dish1);
        cart.addOne(dish2);
        cart.addOne(dish3);
        cart.addOne(dish2);
        cart.addOne(dish3);
        cart.addOne(dish3);
        assertEquals(1, cart.getContents().get(dish1).intValue());
        assertEquals(2, cart.getContents().get(dish2).intValue());
        assertEquals(3, cart.getContents().get(dish3).intValue());
    }

    @Test
    public void shouldRemoveSingleDish() throws Exception {
        cart.addOne(dish1);
        cart.removeOne(dish1);
        assertFalse(cart.getContents().containsKey(dish1));
    }

    @Test
    public void shouldDecrementDishWhenRemoved() throws Exception {
        cart.addOne(dish1);
        cart.addOne(dish1);
        cart.addOne(dish1);
        cart.removeOne(dish1);
        assertTrue(cart.getContents().containsKey(dish1));
        assertEquals(2, cart.getContents().get(dish1).intValue());
    }

    @Test
    public void shouldRemoveAllSimilarDishes() throws Exception {
        cart.addOne(dish1);
        cart.addOne(dish1);
        cart.addOne(dish2);
        cart.removeAll(dish1);
        assertFalse(cart.getContents().containsKey(dish1));
        assertTrue(cart.getContents().containsKey(dish2));
    }

    @Test
    public void shouldEmpty() throws Exception {
        cart.addOne(dish1);
        cart.addOne(dish1);
        cart.addOne(dish2);
        cart.empty();
        assertFalse(cart.getContents().containsKey(dish1));
        assertFalse(cart.getContents().containsKey(dish2));
    }

    @Test
    public void shouldIdentifyDifferentInstancesOfTheSameDish() throws Exception {
        Dish dish1clone = new Dish(1, "dish1", 5f, 2d, "", null);
        cart.addOne(dish1);
        cart.addOne(dish1clone);

        assertTrue(cart.getContents().containsKey(dish1));
        assertTrue(cart.getContents().containsKey(dish1clone));

        assertEquals(2, cart.getContents().get(dish1).intValue());
        assertEquals(2, cart.getContents().get(dish1clone).intValue());
    }

    @Test
    public void shouldCreateOrderWhenCheckout() throws Exception {
        cart.addOne(dish1);
        cart.addOne(dish1);
        cart.addOne(dish2);

        Order order = cart.checkout();

        //assertFalse(cart.getContents().containsKey(dish1));
        //assertFalse(cart.getContents().containsKey(dish2));

        assertEquals(2, order.getOrderItems().size());
    }

    @Test
    public void shouldReturnCorrectSize() throws Exception {
        assertEquals(0, cart.size());

        cart.addOne(dish1);
        cart.addOne(dish1);
        cart.addOne(dish2);

        assertEquals(3, cart.size());
    }

    @Test
    public void shouldReturnCorrectPrice() throws Exception {
        assertEquals(0d, cart.getTotalCost(), 0.1d);

        cart.addOne(dish1);
        assertEquals(2d, cart.getTotalCost(), 0.1d);

        cart.addOne(dish1);
        assertEquals(4d, cart.getTotalCost(), 0.1d);

        cart.addOne(dish2);
        assertEquals(7.99d, cart.getTotalCost(), 0.1d);
    }

    @Test
    public void shouldReturnItemsAsList() throws Exception {
        cart.addOne(dish1);

        OrderItem oi1 = cart.getItemsAsList().get(0);

        assertEquals(1, oi1.getDish_id());

        cart.addOne(dish1);
        assertEquals(1, cart.getItemsAsList().size());

        cart.addOne(dish2);
        assertEquals(2, cart.getItemsAsList().size());
    }
}
