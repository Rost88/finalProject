/**
 * The class helps a user to create new order
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

@WebServlet(name = "ServletCreateOrder", value = "/new-order")  //ServletCreateOrder изменил я значение в скобках
public class ServletCreateOrder extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletCreateOrder.class);

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

        String orderShort = request.getParameter("short");
        String orderFull = request.getParameter("full");

        User user = (User) session.getAttribute("entityUser");

         int userID = user.getId();

        logger.info("User id {} is trying to create new order", userID);

        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
            Connection connection = ConnectionPool.getInstance().getConnection();
            String creatingOrder = "INSERT INTO orders(name, description, user_id) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(creatingOrder);
            preparedStatement.setString(1, orderShort);
            preparedStatement.setString(2, orderFull);
            preparedStatement.setInt(3, userID);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e)  {
            logger.error("New order didn't create", e);
        //    throw new RuntimeException(e);
        }

        String adressRedirect = "/user";
        logger.info("User, id {} has created new order", userID);
        response.sendRedirect(adressRedirect);
    }
}
