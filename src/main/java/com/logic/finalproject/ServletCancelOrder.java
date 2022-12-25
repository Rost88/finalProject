/**
 * The class helps user change order's status from "pending payment" to "canceled"
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

@WebServlet(name = "ServletCancelOrder", value = "/ServletCancelOrder")
public class ServletCancelOrder extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ServletCancelOrder.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String orderID = request.getParameter("orderID");
        logger.info("ServletCancelOrder doGet started. OrderID = {}", orderID );
        String commandUpdate = "UPDATE orders SET status = 'CANCELED' WHERE id = " + orderID;

        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
            Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(commandUpdate);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String adressRedirect = "/user";
        logger.info("Order id = {} was cancelled. Redirect to page/user", orderID);
        response.sendRedirect(adressRedirect);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
