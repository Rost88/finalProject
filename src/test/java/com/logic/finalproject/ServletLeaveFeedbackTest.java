package com.logic.finalproject;

import com.connection.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServletLeaveFeedbackTest {
    private static MockedStatic<ConnectionPool> cp1;
    @BeforeAll
    public static void init(){
        cp1 = mockStatic(ConnectionPool.class);
    }
    @AfterAll
    public static void close(){
        cp1.close();
    }

    @Test
    void doPost() throws ServletException, IOException {
        ServletLeaveFeedback servlet = new ServletLeaveFeedback();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final PrintWriter printWriter = mock(PrintWriter.class);

        String adressRedirect = "/user";
        final Connection connection = mock(Connection.class);
        final Statement statement = mock(Statement.class);
        final ConnectionPool instance = mock(ConnectionPool.class);
        try {
            cp1.when(ConnectionPool::getInstance).thenReturn(instance);
            when(instance.getConnection()).thenReturn(connection);
            when(connection.createStatement()).thenReturn(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doPost(request, response);
        verify(request, times(1)).getParameter("orderID");
        verify(response).sendRedirect(adressRedirect);
        verify(request, never()).getSession();
        verify(printWriter, never()).println("Страница создания отзыва о мастере");
    }
}