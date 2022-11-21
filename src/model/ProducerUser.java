package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProducerUser extends User implements Comparable<ProducerUser> {
    
    private String name;
    private String urlPhoto;
    private int accumulatedReproductions;
    private int totalTimeReproductions;
    private ArrayList<Audio> audios;

    public ProducerUser(String id, LocalDate timeRegister, String name, String urlPhoto, int accumulatedReproductions, int totalTimeReproductions){
        super(id, timeRegister);
        this.name=name;
        this.urlPhoto=urlPhoto;
        this.accumulatedReproductions=accumulatedReproductions;
        this.totalTimeReproductions=totalTimeReproductions;
        this.audios = new ArrayList<Audio>();
    }
    /**Adds an audio to the audio list
     * @param audio object audio
     * @return true added, false not added
     */
    public boolean addAudio(Audio audio){
        if(existAudio(audio.getName())==null){
            return audios.add(audio);
        }else{
            return false;
        }
    }

    /**Search an audio by its name
     * @param audioName name of the audio
     * @return objet audio if it's found, null if not
     */
    public Audio existAudio(String audioName){
        boolean existAudio = false;
        Audio a = null;
        for (int i = 0; i < audios.size() && !existAudio; i++) {
            if(audios.get(i).getName().equals(audioName)){
                existAudio = true;
                a = audios.get(i);
            }
        }
        return a;
    }
    /**
     * Calculates the acumulated reproduction 
     */
    public void calculateAccumulated(){
        int accumulatedReproductions = 0;
        for (int i = 0; i < audios.size(); i++) {
            accumulatedReproductions += audios.get(i).getReproducction();
        }
        setAccumulatedReproductions(accumulatedReproductions);
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(ProducerUser b){
        calculateAccumulated();
        return this.accumulatedReproductions-b.getAccumulatedReproductions();
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

    public int getAccumulatedReproductions() {
        return accumulatedReproductions;
    }

    public void setAccumulatedReproductions(int accumulatedReproductions) {
        this.accumulatedReproductions = accumulatedReproductions;
    }

    public int getTotalTimeReproductions() {
        return totalTimeReproductions;
    }

    public void setTotalTimeReproductions(int totalTimeReproductions) {
        this.totalTimeReproductions = totalTimeReproductions;
    }

    public void setAudios(ArrayList<Audio> audios) {
        this.audios = audios;
    }
    public ArrayList<Audio> getAudios() {
        return audios;
    }

}
