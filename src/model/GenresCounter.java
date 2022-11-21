package model;

public class GenresCounter implements Comparable<GenresCounter>{
    private Genre genre;
    private int views;

 
    /**Constructor of the GenrePlays Class
     * @param genre song genre
     */
    public GenresCounter(Genre genre){
        this.genre = genre;
        views = 0;
    }

    /**
     * Adds one 
     */
    public void updatePlays(){
        this.views++;
    }

    /**Gets the views
     * @return views
     */
    public int getViews() {
        return views;
    }


    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(GenresCounter b){
        return this.views-b.getViews(); 
    }

    /**Gets the genre of the counter
     * @return genre
     */
    public Genre getGenre() {
        return genre;
    }


}
