package com.logic.finalproject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServletCheckEnterCraftsmanTest {

    @Test
    void doPost() throws ServletException, IOException {
        ServletCheckEnterCraftsman servlet = new ServletCheckEnterCraftsman();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        String adressRedirect = "/craftsman";
        when(request.getParameter("password")).thenReturn(adressRedirect);
        when(request.getSession()).thenReturn(session);
        assertEquals(adressRedirect, request.getParameter("password"));
        servlet.doPost(request, response);
        verify(request, never()).getSession();
        verify(request,times(1)).getParameter("login");
    }
}