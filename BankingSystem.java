import java.util.*;

public class BankingSystem {
    private Map<String, Double> customerBalances;
    
    public BankingSystem() {
        customerBalances = new HashMap<>();
    }
    
    public void addCustomer(String accountNumber, double initialBalance) {
        customerBalances.put(accountNumber, initialBalance);
    }
    
    public boolean deposit(String accountNumber, double amount) {
        if (customerBalances.containsKey(accountNumber)) {
            customerBalances.put(accountNumber, customerBalances.get(accountNumber) + amount);
            return true;
        }
        return false;
    }
    
    public boolean withdraw(String accountNumber, double amount) {
        if (customerBalances.containsKey(accountNumber)) {
            double currentBalance = customerBalances.get(accountNumber);
            if (currentBalance >= amount) {
                customerBalances.put(accountNumber, currentBalance - amount);
                return true;
            }
        }
        return false; // Insufficient balance or account not found
    }
    
    public void printCustomersByBalance() {
        List<Map.Entry<String, Double>> entries = new ArrayList<>(customerBalances.entrySet());
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        for (Map.Entry<String, Double> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public List<String> getTop3Customers() {
        List<Map.Entry<String, Double>> entries = new ArrayList<>(customerBalances.entrySet());
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        List<String> top3 = new ArrayList<>();
        int count = 0;
        for (Map.Entry<String, Double> entry : entries) {
            if (count >= 3) break;
            top3.add(entry.getKey());
            count++;
        }
        
        return top3;
    }
    
    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();
        bank.addCustomer("ACC001", 5000.0);
        bank.addCustomer("ACC002", 15000.0);
        bank.addCustomer("ACC003", 8000.0);
        bank.addCustomer("ACC004", 25000.0);
        bank.addCustomer("ACC005", 12000.0);
        
        bank.deposit("ACC01", 2000.0);
        bank.withdraw("ACC003", 3000.0);
        
        System.out.println("Customers by balance (descending):");
        bank.printCustomersByBalance();
        
        System.out.println("Top 3 customers: " + bank.getTop3Customers());
    }
}