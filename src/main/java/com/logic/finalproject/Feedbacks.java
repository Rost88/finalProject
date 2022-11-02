/**
 * The class creates a feedback from a user about a craftsman
 *
 * @author Kuznietsov Rostyslav
 */

package com.logic.finalproject;

public class Feedbacks {
    String userName;
    String orderName;
    String feedback;

    public Feedbacks(String userName, String orderName, String feedback) {
        this.userName = userName;
        this.orderName = orderName;
        this.feedback = feedback;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
