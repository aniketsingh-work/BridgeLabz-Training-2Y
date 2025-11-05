class ClothingCategory implements Category {
    private String categoryName;
    
    public ClothingCategory(String categoryName) {
        this.categoryName = categoryName;
    }
    
    @Override
    public String getCategoryName() {
        return categoryName;
    }
}