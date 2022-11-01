package com.logic.finalproject;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.pageConstructor.finalproject.PageConstructor.*;

@WebServlet(name = "ServletCancelOrder", value = "/ServletCancelOrder")
public class ServletCancelOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String orderID = request.getParameter("orderID");
//        PrintWriter printWriter = response.getWriter();
//        printWriter.println(startPageStartTitle);
//        printWriter.println("Страница cancel  заказа");
//       // printWriter.println(finishTitleStartBody("uk"));
//        printWriter.println("Страница записи в БД отмены для заказа");
//        printWriter.println("<br>Номер заказа: " + orderID);

       // printWriter.println(finishPage);

        String commandUpdate = "UPDATE orders SET status = 'CANCELED' WHERE id = " + orderID;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
            Statement statement = connection.createStatement();
            statement.executeUpdate(commandUpdate);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String adressRedirect = "/user";
        response.sendRedirect(adressRedirect);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
