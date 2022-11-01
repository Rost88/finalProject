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
   //     String userID = request.getParameter("userID");

        PrintWriter printWriter = response.getWriter();
        printWriter.println(startPageStartTitle);
        printWriter.println("Страница pay  заказа");
      //  printWriter.println(finishTitleStartBody("uk"));
        printWriter.println("Страница записи в БД pay для заказа");
        printWriter.println("<br>Номер заказа: " + orderID);
        printWriter.println("<br>userID: " + userID);
        printWriter.println("<br>orderPrice: " + orderPrice);

      //  printWriter.println(finishPage);

        String commandUpdate1 = "UPDATE orders SET status = 'PAID' WHERE id = " + orderID;
        String commandUpdate2 = "UPDATE users SET balance = balance - " + orderPrice + " WHERE id = " + userID;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate(commandUpdate1);
            statement.executeUpdate(commandUpdate2);
            connection.commit();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error("Order was not paid", e);
            throw new RuntimeException(e);
        }
        String addressRedirect = "/user";
        logger.info("Order id {}, price {} was paid", orderID, orderPrice);
        response.sendRedirect(addressRedirect);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
