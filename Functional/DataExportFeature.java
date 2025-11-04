import java.util.*;

// Original interface for data reporting
interface DataExporter {
    // Original method for CSV export
    void exportToCSV(List<Map<String, Object>> data);
    
    // New default method for JSON export - added without breaking existing implementations
    default void exportToJSON(List<Map<String, Object>> data) {
        System.out.println("Exporting data to JSON format:");
        System.out.println("{");
        System.out.println("  \"data\": [");
        
        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> row = data.get(i);
            System.out.print("    {");
            
            int j = 0;
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                System.out.print("\"" + entry.getKey() + "\": \"" + entry.getValue() + "\"");
                if (j < row.size() - 1) {
                    System.out.print(", ");
                }
                j++;
            }
            
            System.out.print("}");
            if (i < data.size() - 1) {
                System.out.print(",");
            }
            System.out.println();
        }
        
        System.out.println("  ]");
        System.out.println("}");
    }
    
    // New default method for PDF export
    default void exportToPDF(List<Map<String, Object>> data) {
        System.out.println("Generating PDF report with " + data.size() + " records...");
        System.out.println("PDF export completed successfully.");
    }
    
    // Default method for data validation
    default boolean validateData(List<Map<String, Object>> data) {
        if (data == null || data.isEmpty()) {
            System.out.println("Warning: No data to export");
            return false;
        }
        
        System.out.println("Validating data before export...");
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null) {
                System.out.println("Error: Null record found at index " + i);
                return false;
            }
        }
        
        System.out.println("Data validation passed.");
        return true;
    }
    
    // Default method for export statistics
    default void printExportStats(List<Map<String, Object>> data, String format) {
        System.out.println("Export completed: " + data.size() + " records exported to " + format + " format");
        System.out.println("Timestamp: " + new Date());
    }
}

// Existing implementation before JSON export was added
class SalesReportExporter implements DataExporter {
    @Override
    public void exportToCSV(List<Map<String, Object>> data) {
        System.out.println("Exporting sales data to CSV:");
        if (data != null && !data.isEmpty()) {
            // Print headers
            System.out.print("Product,Quantity,Price,Total\n");
            
            for (Map<String, Object> row : data) {
                System.out.printf("%s,%s,%s,%s\n", 
                    row.get("Product"), 
                    row.get("Quantity"), 
                    row.get("Price"), 
                    row.get("Total"));
            }
        }
        System.out.println();
    }
}

// Another existing implementation
class UserReportExporter implements DataExporter {
    @Override
    public void exportToCSV(List<Map<String, Object>> data) {
        System.out.println("Exporting user data to CSV:");
        if (data != null && !data.isEmpty()) {
            // Print headers
            System.out.print("ID,Name,Email,Role\n");
            
            for (Map<String, Object> row : data) {
                System.out.printf("%s,%s,%s,%s\n", 
                    row.get("ID"), 
                    row.get("Name"), 
                    row.get("Email"), 
                    row.get("Role"));
            }
        }
        System.out.println();
    }
}

// New implementation that can optionally override default methods
class AnalyticsExporter implements DataExporter {
    @Override
    public void exportToCSV(List<Map<String, Object>> data) {
        System.out.println("Exporting analytics data to CSV:");
        if (data != null && !data.isEmpty()) {
            // Print headers
            System.out.print("Metric,Value,Date,Source\n");
            
            for (Map<String, Object> row : data) {
                System.out.printf("%s,%s,%s,%s\n", 
                    row.get("Metric"), 
                    row.get("Value"), 
                    row.get("Date"), 
                    row.get("Source"));
            }
        }
        System.out.println();
    }
    
    // Override the default JSON export method with analytics-specific formatting
    @Override
    public void exportToJSON(List<Map<String, Object>> data) {
        System.out.println("Exporting analytics data to specialized JSON format:");
        System.out.println("{");
        System.out.println("  \"analytics_data\": {");
        System.out.println("    \"records\": " + data.size() + ",");
        System.out.println("    \"data\": [");
        
        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> row = data.get(i);
            System.out.print("      {");
            
            int j = 0;
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                System.out.print("\"" + entry.getKey() + "\": \"" + entry.getValue() + "\"");
                if (j < row.size() - 1) {
                    System.out.print(", ");
                }
                j++;
            }
            
            System.out.print("}");
            if (i < data.size() - 1) {
                System.out.print(",");
            }
            System.out.println();
        }
        
        System.out.println("    ]");
        System.out.println("  }");
        System.out.println("}");
    }
}

public class DataExportFeature {
    public static void main(String[] args) {
        System.out.println("Data Export Feature with Default Methods:");
        System.out.println("=========================================");
        
        // Sample data for export
        List<Map<String, Object>> salesData = Arrays.asList(
            createRecord("Laptop", 5, "$1200", "$6000"),
            createRecord("Mouse", 20, "$25", "$500"),
            createRecord("Keyboard", 15, "$75", "$1125")
        );
        
        List<Map<String, Object>> userData = Arrays.asList(
            createRecord("U001", "John Doe", "john@example.com", "Admin"),
            createRecord("U02", "Jane Smith", "jane@example.com", "User"),
            createRecord("U003", "Bob Johnson", "bob@example.com", "Manager")
        );
        
        List<Map<String, Object>> analyticsData = Arrays.asList(
            createRecord("Page Views", "1500", "2023-10-15", "Web Analytics"),
            createRecord("Clicks", "450", "2023-10-15", "Web Analytics"),
            createRecord("Conversions", "32", "2023-10-15", "Web Analytics")
        );
        
        // Create exporters
        DataExporter[] exporters = {
            new SalesReportExporter(),
            new UserReportExporter(),
            new AnalyticsExporter()
        };
        
        List<List<Map<String, Object>>> datasets = Arrays.asList(salesData, userData, analyticsData);
        String[] datasetNames = {"Sales", "User", "Analytics"};
        
        // Export all datasets to CSV (original method)
        System.out.println("Exporting to CSV (Original Method):");
        System.out.println("===================================");
        for (int i = 0; i < exporters.length; i++) {
            System.out.println("\n" + datasetNames[i] + " Report:");
            if (exporters[i].validateData(datasets.get(i))) {
                exporters[i].exportToCSV(datasets.get(i));
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Exporting to JSON (New Feature via Default Method):");
        System.out.println("==================================================");
        
        // Export all datasets to JSON (new default method)
        for (int i = 0; i < exporters.length; i++) {
            System.out.println("\n" + datasetNames[i] + " Report (JSON):");
            if (exporters[i].validateData(datasets.get(i))) {
                exporters[i].exportToJSON(datasets.get(i));
                exporters[i].printExportStats(datasets.get(i), "JSON");
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Exporting to PDF (Another Default Method):");
        System.out.println("==========================================");
        
        // Export to PDF (another default method)
        for (int i = 0; i < exporters.length; i++) {
            System.out.println("\n" + datasetNames[i] + " Report (PDF):");
            if (exporters[i].validateData(datasets.get(i))) {
                exporters[i].exportToPDF(datasets.get(i));
                exporters[i].printExportStats(datasets.get(i), "PDF");
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Analytics Exporter's Custom JSON Implementation:");
        System.out.println("================================================");
        
        // Demonstrate AnalyticsExporter's custom JSON implementation
        AnalyticsExporter analyticsExporter = new AnalyticsExporter();
        System.out.println("Using AnalyticsExporter's custom JSON method:");
        analyticsExporter.exportToJSON(analyticsData);
        
        System.out.println("\nDefault methods in interfaces allow adding new export formats without");
        System.out.println("breaking existing implementations, enabling evolutionary feature addition.");
    }
    
    // Helper method to create data records
    private static Map<String, Object> createRecord(Object... values) {
        Map<String, Object> record = new LinkedHashMap<>();
        String[] keys = {"Product", "Quantity", "Price", "Total", "ID", "Name", "Email", "Role", "Metric", "Value", "Date", "Source"};
        
        for (int i = 0; i < values.length && i < keys.length; i++) {
            record.put(keys[i], values[i]);
        }
        
        return record;
    }
}