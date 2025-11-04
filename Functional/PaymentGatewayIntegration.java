// Original interface without refund method
interface PaymentProcessor {
    boolean processPayment(double amount, String cardNumber);
    
    // Default method added later - this is the key feature of default methods
    // It allows adding new functionality without breaking existing implementations
    default boolean refund(double amount, String transactionId) {
        System.out.println("Refund of $" + amount + " initiated for transaction " + transactionId);
        System.out.println("Default refund processing - this may take 3-5 business days");
        return true; // Simulate successful refund
    }
    
    // Another default method for transaction validation
    default boolean validateTransaction(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid transaction amount: $" + amount);
            return false;
        }
        
        if (amount > 10000) { // Arbitrary high-value threshold
            System.out.println("High-value transaction detected: $" + amount);
            System.out.println("Additional security verification required");
        }
        
        return true;
    }
    
    // Method to get transaction fee (default implementation)
    default double getTransactionFee(double amount) {
        // Default fee is 2.9% of transaction amount
        return amount * 0.029;
    }
}

// Existing implementation before refund method was added
class PayPalProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount, String cardNumber) {
        System.out.println("Processing PayPal payment of $" + amount);
        System.out.println("Connected to PayPal API");
        return true;
    }
}

// Another existing implementation
class StripeProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount, String cardNumber) {
        System.out.println("Processing Stripe payment of $" + amount);
        System.out.println("Using Stripe API with card ending in " + cardNumber.substring(cardNumber.length() - 4));
        return true;
    }
}

// New implementation that can optionally override default methods
class SquareProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount, String cardNumber) {
        System.out.println("Processing Square payment of $" + amount);
        System.out.println("Using Square payment system");
        return true;
    }
    
    // Override the default refund method with Square-specific implementation
    @Override
    public boolean refund(double amount, String transactionId) {
        System.out.println("Square-specific refund processing for $" + amount);
        System.out.println("Refund initiated via Square dashboard for transaction " + transactionId);
        System.out.println("Refund will be processed within 1-2 business days");
        return true;
    }
}

public class PaymentGatewayIntegration {
    public static void main(String[] args) {
        System.out.println("Payment Gateway Integration with Default Methods:");
        System.out.println("================================================");
        
        // Create instances of different payment processors
        PaymentProcessor[] processors = {
            new PayPalProcessor(),
            new StripeProcessor(),
            new SquareProcessor()
        };
        
        // Process payments using all processors
        System.out.println("Processing Payments:");
        for (PaymentProcessor processor : processors) {
            System.out.println("\nUsing " + processor.getClass().getSimpleName() + ":");
            
            // Validate transaction
            if (processor.validateTransaction(250.0)) {
                // Process payment
                processor.processPayment(250.0, "1234567890123456");
                
                // Calculate and display fee
                double fee = processor.getTransactionFee(250.0);
                System.out.println("Transaction fee: $" + String.format("%.2f", fee));
            }
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Refund Processing (New Feature Added via Default Method):");
        System.out.println("=========================================================");
        
        // Process refunds using all processors (the refund method was added later as a default method)
        for (PaymentProcessor processor : processors) {
            System.out.println("\nProcessing refund with " + processor.getClass().getSimpleName() + ":");
            processor.refund(250.0, "TXN001");
        }
        
        // Demonstrate Square's custom refund implementation
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Square's Custom Refund Implementation:");
        System.out.println("======================================");
        
        SquareProcessor square = new SquareProcessor();
        square.refund(150.0, "TXN002");
        
        // Example of a high-value transaction
        System.out.println("\n" + "=".repeat(50));
        System.out.println("High-Value Transaction Example:");
        System.out.println("===============================");
        
        PayPalProcessor paypal = new PayPalProcessor();
        if (paypal.validateTransaction(15000.0)) {
            paypal.processPayment(15000.0, "9876543210987654");
            double fee = paypal.getTransactionFee(15000.0);
            System.out.println("Transaction fee: $" + String.format("%.2f", fee));
        }
        
        // Refund the high-value transaction
        paypal.refund(15000.0, "TXN003");
        
        System.out.println("\nDefault methods in interfaces allow adding new functionality without");
        System.out.println("breaking existing implementations, enabling evolutionary API design.");
    }
}