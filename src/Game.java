import java.util.*;

public class Game {

    private Scanner sc;
    private boolean running;

    public void startGame(String diffPick){

        Difficulty diff = new Difficulty();
        Field field = new Field(diff, diffPick);
        field.resetField();

        sc = new Scanner(System.in);

        running = true;

        gameLoop(field);
    }

    public void gameLoop(Field field){

        while(running){

            System.out.println(field.fieldToString());
            System.out.print("\nColumn: ");
            int rowPick = sc.nextInt();
            System.out.print("Row: ");
            int colPick = sc.nextInt();

            field.updateField(rowPick, colPick);
        }

    }

    public void stopGame(){
        sc.close();
        running = false;
    }
}
