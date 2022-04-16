package shop;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderTest {
    private ShoppingCart cart;
    private StandardItem newStandardItem;
    private String customerName = "name";
    private String customerAddress = "address";
    private int state1 = 1;
    private int state2; // null
    private shop.Order newOrder;
    private shop.Order newOrder2;

    @BeforeEach
    void setOrder() {
        cart = new ShoppingCart();
        newStandardItem = new StandardItem(1, "name1", 10F, "category1", 0);
        cart.addItem(newStandardItem);
        newOrder = new shop.Order(cart, customerName, customerAddress, state1);
        newOrder2 = new shop.Order(cart, customerName, customerAddress, state2);
    }

    @Test
    @Order(0)
    void Order_createNewOrderNameIsSet_returnsName() {Assertions.assertEquals(newOrder.getCustomerName(), customerName);}

    @Test
    @Order(1)
    void Order_createNewOrderAddressIsSet_returnsAddress() {Assertions.assertEquals(newOrder.getCustomerAddress(), customerAddress);}

    @Test
    @Order(2)
    void Order_createNewOrderCartIsSet_returnsName() {Assertions.assertEquals(newOrder.getItems(), cart.getCartItems());}

    @Test
    @Order(3)
    void Order_createNewOrderStateIsSet_returnsState() {Assertions.assertEquals(newOrder.getState(), state1);}

    @Test
    @Order(4)
    void Order_createNewOrderNullStateIsSet_returnsNull() {Assertions.assertEquals(newOrder2.getState(), state2);}
}