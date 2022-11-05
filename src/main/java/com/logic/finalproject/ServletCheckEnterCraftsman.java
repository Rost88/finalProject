/**
 * The class checks craftsman's login and password and take an authorisation in system
 *
 * @author Kuznietsov Rostyslav
 */
package com.logic.finalproject;

import com.connection.ConnectionPool;
import com.mysql.cj.jdbc.Driver;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ResourceBundle;

import static com.pageConstructor.finalproject.PageConstructor.*;

@WebServlet(name = "ServletCheckEnterCraftsman", value = "/ServletCheckEnterCraftsman")
public class ServletCheckEnterCraftsman extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletCheckEnterCraftsman.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("Enter to doPost craftsman");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Craftsman craftsman = new Craftsman();

        String adressRedirect = "/wrong-password?login=" + login + "&password=" + password;
        try {
            logger.trace("start craftsman try{, connect to DB");
//            DriverManager.registerDriver(new Driver());
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
            Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Craftsman WHERE login = '" + login + "';");
            resultSet.next();
            craftsman.setName(resultSet.getString("name"));
            craftsman.setPassword(resultSet.getString("password"));
            craftsman.setId(resultSet.getInt("id"));
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Wrong login, Catch exception", e);
            adressRedirect = "/wrong-login?login=" + login;

        }

        if(password.equals(craftsman.getPassword())) {
            Cookie craftsmanID = new Cookie("craftsmanID" , craftsman.getId() + "");
            craftsmanID.setMaxAge(3600);
            response.addCookie(craftsmanID);
            Cookie entity = new Cookie("entity", "craftsmen");
            entity.setMaxAge(3600);
            response.addCookie(entity);
            adressRedirect = "/craftsman";
            logger.info("All ok, craftsman come in");

        }
        response.sendRedirect(adressRedirect);
    }
}
