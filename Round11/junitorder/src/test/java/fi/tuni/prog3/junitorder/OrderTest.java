
package fi.tuni.prog3.junitorder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OrderTest {
    
    public OrderTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testAddItems_OrderItem_int_IllegalState() {
        System.out.println("AddItems_OrderItem_int_IllegalState");
        Exception exception;
        Order instance = new Order();
        instance.addItems(new Order.Item("Apple", 0.50), 5); 
        exception = assertThrows(IllegalStateException.class, () -> {
            instance.addItems(new Order.Item("Apple", 1.50), 4);
        });

        String expectedMessage = "The added item and an existing item have different prices!";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void testAddItems_OrderItem_int_IllegalArgument() {
        System.out.println("AddItems_OrderItem_int_IllegalArgument");
        Exception exception;
        Order instance = new Order();
        exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.addItems(new Order.Item("Apple", 0.50), -4);
        });

        String expectedMessage = "Illegal item unit count: -4";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void testAddItems_String_int_UnknownItem() {
        System.out.println("AddItems_String_int_UnknownItem");
        Exception exception;
        Order instance = new Order();
        exception = assertThrows(NoSuchElementException.class, () -> {
            instance.addItems("Apple", 4);
        });

        String expectedMessage = "The item was not found from the order!";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void testAddItems_String_int_IllegalArgument() {
        System.out.println("AddItems_String_int_IllegalArgument");
        Exception exception;
        Order instance = new Order();
        exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.addItems(new Order.Item("Apple", 0.50), -2);
        });

        String expectedMessage = "Illegal item unit count: -2";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void testremoveItems_IllegalArgument() {
        System.out.println("removeItems_IllegalArgument");
        Exception exception;
        Order instance = new Order();
        instance.addItems(new Order.Item("Apple", 0.50), 5); 
        exception = assertThrows(IllegalArgumentException.class, () -> {
            instance.removeItems("Apple", -1);
        });

        String expectedMessage = "Illegal item unit count: -1";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void testremoveElements_UnkownElement() {
        System.out.println("removeItems_UnkownElement");
        Exception exception;
        Order instance = new Order();
        instance.addItems(new Order.Item("Apple", 0.50), 5); 
        exception = assertThrows(NoSuchElementException.class, () -> {
            instance.removeItems("Orange", 1);
        });

        String expectedMessage = "The item was not found from the order!";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void testItemIllegalName() {
        System.out.println("ItemIllegalName");
        Exception exception;
        exception = assertThrows(IllegalArgumentException.class, () -> {
            Order.Item item = new Order.Item(null, 1);
        });

        String expectedMessage = "Illegal item name: null";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void testItemIllegalPrice() {
        System.out.println("ItemIllegalPrice");
        Exception exception;
        exception = assertThrows(IllegalArgumentException.class, () -> {
            Order.Item item = new Order.Item("Apple", -2.0);
        });

        String expectedMessage = "Illegal negative item price: -2.0";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void testEntryConstructorExecptionThrown() {
        System.out.println("EntryConstructorExecptionThrown");
        Exception exception;
        exception = assertThrows(IllegalArgumentException.class, () -> {
            Order.Entry entry = new Order.Entry(new Order.Item("Apple", 2), -1);
        });

        String expectedMessage = "Illegal item unit count: -1";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    /**
     * Test of addItems method, of class Order.
     */
    @Test
    public void testAddItems_OrderItem_int() {
        System.out.println("addItems");
        Order.Item item = new Order.Item("Apple", 0.50);
        int count = 5;
        Order instance = new Order();
        boolean expResult = true;
        boolean result = instance.addItems(item, count);
        assertEquals(expResult, result);
    }

    /**
     * Test of addItems method, of class Order.
     */
    @Test
    public void testAddItems_String_int() {
        System.out.println("addItems");
        String name = "Apple";
        int count = 1;
        Order instance = new Order();
        instance.addItems(new Order.Item("Apple", 0.50), 5); 
        boolean expResult = true;
        boolean result = instance.addItems(name, count);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEntries method, of class Order.
     */
    @Test
    public void testGetEntries() {
        System.out.println("getEntries");
        Order instance = new Order();
        List<Order.Entry> expResult = new ArrayList<>();
        List<Order.Entry> result = instance.getEntries();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEntryCount method, of class Order.
     */
    @Test
    public void testGetEntryCount() {
        System.out.println("getEntryCount");
        Order instance = new Order();
        int expResult = 0;
        int result = instance.getEntryCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getItemCount method, of class Order.
     */
    @Test
    public void testGetItemCount() {
        System.out.println("getItemCount");
        Order instance = new Order();
        int expResult = 0;
        int result = instance.getItemCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalPrice method, of class Order.
     */
    @Test
    public void testGetTotalPrice() {
        System.out.println("getTotalPrice");
        Order instance = new Order();
        double expResult = 0.0;
        double result = instance.getTotalPrice();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of isEmpty method, of class Order.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Order instance = new Order();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

    }

    /**
     * Test of removeItems method, of class Order.
     */
    @Test
    public void testRemoveItems() {
        System.out.println("removeItems");
        String name = "Apple";
        int count = 1;
        Order instance = new Order();
        instance.addItems(new Order.Item("Apple", 0.50), 5); 
        boolean expResult = true;
        boolean result = instance.removeItems(name, count);
        assertEquals(expResult, result);
    }
    
    
}
