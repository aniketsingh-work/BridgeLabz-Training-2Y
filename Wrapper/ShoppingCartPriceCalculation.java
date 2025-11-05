public class ShoppingCartPriceCalculation {
    public static void main(String[] args) {
        String[] stringPrices = {"250", "499", "99", "abc", "0"};
        int totalPrice = 0;
        
        for (String priceStr : stringPrices) {
            try {
                int price = Integer.parseInt(priceStr);
                totalPrice += price;
                System.out.println("Added price: " + price);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price '" + priceStr + "' ignored");
            }
        }
        System.out.println("Total price: " + totalPrice);
    }
}