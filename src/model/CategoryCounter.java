package model;
public class CategoryCounter implements Comparable<CategoryCounter>{
    private Category category;
    private int views;

    public CategoryCounter(Category category){
        this.category = category;
        views = 0;
    }

    public int getViews() {
        return views;
    }

    /**Gets the category
     * @return category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Adds one 
     */
    public void updatePlays(){
        views++;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(CategoryCounter b){
        return this.views-b.getViews(); 
    }
}
