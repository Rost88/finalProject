/**
 * The class helps a manager to set a craftsman for the order
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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "ServletSetCraftsman", value = "/ServletSetCraftsman")
public class ServletSetCraftsman extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletSetCraftsman.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String set_ord_craft = request.getParameter("set_ord_craft");
        String orderID = request.getParameter("ordid");
        String pagination = request.getParameter("pagination");
        String sort = request.getParameter("sort");
        String status = request.getParameter("status");
        String craftsman = request.getParameter("craftsman");
        String currentPage = request.getParameter("currentPage");

        logger.info("Try set craftsman id {} to order id {}", set_ord_craft, orderID);

        String commandUpdate = "UPDATE orders SET craftsman_id = " + set_ord_craft + " WHERE id = " + orderID;

        try {
           Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(commandUpdate);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error("Craftsman was not set to order", e);
            throw new RuntimeException(e);
        }
        String adressRedirect = "/mymanagers?pagination=" + pagination + "&currentPage=" + currentPage +
                "&sort=" + sort + "&status=" + status + "&craftsman=" + craftsman;
        logger.info("Craftsman with id {} was set to order {}", set_ord_craft, orderID);
       response.sendRedirect(adressRedirect);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
