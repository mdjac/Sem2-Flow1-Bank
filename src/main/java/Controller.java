import Entities.Customer;
import Entities.Employee;
import Entities.User;
import Exceptions.AuthenticationException;
import Exceptions.BankException;
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
            productionDatabase = new Database("jdbc:mysql://64.227.113.104:3306/ebberoedBank?serverTimezone=UTC", "ebberoedBank", "ebberoedBank12345!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void runCLI() {
        try {
            CLImainMenu();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    private void CLImainMenu() throws SQLException, AuthenticationException, BankException {
        while (0 < 1) {
            menu.displayMainMenu();
            input = PromtForAnswers.promptForAnswerInt();
            switch (input) {
                case -1:
                    System.exit(0);
                case 1:
                    System.out.println("Please enter Username,Password,Type(1 for employee, 2 for customer),Name & Address");
                    productionDatabase.create_user(PromtForAnswers.promptForAnswerString(), EncryptSHA1.encryptThisString(PromtForAnswers.promptForAnswerString()), PromtForAnswers.promptForAnswerInt(), PromtForAnswers.promptForAnswerString(), PromtForAnswers.promptForAnswerString());
                    break;
                case 2:
                    System.out.println("Please enter Username followed by Password");
                    user = productionDatabase.validate_credentials(PromtForAnswers.promptForAnswerString(), EncryptSHA1.encryptThisString(PromtForAnswers.promptForAnswerString()));
                    System.out.println("You are now logged in with: " + user.getUsername());
                    if (user instanceof Employee) {
                        CLIemployeeMenu();
                    }
                    if (user instanceof Customer) {
                        System.out.println("Your current balance is: " + ((Customer) user).myAccount.getBalance());
                        CLIcustomerMenu();
                    }
                    break;
            }
        }
    }

    private void CLIcustomerMenu() throws SQLException, AuthenticationException, BankException {
        while (0 < 1) {
            if (user instanceof Customer) {
                menu.displayCustomerMenu();
                input = PromtForAnswers.promptForAnswerInt();
                switch (input) {
                    case -1:
                        //Logout
                        user = null;
                        CLImainMenu();
                    case 1:
                        System.out.println(((Customer) user).myAccount.getTransactions());
                        break;
                    case 2:
                        System.out.println("Please insert deposit amount (as Integer)");
                        productionDatabase.deposit_transactions(((Customer) user).myAccount.getAccountID(),PromtForAnswers.promptForAnswerInt());
                        ((Customer) user).myAccount.setTransactions(productionDatabase.get_transactions(user.getUsername()));
                        System.out.println("Your balance is now: "+((Customer) user).myAccount.getBalance());
                        break;
                    case 3:
                        System.out.println("Please insert withdraw amount (as Integer)");
                        productionDatabase.withdraw_transactions(((Customer) user).myAccount.getAccountID(),PromtForAnswers.promptForAnswerInt(),((Customer) user).myAccount.getBalance());
                        ((Customer) user).myAccount.setTransactions(productionDatabase.get_transactions(user.getUsername()));
                        System.out.println("Your balance is now: "+((Customer) user).myAccount.getBalance());
                        break;
                    case 4:
                        System.out.println("Please enter amount and receivers accountID");
                        int amount = PromtForAnswers.promptForAnswerInt();
                        int receiverAccountID = PromtForAnswers.promptForAnswerInt();
                        if (productionDatabase.check_for_accountID(receiverAccountID)) {
                            productionDatabase.withdraw_transactions(((Customer) user).myAccount.getAccountID(), amount, ((Customer) user).myAccount.getBalance());
                            productionDatabase.deposit_transactions(receiverAccountID, amount);
                            ((Customer) user).myAccount.setTransactions(productionDatabase.get_transactions(user.getUsername()));
                            System.out.println("Your balance is now: " + ((Customer) user).myAccount.getBalance());
                        }
                        else {
                            System.out.println("The entered receivers accountID doesnt exist");
                        }
                        break;
                    case 5:
                        System.out.println("Your current balance is: "+((Customer) user).myAccount.getBalance());
                        break;
                }
            }
        }
    }
        private void CLIemployeeMenu () throws SQLException, AuthenticationException, BankException {
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
