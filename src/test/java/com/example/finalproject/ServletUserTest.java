package com.example.finalproject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

import com.logic.finalproject.User;

class ServletUserTest {

    @Test
    void doGet() throws ServletException, IOException {
        ServletUser servlet = new ServletUser();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        final jakarta.servlet.http.Cookie cookie = mock(jakarta.servlet.http.Cookie.class);
        final Cookie[] cookies = {cookie};
        int id = 1;
        String entity = "user";
        final PrintWriter printWriter = mock(PrintWriter.class);
        final User user = mock(User.class);
        boolean is = true;

        when(request.getSession()).thenReturn(session);
        when(request.getCookies()).thenReturn(cookies);
        when(cookie.getName()).thenReturn("name");
        when(cookie.getValue()).thenReturn("name value");
        when(response.getWriter()).thenReturn(printWriter);
        when(request.getRequestDispatcher("/page-user")).thenReturn(requestDispatcher);

        assertEquals(1, id);
        assertEquals("name", cookie.getName());

        servlet.doGet(request, response);
        verify(request, times(1)).getSession();
        verify(request,never()).getParameter("login");
        verify(cookie, atLeast(1)).getName();
    }
}