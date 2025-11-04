package com.access.one;

public class Base {
    public int publicVar = 1;
    protected int protectedVar = 2;
    int defaultVar = 3;  // package-private or default access
    private int privateVar = 4;
    
    public void publicMethod() {
        System.out.println("Public method in Base class");
        System.out.println("Can access all variables within same class:");
        System.out.println("Public: " + publicVar);
        System.out.println("Protected: " + protectedVar);
        System.out.println("Default: " + defaultVar);
        System.out.println("Private: " + privateVar);
    }
    
    protected void protectedMethod() {
        System.out.println("Protected method in Base class");
    }
    
    void defaultMethod() {
        System.out.println("Default method in Base class");
    }
    
    private void privateMethod() {
        System.out.println("Private method in Base class");
    }
}