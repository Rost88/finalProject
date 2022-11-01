package com.logic.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CraftsmanTest {

    Craftsman craftsman;

    @BeforeEach
    void prepareData(){
        craftsman = new Craftsman(99, "name@i.ua", "Name Craftsman", "pass111", "cr.jpg");
    }

    @Test
    void getPhoto() {
        assertEquals("cr.jpg", craftsman.getPhoto());
    }

    @Test
    void setPhoto() {
        craftsman.setPhoto("newphoto.jpg");
        assertEquals("newphoto.jpg", craftsman.getPhoto());
        Craftsman cr = new Craftsman();
        cr.setPhoto("newphoto2.jpg");
        assertEquals("newphoto2.jpg", cr.getPhoto());
    }

    @Test
    void testToString() {
        assertEquals("Name Craftsman", craftsman.toString());
        Craftsman cr = new Craftsman(2, "Second Craftsman");
        assertEquals("Second Craftsman", cr.toString());
        Craftsman cr3 = new Craftsman("third@i.ua", "Third Craftsman", "333");
        assertEquals("Third Craftsman", cr3.toString());
    }
}