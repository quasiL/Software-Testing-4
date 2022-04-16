package shop;

public class StandardItem extends Item{

    /**
     * The class for standard item in the EShop.
     */

    private int loyaltyPoints;

    public StandardItem(int id, String name, float price, String category, int loyaltyPoints) {
        super(id, name, price, category);
        this.loyaltyPoints = loyaltyPoints;
    }

    public String toString() {
        return super.toString()+"   PRICE "+getPrice()+"   LOYALTY POINTS "+loyaltyPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean equals(Object object) {
        if (object instanceof shop.StandardItem) {
            shop.StandardItem zbozi = (shop.StandardItem) object;
            return (super.equals(zbozi)
                    && loyaltyPoints == zbozi.getLoyaltyPoints());
        }
        return false;
    }

    public shop.StandardItem copy() {
        return new shop.StandardItem(getID(), getName(), getPrice(), getCategory(), loyaltyPoints);
    }

}
