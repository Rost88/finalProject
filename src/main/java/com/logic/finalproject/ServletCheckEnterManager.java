/**
 * The class checks manager's login and password and take an authorisation in system
 *
 * @author Kuznietsov Rostyslav
 */
package com.logic.finalproject;

import com.mysql.cj.jdbc.Driver;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ServletCheckEnterManager", value = "/ServletCheckEnterManager")
public class ServletCheckEnterManager extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletCheckEnterManager.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute("langv");
        if(lang==null)
            lang="en";
        if(request.getParameter("lang")!=null)
            lang = request.getParameter("lang");
        session.setAttribute("langv",lang);

        logger.info("Enter to doPost mamager");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Manager manager = new Manager();

        String adressRedirect = "/wrong-password?login=" + login + "&password=" + password;
        try {
            logger.trace("start try{, connect to DB");
            DriverManager.registerDriver(new Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM managers WHERE login = '" + login + "';");
            resultSet.next();
            manager.setName(resultSet.getString("name"));
            manager.setPassword(resultSet.getString("password"));
            manager.setId(resultSet.getInt("id"));
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Wrong login, Catch exception", e);
            adressRedirect = "/wrong-login?login=" + login;

        }
        logger.warn("Someone enter wrong password");

        if(password.equals(manager.getPassword())) {
            Cookie managerID = new Cookie("managerID", manager.getId() + "");
            managerID.setMaxAge(3600);
            response.addCookie(managerID);
            Cookie entity = new Cookie("entity" , "managers");
            entity.setMaxAge(3600);
            response.addCookie(entity);
            adressRedirect = "/mymanagers";
            logger.info("All ok, manager come in");
        }
        response.sendRedirect(adressRedirect);
    }
}
