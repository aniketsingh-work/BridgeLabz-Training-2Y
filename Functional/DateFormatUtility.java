import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

interface DateUtils {
    // Static method to format date in various formats
    static String formatDate(LocalDate date, String format) {
        if (date == null) return "Invalid date";
        
        switch (format.toLowerCase()) {
            case "us":
                return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            case "eu":
                return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            case "iso":
                return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
            case "long":
                return date.format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy"));
            case "short":
                return date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
            case "time":
                return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
            default:
                return date.format(DateTimeFormatter.ofPattern(format));
        }
    }
    
    // Static method to format LocalDateTime
    static String formatDateTime(LocalDateTime dateTime, String format) {
        if (dateTime == null) return "Invalid date time";
        
        switch (format.toLowerCase()) {
            case "us":
                return dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
            case "eu":
                return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            case "iso":
                return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            case "long":
                return dateTime.format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy 'at' HH:mm:ss"));
            case "short":
                return dateTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"));
            default:
                return dateTime.format(DateTimeFormatter.ofPattern(format));
        }
    }
    
    // Static method to get current date in specific format
    static String getCurrentDate(String format) {
        return formatDate(LocalDate.now(), format);
    }
    
    // Static method to get current date and time in specific format
    static String getCurrentDateTime(String format) {
        return formatDateTime(LocalDateTime.now(), format);
    }
    
    // Static method to parse date string to LocalDate
    static LocalDate parseDate(String dateString, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
}

class Invoice {
    private String invoiceId;
    private String customerName;
    private double amount;
    private LocalDate issueDate;
    private LocalDate dueDate;
    
    public Invoice(String invoiceId, String customerName, double amount, LocalDate issueDate, LocalDate dueDate) {
        this.invoiceId = invoiceId;
        this.customerName = customerName;
        this.amount = amount;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }
    
    public String getInvoiceId() {
        return invoiceId;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public LocalDate getIssueDate() {
        return issueDate;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    @Override
    public String toString() {
        return String.format("Invoice{id='%s', customer='%s', amount=$%.2f, issueDate=%s, dueDate=%s}", 
                           invoiceId, customerName, amount, 
                           DateUtils.formatDate(issueDate, "iso"), 
                           DateUtils.formatDate(dueDate, "iso"));
    }
}

public class DateFormatUtility {
    public static void main(String[] args) {
        System.out.println("Date Format Utility for Invoice Generator:");
        System.out.println("==========================================");
        
        // Test individual date formatting
        System.out.println("Individual Date Formatting:");
        LocalDate testDate = LocalDate.of(2023, 10, 15);
        
        System.out.println("Original date: " + testDate);
        System.out.println("US format (MM/dd/yyyy): " + DateUtils.formatDate(testDate, "us"));
        System.out.println("EU format (dd/MM/yyyy): " + DateUtils.formatDate(testDate, "eu"));
        System.out.println("ISO format: " + DateUtils.formatDate(testDate, "iso"));
        System.out.println("Long format: " + DateUtils.formatDate(testDate, "long"));
        System.out.println("Short format: " + DateUtils.formatDate(testDate, "short"));
        System.out.println();
        
        // Test date time formatting
        System.out.println("Date Time Formatting:");
        LocalDateTime testDateTime = LocalDateTime.of(2023, 10, 15, 14, 30, 45);
        
        System.out.println("Original datetime: " + testDateTime);
        System.out.println("US format: " + DateUtils.formatDateTime(testDateTime, "us"));
        System.out.println("EU format: " + DateUtils.formatDateTime(testDateTime, "eu"));
        System.out.println("ISO format: " + DateUtils.formatDateTime(testDateTime, "iso"));
        System.out.println("Long format: " + DateUtils.formatDateTime(testDateTime, "long"));
        System.out.println();
        
        // Current date/time examples
        System.out.println("Current Date/Time Examples:");
        System.out.println("Current date (US): " + DateUtils.getCurrentDate("us"));
        System.out.println("Current date (EU): " + DateUtils.getCurrentDate("eu"));
        System.out.println("Current date (ISO): " + DateUtils.getCurrentDate("iso"));
        System.out.println("Current datetime (US): " + DateUtils.getCurrentDateTime("us"));
        System.out.println("Current datetime (Long): " + DateUtils.getCurrentDateTime("long"));
        System.out.println();
        
        // Invoice generation scenario
        System.out.println("Invoice Generation System:");
        System.out.println("==========================");
        
        // Create sample invoices
        Invoice[] invoices = {
            new Invoice("INV-001", "ABC Corp", 1500.00, 
                       LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 31)),
            new Invoice("INV-002", "XYZ Ltd", 2750.50, 
                       LocalDate.of(2023, 10, 5), LocalDate.of(2023, 11, 4)),
            new Invoice("INV-003", "123 Inc", 899.99, 
                       LocalDate.of(2023, 9, 20), LocalDate.of(2023, 10, 20))
        };
        
        // Display invoices with different date formats for different clients
        for (Invoice invoice : invoices) {
            System.out.println("Invoice: " + invoice.getInvoiceId());
            System.out.println("  Customer: " + invoice.getCustomerName());
            System.out.println("  Amount: $" + invoice.getAmount());
            System.out.println("  Issue Date (US format): " + 
                              DateUtils.formatDate(invoice.getIssueDate(), "us"));
            System.out.println(" Issue Date (EU format): " + 
                              DateUtils.formatDate(invoice.getIssueDate(), "eu"));
            System.out.println("  Due Date (ISO format): " + 
                              DateUtils.formatDate(invoice.getDueDate(), "iso"));
            System.out.println(" Due Date (Long format): " + 
                              DateUtils.formatDate(invoice.getDueDate(), "long"));
            System.out.println();
        }
        
        // Different format for different regions
        System.out.println("Regional Date Formatting:");
        LocalDate dueDate = LocalDate.of(2023, 12, 25);
        System.out.println("Due date for US clients: " + DateUtils.formatDate(dueDate, "us"));
        System.out.println("Due date for EU clients: " + DateUtils.formatDate(dueDate, "eu"));
        System.out.println("Due date for internal use: " + DateUtils.formatDate(dueDate, "iso"));
        System.out.println("Due date for formal letters: " + DateUtils.formatDate(dueDate, "long"));
        
        // Parsing dates
        System.out.println("\nDate Parsing Examples:");
        String dateString = "12/25/2023";
        LocalDate parsedDate = DateUtils.parseDate(dateString, "MM/dd/yyyy");
        System.out.println("Parsed date " + dateString + " = " + parsedDate);
        
        String dateString2 = "25/12/2023";
        LocalDate parsedDate2 = DateUtils.parseDate(dateString2, "dd/MM/yyyy");
        System.out.println("Parsed date " + dateString2 + " = " + parsedDate2);
        
        System.out.println("\nStatic methods in interfaces provide centralized date formatting utilities");
        System.out.println("that can be used without implementing the interface, ideal for invoice systems.");
    }
}