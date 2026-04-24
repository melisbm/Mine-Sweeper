package Console;

import java.util.Scanner;

public class Console {

    private Scanner scanner;

    public Console(){
        scanner = new Scanner(System.in);
    }

    public void print(String text){
        System.out.print(text);
    }

    public void print(String text, int insertsStart, int insertsEnd){
        System.out.print("\n".repeat(insertsStart) + text + "\n".repeat(insertsEnd));
    }

    public int inputInt(){

        int input = scanner.nextInt();
        scanner.nextLine();

        return input;
    }

    public char inputChar(){
        return scanner.nextLine().charAt(0);
    }

    public void close(){
        scanner.close();
    }
}
