package com.pageConstructor.finalproject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PageConstructorTest {

//    static String language = "uk";
//    static String language2 = "en";

    @Test
    void lang() {
        String language = "uk";
        String language2 = "en";
        PageConstructor.lang(language);
        assertEquals(language, PageConstructor.langv);
        PageConstructor.lang(language2);
        assertEquals(language2, PageConstructor.langv);
    }

    @Test
    void finishTitleStartBody() {
    }

    @Test
    void finishPage() {

    }
}