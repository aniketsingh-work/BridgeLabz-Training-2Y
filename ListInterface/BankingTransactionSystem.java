import java.util.*;

public class BankingTransactionSystem {
    
    static class Account {
        private String accountId;
        private String accountHolderName;
        private double balance;
        
        public Account(String accountId, String accountHolderName, double balance) {
            this.accountId = accountId;
            this.accountHolderName = accountHolderName;
            this.balance = balance;
        }
        
        // Getters and setters
        public String getAccountId() { return accountId; }
        public String getAccountHolderName() { return accountHolderName; }
        public double getBalance() { return balance; }
        public void setBalance(double balance) { this.balance = balance; }
        
        @Override
        public String toString() {
            return accountHolderName + "(" + accountId + ", Balance: " + balance + ")";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Account account = (Account) obj;
            return Objects.equals(accountId, account.accountId);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(accountId);
        }
    }
    
    static class Transaction {
        private String transactionId;
        private String accountId;
        private String transactionType; // DEPOSIT, WITHDRAWAL, TRANSFER
        private double amount;
        private Date timestamp;
        private String description;
        
        public Transaction(String transactionId, String accountId, String transactionType, 
                          double amount, String description) {
            this.transactionId = transactionId;
            this.accountId = accountId;
            this.transactionType = transactionType;
            this.amount = amount;
            this.timestamp = new Date();
            this.description = description;
        }
        
        // Getters
        public String getTransactionId() { return transactionId; }
        public String getAccountId() { return accountId; }
        public String getTransactionType() { return transactionType; }
        public double getAmount() { return amount; }
        public Date getTimestamp() { return timestamp; }
        public String getDescription() { return description; }
        
        @Override
        public String toString() {
            return transactionId + "(" + transactionType + ", " + amount + ", " + timestamp + ")";
        }
    }
    
    // Use List to record all transactions
    private List<Transaction> allTransactions;
    
    // Use Queue for pending transactions
    private Queue<Transaction> pendingTransactions;
    
    // Use Set to ensure only valid customer accounts are processed
    private Set<Account> validAccounts;
    
    // Use Stack for rollback (undo) functionality in case of failure
    private Stack<Transaction> rollbackStack;
    
    public BankingTransactionSystem() {
        this.allTransactions = new ArrayList<>();
        this.pendingTransactions = new LinkedList<>();
        this.validAccounts = new HashSet<>();
        this.rollbackStack = new Stack<>();
    }
    
    // 1. Add transactions to the queue
    public void addTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
        System.out.println("Added transaction to queue: " + transaction);
    }
    
    // 2. Validate account IDs using a set
    public boolean validateAccount(String accountId) {
        for (Account account : validAccounts) {
            if (account.getAccountId().equals(accountId)) {
                return true;
            }
        }
        return false;
    }
    
    public void addValidAccount(Account account) {
        validAccounts.add(account);
        System.out.println("Added valid account: " + account);
    }
    
    // 3. Execute transactions sequentially
    public boolean executeNextTransaction() {
        if (pendingTransactions.isEmpty()) {
            System.out.println("No pending transactions to execute");
            return false;
        }
        
        Transaction transaction = pendingTransactions.remove();
        
        // Validate account first
        if (!validateAccount(transaction.getAccountId())) {
            System.out.println("Invalid account: " + transaction.getAccountId() + 
                             " for transaction: " + transaction.getTransactionId());
            return false;
        }
        
        // Find the account to update
        Account targetAccount = null;
        for (Account account : validAccounts) {
            if (account.getAccountId().equals(transaction.getAccountId())) {
                targetAccount = account;
                break;
            }
        }
        
        if (targetAccount == null) {
            System.out.println("Account not found: " + transaction.getAccountId());
            return false;
        }
        
        // Process the transaction
        boolean success = false;
        try {
            switch (transaction.getTransactionType().toUpperCase()) {
                case "DEPOSIT":
                    targetAccount.setBalance(targetAccount.getBalance() + transaction.getAmount());
                    success = true;
                    break;
                case "WITHDRAWAL":
                    if (targetAccount.getBalance() >= transaction.getAmount()) {
                        targetAccount.setBalance(targetAccount.getBalance() - transaction.getAmount());
                        success = true;
                    } else {
                        System.out.println("Insufficient funds for withdrawal: " + transaction.getTransactionId());
                        success = false;
                    }
                    break;
                case "TRANSFER":
                    // For simplicity, we'll handle transfers as withdrawals from source
                    // In a real system, we'd need a destination account as well
                    if (targetAccount.getBalance() >= transaction.getAmount()) {
                        targetAccount.setBalance(targetAccount.getBalance() - transaction.getAmount());
                        success = true;
                    } else {
                        System.out.println("Insufficient funds for transfer: " + transaction.getTransactionId());
                        success = false;
                    }
                    break;
                default:
                    System.out.println("Unknown transaction type: " + transaction.getTransactionType());
                    success = false;
            }
        } catch (Exception e) {
            System.out.println("Error processing transaction: " + e.getMessage());
            success = false;
        }
        
        if (success) {
            // Add to all transactions and push to rollback stack
            allTransactions.add(transaction);
            rollbackStack.push(transaction);
            System.out.println("Executed transaction: " + transaction.getTransactionId() + 
                             " on account: " + targetAccount.getAccountHolderName());
        } else {
            System.out.println("Failed to execute transaction: " + transaction.getTransactionId());
        }
        
        return success;
    }
    
    // 4. Roll back the last transaction using the stack
    public boolean rollbackLastTransaction() {
        if (rollbackStack.isEmpty()) {
            System.out.println("No transactions to rollback");
            return false;
        }
        
        Transaction lastTransaction = rollbackStack.pop();
        
        // Find the account to revert
        Account targetAccount = null;
        for (Account account : validAccounts) {
            if (account.getAccountId().equals(lastTransaction.getAccountId())) {
                targetAccount = account;
                break;
            }
        }
        
        if (targetAccount == null) {
            System.out.println("Cannot find account for rollback: " + lastTransaction.getAccountId());
            // Put the transaction back on the stack
            rollbackStack.push(lastTransaction);
            return false;
        }
        
        // Revert the transaction
        switch (lastTransaction.getTransactionType().toUpperCase()) {
            case "DEPOSIT":
                targetAccount.setBalance(targetAccount.getBalance() - lastTransaction.getAmount());
                break;
            case "WITHDRAWAL":
                targetAccount.setBalance(targetAccount.getBalance() + lastTransaction.getAmount());
                break;
            case "TRANSFER":
                // For transfers, add back the amount
                targetAccount.setBalance(targetAccount.getBalance() + lastTransaction.getAmount());
                break;
        }
        
        // Remove from all transactions
        allTransactions.remove(lastTransaction);
        
        System.out.println("Rolled back transaction: " + lastTransaction.getTransactionId());
        return true;
    }
    
    // Getters
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(allTransactions);
    }
    
    public Queue<Transaction> getPendingTransactions() {
        return new LinkedList<>(pendingTransactions);
    }
    
    public Set<Account> getValidAccounts() {
        return new HashSet<>(validAccounts);
    }
    
    public Stack<Transaction> getRollbackStack() {
        return (Stack<Transaction>) rollbackStack.clone();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Banking Transaction System ===");
        
        BankingTransactionSystem bankSystem = new BankingTransactionSystem();
        
        // Add some valid accounts
        bankSystem.addValidAccount(new Account("ACC001", "John Doe", 1000.0));
        bankSystem.addValidAccount(new Account("ACC002", "Jane Smith", 1500.0));
        bankSystem.addValidAccount(new Account("ACC003", "Bob Johnson", 500.0));
        
        // Add some transactions to the queue
        bankSystem.addTransaction(new Transaction("TXN001", "ACC001", "DEPOSIT", 200.0, "Salary deposit"));
        bankSystem.addTransaction(new Transaction("TXN02", "ACC002", "WITHDRAWAL", 100.0, "ATM withdrawal"));
        bankSystem.addTransaction(new Transaction("TXN003", "ACC003", "DEPOSIT", 300.0, "Gift money"));
        bankSystem.addTransaction(new Transaction("TXN004", "ACC001", "WITHDRAWAL", 50.0, "Purchase"));
        bankSystem.addTransaction(new Transaction("TXN005", "ACC004", "DEPOSIT", 100.0, "Invalid account")); // This should fail
        
        System.out.println("\n1. Valid accounts:");
        for (Account account : bankSystem.getValidAccounts()) {
            System.out.println(" " + account);
        }
        
        System.out.println("\n2. Executing transactions sequentially:");
        while (bankSystem.getPendingTransactions().size() > 0) {
            bankSystem.executeNextTransaction();
        }
        
        System.out.println("\n3. All transactions executed:");
        for (Transaction txn : bankSystem.getAllTransactions()) {
            System.out.println(" " + txn);
        }
        
        System.out.println("\n4. Current account balances:");
        for (Account account : bankSystem.getValidAccounts()) {
            System.out.println(" " + account);
        }
        
        System.out.println("\n5. Rolling back last transaction:");
        bankSystem.rollbackLastTransaction();
        
        System.out.println("\n6. Account balances after rollback:");
        for (Account account : bankSystem.getValidAccounts()) {
            System.out.println(" " + account);
        }
    }
}