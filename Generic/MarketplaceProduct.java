class MarketplaceProduct<T extends Category> {
    private String name;
    private double price;
    private T category;
    
    public MarketplaceProduct(String name, double price, T category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public T getCategory() {
        return category;
    }
    
    @Override
    public String toString() {
        return name + " - $" + price + " (" + category.getCategoryName() + ")";
    }
}