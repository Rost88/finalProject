package com.logic.finalproject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import org.junit.jupiter.api.Test;

//import javax.servlet.RequestDispatcher;
import java.io.IOException;

import static org.mockito.Mockito.*;

class ServletChangeOrdersPriceTest {

    @Test
    void doGet() throws ServletException, IOException {

        ServletChangeOrdersPrice servlet = new ServletChangeOrdersPrice();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        String adressRedirect = "/mymanagers?pagination=null&currentPage=null&sort=null&status=null&craftsman=null";

        servlet.doGet(request, response);
        verify(request, times(1)).getParameter("pagination");
        verify(response).sendRedirect(adressRedirect);
        verify(request, never()).getSession();
    }

    @Test
    void doPost() {
    }
}