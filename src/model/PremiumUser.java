package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class PremiumUser extends ConsumerUser {

    ArrayList<PlayList> playLists;

    public PremiumUser(String nickname, String id, LocalDate timeRegister){
        super(nickname, id, timeRegister);
        this.playLists=new ArrayList<>();
    }
    
    @Override
    public String createPlayList(String name){
        String message="No se agrego la playlist";
        PlayList com = searchPlayList(name);
        if(com==null){
            
            if(playLists.add(new PlayList(name))){
                message="La PlayList se creo exitosamente.";
            }

                    
        }else{
            message="La PlayList ya se encuentra registrada";
        } 
        return message;
    }
    
    @Override
    public PlayList searchPlayList(String name){
        boolean existPlayList = false;
        PlayList j=null;
        for (int i=0; i<playLists.size() && !existPlayList; i++){
            if(playLists.get(i).getName().equalsIgnoreCase(name)){
                existPlayList=true;
                j=playLists.get(i);
            }
        } 
        return j;
    }

    @Override
    public String buySong(Song song){
        String message = "";
        ArrayList<Historic> historics = getHistoric();
        if(searchSong(song.getName())==null){
            historics.add(new Historic(song));
            setHistoric(historics);
            
        }else{
            message = "Ya has comprado esta cancion";
        }
        return message;
    }

}
