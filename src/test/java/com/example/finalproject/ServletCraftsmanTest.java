package com.example.finalproject;

import com.connection.ConnectionPool;
import com.logic.finalproject.ServletCheckEnterUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.logic.finalproject.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ServletCraftsmanTest {

       @Test
    void doGet() throws ServletException, IOException, SQLException {
        ServletCraftsman servlet = new ServletCraftsman();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final jakarta.servlet.http.Cookie cookie = mock(jakarta.servlet.http.Cookie.class);
        final Cookie[] cookies = {cookie};
        final String entity = "craftsmen";
        final String lang = "en";

        when(request.getSession()).thenReturn(session);
        when(request.getCookies()).thenReturn(cookies);
        when(cookie.getName()).thenReturn("name");
        when(cookie.getValue()).thenReturn("name value");

        assertEquals("craftsmen", entity);
        servlet.doGet(request, response);
        verify(request, times(1)).getSession();
        verify(session, times(1)).setAttribute("langv",lang);
        verify(request,never()).getParameter("login");
    }

    private void thenReturn(Connection connection) {
    }
}