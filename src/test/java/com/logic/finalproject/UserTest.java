package com.logic.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;
    User user1;

    @BeforeEach
    void preparedData(){
        user = new User();
        user1 = new User("user@e.mail", "Name User I", "pass123");
    }

    @Test
    void getBalance() {
        assertEquals(0, user1.getBalance());
    }

    @Test
    void setBalance() {
        int newBalance = 222;
        assertEquals(0, user.getBalance());
        user.setBalance(newBalance);
        assertEquals(222, user.getBalance());
    }

    @Test
    void testToString() {
        assertEquals("Name User I", user1.toString());
    }
}