package model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.ArrayList;

public class ConsumerUser extends User {

    private String nickname;
    private ArrayList<Historic> historic; // Historial of purchases - (Bought songs)
    private ArrayList<GenresCounter> genresCounters; // reproduction counters for Genres
    private ArrayList<CategoryCounter> categoryCounters; // reproduction counters for Categories

    public ConsumerUser(String nickname, String id, LocalDate timeRegister){
        super(id, timeRegister);
        this.nickname=nickname;
        this.historic = new ArrayList<Historic>();
        this.categoryCounters = new ArrayList<CategoryCounter>();
        this.genresCounters = new ArrayList<GenresCounter>();
        //The following lines initialize the reproduction counters for each genre and category
        genresCounters.add(new GenresCounter(Genre.ROCK));
		genresCounters.add(new GenresCounter(Genre.POP));
        genresCounters.add(new GenresCounter(Genre.TRAP));
        genresCounters.add(new GenresCounter(Genre.HOUSE));
		categoryCounters.add(new CategoryCounter(Category.POLITCS));
		categoryCounters.add(new CategoryCounter(Category.POLITCS));
		categoryCounters.add(new CategoryCounter(Category.POLITCS));
		categoryCounters.add(new CategoryCounter(Category.POLITCS));
    }
    /**Search a song in the historial
     * @param name name of the song
     * @return the song if it was bought or null meaning that the user haven't bought the song
     */
    public Song searchSong(String name){
        Song song = null;
        for (int i = 0; i < historic.size(); i++) {
            if(historic.get(i).getSong().getName().equals(name)){
                song = historic.get(i).getSong();
            }
        }
        return song;
    }

	/**Sorts the list of Genre counters by reproductions and returns a message with the one  int the first position 
	 * @return the most listened genre for this user
	 */
	public String MaxGenre(){
		Collections.sort(genresCounters, Collections.reverseOrder());
		return "Tu genero mas escuchado es: "+genresCounters.get(0).getGenre()+" Reproducciones: "+genresCounters.get(0).getViews();
	}

	/**Sorts the list of Category counters by reproductions and returns a message with the one in the first position
	 * @return the most listened genre for this user
	 */
	public String MaxCategory(){
		Collections.sort(categoryCounters, Collections.reverseOrder());
		return "Tu categoria mas escuchada es: "+categoryCounters.get(0).getCategory()+" Reproducciones: "+categoryCounters.get(0).getViews();
	}

    /**Creates a playlist and adds it to the list
     * @param name name of the playlist
     * @return confirmation message
     */
    public String createPlayList(String name){
        return null;
    }
    /**Adds a song to the list
     * @param song object song
     * @return confirmation message
     */
    public String buySong(Song song){
        return null;
    }
    /**
     * Searches a playlisti in the list
     * @param name name of the playlist
     * @return Playlist object or null if not found
     */
    public PlayList searchPlayList(String name){
        return null;
    }

    public ArrayList<Historic> getHistoric() {
        return historic;
    }
    public void setHistoric(ArrayList<Historic> historic) {
        this.historic = historic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
