/**
 * The class creates the entity "user"
 *
 * @author Kuznietsov Rostyslav
 */

package com.logic.finalproject;

public class User extends Parent{

    int balance;

    public User() {
    }

    public User(int id, String name) {
        super(id, name);
    }

    public User(String email, String name, String password) {
        super(email, name, password);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return getName();
    }
}
