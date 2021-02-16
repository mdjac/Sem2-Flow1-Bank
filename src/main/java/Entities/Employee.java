package Entities;

import java.util.ArrayList;

public class Employee extends User{
    public Employee(String name, String username, String address) {
        super(name, username, address);
    }

    @Override
    public boolean transferMoney() {
        return super.transferMoney();
    }

    @Override
    public ArrayList<Transaction> showTransactions() {
        return super.showTransactions();
    }

    public ArrayList<Customer> showCustomers(){
        return new ArrayList<>();
    }

    public void test(){
        System.out.println("Im an employee");
    }
}
