import java.time.LocalDate;
import java.util.*;

public class InsurancePolicyManagement {
    
    static class Policy {
        private String policyNumber;
        private String policyholderName;
        private LocalDate expiryDate;
        private String coverageType;
        private double premiumAmount;
        
        public Policy(String policyNumber, String policyholderName, LocalDate expiryDate, 
                     String coverageType, double premiumAmount) {
            this.policyNumber = policyNumber;
            this.policyholderName = policyholderName;
            this.expiryDate = expiryDate;
            this.coverageType = coverageType;
            this.premiumAmount = premiumAmount;
        }
        
        // Getters
        public String getPolicyNumber() { return policyNumber; }
        public String getPolicyholderName() { return policyholderName; }
        public LocalDate getExpiryDate() { return expiryDate; }
        public String getCoverageType() { return coverageType; }
        public double getPremiumAmount() { return premiumAmount; }
        
        @Override
        public String toString() {
            return "Policy{" +
                    "policyNumber='" + policyNumber + '\'' +
                    ", policyholderName='" + policyholderName + '\'' +
                    ", expiryDate=" + expiryDate +
                    ", coverageType='" + coverageType + '\'' +
                    ", premiumAmount=" + premiumAmount +
                    '}';
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Policy policy = (Policy) obj;
            return Objects.equals(policyNumber, policy.policyNumber);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(policyNumber);
        }
    }
    
    // TreeSet comparator for sorting policies by expiry date
    static class PolicyExpiryComparator implements Comparator<Policy> {
        @Override
        public int compare(Policy p1, Policy p2) {
            return p1.getExpiryDate().compareTo(p2.getExpiryDate());
        }
    }
    
    // Storage using different types of sets
    private Set<Policy> hashSetPolicies;      // For quick lookups
    private Set<Policy> linkedHashSetPolicies; // To maintain insertion order
    private Set<Policy> treeSetPolicies;      // To maintain sorted order by expiry date
    
    public InsurancePolicyManagement() {
        this.hashSetPolicies = new HashSet<>();
        this.linkedHashSetPolicies = new LinkedHashSet<>();
        this.treeSetPolicies = new TreeSet<>(new PolicyExpiryComparator());
    }
    
    // 1. Store policies using different sets
    public void addPolicy(Policy policy) {
        hashSetPolicies.add(policy);
        linkedHashSetPolicies.add(policy);
        treeSetPolicies.add(policy);
    }
    
    public boolean removePolicy(String policyNumber) {
        Policy policyToRemove = new Policy(policyNumber, "", null, "", 0);
        boolean removed = false;
        
        Iterator<Policy> iterator = hashSetPolicies.iterator();
        while (iterator.hasNext()) {
            Policy policy = iterator.next();
            if (policy.getPolicyNumber().equals(policyNumber)) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        
        iterator = linkedHashSetPolicies.iterator();
        while (iterator.hasNext()) {
            Policy policy = iterator.next();
            if (policy.getPolicyNumber().equals(policyNumber)) {
                iterator.remove();
                break;
            }
        }
        
        iterator = treeSetPolicies.iterator();
        while (iterator.hasNext()) {
            Policy policy = iterator.next();
            if (policy.getPolicyNumber().equals(policyNumber)) {
                iterator.remove();
                break;
            }
        }
        
        return removed;
    }
    
    // 2. Retrieve policies based on criteria
    public Set<Policy> getAllPolicies() {
        return new HashSet<>(hashSetPolicies);
    }
    
    public List<Policy> getPoliciesExpiringSoon(int days) {
        LocalDate now = LocalDate.now();
        LocalDate futureDate = now.plusDays(days);
        
        List<Policy> expiringSoon = new ArrayList<>();
        for (Policy policy : hashSetPolicies) {
            if (!policy.getExpiryDate().isAfter(futureDate) && 
                !policy.getExpiryDate().isBefore(now)) {
                expiringSoon.add(policy);
            }
        }
        
        return expiringSoon;
    }
    
    public List<Policy> getPoliciesByCoverageType(String coverageType) {
        List<Policy> policies = new ArrayList<>();
        for (Policy policy : hashSetPolicies) {
            if (policy.getCoverageType().equalsIgnoreCase(coverageType)) {
                policies.add(policy);
            }
        }
        return policies;
    }
    
    public List<Policy> getDuplicatePolicies() {
        // Since we're using sets, there shouldn't be duplicates based on policy number
        // This method would be more relevant if we were comparing based on other criteria
        return new ArrayList<>();
    }
    
    // 3. Performance comparison methods
    public long addPolicyPerformanceTest(Set<Policy> policySet, Policy policy, String setType) {
        long startTime = System.nanoTime();
        policySet.add(policy);
        long endTime = System.nanoTime();
        System.out.println("Time to add policy to " + setType + ": " + (endTime - startTime) + " ns");
        return endTime - startTime;
    }
    
    public long removePolicyPerformanceTest(Set<Policy> policySet, String policyNumber, String setType) {
        long startTime = System.nanoTime();
        Policy policyToRemove = new Policy(policyNumber, "", null, "", 0);
        policySet.remove(policyToRemove);
        long endTime = System.nanoTime();
        System.out.println("Time to remove policy from " + setType + ": " + (endTime - startTime) + " ns");
        return endTime - startTime;
    }
    
    public long searchPolicyPerformanceTest(Set<Policy> policySet, String policyNumber, String setType) {
        long startTime = System.nanoTime();
        Policy policyToSearch = new Policy(policyNumber, "", null, "", 0);
        boolean found = policySet.contains(policyToSearch);
        long endTime = System.nanoTime();
        System.out.println("Time to search policy in " + setType + ": " + (endTime - startTime) + " ns, Found: " + found);
        return endTime - startTime;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Insurance Policy Management System ===");
        
        InsurancePolicyManagement system = new InsurancePolicyManagement();
        
        // Add some sample policies
        Policy policy1 = new Policy("P001", "John Doe", LocalDate.now().plusDays(15), "Health", 5000.0);
        Policy policy2 = new Policy("P002", "Jane Smith", LocalDate.now().plusDays(45), "Auto", 3000.0);
        Policy policy3 = new Policy("P003", "Bob Johnson", LocalDate.now().plusDays(5), "Home", 7000.0);
        Policy policy4 = new Policy("P004", "Alice Williams", LocalDate.now().plusDays(25), "Health", 6000.0);
        
        system.addPolicy(policy1);
        system.addPolicy(policy2);
        system.addPolicy(policy3);
        system.addPolicy(policy4);
        
        System.out.println("\n1. All policies:");
        for (Policy policy : system.getAllPolicies()) {
            System.out.println(policy);
        }
        
        System.out.println("\n2. Policies expiring soon (within 30 days):");
        List<Policy> expiringSoon = system.getPoliciesExpiringSoon(30);
        for (Policy policy : expiringSoon) {
            System.out.println(policy);
        }
        
        System.out.println("\n3. Health coverage policies:");
        List<Policy> healthPolicies = system.getPoliciesByCoverageType("Health");
        for (Policy policy : healthPolicies) {
            System.out.println(policy);
        }
        
        System.out.println("\n4. Performance Comparison:");
        Policy testPolicy = new Policy("P999", "Test User", LocalDate.now().plusDays(10), "Life", 4000.0);
        
        // Performance test for HashSet
        Set<Policy> hashSet = new HashSet<>();
        system.addPolicyPerformanceTest(hashSet, testPolicy, "HashSet");
        system.searchPolicyPerformanceTest(hashSet, "P999", "HashSet");
        system.removePolicyPerformanceTest(hashSet, "P999", "HashSet");
        
        // Performance test for LinkedHashSet
        Set<Policy> linkedHashSet = new LinkedHashSet<>();
        system.addPolicyPerformanceTest(linkedHashSet, testPolicy, "LinkedHashSet");
        system.searchPolicyPerformanceTest(linkedHashSet, "P999", "LinkedHashSet");
        system.removePolicyPerformanceTest(linkedHashSet, "P999", "LinkedHashSet");
        
        // Performance test for TreeSet
        Set<Policy> treeSet = new TreeSet<>(new PolicyExpiryComparator());
        system.addPolicyPerformanceTest(treeSet, testPolicy, "TreeSet");
        system.searchPolicyPerformanceTest(treeSet, "P999", "TreeSet");
        system.removePolicyPerformanceTest(treeSet, "P999", "TreeSet");
    }
}