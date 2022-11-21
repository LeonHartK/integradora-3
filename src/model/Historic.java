package model;

import java.time.LocalDate;

public class Historic {
    private Song song;
    private LocalDate purchasDate;

    public Historic(Song song) {
        this.song = song;
        this.purchasDate = LocalDate.now();
    }
    /**
     * Gets the song
     * @return song
     */
    public Song getSong() {
        return song;
    }

    
}
