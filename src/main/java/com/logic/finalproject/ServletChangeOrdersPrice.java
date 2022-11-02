/**
 * The class helps a manager to change the order's price
 *
 * @author Kuznietsov Rostyslav
 */
package com.logic.finalproject;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static com.pageConstructor.finalproject.PageConstructor.*;

@WebServlet(name = "ServletChangeOrdersPrice", value = "/ServletChangeOrdersPrice")
public class ServletChangeOrdersPrice extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletChangeOrdersPrice.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String newPrice = request.getParameter("newprice");
        String orderID = request.getParameter("orID");

        String pagination = request.getParameter("pagination");
        String sort = request.getParameter("sort");
        String status = request.getParameter("status");
        String craftsman = request.getParameter("craftsman");
        String currentPage = request.getParameter("currentPage");

        String SQLInsert = "UPDATE orders SET price = " + newPrice + " WHERE id = " + orderID;
        logger.info("Manager is changing price, SQLInsert: {}", SQLInsert);

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");

         Statement statement = connection.createStatement();
              statement.executeUpdate(SQLInsert);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error("SQLException", e);
            throw new RuntimeException(e);
        }
        String adressRedirect = "/mymanagers?pagination=" + pagination + "&currentPage=" + currentPage +
                "&sort=" + sort + "&status=" + status + "&craftsman=" + craftsman;
        logger.info("All ok, redirect to /mymamagers");
        response.sendRedirect(adressRedirect);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
