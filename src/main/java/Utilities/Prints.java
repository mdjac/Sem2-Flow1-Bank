package Utilities;

import Entities.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Prints {

    public static void printTransactionsArrayList(List<Transaction> arrayList){
        for (Transaction t : arrayList){
            System.out.println(t);
        }
    }
}
