package com.example.finalproject;

import com.logic.finalproject.ServletCheckEnterUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ServletCraftsmanTest {

    @Test
    void doGet() throws ServletException, IOException {
        ServletCraftsman servlet = new ServletCraftsman();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final PrintWriter printWriter = mock(PrintWriter.class);
        final String entity = "craftsmen";


        String lang = "en";
        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(printWriter);

        assertEquals("craftsmen", entity);
        servlet.doGet(request, response);
        verify(request, times(1)).getSession();
        verify(session, times(1)).setAttribute("langv",lang);
        verify(request,never()).getParameter("login");
    }
}