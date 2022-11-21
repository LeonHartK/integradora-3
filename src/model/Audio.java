package model;
public class Audio implements Comparable<Audio> {

    private String name;
    private String urlPhoto;
    private int time;
    private int reproducction;
    private int accumulatedTimePlayed;

    Audio(String name, String urlPhoto, int time, int accumulatedTimePlayed){
        this.name=name;
        this.urlPhoto=urlPhoto;
        this.time=time;
        this.accumulatedTimePlayed=accumulatedTimePlayed;
        this.reproducction = 0;
    }
    public int getReproducction() {
        return reproducction;
    }

    public void setReproducction(int reproducction) {
        this.reproducction = reproducction;
    }
    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Audio b){
        return this.reproducction-b.getReproducction();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrlPhoto() {
        return urlPhoto;
    }
    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public int getAccumulatedTimePlayed() {
        return accumulatedTimePlayed;
    }
    public void setAccumulatedTimePlayed(int accumulatedTimePlayed) {
        this.accumulatedTimePlayed = accumulatedTimePlayed;
    }
}
