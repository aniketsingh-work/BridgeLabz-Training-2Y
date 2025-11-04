import java.util.*;
import java.util.stream.Collectors;

class Invoice {
    private String invoiceId;
    private String transactionId;
    private double amount;
    private String customerName;
    private String date;
    
    // Constructor that takes a transaction ID and creates an invoice
    public Invoice(String transactionId) {
        this.transactionId = transactionId;
        this.invoiceId = "INV-" + transactionId.substring(1); // Convert TRX001 to INV001
        this.amount = generateRandomAmount(); // Simulate amount based on transaction
        this.customerName = "Customer for " + transactionId;
        this.date = "2023-10-01"; // Simulated date
    }
    
    private double generateRandomAmount() {
        // Generate a random amount between 50 and 500 for simulation
        Random rand = new Random();
        return Math.round((rand.nextDouble() * 450 + 50) * 100.0) / 100.0;
    }
    
    public String getInvoiceId() {
        return invoiceId;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public String getDate() {
        return date;
    }
    
    @Override
    public String toString() {
        return String.format("Invoice ID: %s | Transaction ID: %s | Amount: $%.2f | Customer: %s | Date: %s", 
                           invoiceId, transactionId, amount, customerName, date);
    }
}

public class InvoiceObjectCreation {
    public static void main(String[] args) {
        List<String> transactionIds = Arrays.asList(
            "TRX01", "TRX002", "TRX003", "TRX004", "TRX005", 
            "TRX006", "TRX007", "TRX008", "TRX009", "TRX010"
        );
        
        System.out.println("Transaction IDs:");
        transactionIds.forEach(System.out::println);
        System.out.println();
        
        // Generate invoice objects from transaction IDs using constructor reference
        System.out.println("Generated Invoices (using constructor reference Invoice::new):");
        List<Invoice> invoices = transactionIds.stream()
                .map(Invoice::new)  // Using constructor reference instead of lambda
                .collect(Collectors.toList());
        
        invoices.forEach(System.out::println);
        System.out.println();
        
        // Alternative approach using lambda (for comparison)
        System.out.println("Generated Invoices (using lambda for comparison):");
        List<Invoice> invoicesWithLambda = transactionIds.stream()
                .map(id -> new Invoice(id))  // Using lambda instead of constructor reference
                .collect(Collectors.toList());
        
        // Just show the first 3 to compare
        invoicesWithLambda.stream()
                .limit(3)
                .forEach(System.out::println);
        System.out.println();
        
        // Filter invoices based on amount
        System.out.println("Invoices with amount > $200:");
        List<Invoice> highValueInvoices = invoices.stream()
                .filter(invoice -> invoice.getAmount() > 200.0)
                .collect(Collectors.toList());
        highValueInvoices.forEach(System.out::println);
        System.out.println();
        
        // Get total amount for all invoices
        double totalAmount = invoices.stream()
                .mapToDouble(Invoice::getAmount)
                .sum();
        System.out.printf("Total amount of all invoices: $%.2f%n", totalAmount);
        System.out.println();
        
        // Get invoice IDs only
        System.out.println("Invoice IDs only:");
        List<String> invoiceIds = invoices.stream()
                .map(Invoice::getInvoiceId)
                .collect(Collectors.toList());
        invoiceIds.forEach(System.out::println);
        System.out.println();
        
        System.out.println("Constructor references (Invoice::new) provide a clean way to");
        System.out.println("create objects from a stream of input values, making the code");
        System.out.println("more readable and concise than equivalent lambda expressions.");
    }
}