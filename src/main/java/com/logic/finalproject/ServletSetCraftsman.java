package com.logic.finalproject;

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

        PrintWriter printWriter = response.getWriter();
     //   printWriter.println(startPageStartTitle);
        printWriter.println("Страница записи в БД присвоенного мастера для заказа");
     //   printWriter.println(finishTitleStartBody("uk"));
        printWriter.println("Страница записи в БД присвоенного мастера для заказа");
        printWriter.println("<br>Номер заказа: " + orderID);
        printWriter.println("<br>ID Мастерa: " + set_ord_craft);
        printWriter.println(currentPage + " " + pagination + " " + craftsman + " " + status + " " + sort);
     //   printWriter.println(finishPage);
        logger.info("Try set craftsman id {} to order id {}", set_ord_craft, orderID);

        String commandUpdate = "UPDATE orders SET craftsman_id = " + set_ord_craft + " WHERE id = " + orderID;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
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
