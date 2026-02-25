import java.util.*;

public class Game {
    private Scanner sc;
    private boolean running;

    public void startGame(String diff){

        Field field = new Field(diff);

        sc = new Scanner(System.in);
        running = true;

    }

    public void gameLoop(){

        while(running){
        }

    }

    public void stopGame(){
        sc.close();
        running = false;
    }



}
