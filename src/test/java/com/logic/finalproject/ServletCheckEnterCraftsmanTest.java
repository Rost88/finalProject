package com.logic.finalproject;

import com.connection.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServletCheckEnterCraftsmanTest {
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
    void doPost() throws ServletException, IOException {
        ServletCheckEnterCraftsman servlet = new ServletCheckEnterCraftsman();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        final Connection connection = mock(Connection.class);
        final Statement statement = mock(Statement.class);
        final ConnectionPool instance = mock(ConnectionPool.class);
        final ResultSet resultSet = mock(ResultSet.class);
        try {
            cp.when(ConnectionPool::getInstance).thenReturn(instance);
            when(instance.getConnection()).thenReturn(connection);
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(any())).thenReturn(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String adressRedirect = "/craftsman";
        when(request.getParameter("password")).thenReturn(adressRedirect);
        when(request.getSession()).thenReturn(session);
        assertEquals(adressRedirect, request.getParameter("password"));
        servlet.doPost(request, response);
        verify(request, times(1)).getSession();
        verify(request,times(1)).getParameter("login");
    }
}