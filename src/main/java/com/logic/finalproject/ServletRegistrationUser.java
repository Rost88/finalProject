/**
 * The class checks user's login, name and password and put them in Data base
 *
 * @author Kuznietsov Rostyslav
 */
package com.logic.finalproject;

import com.connection.ConnectionPool;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.pageConstructor.finalproject.PageConstructor.*;

@WebServlet(name = "ServletRegistrationUser", value = "/ServletRegistrationUser")
public class ServletRegistrationUser extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletRegistrationUser.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        logger.info("Registration new user login {}, name {}", login, name);

        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
            Connection connection = ConnectionPool.getInstance().getConnection();
            String registrationUser = "INSERT INTO users(login, name, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(registrationUser);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException e)  {
            logger.error("New user wasn't registred", e);
            throw new RuntimeException(e);
        }
        logger.info("New user was registrated login {} name {}", login, name);
        response.sendRedirect("/autorisation-users");
    }
}
