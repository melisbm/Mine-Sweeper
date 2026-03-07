import java.util.*;

public class Game {

    private Scanner sc;
    private boolean running;

    public void startGame(String diffPick){

        Field field = new Field(diffPick);
        field.resetField();

        sc = new Scanner(System.in);

        running = true;

        gameLoop(field);
    }

    public void gameLoop(Field field){

        while(running){

            System.out.println(field.fieldToString());

            System.out.print("\nRow: ");
            int rowPick = sc.nextInt() - 1;

            System.out.print("Column: ");
            int colPick = sc.nextInt() - 1;
            sc.nextLine();

            System.out.print("([R]eveal, [F]lag down: ");
            String actionPick = sc.next();

            if(field.updateField(rowPick, colPick, actionPick)){
                System.out.println("You stepped on a mine. You lost!");
                running = false;
            }

            System.out.println();
        }
    }

    public void stopGame(){
        sc.close();
        running = false;
    }
}
