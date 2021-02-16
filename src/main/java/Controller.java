import Entities.Customer;
import Entities.Employee;
import Entities.User;
import Exceptions.AuthenticationException;
import Utilities.Database;
import Utilities.EncryptSHA1;
import Utilities.PromtForAnswers;

import java.sql.SQLException;


public class Controller {
    Menu menu;
    Database productionDatabase;
    private int input;
    private User user;

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
            CLImainMenu();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    private void CLImainMenu() throws SQLException, AuthenticationException {
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
                    user = productionDatabase.validate_credentials(PromtForAnswers.promptForAnswerString(),EncryptSHA1.encryptThisString(PromtForAnswers.promptForAnswerString()));
                    System.out.println("You are now logged in with: "+user.getUsername());
                    user.test();
                    if(user instanceof Employee){
                        CLIemployeeMenu();
                    }
                    if(user instanceof Customer){
                        ((Customer) user).myAccount.test();
                        System.out.println(((Customer) user).myAccount.getBalance());
                        System.out.println(((Customer) user).myAccount.getTransactions());
                        CLIcustomerMenu();
                    }
                    break;
            }
        }
    }

    private void CLIcustomerMenu() throws SQLException, AuthenticationException {
        while (0 < 1) {
            menu.displayCustomerMenu();
            input = PromtForAnswers.promptForAnswerInt();
            switch (input) {
                case -1:
                    //Logout
                    user = null;
                    CLImainMenu();
                case 1:
                    //TODO : Must show transactions
                    break;
                case 2:
                    //TODO : Must deposit transactions
                    break;
                case 3:
                    //TODO : Must withdraw transactions
                    break;
                case 4:
                    //TODO : Must transfer
                    break;
            }
        }
    }
    private void CLIemployeeMenu() throws SQLException, AuthenticationException {
        while (0 < 1) {
            menu.displayEmployeeMenu();
            input = PromtForAnswers.promptForAnswerInt();
            switch (input) {
                case -1:
                    //Logout
                    user = null;
                    CLImainMenu();
                case 1:
                    //TODO : Must show transactions
                    break;
                case 2:
                    //TODO : Show customers
                    break;
                case 3:
                    //TODO : Transfer
                    break;
            }
        }
    }

}
