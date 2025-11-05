public class MarketplaceDemo {
    // Generic method to apply discounts dynamically
    public static <T extends MarketplaceProduct> void applyDiscount(T product, double percentage) {
        double discount = product.getPrice() * (percentage / 100);
        double newPrice = product.getPrice() - discount;
        System.out.println("Applied " + percentage + "% discount to " + product.getName());
        System.out.println("Old price: $" + product.getPrice() + ", New price: $" + newPrice);
    }
    
    public static void main(String[] args) {
        // Create products with different categories
        MarketplaceProduct<BookCategory> book = new MarketplaceProduct<>(
            "Java Programming", 49.99, new BookCategory("Education")
        );
        
        MarketplaceProduct<ClothingCategory> shirt = new MarketplaceProduct<>(
            "Designer T-Shirt", 29.99, new ClothingCategory("Casual Wear")
        );
        
        MarketplaceProduct<GadgetCategory> smartwatch = new MarketplaceProduct<>(
            "Smart Watch", 199.99, new GadgetCategory("Wearable Tech")
        );
        
        System.out.println("Original products:");
        System.out.println(book);
        System.out.println(shirt);
        System.out.println(smartwatch);
        
        System.out.println("\nApplying discounts:");
        applyDiscount(book, 10);  // 10% discount on book
        System.out.println();
        applyDiscount(shirt, 15); // 15% discount on shirt
        System.out.println();
        applyDiscount(smartwatch, 5); // 5% discount on smartwatch
    }
}