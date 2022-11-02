/**
 * The class creates the entity "craftsman"
 *
 * @author Kuznietsov Rostyslav
 */
package com.logic.finalproject;

public class Craftsman extends Parent {
    private String photo;

    public Craftsman() {
    }
    public Craftsman(int id, String name) {
        super(id, name);
    }

    public Craftsman(String email, String name, String password) {
        super(email, name, password);
    }

    public Craftsman(int id, String email, String name, String password, String photo) {
        super(id, email, name, password);
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return getName();
    }
}
