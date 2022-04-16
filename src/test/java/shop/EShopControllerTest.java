package shop;

import archive.PurchasesArchive;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import storage.NoItemInStorage;
import storage.Storage;
import java.util.ArrayList;

class EShopControllerTest {
    private static Storage storage;
    private static PurchasesArchive archive;
    private static ArrayList<ShoppingCart> carts;
    private static ArrayList<Order> orders;
    private static ShoppingCart newCart;

    @Test
    static void EShopController_isEshopWorking_returnsStatus() throws NoItemInStorage {
        EShopController.startEShop();

        int[] itemCount = {10,10,4,5,10,2};

        Item[] storageItems = {
                new StandardItem(1, "Dancing Panda v.2", 5000, "GADGETS", 5),
                new StandardItem(2, "Dancing Panda v.3 with USB port", 6000, "GADGETS", 10),
                new StandardItem(3, "Screwdriver", 200, "TOOLS", 5),
                new DiscountedItem(4, "Star Wars Jedi buzzer", 500, "GADGETS", 30, "1.8.2013", "1.12.2013"),
                new DiscountedItem(5, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013"),
                new DiscountedItem(6, "Soft toy Angry bird (size 40cm)", 800, "GADGETS", 10, "1.8.2013", "1.12.2013")
        };

        for (int i = 0; i < storageItems.length; i++) {
            storage.insertItems(storageItems[i], itemCount[i]);
        }

        storage.removeItems(storageItems[0], 5);
        Assertions.assertEquals(5, storage.getItemCount(1));

        newCart = new ShoppingCart();
        newCart.addItem(storageItems[0]);
        newCart.addItem(storageItems[1]);
        newCart.addItem(storageItems[2]);
        newCart.addItem(storageItems[4]);
        newCart.addItem(storageItems[5]);

        newCart.removeItem(6);
        Assertions.assertEquals(5, newCart.getItemsCount());

        StandardItem missedItem = new StandardItem(111, "name", 20, "category", 5);
        newCart.addItem(missedItem);
        Exception exception = Assertions.assertThrows(NoItemInStorage.class, () ->
                EShopController.purchaseShoppingCart(newCart, "Libuse Novakova","Kosmonautu 25, Praha 8"));
        Assertions.assertEquals("No item in storage", exception.getMessage());

        newCart.removeItem(111);
        Assertions.assertEquals(5, archive.getHowManyTimesHasBeenItemSold(storageItems[0]));
    }
}