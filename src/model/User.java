package model;

import java.time.LocalDate;

public class User {

    private String id;
    private LocalDate timeRegister;

    public User(String id, LocalDate timeRegister){
        this.id=id;
        this.timeRegister=timeRegister;
    }

    public LocalDate getTimeRegister() {
        return timeRegister;
    }

    public void setTimeRegister(LocalDate timeRegister) {
        this.timeRegister = timeRegister;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String nickname, String id){
        this.id=id;
    }

}
