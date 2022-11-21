package model;

public class Podcast extends Audio{
    
    private String description;
    private Category category;

    Podcast(String name, String urlPhoto, int time, int accumulatedTimePlayed, String description, Category category){
        super(name, urlPhoto, time, accumulatedTimePlayed);
        this.description=description;
        this.category=category;
    }    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }
}
