/**
 * The class helps a user to pay the order
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
import java.sql.SQLException;
import java.sql.Statement;

import static com.pageConstructor.finalproject.PageConstructor.*;

@WebServlet(name = "ServletPayOrder", value = "/ServletPayOrder")
public class ServletPayOrder extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletPayOrder.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        int userID = 0;
        for(Cookie cook : cookies) {
            if (cook.getName().equals("userID"))
                userID = Integer.parseInt(cook.getValue());
        }
        if(userID == 0) {
            logger.warn("This entity can't pay this order!");
            response.sendRedirect("/");

        }

        String orderID = request.getParameter("orderID");
        String orderPrice = request.getParameter("orderPrice");
        String commandUpdate1 = "UPDATE orders SET status = 'PAID' WHERE id = " + orderID;
        String commandUpdate2 = "UPDATE users SET balance = balance - " + orderPrice + " WHERE id = " + userID;

        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
            Connection connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(commandUpdate1);
                statement.executeUpdate(commandUpdate2);
                connection.commit();
                statement.close();
                logger.info("Order id {}, price {} was paid", orderID, orderPrice);
            } catch (SQLException e) {
                logger.error("Order was not paid, connection.rollback()", e);
                connection.rollback();
            }

            connection.close();
        } catch (SQLException e) {
            logger.error("Order was not paid", e);
            throw new RuntimeException(e);
        }
        String addressRedirect = "/user";
        logger.info("All ok! Send redirect {}", addressRedirect);
        response.sendRedirect(addressRedirect);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
