package com.bank.util;

public class InterestCalculator {
    public double calculateSimpleInterest(double principal, double rate, double time) {
        // SI = (P × R × T) / 100
        return (principal * rate * time) / 100;
    }
    
    public double calculateCompoundInterest(double principal, double rate, double time) {
        // CI = P × (pow((1 + R/100), T)) - P
        return principal * (Math.pow((1 + rate/100), time)) - principal;
    }
}