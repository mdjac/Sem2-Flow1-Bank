package Entities;

import lombok.Data;

import java.util.Objects;

public class Customer extends User {

    public Account myAccount;

    public Customer(String name, String username, String address, Account myAccount) {
        super(name, username, address);
        this.myAccount = myAccount;
    }

    public Customer(String name, String username, String address) {
        super(name, username, address);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getName(), customer.getName());
    }

    @Override
    public void test(){
        System.out.println("Im a customer");
    }

}
