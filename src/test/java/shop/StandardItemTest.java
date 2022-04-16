package shop;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StandardItemTest {

    private int id = 1;
    private String name = "name1";
    private float price = 10F;
    private String category = "category1";
    private int lp = 0;
    private StandardItem newStandardItem;

    @BeforeEach
    void setStandardItem() {
        newStandardItem = new StandardItem(id, name, price, category, lp);
    }

    @Test
    @Order(0)
    void StandardItem_createNewObjectIdIsSet_returnsId() {Assertions.assertEquals(newStandardItem.getID(), id);}

    @Test
    @Order(1)
    void StandardItem_createNewObjectNameIsSet_returnsName() {Assertions.assertEquals(newStandardItem.getName(), name);}

    @Test
    @Order(2)
    void StandardItem_createNewObjectPriceIsSet_returnsPrice() {Assertions.assertEquals(newStandardItem.getPrice(), price);}

    @Test
    @Order(3)
    void StandardItem_createNewObjectCategoryIsSet_returnsCategory() {Assertions.assertEquals(newStandardItem.getCategory(), category);}

    @Test
    @Order(4)
    void StandardItem_createNewObjectLoyaltyPointsIsSet_returnsLoyaltyPoints() {Assertions.assertEquals(newStandardItem.getLoyaltyPoints(), lp);}

    @Test
    @Order(5)
    void copy_copyObjectStandardItem_returnsSameObjectStandardItem() {
        Assertions.assertEquals(newStandardItem, newStandardItem.copy());
    }

    static StandardItem[] listOfStandardItems() {
        return new StandardItem[] {new StandardItem(1, "name1", 10F, "category1", 0),
                new StandardItem(3, "name3", 70F, "category3", 0)};
    }
    //the first StandardItem object is equal to the newStandardItem and the second object is not
    @ParameterizedTest
    @MethodSource("listOfStandardItems")
    @Order(6)
    void equal_isItTheSameObjects_returnsTrue(StandardItem si) {
        Assertions.assertTrue(si.equals(newStandardItem));
    }
}