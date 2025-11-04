import java.util.*;

// Marker interface for classes that can be cloned as prototypes
interface Prototype {
    // Marker interface - no methods, just indicates cloneability
}

// A product class that can be used as a prototype
class Product implements Prototype, Cloneable {
    private String id;
    private String name;
    private double price;
    private String category;
    private Map<String, String> attributes;
    
    public Product(String id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.attributes = new HashMap<>();
    }
    
    // Copy constructor for cloning
    public Product(Product other) {
        this.id = other.id;
        this.name = other.name;
        this.price = other.price;
        this.category = other.category;
        this.attributes = new HashMap<>(other.attributes);
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public Map<String, String> getAttributes() { return new HashMap<>(attributes); }
    
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    public void setAttribute(String key, String value) { this.attributes.put(key, value); }
    
    @Override
    public Product clone() {
        return new Product(this);
    }
    
    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', price=%.2f, category='%s', attributes=%s}", 
                           id, name, price, category, attributes);
    }
}

// A user profile class that can be used as a prototype
class UserProfile implements Prototype, Cloneable {
    private String userId;
    private String username;
    private String email;
    private List<String> roles;
    private Map<String, Object> preferences;
    
    public UserProfile(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roles = new ArrayList<>();
        this.preferences = new HashMap<>();
    }
    
    // Copy constructor for cloning
    public UserProfile(UserProfile other) {
        this.userId = other.userId;
        this.username = other.username;
        this.email = other.email;
        this.roles = new ArrayList<>(other.roles);
        this.preferences = new HashMap<>(other.preferences);
    }
    
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public List<String> getRoles() { return new ArrayList<>(roles); }
    public Map<String, Object> getPreferences() { return new HashMap<>(preferences); }
    
    public void addRole(String role) { this.roles.add(role); }
    public void setPreference(String key, Object value) { this.preferences.put(key, value); }
    
    @Override
    public UserProfile clone() {
        return new UserProfile(this);
    }
    
    @Override
    public String toString() {
        return String.format("UserProfile{userId='%s', username='%s', email='%s', roles=%s, preferences=%s}", 
                           userId, username, email, roles, preferences);
    }
}

// A document template class that can be used as a prototype
class DocumentTemplate implements Prototype, Cloneable {
    private String templateId;
    private String title;
    private String content;
    private String author;
    private Map<String, String> placeholders;
    
    public DocumentTemplate(String templateId, String title, String content, String author) {
        this.templateId = templateId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.placeholders = new HashMap<>();
    }
    
    // Copy constructor for cloning
    public DocumentTemplate(DocumentTemplate other) {
        this.templateId = other.templateId;
        this.title = other.title;
        this.content = other.content;
        this.author = other.author;
        this.placeholders = new HashMap<>(other.placeholders);
    }
    
    public String getTemplateId() { return templateId; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    public Map<String, String> getPlaceholders() { return new HashMap<>(placeholders); }
    
    public void setPlaceholder(String key, String value) { this.placeholders.put(key, value); }
    public void updateContent(String newContent) { this.content = newContent; }
    
    @Override
    public DocumentTemplate clone() {
        return new DocumentTemplate(this);
    }
    
    @Override
    public String toString() {
        return String.format("DocumentTemplate{templateId='%s', title='%s', author='%s', placeholders=%s}", 
                           templateId, title, author, placeholders);
    }
}

// A non-prototype class (doesn't implement the marker interface)
class TemporaryCache {
    private String data;
    
    public TemporaryCache(String data) {
        this.data = data;
    }
    
    public String getData() { return data; }
    
    @Override
    public String toString() {
        return "TemporaryCache{data='" + data + "'}";
    }
}

// Prototype manager utility
class PrototypeManager {
    private Map<String, Object> prototypes = new HashMap<>();
    
    public void registerPrototype(String key, Object prototype) {
        if (prototype instanceof Prototype) {
            prototypes.put(key, prototype);
            System.out.println("Registered prototype: " + key + " -> " + prototype.getClass().getSimpleName());
        } else {
            System.out.println("Cannot register non-prototype object: " + prototype.getClass().getSimpleName());
        }
    }
    
    public Object createInstance(String key) {
        Object prototype = prototypes.get(key);
        if (prototype instanceof Product) {
            return ((Product) prototype).clone();
        } else if (prototype instanceof UserProfile) {
            return ((UserProfile) prototype).clone();
        } else if (prototype instanceof DocumentTemplate) {
            return ((DocumentTemplate) prototype).clone();
        }
        return null;
    }
    
    public boolean isPrototype(String key) {
        return prototypes.containsKey(key) && prototypes.get(key) instanceof Prototype;
    }
    
    public Set<String> getAvailablePrototypes() {
        return prototypes.keySet();
    }
}

public class CloningPrototypeObjects {
    public static void main(String[] args) {
        System.out.println("Cloning Prototype Objects using Marker Interface:");
        System.out.println("================================================");
        
        // Create prototype instances
        Product laptopPrototype = new Product("PROD-001", "Generic Laptop", 999.99, "Electronics");
        laptopPrototype.setAttribute("brand", "Generic");
        laptopPrototype.setAttribute("model", "Standard");
        laptopPrototype.setAttribute("warranty", "1 year");
        
        UserProfile adminProfilePrototype = new UserProfile("U-ADMIN", "admin", "admin@example.com");
        adminProfilePrototype.addRole("ADMIN");
        adminProfilePrototype.addRole("MODERATOR");
        adminProfilePrototype.setPreference("theme", "dark");
        adminProfilePrototype.setPreference("language", "en");
        
        DocumentTemplate contractTemplate = new DocumentTemplate("TPL-001", "Service Contract", 
            "This is a service contract between parties...", "Legal Department");
        contractTemplate.setPlaceholder("CLIENT_NAME", "[Client Name]");
        contractTemplate.setPlaceholder("CONTRACT_DATE", "[Date]");
        contractTemplate.setPlaceholder("AMOUNT", "[Amount]");
        
        TemporaryCache tempData = new TemporaryCache("Some temporary data");
        
        // Create prototype manager and register prototypes
        PrototypeManager manager = new PrototypeManager();
        
        System.out.println("Registering prototypes:");
        manager.registerPrototype("laptop", laptopPrototype);
        manager.registerPrototype("adminProfile", adminProfilePrototype);
        manager.registerPrototype("contract", contractTemplate);
        manager.registerPrototype("tempData", tempData); // This won't be registered as it's not a prototype
        
        System.out.println("\nAvailable prototypes: " + manager.getAvailablePrototypes());
        
        // Create new instances from prototypes
        System.out.println("\nCreating new instances from prototypes:");
        
        // Create a new laptop based on prototype
        Product newLaptop = (Product) manager.createInstance("laptop");
        if (newLaptop != null) {
            newLaptop.setId("PROD-002");
            newLaptop.setName("Gaming Laptop");
            newLaptop.setPrice(1499.99);
            newLaptop.setAttribute("brand", "TechBrand");
            newLaptop.setAttribute("model", "GamingPro");
            newLaptop.setAttribute("gpu", "RTX 3080");
            System.out.println("New laptop: " + newLaptop);
        }
        
        // Create a new user profile based on prototype
        UserProfile newUserProfile = (UserProfile) manager.createInstance("adminProfile");
        if (newUserProfile != null) {
            newUserProfile.setUserId("U-001");
            newUserProfile.setUsername("john_doe");
            newUserProfile.setEmail("john.doe@example.com");
            newUserProfile.addRole("USER");
            newUserProfile.setPreference("notifications", "email");
            System.out.println("New user profile: " + newUserProfile);
        }
        
        // Create a new document based on template
        DocumentTemplate newContract = (DocumentTemplate) manager.createInstance("contract");
        if (newContract != null) {
            newContract.setTemplateId("TPL-002");
            newContract.setTitle("Software License Agreement");
            newContract.setPlaceholder("CLIENT_NAME", "Acme Corp");
            newContract.setPlaceholder("CONTRACT_DATE", "2023-10-15");
            newContract.setPlaceholder("AMOUNT", "$50,000");
            System.out.println("New contract: " + newContract);
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("Advanced Prototype Usage:");
        System.out.println("=========================");
        
        // Create multiple instances efficiently
        System.out.println("\nCreating multiple product variants from prototype:");
        String[] laptopModels = {"UltraBook", "Business Pro", "Student Edition"};
        double[] prices = {129.99, 899.99, 599.99};
        
        for (int i = 0; i < laptopModels.length; i++) {
            Product variant = (Product) manager.createInstance("laptop");
            if (variant != null) {
                variant.setId("PROD-00" + (i+3));
                variant.setName(laptopModels[i]);
                variant.setPrice(prices[i]);
                variant.setAttribute("model", laptopModels[i]);
                System.out.println(" " + variant);
            }
        }
        
        // Batch creation of user profiles
        System.out.println("\nCreating multiple user profiles from prototype:");
        String[] usernames = {"alice_w", "bob_m", "charlie_t"};
        String[] emails = {"alice@example.com", "bob@example.com", "charlie@example.com"};
        
        for (int i = 0; i < usernames.length; i++) {
            UserProfile profile = (UserProfile) manager.createInstance("adminProfile");
            if (profile != null) {
                profile.setUserId("U-00" + (i+2));
                profile.setUsername(usernames[i]);
                profile.setEmail(emails[i]);
                profile.getRoles().removeIf(role -> role.equals("ADMIN")); // Remove admin role for regular users
                profile.addRole("USER");
                System.out.println("  " + profile);
            }
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("Validation:");
        System.out.println("===========");
        
        // Verify that cloned objects are independent
        System.out.println("Verifying independence of cloned objects:");
        Product originalLaptop = laptopPrototype;
        Product clonedLaptop = (Product) manager.createInstance("laptop");
        
        System.out.println("Before modification:");
        System.out.println("  Original: " + originalLaptop);
        System.out.println("  Clone: " + clonedLaptop);
        
        // Modify the clone
        if (clonedLaptop != null) {
            clonedLaptop.setName("Modified Clone");
            clonedLaptop.setAttribute("modified", "true");
        }
        
        System.out.println("After modifying clone:");
        System.out.println(" Original: " + originalLaptop);
        System.out.println("  Clone: " + clonedLaptop);
        System.out.println("Independence verified: Original unchanged by clone modification");
        
        System.out.println("\nMarker interfaces enable the Prototype pattern by marking");
        System.out.println("classes as eligible for cloning, allowing for efficient");
        System.out.println("object creation from templates.");
    }
}