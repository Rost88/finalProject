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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServletCreateOrderTest {
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
    void doPost() throws ServletException, IOException, SQLException {
        ServletCreateOrder servlet = new ServletCreateOrder();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final Cookie cookie = mock(Cookie.class);
        final Cookie[] cookies = {cookie};
        final User user = mock(User.class);
        String adressRedirect = "/user";
        String creatingOrder = "INSERT INTO orders(name, description, user_id) VALUES (?, ?, ?)";

        final PreparedStatement preparedStatement = mock(PreparedStatement.class);
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

        when(connection.prepareStatement(creatingOrder)).thenReturn(preparedStatement);


        when(request.getSession()).thenReturn(session);
        when(request.getCookies()).thenReturn(cookies);
        when(cookie.getName()).thenReturn("name");
        when(session.getAttribute("entityUser")).thenReturn(user);
        servlet.doPost(request, response);
        verify(request, times(1)).getParameter("lang");
        verify(response).sendRedirect(adressRedirect);
        verify(request, times(1)).getSession();
    }
}