/**
 * The class creates the user's order
 *
 * @author Kuznietsov Rostyslav
 */
package com.logic.finalproject;

import java.util.Date;

public class Order {
  private int id;
  private String name;
  private   String description;
  private   Date date;
  private   int price;
  private   String feedback;
  private   String status; // заменил Enum на String: PENDING PAYMENT, PAID, CANCELED, IN PROGRESS, COMPLETED;
  private User user;
  private Craftsman craftsman;

    public Order(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = "PENDING PAYMENT";
    }
    public Order(){
            }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
    public int getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public Craftsman getCraftsman() {
        return craftsman;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCraftsman(Craftsman craftsman) {
        this.craftsman = craftsman;
    }
}
