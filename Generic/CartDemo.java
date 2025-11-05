// Demo class to demonstrate the Cart<T> class
public class CartDemo {
    public static void main(String[] args) {
        // Create a cart for electronics
        Cart<String> electronicsCart = new Cart<>();
        electronicsCart.addItem("Laptop");
        electronicsCart.addItem("Smartphone");
        electronicsCart.addItem("Headphones");
        System.out.println("Electronics Cart:");
        electronicsCart.displayItems();
        
        // Create a cart for clothing
        Cart<String> clothingCart = new Cart<>();
        clothingCart.addItem("T-Shirt");
        clothingCart.addItem("Jeans");
        clothingCart.addItem("Sneakers");
        System.out.println("\nClothing Cart:");
        clothingCart.displayItems();
        
        // Remove an item from the electronics cart
        electronicsCart.removeItem("Headphones");
        System.out.println("\nElectronics Cart after removing headphones:");
        electronicsCart.displayItems();
        
        // Show that one cart can hold only electronics and another only clothes
        // (demonstrated by the type safety - we can't add a clothing item to electronicsCart
        // or an electronic item to clothingCart without changing the type)
    }
}