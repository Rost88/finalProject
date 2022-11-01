package com.logic.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedbacksTest {

    Feedbacks feedbacks;

    @BeforeEach
    void prepareData(){
        feedbacks = new Feedbacks("User Name", "Order short deskr", "Good work!");
    }

    @Test
    void getUserName() {
        assertEquals("User Name", feedbacks.getUserName());
    }

    @Test
    void setUserName() {
        feedbacks.setUserName("New User");
        assertEquals("New User", feedbacks.getUserName());
    }

    @Test
    void getOrderName() {
        assertEquals("Order short deskr", feedbacks.getOrderName());
    }

    @Test
    void setOrderName() {
        feedbacks.setOrderName("New description");
        assertEquals("New description", feedbacks.getOrderName());
    }

    @Test
    void getFeedback() {
        assertEquals("Good work!", feedbacks.getFeedback());
    }

    @Test
    void setFeedback() {
        feedbacks.setFeedback("Really good work!!!");
        assertEquals("Really good work!!!", feedbacks.getFeedback());
    }
}