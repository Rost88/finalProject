package com.logic.finalproject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class ServletCheckEnterUserTest {

    @Test
    void doPost() throws ServletException, IOException {
        ServletCheckEnterUser servlet = new ServletCheckEnterUser();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        String adressRedirect = "/user";
        when(request.getParameter("password")).thenReturn(adressRedirect);
        when(request.getSession()).thenReturn(session);
        assertEquals(adressRedirect, request.getParameter("password"));
        servlet.doPost(request, response);
        verify(request, times(1)).getSession();
        verify(request,times(1)).getParameter("login");
    }
}