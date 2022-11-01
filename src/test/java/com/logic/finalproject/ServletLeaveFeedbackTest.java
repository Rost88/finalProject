package com.logic.finalproject;

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

class ServletLeaveFeedbackTest {

    @Test
    void doPost() throws ServletException, IOException {
        ServletLeaveFeedback servlet = new ServletLeaveFeedback();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final PrintWriter printWriter = mock(PrintWriter.class);

        String adressRedirect = "/user";
        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doPost(request, response);
        verify(request, times(1)).getParameter("orderID");
        verify(response).sendRedirect(adressRedirect);
        verify(request, never()).getSession();
        verify(printWriter, atLeast(1)).println("Страница создания отзыва о мастере");
    }
}