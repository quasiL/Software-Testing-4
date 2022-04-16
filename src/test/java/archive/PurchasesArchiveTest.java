package archive;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.mockito.Mockito;
import shop.Item;
import shop.ShoppingCart;
import shop.StandardItem;
import java.util.Collection;
import java.util.HashMap;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PurchasesArchiveTest {

    private PurchasesArchive archive;
    private ItemPurchaseArchiveEntry itemArchiveEntry;
    private StandardItem newStandardItem;
    private shop.Order newOrder;
    private ShoppingCart cart;
    private HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchive = new HashMap();;
    private Item newItem;

    @BeforeEach
    void setPurchaseArchive() {
        archive = Mockito.mock(PurchasesArchive.class);
    }

    @Test
    @Order(0)
    void printItemPurchaseStatistics_isPrintCalled_returnsVerify() {
        archive.printItemPurchaseStatistics();
        Mockito.verify(archive).printItemPurchaseStatistics();
    }

    @Test
    @Order(1)
    void getHowManyTimesHasBeenItemSold_isHowManyWasCalled_returnsVerify() {
        newItem = Mockito.mock(Item.class);
        itemArchiveEntry = new ItemPurchaseArchiveEntry(newItem);
        itemArchiveEntry.increaseCountHowManyTimesHasBeenSold(Mockito.anyInt());
        archive.getHowManyTimesHasBeenItemSold(newItem);
        Mockito.verify(archive).getHowManyTimesHasBeenItemSold(newItem);
    }

    @Test
    @Order(2)
    void putOrderToPurchasesArchive_isPutOrderWasCalled_returnsVerify() {
        newOrder = Mockito.mock(shop.Order.class);
        archive.putOrderToPurchasesArchive(newOrder);
        Mockito.verify(archive).putOrderToPurchasesArchive(newOrder);
    }

    @Test
    @Order(3)
     void printlnStream_isPrintCalledForQuery_returnsVerify() {
        Collection<ItemPurchaseArchiveEntry> itemEntries = itemPurchaseArchive.values();
        newOrder = Mockito.mock(shop.Order.class);
        Mockito.when(archive.putOrderToPurchasesArchive(newOrder)).thenReturn(archive.printItemPurchaseStatistics());
    }

    @Test
    @Order(4)
    void orderArchive_isNewOrderAddedToArchive_returnsVerify() {
        newOrder = Mockito.mock(shop.Order.class);
        archive.putOrderToPurchasesArchive(newOrder);
        Mockito.verify(archive).putOrderToPurchasesArchive(newOrder);
    }

    @Test
    @Order(5)
    void ItemPurchaseArchiveEntry_isNewArchiveItemCreated_returnsVerify() {
        itemArchiveEntry = Mockito.mock(ItemPurchaseArchiveEntry.class);
        itemArchiveEntry.getCountHowManyTimesHasBeenSold();
        Mockito.verify(itemArchiveEntry).getCountHowManyTimesHasBeenSold();
    }

    @Test
    @Order(6)
    void ItemPurchaseArchiveEntry_createNewArchiveItem_returnsSoldCount() {
        newStandardItem = new StandardItem(1, "name1", 10F, "category1", 0);
        itemArchiveEntry = new ItemPurchaseArchiveEntry(newStandardItem);

        Assertions.assertEquals(1, itemArchiveEntry.getCountHowManyTimesHasBeenSold());
    }
}