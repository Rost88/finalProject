/**
 * The class helps a user to create feedback about a craftsman
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

@WebServlet(name = "ServletLeaveFeedback", value = "/ServletLeaveFeedback")
public class ServletLeaveFeedback extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletLeaveFeedback.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderID = request.getParameter("orderID");
        String feedback = request.getParameter("feedback");
        Boolean isUser = false;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cook : cookies) {
                if (cook.getName().equals("entity") && cook.getValue().equals("user")) {
                    isUser = true;
                }
            }
        }
        logger.info("User is trying to send feedback, order id {}", orderID);
        if(isUser == false) {
            logger.warn("This entity can't send this feedbck!");
            response.sendRedirect("/");
        }

        String commandUpdate = "UPDATE orders SET feedback = '" + feedback + "' WHERE id = " + orderID;

//        PrintWriter printWriter = response.getWriter();
//        printWriter.println("The page, where feedback is created");
//        printWriter.println("The page, where feedback is created");
//        printWriter.println("<br> orderID = " + orderID);
//        printWriter.println("<br> feedback = " + feedback);

                try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");

            Statement statement = connection.createStatement();
            statement.executeUpdate(commandUpdate);
            statement.close();
            connection.close();
//            printWriter.println("<br> Feedback was sent ");
//            printWriter.println("<br><a href = \"/user\"> Return to personal page </a>");
        } catch (SQLException e)  {
                    logger.error("Feedback didn't send", e);
            throw new RuntimeException(e);
        }

        String adressRedirect = "/user";
        logger.info("Feedback was sent, order id {}", orderID);
        response.sendRedirect(adressRedirect);

    }
}
