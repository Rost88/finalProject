package com.logic.finalproject;

import com.connection.ConnectionPool;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class ServletCancelOrderTest {

    private static final String path = "/user";
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

        ServletCancelOrder servlet = new ServletCancelOrder();
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

        servlet.doGet(request, response);
        verify(response, times(1)).sendRedirect(path);
        verify(request, never()).getSession();


    }
    @Test
    void doPost() throws ServletException, IOException {
        ServletCancelOrder servlet = new ServletCancelOrder();
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

        servlet.doGet(request, response);
        verify(request, never()).getSession();

    }

}