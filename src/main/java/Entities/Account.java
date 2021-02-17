package Entities;

import Entities.Customer;
import Exceptions.BankException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private int accountID;
    private List<Transaction> transactions;

    public Account(ArrayList<Transaction> transactions,int accountID) {
        this.transactions = transactions;
        this.accountID = accountID;
    }

    public int getBalance(){
        // TODO: skal debugges
        int sum = 0;
        for (Transaction transaction : transactions) {
            sum += transaction.getAmount();
        }
        return sum;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
