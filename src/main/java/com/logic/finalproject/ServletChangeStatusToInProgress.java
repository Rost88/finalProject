/**
 * The class helps a craftsman to change order's status from "paid" to "in progress"
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

@WebServlet(name = "ServletChangeStatusToInProgress", value = "/ServletChangeStatusToInProgress")
public class ServletChangeStatusToInProgress extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletChangeStatusToInProgress.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderID = request.getParameter("orderID");
        logger.info("Change order status {}" , orderID);

        String commandUpdate = "UPDATE orders SET status = 'IN PROGRESS' WHERE id = " + orderID;

        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
            Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(commandUpdate);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error("Error update status {} {}", commandUpdate, e);
            throw new RuntimeException(e);
        }
        String adressRedirect = "/craftsman";
        logger.info("Order status was changed to In Progress");
        response.sendRedirect(adressRedirect);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
