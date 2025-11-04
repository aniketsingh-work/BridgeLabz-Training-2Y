package com.access.two;
import com.access.one.Base;

public class Derived extends Base {
    public void testAccess() {
        System.out.println("Testing access from Derived class in different package:");
        
        // Can access public members
        System.out.println("Public variable: " + publicVar);
        
        // Can access protected members
        System.out.println("Protected variable: " + protectedVar);
        
        // Cannot access default (package-private) members from another package
        // System.out.println("Default variable: " + defaultVar); // This would cause a compilation error
        
        // Cannot access private members
        // System.out.println("Private variable: " + privateVar); // This would cause a compilation error
        
        // Calling methods
        publicMethod();  // Can call public method
        protectedMethod();  // Can call protected method
        // defaultMethod();  // This would cause a compilation error
        // privateMethod();  // This would cause a compilation error
    }
    
    public static void main(String[] args) {
        Derived derived = new Derived();
        derived.testAccess();
        
        System.out.println("\nSummary of access from Derived class in another package:");
        System.out.println("Public: Accessible - Yes");
        System.out.println("Protected: Accessible - Yes");
        System.out.println("Default (package-private): Accessible - No");
        System.out.println("Private: Accessible - No");
        
        System.out.println("\nAccess Modifier Table:");
        System.out.println("+----------------+---------+-----------+----------+---------+");
        System.out.println("| Access Modifier| Same CL | Same Pkg  | Subclass | Other   |");
        System.out.println("|                |         | (diff CL) | (any pkg)| Package |");
        System.out.println("+----------------+---------+-----------+----------+---------+");
        System.out.println("| public         | Yes     | Yes       | Yes      | Yes     |");
        System.out.println("| protected      | Yes     | Yes       | Yes      | No      |");
        System.out.println("| default        | Yes     | Yes       | No*      | No      |");
        System.out.println("| private        | Yes     | No        | No       | No      |");
        System.out.println("+----------------+---------+-----------+----------+---------+");
        System.out.println("* Only if subclass is in the same package");
    }
}