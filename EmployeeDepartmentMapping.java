import java.util.*;

public class EmployeeDepartmentMapping {
    private Map<Integer, String> employeeDepartmentMap;
    
    public EmployeeDepartmentMapping() {
        employeeDepartmentMap = new HashMap<>();
    }
    
    public void addEmployee(int employeeId, String department) {
        employeeDepartmentMap.put(employeeId, department);
    }
    
    public void changeDepartment(int employeeId, String newDepartment) {
        if (employeeDepartmentMap.containsKey(employeeId)) {
            employeeDepartmentMap.put(employeeId, newDepartment);
        }
    }
    
    public List<Integer> getEmployeesInDepartment(String department) {
        List<Integer> employees = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : employeeDepartmentMap.entrySet()) {
            if (entry.getValue().equals(department)) {
                employees.add(entry.getKey());
            }
        }
        return employees;
    }
    
    public Map<String, Integer> getTotalEmployeesPerDepartment() {
        Map<String, Integer> departmentCount = new HashMap<>();
        for (String department : employeeDepartmentMap.values()) {
            departmentCount.put(department, departmentCount.getOrDefault(department, 0) + 1);
        }
        return departmentCount;
    }
    
    public static void main(String[] args) {
        EmployeeDepartmentMapping mapping = new EmployeeDepartmentMapping();
        mapping.addEmployee(1, "HR");
        mapping.addEmployee(2, "IT");
        mapping.addEmployee(3, "HR");
        mapping.addEmployee(4, "Finance");
        mapping.addEmployee(5, "IT");
        mapping.addEmployee(6, "HR");
        
        System.out.println("Employees in HR: " + mapping.getEmployeesInDepartment("HR"));
        
        mapping.changeDepartment(2, "Finance");
        
        System.out.println("Total employees per department: " + mapping.getTotalEmployeesPerDepartment());
    }
}