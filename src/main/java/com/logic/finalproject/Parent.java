/**
 * The abstract class is the parent of user, manager and craftsman
 *
 * @author Kuznietsov Rostyslav
 */
package com.logic.finalproject;

public abstract class Parent {
    private int id;
    private String email; //login
    private String name;
    private String password;

    public Parent(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Parent(int id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Parent() {
    }

    public Parent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
