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
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class ServletSetCraftsmanTest {
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
    void doGet() throws SQLException, ServletException, IOException {
        ServletSetCraftsman servlet = new ServletSetCraftsman();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final Connection connection = mock(Connection.class);
        final Statement statement = mock(Statement.class);
        final ConnectionPool instance = mock(ConnectionPool.class);

        cp.when(ConnectionPool::getInstance).thenReturn(instance);
        when(instance.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);

        servlet.doGet(request, response);
        verify(request, times(1)).getParameter("currentPage");
        verify(response).sendRedirect(any());
        verify(request, never()).getSession();
    }
}