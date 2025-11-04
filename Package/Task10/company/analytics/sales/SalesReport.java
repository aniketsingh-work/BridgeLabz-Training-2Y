package com.company.analytics.sales;

public class SalesReport {
    private String[] regions = {"North", "South", "East", "West"};
    private double[] salesData = {125000.0, 98000.0, 110000.0, 87000.0};
    
    public void printRegionWiseSalesData() {
        System.out.println("SALES REPORT - REGION-WISE");
        System.out.println("==========================");
        for (int i = 0; i < regions.length; i++) {
            System.out.println(regions[i] + " Region: $" + salesData[i]);
        }
        System.out.println();
    }
    
    public double getTotalSales() {
        double total = 0;
        for (double sale : salesData) {
            total += sale;
        }
        return total;
    }
}