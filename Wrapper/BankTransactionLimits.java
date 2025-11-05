public class BankTransactionLimits {
    public static void main(String[] args) {
        Double[] limits = {500.0, 1000.0, null, 750.0};
        for (Double limit : limits) {
            double remaining = calculateRemainingLimit(limit, 200.0);
            System.out.println("With limit " + limit + ", remaining after withdrawal: " + remaining);
        }
    }
    
    public static double calculateRemainingLimit(Double limit, double withdrawal) {
        if (limit == null) {
            return 0.0;
        }
        return limit - withdrawal;
    }
}