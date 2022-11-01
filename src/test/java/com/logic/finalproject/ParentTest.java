package com.logic.finalproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParentTest {

    Parent parent;
    @BeforeEach
    void prepareData(){
        parent = new Parent(99, "name@i.ua", "Name Parent", "pass111" ) {
            @Override
            public int getId() {
                return super.getId();
            }
        };
    }

    @Test
    void getId() {
        assertEquals(99, parent.getId());
    }

    @Test
    void getEmail() {
        assertEquals("name@i.ua", parent.getEmail());
    }

    @Test
    void getName() {
        assertEquals("Name Parent", parent.getName());
    }

    @Test
    void getPassword() {
        assertEquals("pass111", parent.getPassword());
    }

    @Test
    void setId() {
        int newID = 5;
        parent.setId(newID);
        assertEquals(newID, parent.getId());
    }

    @Test
    void setEmail() {
        String newEmail = "new@e.mail";
        parent.setEmail(newEmail);
        assertEquals(newEmail, parent.getEmail());
    }

    @Test
    void setName() {
        String newName = "New Name";
        parent.setName(newName);
        assertEquals(newName, parent.getName());
    }

    @Test
    void setPassword() {
        String newPass = "new111pass";
        parent.setPassword(newPass);
        assertEquals(newPass, parent.getPassword());
    }

    @Test
    void testConstructorParent() {
        Parent par = new Parent() {
            @Override
            public int getId() {
                return super.getId();
            }
        };
        assertNull(par.getName());
        assertNull(par.getName());
    }

    @Test
    void testConstructorParentWithTwoParameter() {
        Parent par = new Parent(99, "Name Parent" ) {
            @Override
            public int getId() {
                return super.getId();
            }
        };
        assertEquals(99, par.getId());
        assertEquals("Name Parent", par.getName());
    }

    @Test
    void testConstructorParentWithThreeParameter() {
        Parent par = new Parent("name@i.ua", "Name Parent", "pass111" ) {
            @Override
            public int getId() {
                return super.getId();
            }
        };
        assertEquals("Name Parent", par.getName());
        assertEquals("pass111", par.getPassword());
    }

    @Test
    void testConstructorParentWithFourParameter() {
        Parent par = new Parent(99, "name@i.ua", "Name Parent", "pass111" ) {
            @Override
            public int getId() {
                return super.getId();
            }
        };
        assertEquals(99, par.getId());
        assertEquals("pass111", par.getPassword());
    }
}