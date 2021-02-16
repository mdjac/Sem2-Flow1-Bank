package Utilities;
import java.util.Scanner;

public class PromtForAnswers {
    //Scanner til at læse en string fra tastaturet
    public static String promptForAnswerString()
    {
        try {
            Scanner keyboard = new Scanner(System.in);
            String input = keyboard.nextLine();
            return input;
        }catch (Exception e){
            System.out.println(e + " You have entered an invalid input, please try again");
        }
        return promptForAnswerString();
    }
    //Scanner til at læse en Integer fra tastaturet
    public static int promptForAnswerInt()
    {
        try
        {
            Scanner keyboard = new Scanner(System.in);
            return keyboard.nextInt();
        } catch (Exception e)
        {
            System.out.println(e + " You have entered an invalid input, please try again");
        }
        return promptForAnswerInt();
    }
}
