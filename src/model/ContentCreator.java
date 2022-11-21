package model;

import java.time.LocalDate;

public class ContentCreator extends ProducerUser {

    public ContentCreator(String id, LocalDate timeRegister, String name, String urlPhoto, int accumulatedReproductions, int totalTimeReproductions){
        super(id, timeRegister, name, urlPhoto, accumulatedReproductions, totalTimeReproductions);
    }

}
