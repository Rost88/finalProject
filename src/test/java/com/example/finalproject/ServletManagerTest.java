package com.example.finalproject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import com.logic.finalproject.Manager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class ServletManagerTest {

    @Test
    void doGet() throws ServletException, IOException {
        ServletManager servlet = new ServletManager();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final jakarta.servlet.http.Cookie cookie = mock(jakarta.servlet.http.Cookie.class);
        final Cookie[] cookies = {cookie};
        int id = 1;

        when(request.getSession()).thenReturn(session);
        when(request.getCookies()).thenReturn(cookies);
        when(cookie.getName()).thenReturn("name");
        when(cookie.getValue()).thenReturn("name value");

        assertEquals(1, id);
        assertEquals("name", cookie.getName());


        servlet.doGet(request, response);
        verify(request, times(1)).getSession();
        verify(request,times(1)).getParameter("sort");
        verify(cookie, atLeast(1)).getName();
    }
}