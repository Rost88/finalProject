package com.logic.finalproject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServletCreateOrderTest {

    @Test
    void doPost() throws ServletException, IOException {
        ServletCreateOrder servlet = new ServletCreateOrder();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final PrintWriter printWriter = mock(PrintWriter.class);
        final Cookie cookie = mock(Cookie.class);
        final Cookie[] cookies = {cookie};

        String adressRedirect = "/user";
        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(printWriter);
        when(request.getCookies()).thenReturn(cookies);
        when(cookie.getName()).thenReturn("name");
        servlet.doPost(request, response);
        verify(request, times(1)).getParameter("lang");
        verify(response).sendRedirect(adressRedirect);
        verify(request, times(1)).getSession();
    }
}