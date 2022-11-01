package com.logic.finalproject;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

class ServletCancelOrderTest {

    private static final String path = "/user";

    @Test
    void doGet() throws ServletException, IOException {

        ServletCancelOrder servlet = new ServletCancelOrder();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        servlet.doGet(request, response);
        verify(response, times(1)).sendRedirect(path);
        verify(request, never()).getSession();


    }
    @Test
    void doPost() throws ServletException, IOException {
        ServletCancelOrder servlet = new ServletCancelOrder();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        servlet.doGet(request, response);
        verify(request, never()).getSession();

    }

}