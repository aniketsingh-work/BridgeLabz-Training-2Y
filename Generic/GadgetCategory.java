class GadgetCategory implements Category {
    private String categoryName;
    
    public GadgetCategory(String categoryName) {
        this.categoryName = categoryName;
    }
    
    @Override
    public String getCategoryName() {
        return categoryName;
    }
}