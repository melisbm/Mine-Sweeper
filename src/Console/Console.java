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
}
