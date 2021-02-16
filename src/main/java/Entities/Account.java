package Entities;

import Entities.Customer;
import Exceptions.BankException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    private List<Transaction> transactions;

    public Account(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int getBalance(){
        // TODO: skal debugges
        int sum = 0;
        for (Transaction transaction : transactions) {
            sum += transaction.getAmount();
        }
        return sum;
    }

    public int withDrawAmount(int amount) throws BankException {
        // TODO: skal kodes og returnere ny saldo. Smid fejl hvis amount > saldo
        if (getBalance() < amount){
            throw new BankException("Du forsøger at hæve et beløb som er større end din saldo");
        } else if (amount <= 0) {
            throw new BankException("Du kan ikke hæve et beløb som er 0 eller negativt");
        }
        transactions.add(new Transaction(-amount,""));
        return getBalance() ;
    }

    public int depositAmount(int amount, String date){
        // TODO: skal debugges og returnere ny saldo. Smid fejl hvis amount < 0.
        transactions.add(new Transaction(amount,date));
        return getBalance();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void test (){
        System.out.println("This is an account");
    }
}
