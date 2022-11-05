/**
 * The class checks user's login and password and take him an authorisation in system
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
import java.sql.*;

import static com.pageConstructor.finalproject.PageConstructor.*;

@WebServlet(name = "ServletCheckEnterUser", value = "/ServletCheckEnterUser")
public class ServletCheckEnterUser extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletCheckEnterUser.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Enter to doPost user");
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute("langv");
        if(lang==null)
            lang="en";
        if(request.getParameter("lang")!=null)
            lang = request.getParameter("lang");
        session.setAttribute("langv",lang);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User();
        String adressRedirect = "/wrong-password?login=" + login + "&password=" + password;
        try {
            logger.trace("start user try{, connect to DB {}", login);
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
            Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE login = '" + login + "';");
            resultSet.next();
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setId(resultSet.getInt("id"));
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Wrong login, Catch exception", e);
            adressRedirect = "/wrong-login?login=" + login;

        }
        logger.info("User {} entered password" , login);

        if(password.equals(user.getPassword())) {
            Cookie userName = new Cookie("login", login);
            userName.setMaxAge(60*60);
            response.addCookie(userName);
            Cookie userPass = new Cookie("userID", user.getId()+"");
            userPass.setMaxAge(3600);
            response.addCookie(userPass);
            Cookie entity = new Cookie("entity" , "user");
            entity.setMaxAge(3600);
            response.addCookie(entity);
            adressRedirect = "/user";
            logger.info("All ok, user {} id {} came in his userpage" , login, user.getId());
        }
        response.sendRedirect(adressRedirect);
    }
}
