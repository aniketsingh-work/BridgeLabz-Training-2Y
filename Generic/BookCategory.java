class BookCategory implements Category {
    private String categoryName;
    
    public BookCategory(String categoryName) {
        this.categoryName = categoryName;
    }
    
    @Override
    public String getCategoryName() {
        return categoryName;
    }
}