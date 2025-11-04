import static java.lang.Math.*;
import com.bank.util.InterestCalculator;

public class BankingMain {
    public static void main(String[] args) {
        InterestCalculator calc = new InterestCalculator();
        
        double principal = 10000.0;
        double rate = 5.0;  // 5%
        double time = 3.0;  // 3 years
        
        System.out.println("Banking System - Interest Calculations");
        System.out.println("=====================================");
        System.out.println("Principal Amount: $" + principal);
        System.out.println("Rate of Interest: " + rate + "%");
        System.out.println("Time Period: " + time + " years");
        System.out.println();
        
        // Calculate Simple Interest
        double si = calc.calculateSimpleInterest(principal, rate, time);
        System.out.println("Simple Interest: $" + round(si * 100.0) / 100.0);  // Round to 2 decimal places
        
        // Calculate Compound Interest using static import of Math.pow()
        double ci = calc.calculateCompoundInterest(principal, rate, time);
        System.out.println("Compound Interest: $" + round(ci * 10.0) / 100.0);  // Round to 2 decimal places
        
        // Demonstrating other Math functions with static import
        System.out.println("\nOther Math operations using static import:");
        System.out.println("Square root of principal: " + round(sqrt(principal) * 100.0) / 100.0);
        System.out.println("Max of rate and time: " + max(rate, time));
        System.out.println("Min of rate and time: " + min(rate, time));
        System.out.println("Principal raised to power of 1.5: " + round(pow(principal, 1.5) * 100.0) / 100.0);
    }
}