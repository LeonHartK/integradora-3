package model;

import java.time.LocalDate;

public class Artist extends ProducerUser{

    public Artist(String id, LocalDate timeRegister, String name, String urlPhoto, int accumulatedReproductions, int totalTimeReproductions){
        super(id, timeRegister, name, urlPhoto, accumulatedReproductions, totalTimeReproductions);
    }
 
}
