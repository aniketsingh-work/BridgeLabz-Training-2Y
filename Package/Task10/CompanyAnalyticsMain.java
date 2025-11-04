import com.company.analytics.sales.SalesReport;
import com.company.analytics.hr.EmployeeReport;

public class CompanyAnalyticsMain {
    public static void main(String[] args) {
        // Create instances of both report classes
        SalesReport salesReport = new SalesReport();
        EmployeeReport employeeReport = new EmployeeReport();
        
        // Print sales report
        salesReport.printRegionWiseSalesData();
        
        // Print employee report
        employeeReport.printEmployeePerformanceData();
        
        // Combined company report
        System.out.println("COMBINED COMPANY REPORT");
        System.out.println("=======================");
        System.out.println("Total Sales: $" + salesReport.getTotalSales());
        System.out.println("Total Employees: " + employeeReport.getTotalEmployees());
        System.out.println();
        
        System.out.println("Explanation of sub-packages:");
        System.out.println("1. Sub-packages provide logical grouping of related functionality.");
        System.out.println("2. com.company.analytics.sales and com.company.analytics.hr are");
        System.out.println("   both under the com.company.analytics parent package.");
        System.out.println("3. Despite sharing a parent package, sub-packages are independent");
        System.out.println("   of each other in terms of access control.");
        System.out.println("4. Classes in the sales package cannot directly access package-private");
        System.out.println("   members of classes in the hr package, even though they share a parent.");
        System.out.println("5. They can access each other's public members through proper imports.");
    }
}