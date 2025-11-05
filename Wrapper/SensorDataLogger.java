public class SensorDataLogger {
    public static void main(String[] args) {
        double primitiveTemp = 25.5;
        Double wrapperTemp = 26.7;
        
        System.out.println("Processing primitive temperature: " + primitiveTemp);
        System.out.println("Processing wrapper temperature: " + wrapperTemp);
        System.out.println("Average of both (demonstrating auto-boxing/unboxing): " + 
                          getAverage(primitiveTemp, wrapperTemp));
    }
    
    public static double getAverage(double temp1, Double temp2) {
        // Demonstrates auto-unboxing when temp2 is used as a primitive
        return (temp1 + temp2) / 2;
    }
}