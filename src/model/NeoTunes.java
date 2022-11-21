package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import javax.lang.model.type.ArrayType;

public class NeoTunes {
    
    ArrayList<User> users;

    public NeoTunes(){
        this.users = new ArrayList<>();
    }

    public String addUser(String nickname, String id, LocalDate timeRegister){
        String message="";
        if (!searchUser(id)){
            users.add(new StandardUser(nickname, id, timeRegister));
            message="El usuario fue registrado exitosamente";
        } else {
            message="El usuario ya esta registrado";
        } 
        return message;
    } 

    public String addPremiumUser(String nickname, String id, LocalDate timerRegister){
        String message="";
        if (!searchUser(id)){
            users.add(new PremiumUser(nickname, id, timerRegister));
            message="El usuario fue registrado exitosamente";
        } else {
            message="El usuario ya esta registrado";
        } 
        return message;
    }

    public String addUser(String id, LocalDate timeRegister, String name, String urlPhoto, int accumulatedReproductions, int totalTimeReproductions){
        String message="";
        if (!searchUser(id)){
            users.add(new Artist(id, timeRegister, name, urlPhoto, 0, 0));
            message="El usuario fue registrado exitosamente";
        } else {
            message="El usuario ya esta registrado";
        } 
        return message;
    } 

    public String addUserContentCreator(String id, LocalDate timeRegister, String name, String urlPhoto, int accumulatedReproductions, int totalTimeReproductions){
        String message="";
        if (!searchUser(id)){
            users.add(new ContentCreator(id, timeRegister, name, urlPhoto, 0, 0));
            message="El usuario fue registrado exitosamente";
        } else {
            message="El usuario ya esta registrado";
        } 
        return message;
    }

    /**Adds an audio to the user list
     * @param user owner of audio
     * @param audio audio to be added
     * @return confirmation message
     */
    public String addAudio(User user, Audio audio){
        if(((ProducerUser)user).addAudio(audio)){
            return "Audio agregado";
        }else{
            return "Ya hay un audio con ese nombre";
        }
       
    }

    /**Creates an audio depending of the type
     * @param name name of the audio
     * @param urlPhoto url of audio's photo
     * @param time duration in seconds
     * @param nameAlbum name of the album if it's a song
     * @param genre genre if it's a song
     * @param price price ig it's a song
     * @param description description if it's a podcast
     * @param category category if it's a podcast
     * @param type 1 if it's a song, 2 if it's a podcast
     * @return
     */
    public Audio createAudio(String name, String urlPhoto, int time,  String nameAlbum, int genre, double price, String description, int category, int type){
        if(type==1){
            Genre genre2 = null;
            if(genre==1){
                genre2 = Genre.ROCK;
            }
            if(genre==2){
                genre2 = Genre.POP;
            }
            if(genre==3){
                genre2 = Genre.TRAP;
            }
            if(genre==4){
                genre2 = Genre.HOUSE;
            }
            if(genre>4 || genre<1){
                genre2 = Genre.POP;
            }
            return new Song(name, urlPhoto, time, 0, nameAlbum, genre2, price, 0);
        }else{
            Category categ = null;
            if(category==1){
                categ = Category.POLITCS;
            }
            if(category==2){
                categ = Category.ENTERTAINMENT;
            }
            if(category==3){
                categ = Category.MODE;
            }
            if(category==4){
                categ = Category.VIDEOGAMES;
            }
            if(category >4 || category<1){
                categ = Category.ENTERTAINMENT;
            }
            return new Podcast(nameAlbum, urlPhoto, time, 0, description, categ);
        
        }
    }

    public boolean searchUser(String id){
        boolean status=false;
        for (int i=0;i<users.size() && !status;i++){
            if (users.get(i).getId().equalsIgnoreCase(id)){
                status=true;
            }
        } 
        return status;
    }

    /**Searches an user by its id
     * @param id user if
     * @return object user if it's found, null if not
     */
    public User searchUserObj(String id){
        boolean status=false;
        User user = null;
        for (int i=0;i<users.size() && !status;i++){
            if (users.get(i).getId().equalsIgnoreCase(id)){
                status=true;
                user = users.get(i);
            }
        } 
        return user;
    }

    /**Calls the method to create a playlist
     * @param id id of the user
     * @param name name of the playlist
     * @return confirmation message
     */
    public String createPlaylist(String id, String name){
        ConsumerUser user = (ConsumerUser)searchUserObj(id);
        users.remove(user);
        String message = user.createPlayList(name);
        users.add(user);
        return message;   
    }

    /**
     * @param id
     * @param name
     * @param audioNme
     * @param option
     * @return
     */
    public String editPlaylist(String id, String name, String audioNme, int option){
        String message = "";
        ConsumerUser user = (ConsumerUser)searchUserObj(id);
        Audio audio = searchAudio(audioNme);
        PlayList playList = user.searchPlayList(name);
        if(playList!=null){
            if(audio!=null){
                boolean isOwned = false;
                if(audio instanceof Song){
                    isOwned = user.searchSong(name)!=null;
                }
                if(isOwned || audio instanceof Podcast){
                    message = playList.edit(audio, option);
                    users.remove(user);
                    users.add(user);
                }else{
                    message = "No has comprado esta cancion";
                }
            }else{
                message  = "No se encontro el audio";
            }
            
        }else{
            message = "No se encontro la playlist";
        }
        return message;
    }

    public boolean isSong(Audio song){
        return song instanceof Song;
    }

    /**Searches an aidop by its name
     * @param name audio name
     * @return Object audio if it's found, null if not
     */
    public Audio searchAudio(String name){
        Audio a= null;
        boolean isFound = false;
        for (int i = 0; i < users.size() && !isFound; i++) {
            if(users.get(i) instanceof ProducerUser){
                if(((ProducerUser)users.get(i)).existAudio(name)!=null){
                    a = ((ProducerUser)users.get(i)).existAudio(name);
                }
            }
        }
        return a;
    }

    public ArrayList<Audio> getAudioList(){
        ArrayList<Audio> a= new ArrayList<Audio>();
        boolean isFound = false;
        for (int i = 0; i < users.size() && !isFound; i++) {
            if(users.get(i) instanceof ProducerUser){
                for (int j = 0; j < ((ProducerUser)users.get(i)).getAudios().size(); j++) {
                    a.add(((ProducerUser)users.get(i)).getAudios().get(j));
                }
            }
        }
        return a;
    }

    public boolean updateAudio(Audio audio){
        boolean isFound = false;
        for (int i = 0; i < users.size() && !isFound; i++) {
            if(users.get(i) instanceof ProducerUser){
                if(((ProducerUser)users.get(i)).getAudios().contains(audio)){
                    ((ProducerUser)users.get(i)).getAudios().remove(audio);
                    ((ProducerUser)users.get(i)).getAudios().add(audio);
                    isFound = true;
                }
            }
        }
        return isFound;
    }
    public boolean isStandar(User user){
        return user instanceof StandardUser;
    }

    public String buySong(String userId, String SongName){
        String message = "";
        ConsumerUser user = (ConsumerUser)searchUserObj(userId);
        Audio song = searchAudio(SongName);
        if(song instanceof Song && song !=null){
            user.buySong((Song)song);
            ((Song)(song)).setTimesSold(((Song)(song)).getTimesSold()+1);
            updateAudio(song);
            users.remove(user);
            users.add(user);
        }else if(song == null){
            message = "No se encontro el audio";
        }else if(!(song instanceof Song)){
            message = "Este audio no es de tipo cancion";
        }
        return message;
    } 
    public String sharePlaylist(String id, String name){
        String message = "";
        ConsumerUser user = (ConsumerUser)searchUserObj(id);
        PlayList playlist = user.searchPlayList(name);
        if(playlist!=null){
            message = playlist.share();
            users.remove(user);
            users.add(user);
        }else{
            message = "No se encontro la playlist";
        }
        return message;
    }

    public void playAudio(Audio audio, int playedTime){
        audio.setAccumulatedTimePlayed(audio.getAccumulatedTimePlayed()+playedTime);
        audio.setReproducction(audio.getReproducction()+1);
        updateAudio(audio);
    }

    public String generateStadistics(String id){
        int[] plays = getAccumulatedReproductionsP();
        return "Reproducciones totales de podcast: "+plays[0]+"\n"+
        "Reproducciones totales de canciones: "+plays[1]+"\n\n"+
        getMaxPlayedGenre()+"\n"+
        getMaxPlayedCategory()+"\n"+
        getMaxGenre(id)+"\n"+
        getMaxCategory(id)+"\n\n"+
        getTop5Producers()+"\n\n"+
        getTop10Audios()+"\n\n"+
        getGenreSales()+"\n\n"+
        getMostSold();
    }

    public int[] getAccumulatedReproductionsP(){
        int[] accumulatedReproductions = new int[]{0, 0};
        boolean isFound = false;
        for (int i = 0; i < users.size() && !isFound; i++) {
            if(users.get(i) instanceof ProducerUser){
                for (int j = 0; j < ((ProducerUser)users.get(i)).getAudios().size(); j++) {
                    if((((ProducerUser)users.get(i)).getAudios().get(i)) instanceof Podcast){
                        accumulatedReproductions[0] += ((ProducerUser)users.get(i)).getAudios().get(j).getAccumulatedTimePlayed();
                    }else{
                        accumulatedReproductions[1]+= ((ProducerUser)users.get(i)).getAudios().get(j).getAccumulatedTimePlayed();
                    }
                }
            }
        }
        return accumulatedReproductions;
    }
    public String getMaxPlayedGenre(){
        int[] genres = getGenrePlays();
        int max = genres[0]; 
        int maxPos = 0;
        int min = 0;
        for (int i = 1; i < genres.length; i++) {
            min = genres[i];
            if(min>max){
                max = genres[i];
                maxPos = i;
            }
        }
        String message = ""; 
        if(maxPos == 0){
            message = "El genero mas reproducido es ROCK con "+genres[maxPos]+ " Reproducciones";
        }
        if(maxPos == 1){
            message = "El genero mas reproducido es POP con "+genres[maxPos]+ " Reproducciones";
        }
        if(maxPos == 2){
            message = "El genero mas reproducido es TRAP con "+genres[maxPos]+ " Reproducciones";
        }
        if(maxPos == 3){
            message = "El genero mas reproducido es HOUSE con "+genres[maxPos]+ " Reproducciones";
        }

        return message;
    }
    public int[] getGenrePlays(){
        boolean isFound = false;
        //                           R  P  T  H
        int[] genrePlays = new int[]{0, 0, 0, 0};
        for (int i = 0; i < users.size() && !isFound; i++) {
            if(users.get(i) instanceof ProducerUser){
                for (int j = 0; j < ((ProducerUser)users.get(i)).getAudios().size(); j++) {
                    if((((ProducerUser)users.get(i)).getAudios().get(i)) instanceof Song){
                        if(((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getGenre() == Genre.ROCK){
                            genrePlays[0]++;
                        }
                        if(((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getGenre() == Genre.POP){
                            genrePlays[1]++;
                        }
                        if(((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getGenre() == Genre.TRAP){
                            genrePlays[2]++;
                        }
                        if(((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getGenre() == Genre.HOUSE){
                            genrePlays[3]++;
                        }
                    }
                }
            }
        }
        return genrePlays;
    }

    public String getMaxPlayedCategory(){
        int[] genres = getCategoryPlays();
        int max = genres[0]; 
        int maxPos = 0;
        int min = 0;
        for (int i = 1; i < genres.length; i++) {
            min = genres[i];
            if(min>max){
                max = genres[i];
                maxPos = i;
            }
        }
        String message = ""; 
        if(maxPos == 0){
            message = "La categoria mas reproducida es POLITICA con "+genres[maxPos]+ " Reproducciones";
        }
        if(maxPos == 1){
            message = "La categoria mas reproducida es ENTRETENIMIENTO con "+genres[maxPos]+ " Reproducciones";
        }
        if(maxPos == 2){
            message = "La categoria mas reproducida es MODA con "+genres[maxPos]+ " Reproducciones";
        }
        if(maxPos == 3){
            message = "La categoria mas reproducida es VIDEOJUEGOS con "+genres[maxPos]+ " Reproducciones";
        }

        return message;
    }
    public int[] getCategoryPlays(){
        //                           P  E  M  V
        int[] genrePlays = new int[]{0, 0, 0, 0};
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i) instanceof ProducerUser){
                for (int j = 0; j < ((ProducerUser)users.get(i)).getAudios().size(); j++) {
                    if((((ProducerUser)users.get(i)).getAudios().get(i)) instanceof Podcast){
                        if(((Podcast)(((ProducerUser)users.get(i)).getAudios().get(i))).getCategory() == Category.POLITCS){
                            genrePlays[0]++;
                        }
                        if(((Podcast)(((ProducerUser)users.get(i)).getAudios().get(i))).getCategory() == Category.ENTERTAINMENT){
                            genrePlays[1]++;
                        }
                        if(((Podcast)(((ProducerUser)users.get(i)).getAudios().get(i))).getCategory() == Category.MODE){
                            genrePlays[2]++;
                        }
                        if(((Podcast)(((ProducerUser)users.get(i)).getAudios().get(i))).getCategory() == Category.VIDEOGAMES){
                            genrePlays[3]++;
                        }
                    }
                }
            }
        }
        return genrePlays;
    }

    public String getMaxCategory(String id){
        String message = "";
        User user = searchUserObj(id);
        if(user instanceof ConsumerUser){
            message = ((ConsumerUser)(user)).MaxCategory();
        }else{
            message = "Este calculo no es visible para usuarios productores";
        }
        return message;
    }

    public String getMaxGenre(String id){
        String message = "";
        User user = searchUserObj(id);
        if(user instanceof ConsumerUser){
            message = ((ConsumerUser)(user)).MaxGenre();
        }else{
            message = "Este calculo no es visible para usuarios productores";
        }
        return message;
    }

    public String getTop5Producers() {
        ArrayList<ProducerUser> artists = new ArrayList<ProducerUser>();
		ArrayList<ProducerUser> creators = new ArrayList<ProducerUser>();
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i) instanceof ContentCreator){
				creators.add((ProducerUser)users.get(i));
			}
			if(users.get(i) instanceof Artist){
				artists.add((ProducerUser)users.get(i));
			}
		}
		if(creators.size()>1){
			Collections.sort(creators, Collections.reverseOrder());
		}
		if(artists.size()>1){
			Collections.sort(artists, Collections.reverseOrder());
		}
        String message2 = "\n Top 5 artistas \n\n";
		if(artists.size()>1){ 
			for (int i = 0; i <= 5 && i<creators.size(); i++) {
				message2 += i+1+":"+artists.get(i).getName()+ " Reproducciones: "+artists.get(i).getAccumulatedReproductions() + "\n";
			}
		}else if(artists.size()==1){
			message2 += 1+":"+artists.get(0).getName()+ " Reproducciones: "+artists.get(0).getAccumulatedReproductions() + "\n";
		}else{	
    		message2 = "No se encontraron artistas \n";
        }
		String message = "Top 5 Creadores de contenido \n\n";
			if(creators.size()>1){
				for (int i = 0; i <= 5 && i<creators.size(); i++) {
					message += i+1+":"+creators.get(i).getName()+ " Reproducciones: "+creators.get(i).getAccumulatedReproductions() + "\n";
				}
			}else if (creators.size()==1){
				message += 1+":"+creators.get(0).getName()+ " Reproducciones: "+creators.get(0).getAccumulatedReproductions() + "\n";
			}else{
				message = "No se encontraron creadores \n";
			}
			
		return message2+"\n"+message;
	}

    public String getTop10Audios() {
        ArrayList<Audio> audios = getAudioList();
		ArrayList<Audio> songs= new ArrayList<Audio>();
		ArrayList<Audio> podcast = new ArrayList<Audio>();
		for (int i = 0; i < audios.size(); i++) {
			if(audios.get(i) instanceof Song){
				songs.add(audios.get(i));
			}
			if(audios.get(i) instanceof Podcast){
				podcast.add(audios.get(i));
			}
		}
		Collections.sort(songs, Collections.reverseOrder());
		Collections.sort(podcast, Collections.reverseOrder());
		String message = "Top 10 Canciones \n\n";
		for (int i = 0; i <= 10 && i<songs.size(); i++) {
			message += i+1+": "+songs.get(i).getName()+" Genero: "+((Song)songs.get(i)).getGenre() +" Reproducciones: "+songs.get(i).getReproducction() + "\n";
			
		}
        if(songs.size()==0){
            message = "No se encontraron canciones \n";
        }

		String message2 = "\n Top 10 Podcast \n\n";
		for (int i = 0; i <= 10 && i<podcast.size(); i++) {
			message2 += i+1+": "+podcast.get(i).getName()+" Categoria: "+((Podcast)podcast.get(i)).getCategory() + " Reproducciones: "+podcast.get(i).getReproducction() + "\n";
		}
        if(podcast.size()==0){
            message2 = "No se encontraron podcast \n";
        }
		return message+message2;
	}
    public String getGenreSales(){
        //                         R  P  T  H
        int[][] genrePlays = new int[2][4];
        for (int i = 0; i < users.size() ; i++) {
            if(users.get(i) instanceof ProducerUser){
                for (int j = 0; j < ((ProducerUser)users.get(i)).getAudios().size(); j++) {
                    if((((ProducerUser)users.get(i)).getAudios().get(i)) instanceof Song){
                        if(((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getGenre() == Genre.ROCK){
                            genrePlays[0][0]+= ((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getTimesSold();
                            genrePlays[1][0]+=genrePlays[0][0]*((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getPrice();
                        }
                        if(((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getGenre() == Genre.POP){
                            genrePlays[0][1]+= ((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getTimesSold();
                            genrePlays[1][1]+=genrePlays[0][1]*((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getPrice();
                        }
                        if(((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getGenre() == Genre.TRAP){
                            genrePlays[0][2]+= ((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getTimesSold();
                            genrePlays[1][2]+=genrePlays[0][2]*((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getPrice();

                        }
                        if(((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getGenre() == Genre.HOUSE){
                            genrePlays[0][3]+= ((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getTimesSold();
                            genrePlays[1][3]+=genrePlays[0][3]*((Song)(((ProducerUser)users.get(i)).getAudios().get(i))).getPrice();
                        }
                    }
                }
            }
        }
        return "ROCK:\n"+
        "Canciones vendidas: "+genrePlays[0][0]+"\n"+
        "Ventas: "+genrePlays[1][0]+"\n"+
        "POP:\n"+
        "Canciones vendidas: "+genrePlays[0][1]+"\n"+
        "Ventas: "+genrePlays[1][1]+"\n"+
        "TRAP:\n"+
        "Canciones vendidas: "+genrePlays[0][2]+"\n"+
        "Ventas: "+genrePlays[1][2]+"\n"+
        "HOUSE:\n"+
        "Canciones vendidas: "+genrePlays[0][3]+"\n"+
        "Ventas: "+genrePlays[1][3];
    }

    public String getMostSold(){
        ArrayList<Audio> audios = getAudioList();
		ArrayList<Audio> songs= new ArrayList<Audio>();
		for (int i = 0; i < audios.size(); i++) {
			if(audios.get(i) instanceof Song){
				songs.add(audios.get(i));
			}
		}    
        double min = 0;
        double max = 0;
        int maxPos = 0;
        if(songs.size()>1){
            max = ((Song)(songs.get(0))).calculateTotalSale();
            for (int i = 1; i < songs.size(); i++) {
                min = ((Song)(songs.get(i))).calculateTotalSale();
                if(min>max){
                    max = min;
                    maxPos = i;
                }
            }
            
        }else if(songs.size()==1){
            max = ((Song)(songs.get(0))).calculateTotalSale();
            maxPos = 0;
        }
        if(songs.size()==0){
            return "No hay canciones registradas";
        }else{
            return "La cancion mas vendida es: "+songs.get(maxPos).getName()+ " Total de compras: "+((Song)(songs.get(maxPos))).getTimesSold()+ "Total de venta: "+((Song)(songs.get(maxPos))).calculateTotalSale();
        }
        

    }

    public boolean isOwned(User u, String songName){
        return ((ConsumerUser)u).searchSong(null)!=null;
    }

    /**Checks if a user is consumer
     * @param user user
     * @return true if is consumer
     */
    public boolean isConsumer(User user){
        return user instanceof ConsumerUser;
    }

    /**Checks if a user is artist
     * @param user user
     * @return true if is artist
     */
    public boolean isArtist(User user){
        return user instanceof Artist;
    }

    public String ShowInfo(){
        String message="";
        for(int i=0;i<users.size();i++){
            message+=users.get(i).toString();
        }
        return message;
    }

}
