import java.util.*;

public class WrapperObjectsInCollections {
    public static void main(String[] args) {
        double[] prices = {10.5, 20.0, 35.75, 5.5};
        ArrayList<Double> priceList = new ArrayList<>();
        
        for (double price : prices) {
            priceList.add(price); // auto-boxing
        }
        
        double highest = Collections.max(priceList);
        double sumPrices = 0;
        for (double price : priceList) {
            sumPrices += price; // auto-unboxing
        }
        double average = sumPrices / priceList.size();
        
        System.out.println("Highest price: " + highest);
        System.out.println("Average price: " + average);
    }
}