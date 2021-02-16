import Entities.User;
import Utilities.Database;
import Utilities.EncryptSHA1;
import Utilities.PromtForAnswers;

import java.sql.SQLException;


public class Controller {
    Menu menu;
    Database productionDatabase;
    private int input;
    private String username;

    public Controller() {
        menu = new Menu();
        try {
            productionDatabase = new Database("jdbc:mysql://64.227.113.104:3306/ebberoedBank?serverTimezone=UTC","ebberoedBank","ebberoedBank12345!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public  void runCLI(){
        try {
            displayCLI();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void displayCLI() throws SQLException {
        while (0 < 1) {
            menu.displayMainMenu();
            input = PromtForAnswers.promptForAnswerInt();
            switch (input) {
                case -1:
                    System.exit(0);
                case 1:
                    System.out.println("Please enter Username,Password,Type(1 for employee, 2 for customer),Name & Address");
                    productionDatabase.create_user(PromtForAnswers.promptForAnswerString(), EncryptSHA1.encryptThisString(PromtForAnswers.promptForAnswerString()),PromtForAnswers.promptForAnswerInt(),PromtForAnswers.promptForAnswerString(),PromtForAnswers.promptForAnswerString());
                    break;
                case 2:
                    System.out.println("Please enter Username followed by Password");
                    User user = productionDatabase.validate_credentials(PromtForAnswers.promptForAnswerString(),EncryptSHA1.encryptThisString(PromtForAnswers.promptForAnswerString()));
                    System.out.println("You are now logged in with: "+user.getUsername());
                    user.test();
                    break;
            }
        }
    }
}
