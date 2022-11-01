package com.logic.finalproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

   Order order;
   Order order1;
   Date date = new Date();
   User user = new User(99, "User Name");
   Craftsman craftsman = new Craftsman(33, "Craftsman Name");

    @BeforeEach
   void prepareData(){
       order = new Order();
       order1 = new Order("order's name", "order's description");
    }

    @Test
    void getId() {
        assertEquals(0, order.getId());
        order.setId(88);
        assertEquals(88, order.getId());
    }

    @Test
    void setId() {
        assertEquals(0, order.getId());
        order.setId(89);
        assertEquals(89, order.getId());
    }

    @Test
    void getName() {
        assertEquals("order's name", order1.getName());
    }

    @Test
    void getDescription() {
     assertEquals("order's description", order1.getDescription());
    }

    @Test
    void getDate() {
     assertNull(order.getDate());
     order.setDate(date);
     assertEquals(date, order.getDate());
    }

    @Test
    void getPrice() {
     assertEquals(0, order.getPrice());
     order.setPrice(899);
     assertEquals(899, order.getPrice());
    }

    @Test
    void getStatus() {
     assertNull(order.getStatus());
     order.setStatus("COMPLITED");
     assertEquals("COMPLITED", order.getStatus());
    }

    @Test
    void getUser() {
     assertNull(order.getUser());
     order.setUser(user);
     assertEquals("User Name", order.getUser().getName());
    }

    @Test
    void getCraftsman() {
     assertNull(order.getCraftsman());
     order.setCraftsman(craftsman);
     assertEquals("Craftsman Name", order.getCraftsman().getName());
    }

    @Test
    void getFeedback() {
     assertNull(order.getFeedback());
     order.setFeedback("Good work!");
     assertEquals("Good work!", order.getFeedback());
    }

    @Test
    void setFeedback() {
     assertNull(order.getFeedback());
     order.setFeedback("Good work!");
     assertEquals("Good work!", order.getFeedback());
    }

    @Test
    void setName() {
     assertNull(order.getName());
     order.setName("New Name");
     assertEquals("New Name", order.getName());
    }

    @Test
    void setDescription() {
     assertNull(order.getDescription());
     order.setDescription("New description");
     assertEquals("New description", order.getDescription());
    }

    @Test
    void setDate() {
     assertNull(order.getDate());
     order.setDate(date);
     assertEquals(date, order.getDate());
    }

    @Test
    void setPrice() {
     assertEquals(0, order.getPrice());
     order.setPrice(999);
     assertEquals(999, order.getPrice());
    }

    @Test
    void setStatus() {
     assertNull(order.getStatus());
     order.setStatus("COMPLITED");
     assertEquals("COMPLITED", order.getStatus());
    }

    @Test
    void setUser() {
     assertNull(order.getUser());
     order.setUser(user);
     assertEquals("User Name", order.getUser().getName());
    }

    @Test
    void setCraftsman() {
     assertNull(order.getCraftsman());
     order.setCraftsman(craftsman);
     assertEquals("Craftsman Name", order.getCraftsman().getName());
    }
}