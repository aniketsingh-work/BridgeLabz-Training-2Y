import java.util.*;

public class CountryCapitalLookup {
    private Map<String, String> countryCapitalMap;
    
    public CountryCapitalLookup() {
        countryCapitalMap = new HashMap<>();
    }
    
    public void addCountryCapital(String country, String capital) {
        countryCapitalMap.put(country, capital);
    }
    
    public String getCapital(String country) {
        if (countryCapitalMap.containsKey(country)) {
            return countryCapitalMap.get(country);
        }
        return "Unknown country";
    }
    
    public void printCountriesInAlphabeticalOrder() {
        Map<String, String> sortedMap = new TreeMap<>(countryCapitalMap);
        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public static void main(String[] args) {
        CountryCapitalLookup lookup = new CountryCapitalLookup();
        lookup.addCountryCapital("India", "New Delhi");
        lookup.addCountryCapital("USA", "Washington D.C.");
        lookup.addCountryCapital("China", "Beijing");
        lookup.addCountryCapital("Japan", "Tokyo");
        lookup.addCountryCapital("Germany", "Berlin");
        lookup.addCountryCapital("France", "Paris");
        lookup.addCountryCapital("Brazil", "Brasilia");
        lookup.addCountryCapital("Canada", "Ottawa");
        
        System.out.println("Capital of India: " + lookup.getCapital("India"));
        System.out.println("Capital of UK: " + lookup.getCapital("UK"));
        
        System.out.println("\nCountries in alphabetical order:");
        lookup.printCountriesInAlphabeticalOrder();
    }
}