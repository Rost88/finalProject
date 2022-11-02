/**
 * The class helps a manager to top up user's balance
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.pageConstructor.finalproject.PageConstructor.*;

@WebServlet(name = "ServletTopOnUsersBalance", value = "/ServletTopOnUsersBalance")
public class ServletTopOnUsersBalance extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletTopOnUsersBalance.class);

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

        String userID = request.getParameter("userID");
        String sum = request.getParameter("sum");
        String pagination = request.getParameter("pagination");
        String sort = request.getParameter("sort");
        String status = request.getParameter("status");
        String craftsman = request.getParameter("craftsman");
        String currentPage = request.getParameter("currentPage");
        logger.info("Trying to top on ballance of user {} at {}", userID, sum);
//        PrintWriter printWriter = response.getWriter();
//        //   printWriter.println(startPageStartTitle);
//        printWriter.println("Страница отправки данных на сервер при пополнении баланса пользователя");
//        //  printWriter.println(finishTitleStartBody(lang));
//        printWriter.println("Страница отправки данных на сервер при пополнении баланса пользователя");
//        printWriter.println(currentPage + " " + pagination + " " + craftsman + " " + status + " " + sort);
//        printWriter.println("user: " + userID + " summa: " + sum);
//        //     printWriter.println(finishPage(lang));

        String commandUpdate = "UPDATE users SET balance = balance + " + sum + " WHERE id = " + userID;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
            Statement statement = connection.createStatement();
            statement.executeUpdate(commandUpdate);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error("Top on error", e);
            throw new RuntimeException(e);
        }

        String adressRedirect = "/mymanagers?pagination=" + pagination + "&currentPage=" + currentPage +
                "&sort=" + sort + "&status=" + status + "&craftsman=" + craftsman;
        logger.info("Top on balance successful");
        response.sendRedirect(adressRedirect);

    }
}
