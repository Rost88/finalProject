package com.logic.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    Manager manager;

    @BeforeEach
    void prepareData(){
        manager = new Manager();
    }

    @Test
    void getPhoto() {
        assertNull(manager.getPhoto());
        manager.setPhoto("photo.jpg");
        assertEquals("photo.jpg", manager.getPhoto());
    }

    @Test
    void setPhoto() {
        assertNull(manager.getPhoto());
        manager.setPhoto("photo.jpg");
        assertEquals("photo.jpg", manager.getPhoto());
    }
}