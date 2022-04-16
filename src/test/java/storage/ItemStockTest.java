package storage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import shop.Item;
import shop.StandardItem;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemStockTest {

    private Item newItem;
    private int id = 1;
    private String name = "name1";
    private float price = 10F;
    private String category = "category1";
    private int lp = 0;
    private ItemStock newItemStock;

    @BeforeEach
    void setItem() {
        newItem = new StandardItem(id, name, price, category, lp);
        newItemStock = new ItemStock(newItem);
    }

    @Test
    @Order(0)
    void ItemStock_createNewObjectItemIsSet_returnsItem() {
        Assertions.assertEquals(newItemStock.getItem(), newItem);
    }

    @ParameterizedTest(name = "count {0} should be equal to {1}")
    @Order(1)
    @CsvSource({"0, 0", "20, 20", "3000, 3000"})
    void increaseItemCount_countIncreasesToCount_returnsCount(int addToCount, int resultCount) {
        newItemStock.IncreaseItemCount(addToCount);
        Assertions.assertEquals(resultCount, newItemStock.getCount());
    }

    @ParameterizedTest(name = "subtracted count {0}, the result should be {1}")
    @Order(2)
    @CsvSource({"0, 0", "20, 0", "3000, 0"})
    void decreaseItemCount_countDecreasesToCount_returnsCount(int subtractFromCount, int resultCount) {
        newItemStock.IncreaseItemCount(subtractFromCount);
        newItemStock.decreaseItemCount(subtractFromCount);
        Assertions.assertEquals(resultCount, newItemStock.getCount());
    }
}