package model;

public class Song extends Audio{

    private String nameAlbum;
    private Genre genre;
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    private double price;
    private int timesSold;

    public Song(String name, String urlPhoto, int time, int accumulatedTimePlayed, String nameAlbum, Genre genre, double price, int timesSold){
        super(nameAlbum, urlPhoto, timesSold, accumulatedTimePlayed);
        this.nameAlbum=nameAlbum;
        this.genre=genre;
        this.price=price;
        this.timesSold=timesSold;
    }

    /**Calculates the price of it sales
     * @return total sales
     */
    public double calculateTotalSale(){
		return price*timesSold;
	}

    public String getNameAlbum() {
        return nameAlbum;
    }
    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getTimesSold() {
        return timesSold;
    }
    public void setTimesSold(int timesSold) {
        this.timesSold = timesSold;
    }
}
