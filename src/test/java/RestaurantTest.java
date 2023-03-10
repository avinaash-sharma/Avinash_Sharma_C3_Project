import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    Restaurant restaurant;

    @BeforeEach
    public void testSetUp(){
        LocalTime openingTime = LocalTime.parse("09:00:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

    }
    //REFACTOR ALL THE REPEATED LINES OF CODE

    public boolean get_Restaurant_open_status(LocalTime currentTime){
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(currentTime);
        return spyRestaurant.isRestaurantOpen();
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        System.out.println(restaurant.getMenu());
        assertTrue(get_Restaurant_open_status(LocalTime.parse("14:45:00")));
        assertTrue(get_Restaurant_open_status(LocalTime.parse("21:45:00")));
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        assertFalse(get_Restaurant_open_status(LocalTime.parse("08:45:00")));
        assertFalse(get_Restaurant_open_status(LocalTime.parse("23:45:00")));

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<GET Total>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void not_adding_anything_should_return_zero(){
        List<String> itemNames = Arrays.asList();

        assertEquals(0, restaurant.getTotalCost(itemNames));
    }
    @Test
    public void adding_anything_should_return_anything_but_zero_here_adding_119(){
        restaurant.addToMenu("Sweet corn soup",119);
        List<String> itemNames = Arrays.asList("Sweet corn soup");

        assertEquals(119, restaurant.getTotalCost(itemNames));
    }

    @Test
    public void get_order_total_for_selected_values_of_amount_119_269_388(){

        List<String> itemNames = Arrays.asList("Sweet corn soup", "Vegetable lasagne");
        assertEquals(388, restaurant.getTotalCost(itemNames));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<GET Total>>>>>>>>>>>>>>>>>>>>>>>>
}