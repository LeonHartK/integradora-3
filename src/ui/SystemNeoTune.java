package ui;

import java.util.Scanner;
import java.time.LocalDate;
import model.NeoTunes;

public class SystemNeoTune{
    Scanner entrada;
    NeoTunes mensajero;
    String currentUser;

    public SystemNeoTune(){
        entrada = new Scanner(System.in);
        mensajero = new NeoTunes();
    }
    
    public static void main(String[] args) {
        SystemNeoTune neoTune = new SystemNeoTune();

        int opc;

        do{
            opc=neoTune.menu();

            neoTune.ejecutar(opc);

            
        } while(opc!=3);
    }

    public int menu(){
        System.out.println("1) Iniciar sesion");
        System.out.println("2) Registrarme");
        System.out.println("3) Salir");

        int n=entrada.nextInt();
        entrada.nextLine();

        return n;
    }

    public int menu2(){
        System.out.println("Que deseas hacer en NeoTunes?");
        System.out.println("1) Producir musica o podcast");
        System.out.println("2) Escuchar musica");

        int n=entrada.nextInt();
        entrada.nextLine();

        return n;
    }

    public int menu3(){
        System.out.println("Que tipo de usuario eres?");
        System.out.println("1) Artista");
        System.out.println("2) Creador de contenido");

        int n=entrada.nextInt();
        entrada.nextLine();

        return n;
    }

    public int menu4(){
        System.out.println("Escoge un tipo de usuario");
        System.out.println("1) Premium");
        System.out.println("2) Estandar");

        int n=entrada.nextInt();
        entrada.nextLine();

        return n;
    }

    /**Show the menu for a Prodcer user
     * @return option selected
     */
    public int menu5(){
        System.out.println("1) Añadir Audio");
        System.out.println("2) Ver informacion de la app");
        System.out.println("3) Cerrar sesion");

        int n=entrada.nextInt();
        entrada.nextLine();

        return n;
    }

    /**Shows the menu for a Consumer user
     * @return option selected  
     */
    public int menu6(){
        System.out.println("1) Crear lista de reproduccion");
        System.out.println("2) Editar una lista de reproduccion");
        System.out.println("3) Compartir una lista de reproduccion");
        System.out.println("4) Reproducir una cancion");
        System.out.println("5) Comprar una cancion");
        System.out.println("6) Ver informacion de la app");
        System.out.println("7) Cerrar sesion");

        int n=entrada.nextInt();
        entrada.nextLine();

        return n;
    }

    /**
     * Gets information to register an Artist
     */
    public void registerArtist(){
        System.out.println("Ingresa tu nombre de usuario");
        String nickname=entrada.nextLine();

        System.out.println("Ingresa tu numero de cedula, sin comas ni espacios");
        String id=entrada.nextLine();

        System.out.println("Inserta una url con tu foto o imagen distintiva");
        String URL=entrada.nextLine();

        LocalDate registerDate=LocalDate.now();
        //System.out.println(registerDate);

        System.out.println(mensajero.addUser(id, registerDate, nickname, URL, 0, 0));
    }

    /**
     * Gets information to register a Content Creator
     */
    public void registerContentCreator(){
        System.out.println("Ingresa tu nombre completo por favor");
        String nickname=entrada.nextLine();

        System.out.println("Ingresa tu numero de cedula, sin comas ni espacios");
        String id=entrada.nextLine();

        System.out.println("Inserta una url con tu foto o imagen distintiva");
        String URL=entrada.nextLine();

        LocalDate registerDate=LocalDate.now();
        //System.out.println(registerDate);

        System.out.println(mensajero.addUserContentCreator(id, registerDate, nickname, URL, 0, 0));
    }

    public void registerPremium(){
        System.out.println("Ingresa tu nombre de usuario");
        String nickname=entrada.nextLine();

        System.out.println("Ingresa tu numero de cedula, sin comas ni espacios");
        String id=entrada.nextLine();

        LocalDate registerDate=LocalDate.now();
        //System.out.println(registerDate);

        System.out.println(mensajero.addPremiumUser(nickname, id, registerDate));
    }

    public void registerUserStandard(){
        System.out.println("Ingresa tu nombre de usuario");
        String nickname=entrada.nextLine();

        System.out.println("Ingresa tu numero de cedula, sin comas ni espacios");
        String id=entrada.nextLine();

        LocalDate registerDate=LocalDate.now();
        //System.out.println(registerDate);

        System.out.println(mensajero.addUser(nickname, id, registerDate));
    }

    /**
     * Gets data to add a song
     */
    public void registerSong(){
        System.out.println("Ingrese el nombre de la cancion");
        String name=entrada.nextLine();

        System.out.println("Ingrese el nombre del album");
        String nameAlbum=entrada.nextLine();

        System.out.println("Digita la url de la foto de la cancion");
        String urlPhoto=entrada.nextLine();

        System.out.println("Cuanto tiempo dura la cancion? Escribelo en formato de segundos");
        int time=entrada.nextInt();
        

        System.out.println("Cual es el genero de la cancion?");
        System.out.println("1) Rock");
        System.out.println("2) Pop");
        System.out.println("3) Trap");
        System.out.println("4) House");
        int genre=entrada.nextInt();
        entrada.nextLine();

        System.out.println("Cual es el precio para la canción");
        double price=entrada.nextDouble();
        
        System.out.println(mensajero.addAudio(mensajero.searchUserObj(getCurrentUser()), mensajero.createAudio(name, urlPhoto, time, nameAlbum, genre, price, null, 0, 1)));
    }

    /**
     * Gets data to add a podcast
     */
    public void registerPodcast(){
        System.out.println("Ingrese el nombre para el podcast");
        String name=entrada.nextLine();

        System.out.println("Digita la url de la imagen distintiva");
        String urlPhoto=entrada.nextLine();

        System.out.println("Cuanto tiempo dura el podcast? Escribelo en formato de segundos");
        int time=entrada.nextInt();
        entrada.nextLine();

        System.out.println("Añade una descripcion para el podcast");
        String desc=entrada.nextLine();

        System.out.println("Cual es la categoria?");
        System.out.println("1) Politica");
        System.out.println("2) Entretenimiento");
        System.out.println("3) Moda");
        System.out.println("4) Videojuegos");
        int categ=entrada.nextInt();

        System.out.println(mensajero.addAudio(mensajero.searchUserObj(getCurrentUser()), mensajero.createAudio(name, urlPhoto, time, name, 0, 0.0, desc, categ, time)));
    }

    /**
     * Gets data to add a playlist
     */
    public void createListReproduction(){
        System.out.println("Digite el nombre de la lista de reproduccion");
        String name=entrada.nextLine();
        System.out.println(mensajero.createPlaylist(getCurrentUser(), name));
    }

    /**
     * Gets the data to edit a playlist
     */
    public void editPlaylist(){
        System.out.println("Digite el nombre de la playlist que desea editar");
        String name = entrada.nextLine();
        System.out.println("Escoja la accion que desea hacer");
        System.out.println("1) Agregar audio");
        System.out.println("2) Eliminar audio");
        int option = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite el nombre del audio");
        String audioName = entrada.nextLine();
        System.out.println(mensajero.editPlaylist(getCurrentUser(), name, audioName, option));
    }

    /**
     * Gets the name of a playlist to share
     */
    public void sharePlaylist(){
        System.out.println("Digite el nombre de la playlist que desea compartir");
        String name = entrada.nextLine();
        System.out.println(mensajero.sharePlaylist(getCurrentUser(), name));
    }

    /**
     * Simulates an Audio playing
     */
    public void playSong(){
        boolean isStandar = mensajero.isStandar(mensajero.searchUserObj(getCurrentUser()));
        System.out.println("Digite el nombre del audio que desea reproducir");
        String name = entrada.nextLine();
        if(mensajero.searchAudio(name)!=null){
            int seconds = mensajero.searchAudio(name).getTime();
            System.out.println("Reproduciendo "+name);
            int songCount = 0;
            Boolean isOwned = false;
            Boolean isSong = mensajero.isSong(mensajero.searchAudio(name));
            if(isSong){
                isOwned = mensajero.isOwned(mensajero.searchUserObj(getCurrentUser()), name);
            }
            String opt;
            int playedTime = 0;
            boolean isDone = false;
            if((isSong && isOwned) || !isSong){ 
                for (int i = 0; i < seconds && !isDone; i++) {
                    if(isStandar && !isSong){
                        System.out.println("Anuncio publicitario, su audio sera reproducido en breve");
                        try {
                            Thread.sleep(5*1000);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    if(isSong){
                        songCount++;
                    }
                    if(isStandar && songCount%2==0){
                        System.out.println("Anuncio publicitario, su audio sera reproducido en breve");
                        try {
                            Thread.sleep(5*1000);
                        } catch (Exception e) {
                            System.out.println(e);
                        }       
                    }
                    try {
                        Thread.sleep(1*1000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    playedTime = i;
                    if(playedTime%10==0 && playedTime>0){
                        System.out.println("Interaccion obligatoria, presiona enter para seguir reproduciendo");
                        opt = entrada.nextLine();
                        if(!opt.equals("")){
                            isDone = true;
                        }
                    }
                }
                mensajero.playAudio(mensajero.searchAudio(name), playedTime);
                System.out.println("Reproduccion terminada");
            }else{
                System.out.println("No has comprado esta cancion");
            }
            
        }else{
            System.out.println("No se encontro el audio");
        }
    }

    /**
     * Gets information to buy a song
     */
    public void buySong(){
        System.out.println("Digite el nombre de la cancion que desea comprar");
        String name = entrada.nextLine();
        System.out.println(mensajero.buySong(getCurrentUser(), name));
    }

    public void generateStadistis(){
        System.out.println(mensajero.generateStadistics(getCurrentUser()));
    }

    /**Executes the option got as parameter
     * @param n option
     */
    public void ejecutar(int n){

        if (n==1){
            int nume = 0;
            System.out.println("Digita tu id (Cedula)");
            String id=entrada.nextLine();
            if (mensajero.searchUser(id)==true){
                setCurrentUser(id);
                if(!mensajero.isConsumer(mensajero.searchUserObj(id))){
                    do{ 
                        nume=menu5();
                        if(mensajero.isArtist(mensajero.searchUserObj(id))){ 
                            if (nume==1){
                                registerSong();
                            } else if(nume==2){
                                generateStadistis();
                            } else if(nume==3){
                                System.out.println("Sesion cerrada");
                            } else{
                                System.out.println("Invalido");
                            }
                        }else{
                            if (nume==1){
                                registerPodcast();
                            } else if(nume==2){
                                generateStadistis();
                            } else if(nume==3){
                                System.out.println("Sesion cerrada");
                            } else{
                                System.out.println("Invalido");
                            }
                        }
                    }while(nume!=3);
                }else{
                    do{ 
                        nume=menu6();
                        if (nume==1){
                            createListReproduction();
                        } else if(nume==2){
                            editPlaylist();
                        } else if(nume==3){
                            sharePlaylist();
                        } else if(nume==4){
                            playSong();
                        }else if(nume==5){
                            buySong();
                        }else if(nume==6){
                            generateStadistis();
                        }else if(nume==7){
                            System.out.println("Sesion cerrada");
                        }else{
                            System.out.println("Invalido");
                        }
                        
                    }while(nume!=7);
                }
                    
            } else {
                System.out.println("Che algo anda mal, checate bien");
            }
        } else if (n==2){
            int num=menu2();
            if (num==1){
                int exp=menu3();
                if (exp==1){
                    registerArtist();
                } else if (exp==2){
                    registerContentCreator();
                }
            } else if (num==2){
                int sum=menu4();
                if (sum==1){
                    registerPremium();
                } else if (sum==2){
                    registerUserStandard();  
                }
            }
        } else if (n==3){
            System.out.println("Saliendo..");
        }
    }

    
    /**Gets the id of the current loged user
     * @return id of the user
     */
    public String getCurrentUser() {
        return currentUser;
    }
    /**
     * Sets the id of the last current user to the actual loged user
     * @param currentUser actual loged user id
     */
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

}