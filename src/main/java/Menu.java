public class Menu {
    public void displayMainMenu(){
        System.out.println("-----Main Menu-----");
        System.out.println("1  : Create User");
        System.out.println("2  : Login");
        System.out.println("-1 : Quit program");
    }
    public void displayCustomerMenu(){
        System.out.println("-----Customer Menu-----");
        System.out.println("1  : Show transactions");
        System.out.println("2  : Deposit");
        System.out.println("3  : Withdraw");
        System.out.println("4  : Transfer");
        System.out.println("-1  : Logout");
    }
    public void displayEmployeeMenu(){
        System.out.println("-----Employee Menu-----");
        System.out.println("1  : Show transactions");
        System.out.println("2  : Show customers");
        System.out.println("3  : Transfer");
        System.out.println("-1  : Logout");
    }
}
