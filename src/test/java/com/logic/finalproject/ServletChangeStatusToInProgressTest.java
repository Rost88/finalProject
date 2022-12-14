package com.logic.finalproject;

import com.connection.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServletChangeStatusToInProgressTest {
    private static MockedStatic<ConnectionPool> cp;
    @BeforeAll
    public static void init(){
       cp = mockStatic(ConnectionPool.class);
    }
    @AfterAll
    public static void close(){
        cp.close();
    }

    @Test
    void doGet() throws ServletException, IOException {
        ServletChangeStatusToInProgress servlet = new ServletChangeStatusToInProgress();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        final Connection connection = mock(Connection.class);
        final Statement statement = mock(Statement.class);
        final ConnectionPool instance = mock(ConnectionPool.class);
        try {
            cp.when(ConnectionPool::getInstance).thenReturn(instance);
            when(instance.getConnection()).thenReturn(connection);
            when(connection.createStatement()).thenReturn(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String adressRedirect = "/craftsman";

        servlet.doGet(request, response);
        verify(request, times(1)).getParameter("orderID");
        verify(response).sendRedirect(adressRedirect);
        verify(request, never()).getSession();
    }
}