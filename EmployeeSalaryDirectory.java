import java.util.*;

public class EmployeeSalaryDirectory {
    private Map<String, Double> employeeSalaries;
    
    public EmployeeSalaryDirectory() {
        employeeSalaries = new HashMap<>();
    }
    
    public void addEmployee(String name, double salary) {
        employeeSalaries.put(name, salary);
    }
    
    public void giveRaise(String name, double percentage) {
        if (employeeSalaries.containsKey(name)) {
            double currentSalary = employeeSalaries.get(name);
            double newSalary = currentSalary * (1 + percentage / 100);
            employeeSalaries.put(name, newSalary);
        } else {
            System.out.println("Employee not found");
        }
    }
    
    public double getAverageSalary() {
        if (employeeSalaries.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (double salary : employeeSalaries.values()) {
            sum += salary;
        }
        return sum / employeeSalaries.size();
    }
    
    public List<String> getHighestPaidEmployees() {
        List<String> highestPaid = new ArrayList<>();
        double maxSalary = Double.MIN_VALUE;
        
        for (Map.Entry<String, Double> entry : employeeSalaries.entrySet()) {
            if (entry.getValue() > maxSalary) {
                maxSalary = entry.getValue();
                highestPaid.clear();
                highestPaid.add(entry.getKey());
            } else if (entry.getValue().equals(maxSalary)) {
                highestPaid.add(entry.getKey());
            }
        }
        
        return highestPaid;
    }
    
    public static void main(String[] args) {
        EmployeeSalaryDirectory directory = new EmployeeSalaryDirectory();
        directory.addEmployee("Alice", 75000.0);
        directory.addEmployee("Bob", 80000.0);
        directory.addEmployee("Charlie", 70000.0);
        directory.addEmployee("David", 85000.0);
        directory.addEmployee("Eve", 80000.0);
        directory.addEmployee("Frank", 75000.0);
        
        directory.giveRaise("Alice", 10);
        directory.giveRaise("Bob", 5);
        
        System.out.println("Average salary: " + directory.getAverageSalary());
        System.out.println("Highest paid employees: " + directory.getHighestPaidEmployees());
    }
}