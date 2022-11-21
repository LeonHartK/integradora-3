package model;
import java.lang.Math;
import java.util.ArrayList;

public class PlayList {
    private String name;
    private String id;
    ArrayList<Audio> audios;

    PlayList(String name){
        this.name=name;
        audios = new ArrayList<Audio>();
    }

    /**Adds or removes an audio from the playlist
     * @param audio audio to be added or removede
     * @param action action 1 add, 2 remove
     * @return
     */
    public String edit(Audio audio, int action){
        String message = "";
        if(action==1){
            if(audios.contains(audio)){
                message = "Este audio ya esta agregado";
            }else{
                audios.add(audio);
                message = "Se agrego el audio";
            }
        }else if(action==2){
            if(audios.contains(audio)){
                audios.remove(audio);
                message = "Se elimino el audio";
                
            }else{
                message = "Este audio no esta agregado";
            }
        }else{
            message = "Invalido";
        }
        return message;
    }
    /**Generates a code based of the types of audios in it
     * @return generated code and matrix where the code took reference
     */
    public String generateCode(){
        boolean isShare = false;
        int[][] matrix = new int[6][6];
        for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = (int)((Math.random()*10));
			}
		}
        String code = "";
		int[] audioscount = countAudioByType();
		if(audioscount[0]==0 && audioscount[1]>0){
		    for (int i = matrix.length-1; i >= 0; i--) {
			    code += matrix[i][0];
		    }
		    for (int i = 1; i < matrix.length-1; i++) {
			    code += matrix[i][i];
		    }
		    for (int i = matrix.length-1; i >= 0; i--) {
			    code += matrix[i][matrix.length-1];
		    }
            isShare = true;
		}
        if(audioscount[0]>0 && audioscount[1]==0){
			for (int i = 0; i < (matrix.length/3); i++) {
                code += matrix[0][i];
            }
            for (int i = 0; i < matrix[0].length ; i++) {
                code += matrix[i][(matrix.length/2)-1];
            }
            for (int i = matrix.length-1; i >= 0 ; i--) {
                code += matrix[i][matrix.length/2];
    
            }
            for (int i = matrix.length-(matrix.length/3); i < matrix.length; i++) {
                code += matrix[0][i];
            }
            isShare = true;
		}
		if(audioscount[0]>0 && audioscount[1]>0){
			for (int i = matrix.length-1; i >= 0; i--) {
                for (int j = matrix[0].length-1; j >=0 ; j--) {
                    if(	( ((i+j)%2) != 0 )  && ( (i+j)>1 ) ){
                        code += matrix[i][j];
                    }
                }
            }
            isShare = true;
		}
        if(!isShare){
            code = "No hay audios en esta playlist";
        }else{
            setId(code);
            code += "\n"+printMatrix(matrix);
        }
		
        return code;
    }

    /**Count de quantity of each audio in the playlist
     * @return
     */
    public int[] countAudioByType(){
		
		int podcastCount = 0;
		int songCount = 0;
		for (int i = 0; i < audios.size(); i++) {
			if(audios.get(i) instanceof Song){
				songCount++;
			}
			if(audios.get(i) instanceof Podcast){
				podcastCount++;
			}
		}
		int[] podSong = new int[]{podcastCount, songCount};
		return podSong;
	}

    /**Prints a matrix
     * @param matrix matrix
     * @return printed matrix
     */
    public String printMatrix(int[][] matrix){
        String print = "";
        for (int i = 0; i < matrix.length; i++) {
            print += "\n";
            for (int j = 0; j < matrix[0].length; j++) {
                print += matrix[i][j]+" ";
            }
        }
        return print;
    }
    /** Returns the share display
     * @return share display
     */
    public String share(){
        return generateCode();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }    
    
    public String getName() {
        return name;
    }
}
