import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void mockRestaurant() {

        //create spy object

        restaurant = spy(new Restaurant());

    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {

        LocalTime checkingTime = LocalTime.parse("12:30:00");
        LocalTime checkingEdgeTiming = LocalTime.parse("10:31:00");
        // Set Open And Close Timing
        restaurant.openingTime = LocalTime.parse("10:30:00");
        restaurant.closingTime = LocalTime.parse("20:30:00");

        //change the current time to 7:30 AM
        Mockito.when(restaurant.getCurrentTime()).thenReturn(checkingTime);

        assertTrue(restaurant.isRestaurantOpen());

        Mockito.when(restaurant.getCurrentTime()).thenReturn(checkingEdgeTiming);

        assertTrue(restaurant.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE

        LocalTime checkingTime = LocalTime.parse("07:30:00");
        LocalTime checkingEdgeTiming = LocalTime.parse("08:29:00");

        //change the current time to 7:30 AM
        Mockito.when(restaurant.getCurrentTime()).thenReturn(checkingTime);

        // Set Open And Close Timing
        restaurant.openingTime = LocalTime.parse("08:30:00");
        restaurant.closingTime = LocalTime.parse("20:30:00");

        Mockito.when(restaurant.getCurrentTime()).thenReturn(checkingTime);

        assertFalse(restaurant.isRestaurantOpen());

        Mockito.when(restaurant.getCurrentTime()).thenReturn(checkingEdgeTiming);

        assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        create_restaurant_with_two_menus();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws ItemNotFoundException {
        create_restaurant_with_two_menus();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        create_restaurant_with_two_menus();

        assertThrows(ItemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void create_restaurant_with_two_menus(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
}