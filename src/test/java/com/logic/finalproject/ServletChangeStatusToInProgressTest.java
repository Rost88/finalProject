package com.logic.finalproject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServletChangeStatusToInProgressTest {

    @Test
    void doGet() throws ServletException, IOException {
        ServletChangeStatusToInProgress servlet = new ServletChangeStatusToInProgress();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        String adressRedirect = "/craftsman";

        servlet.doGet(request, response);
        verify(request, times(1)).getParameter("orderID");
        verify(response).sendRedirect(adressRedirect);
        verify(request, never()).getSession();
    }
}