/**
 * The class shows information for managers, take in DB information about the manager and all orders and shows on
 * the page
 * @author Kuznietsov Rostyslav
 */

package com.example.finalproject;

import com.connection.ConnectionPool;
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
import java.util.ResourceBundle;

import static com.pageConstructor.finalproject.PageConstructor.*;

@WebServlet(name = "ServletManager", value = "/ServletManager")
public class ServletManager extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletManager.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute("langv");
        if(lang==null)
            lang="en";
        if(request.getParameter("lang")!=null)
            lang = request.getParameter("lang");
        session.setAttribute("langv",lang);

        ResourceBundle bundle = languages.ChooseLanguage.chooseBundle(lang);

        String sort = request.getParameter("sort");
        logger.info("sort from request = {}", sort);
        if(sort == null)
        sort = (String) session.getAttribute("sort");
        logger.info("sort get from session = {}", sort);
        if(sort == null)
            sort = "";
        logger.info("sort set to session = {}", sort);
        session.setAttribute("sort", sort);
        String checkStatus = request.getParameter("status");
        if(checkStatus == null)
            checkStatus = "";
        session.setAttribute("checkStatus", checkStatus);
        String checkCraftsman = request.getParameter("craftsman");
        if(checkCraftsman == null)
            checkCraftsman = "";
        session.setAttribute("checkCraftsman", checkCraftsman);
        String currentPage = request.getParameter("currentPage");
        if(currentPage == null)
            currentPage = "1";
        String pagination = request.getParameter("pagination");
        if(pagination == null)
            pagination = (String) session.getAttribute("pagination");
        if(pagination == null)
            pagination = "";
        session.setAttribute("pagination", pagination);
        if(pagination.equals("all"))
            currentPage = "1";



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

            Manager manager = (Manager) session.getAttribute("entityManager");
//            List<Order> allOrders = new ArrayList<>();
//            try {
////                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
////                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
//                Connection connection = ConnectionPool.getInstance().getConnection();
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery("SELECT * FROM managers WHERE id = '" + id + "';");
//                resultSet.next();
//                manager.setName(resultSet.getString("name"));
//
//                manager.setPassword(resultSet.getString("password"));
//                manager.setEmail(resultSet.getString("login"));
//                manager.setPhoto(resultSet.getString("photo"));
//                resultSet.close();
//
//                ResultSet resultSet1 = statement.executeQuery("SELECT * FROM orders");
//                while (resultSet1.next()) {
//                    Order order = new Order();
//                    order.setId(resultSet1.getInt("id"));
//                    order.setName(resultSet1.getString("name"));
//                    order.setDate(resultSet1.getDate("date"));
//                    order.setPrice(resultSet1.getInt("price"));
//                    order.setStatus(resultSet1.getString("status"));
//                    allOrders.add(order);
//                }
//                statement.close();
//                connection.close();
//            } catch (SQLException e) {
//                logger.error("Catch excrption", e);
//                throw new RuntimeException(e);
//            }
//            session.setAttribute("allOrders", allOrders);
            if (id > 0) {
                ShowTableManager showTableManager = new ShowTableManager(session);
                showTableManager.setAllOrders(checkStatus, checkCraftsman, sort);
                showTableManager.setEL(pagination, currentPage);

//                PrintWriter printWriter = response.getWriter();
//                printWriter.println(startPageStartTitle);
//                printWriter.println("Личная страница пользователя \"managers\"");
//                printWriter.println(finishTitleStartBody(lang));
//                printWriter.println("<table width = \"70%\"><tr><td>  Hello, " + manager.getName() + "<img src = \"/images/" + manager.getPhoto() + "\" align = \"right\">");
//                printWriter.println("<br>e-mail: " + manager.getEmail());
//                printWriter.println("<br>password: " + manager.getPassword());
//                printWriter.println("</td></tr></table><br>");
//                printWriter.println("<hr>");
//                printWriter.println(ShowTable.filters(sort, pagination, lang));
//                printWriter.println("<hr>");
//                printWriter.println(ShowTable.pagination(sort, checkStatus, checkCraftsman));
//                printWriter.println("<hr>");
//                printWriter.println(ShowTable.show1(sort, checkStatus, checkCraftsman, pagination, currentPage, request));
//                printWriter.println(finishPage(lang));

                String managerTitle = bundle.getString("Title - Manager's page");
                request.setAttribute("managerTitle", managerTitle);
                String hello = bundle.getString("Hello");
                request.setAttribute("hello", hello);
                String password = bundle.getString("password");
                request.setAttribute("password", password);
                String myOrders = bundle.getString("my orders");
                request.setAttribute("myOrders", myOrders);
                String ordersID = bundle.getString("Order ID");
                request.setAttribute("ordersID", ordersID);
                String orderName = bundle.getString("Order name");//"Order's name";
                request.setAttribute("orderName", orderName);
                String сhooseStatus = bundle.getString("Choose Status");
                request.setAttribute("сhooseStatus", сhooseStatus);
                String сhooseCraftsman = bundle.getString("Choose Craftsman");
                request.setAttribute("сhooseCraftsman", сhooseCraftsman);
                String applyFilters = bundle.getString("Apply filters");
                request.setAttribute("applyFilters", applyFilters);
                String show = bundle.getString("Show");
                request.setAttribute("show", show);
                String showOnThePage = bundle.getString("Show on the page");
                request.setAttribute("showOnThePage", showOnThePage);
                String prev = bundle.getString("Prev");
                request.setAttribute("prev", prev);
                String next = bundle.getString("Next");
                request.setAttribute("next", next);
                String resetAllFilters = bundle.getString("Reset all filters");
                request.setAttribute("resetAllFilters", resetAllFilters);
                String user = bundle.getString("User");
                request.setAttribute("user", user);
                String orderStatus = bundle.getString("Order status");
                request.setAttribute("orderStatus", orderStatus);
                String orderCraftsman = bundle.getString("Craftsman");
                request.setAttribute("orderCraftsman", orderCraftsman);
                String orderData = bundle.getString("Orders Data");
                request.setAttribute("orderData", orderData);
                String orderPrice = bundle.getString("Order price");
                request.setAttribute("orderPrice", orderPrice);
                String setCraftsman = bundle.getString("Set craftsman");
                request.setAttribute("setCraftsman", setCraftsman);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page-manager");
                requestDispatcher.forward(request, response);
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
