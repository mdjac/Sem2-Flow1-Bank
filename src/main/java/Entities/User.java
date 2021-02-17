package Entities;

import lombok.Data;

import java.util.ArrayList;

@Data
public class User {
    private String name;
    private String username;
    private String address;

    public User(String name, String username, String address) {
        this.name = name;
        this.username = username;
        this.address = address;
    }
}
