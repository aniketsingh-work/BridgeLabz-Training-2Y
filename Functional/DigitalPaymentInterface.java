// Interface defining the common method for all payment methods
interface PaymentMethod {
    boolean pay(double amount);
}

// Implementation for UPI payment
class UPIPayment implements PaymentMethod {
    private String upiId;
    private double balance;
    
    public UPIPayment(String upiId, double balance) {
        this.upiId = upiId;
        this.balance = balance;
    }
    
    @Override
    public boolean pay(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount for UPI payment");
            return false;
        }
        
        if (balance >= amount) {
            balance -= amount;
            System.out.println("UPI Payment of $" + amount + " successful using " + upiId);
            System.out.println("Remaining balance: $" + balance);
            return true;
        } else {
            System.out.println("Insufficient balance for UPI payment. Current balance: $" + balance);
            return false;
        }
    }
    
    public double getBalance() {
        return balance;
    }
}

// Implementation for Credit Card payment
class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private double creditLimit;
    private double availableCredit;
    
    public CreditCardPayment(String cardNumber, String cardHolderName, double creditLimit) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.creditLimit = creditLimit;
        this.availableCredit = creditLimit;
    }
    
    @Override
    public boolean pay(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount for credit card payment");
            return false;
        }
        
        if (availableCredit >= amount) {
            availableCredit -= amount;
            System.out.println("Credit Card Payment of $" + amount + " successful for card ending in " + 
                             cardNumber.substring(cardNumber.length() - 4));
            System.out.println("Available credit: $" + availableCredit);
            return true;
        } else {
            System.out.println("Insufficient credit for payment. Available credit: $" + availableCredit);
            return false;
        }
    }
    
    public double getAvailableCredit() {
        return availableCredit;
    }
}

// Implementation for Wallet payment
class WalletPayment implements PaymentMethod {
    private String walletId;
    private String ownerName;
    private double balance;
    
    public WalletPayment(String walletId, String ownerName, double balance) {
        this.walletId = walletId;
        this.ownerName = ownerName;
        this.balance = balance;
    }
    
    @Override
    public boolean pay(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount for wallet payment");
            return false;
        }
        
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Wallet Payment of $" + amount + " successful using " + walletId);
            System.out.println("Remaining wallet balance: $" + balance);
            return true;
        } else {
            System.out.println("Insufficient wallet balance for payment. Current balance: $" + balance);
            return false;
        }
    }
    
    public double getBalance() {
        return balance;
    }
}

public class DigitalPaymentInterface {
    public static void main(String[] args) {
        // Create instances of different payment methods
        PaymentMethod upi = new UPIPayment("user@upi", 500.0);
        PaymentMethod creditCard = new CreditCardPayment("1234567890123456", "John Doe", 2000.0);
        PaymentMethod wallet = new WalletPayment("wallet123", "John Doe", 100.0);
        
        System.out.println("Digital Payment System:");
        System.out.println("=======================");
        
        // Process payments using different methods
        System.out.println("Processing payments:");
        
        upi.pay(75.50);
        System.out.println();
        
        creditCard.pay(150.00);
        System.out.println();
        
        wallet.pay(45.75);
        System.out.println();
        
        // Try to process a payment that exceeds the balance
        System.out.println("Trying to process payment exceeding balance:");
        wallet.pay(100.00);  // Wallet has only $54.25 left
        System.out.println();
        
        // Process another payment
        upi.pay(25.00);
        System.out.println();
        
        System.out.println("Using interface polymorphism:");
        System.out.println("==============================");
        
        // Array of payment methods using the interface
        PaymentMethod[] paymentMethods = {
            new UPIPayment("merchant@upi", 1000.0),
            new CreditCardPayment("9876543210987654", "Jane Smith", 3000.0),
            new WalletPayment("wallet456", "Jane Smith", 200.0)
        };
        
        // Process payments with different methods
        double[] amounts = {50.0, 75.0, 30.0};
        
        for (int i = 0; i < paymentMethods.length; i++) {
            System.out.println("Processing payment of $" + amounts[i] + ":");
            paymentMethods[i].pay(amounts[i]);
            System.out.println();
        }
        
        System.out.println("Interface ensures all payment methods implement required method");
        System.out.println("while allowing each payment type to have its specific behavior.");
    }
}