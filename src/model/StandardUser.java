package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class StandardUser extends ConsumerUser{

    PlayList[] playLists;
    
    public StandardUser(String nickname, String id, LocalDate timeRegister){
        super(nickname, id, timeRegister);
        this.playLists= new PlayList[20];
    }
    @Override
    public String createPlayList(String name){
        boolean status=false;
        String message="La PlayList no se pudo crear porque no hay más espacio";
        PlayList com = searchPlayList(name);
        if(com==null){
            for (int i=0; i<playLists.length && !status; i++){
                if (playLists[i]==null){
                        playLists[i]= new PlayList(name);
                        status=true;
                        message="La PlayList se creo exitosamente.";
                }
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
        for (int i=0; i<playLists.length && !existPlayList; i++){
            if(playLists[i]!=null){
                if(playLists[i].getName().equalsIgnoreCase(name)){
                    existPlayList=true;
                    j=playLists[i];
                }
            }
        } return j;
    }
    @Override
    public String buySong(Song song){
        String message = "";
        ArrayList<Historic> historics = getHistoric();
        if(searchSong(song.getName())==null){
            if(historics.size()<100){ 
                historics.add(new Historic(song));
                setHistoric(historics);
            }else{
                message = "No se pudo comprar la cancion porque no hay mas espacio";
            }
        }else{
            message = "Ya has comprado esta cancion";
        }
        return message;
    }
}
