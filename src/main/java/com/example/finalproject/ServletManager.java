/**
 * The class shows information for managers, take in DB information about the manager and all orders and shows on
 * the page
 * @author Kuznietsov Rostyslav
 */

package com.example.finalproject;

import com.logic.finalproject.Manager;
import com.logic.finalproject.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.pageConstructor.finalproject.PageConstructor.*;

@WebServlet(name = "ServletManager", value = "/ServletManager")
public class ServletManager extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletManager.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sort = request.getParameter("sort");
        if(sort == null)
            sort = "";
        String checkStatus = request.getParameter("status");
        if(checkStatus == null)
            checkStatus = "";
        String checkCraftsman = request.getParameter("craftsman");
        if(checkCraftsman == null)
            checkCraftsman = "";
        String currentPage = request.getParameter("currentPage");
        if(currentPage == null)
            currentPage = "1";
        String pagination = request.getParameter("pagination");
        if(pagination == null)
            pagination = "";
        if(pagination.equals("all"))
            currentPage = "1";

        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute("langv");
        if(lang==null)
            lang="en";
        if(request.getParameter("lang")!=null)
            lang = request.getParameter("lang");
        session.setAttribute("langv",lang);

        logger.info("Manager page, parameters: {} {} {} {} {}" , sort, checkStatus, checkCraftsman, currentPage, pagination);

        Cookie[] cookies = request.getCookies();
        int id = 0;
        Boolean isManager = false;
        if(cookies!=null) {
            for (Cookie cook : cookies) {
                if (cook.getName().equals("entity") && cook.getValue().equals("managers")) {
                    isManager = true;

                }
                if (cook.getName().equals("managerID"))
                    id = Integer.parseInt(cook.getValue());
            }
        }
        if(isManager) {

            Manager manager = new Manager();
            List<Order> allOrders = new ArrayList<>();
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM managers WHERE id = '" + id + "';");
                resultSet.next();
                manager.setName(resultSet.getString("name"));

                manager.setPassword(resultSet.getString("password"));
                manager.setEmail(resultSet.getString("login"));
                manager.setPhoto(resultSet.getString("photo"));
                resultSet.close();

                ResultSet resultSet1 = statement.executeQuery("SELECT * FROM orders");
                while (resultSet1.next()) {
                    Order order = new Order();
                    order.setId(resultSet1.getInt("id"));
                    order.setName(resultSet1.getString("name"));
                    order.setDate(resultSet1.getDate("date"));
                    order.setPrice(resultSet1.getInt("price"));

                    order.setStatus(resultSet1.getString("status"));
                    allOrders.add(order);
                }

                statement.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("Catch excrption", e);
                throw new RuntimeException(e);
            }
            if (id > 0) {

                PrintWriter printWriter = response.getWriter();
                printWriter.println(startPageStartTitle);
                printWriter.println("Личная страница пользователя \"managers\"");
                printWriter.println(finishTitleStartBody(lang));
                printWriter.println("<table width = \"70%\"><tr><td>  Hello, " + manager.getName() + "<img src = \"/images/" + manager.getPhoto() + "\" align = \"right\">");
                printWriter.println("<br>e-mail: " + manager.getEmail());
                printWriter.println("<br>password: " + manager.getPassword());
                printWriter.println("</td></tr></table><br>");
                printWriter.println("<hr>");
                printWriter.println(ShowTable.filters(sort, pagination, lang));
                printWriter.println("<hr>");
                printWriter.println(ShowTable.pagination(sort, checkStatus, checkCraftsman));
                printWriter.println("<hr>");
                printWriter.println(ShowTable.show1(sort, checkStatus, checkCraftsman, pagination, currentPage));

                printWriter.println(finishPage(lang));
            }
        }else {
            logger.info("Something try come in page without rights, redirect to main page");
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
