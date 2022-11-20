/**
 * The class shows information for users, take in DB information about the user and his orders and shows on
 * the page
 * @author Kuznietsov Rostyslav
 */
package com.example.finalproject;

import com.connection.ConnectionPool;
import com.logic.finalproject.Craftsman;
import com.logic.finalproject.Order;
import com.logic.finalproject.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.pageConstructor.finalproject.PageConstructor.*;

@WebServlet(name = "ServletUser", value = "/ServletUser")
public class ServletUser extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletUser.class);

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
//        String login = ""; // = request.getParameter("login");  //надо переделать на Куки!!! даже на сессию!
//
//        String entity = "";
//        int id = 0;

//        Cookie[] cookies = request.getCookies();
//
//        if(cookies!=null) {
//            for (Cookie cook : cookies) {
//                if (cook.getName().equals("userID"))
//                    id = Integer.parseInt(cook.getValue());
////                if (cook.getName().equals("login"))
////                    login = cook.getValue();
//                if (cook.getName().equals("entity") && cook.getValue().equals("user")) {
//                    entity = cook.getValue();
//                }
//            }
//        }
        User user = (User) session.getAttribute("entityUser");

        if(user!=null) {
        String login = user.getEmail();
        int id = user.getId();
        logger.info("User id {} , login {}, user.id {}", id, login, user.getId());
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE login = '" + login + "';");
            resultSet.next();
//            user.setName(resultSet.getString("name"));
//            user.setEmail(resultSet.getString("login"));
            user.setBalance(resultSet.getInt("balance"));
//            user.setPassword(resultSet.getString("password"));
//            user.setId(resultSet.getInt("id"));
            resultSet.close();
            ResultSet resultSet1 = statement.executeQuery("select orders.id, orders.name, orders.description, orders.feedback, orders.status, orders.price, orders.craftsman_id, craftsman.name as cn from orders, craftsman  where craftsman.id = orders.craftsman_id and user_id = (select id from users where login = '" + login + "');");

            while (resultSet1.next()){
                Order ord = new Order();
                ord.setName(resultSet1.getString("name"));
                ord.setDescription(resultSet1.getString("description"));
                ord.setFeedback(resultSet1.getString("feedback"));
                ord.setStatus(resultSet1.getString("status"));
                ord.setPrice(resultSet1.getInt("price"));
                ord.setId(resultSet1.getInt("id"));
                ord.setUser(user);
                ord.setCraftsman( new Craftsman(resultSet1.getInt("craftsman_id"), resultSet1.getString("cn")));
                orders.add(ord);

            }
            resultSet1.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error("Catch exception", e);
           throw new RuntimeException(e);
        }
        session.setAttribute("usersOrders", orders);
//            PrintWriter printWriter = response.getWriter();
//            printWriter.println(startPageStartTitle);
//            printWriter.println();
//            printWriter.println("Личная страница пользователя \"user\"");
//            printWriter.println(finishTitleStartBody(lang));
//            printWriter.println("<h2>Hello, " + user.getName());
//            printWriter.println("</h2><br><h3>e-mail: " + user.getEmail() + "</h3><br><h2.l> id: " + user.getId());
//            printWriter.println("</h2><br>password: " + user.getPassword());
//            printWriter.println("<br>Money on balance: " + user.getBalance() + "$ <i>ссылка на сторонний ресурс чтоб пополнить баланс(в разработке)</i>");
//            printWriter.println("<br><h2.l>my orders: </h2.l>" +
//                    "<table border=1>" +
//                    "<tr>" +
//                    "<td> Order ID </td>" +
//                    "<td> Order name </td>" +
//                    "<td> Order description </td>" +
//                    "<td colspan = \"3\"> Order status </td>" +
//                    "<td> Order price </td>" +
//                    "<td> Order craftsman </td>" +
//                    "</tr>");
//            if(orders.size()>0) {
//                for (int i = 0; i < orders.size(); i++) {
//                    String orderStatus = "";
//                    if(orders.get(i).getStatus().equals("PENDING PAYMENT") && orders.get(i).getPrice() > 0 && orders.get(i).getPrice() <= user.getBalance())
//                    {
//                        orderStatus = "<td>" + orders.get(i).getStatus() + "</td><td><a href = \"/pay-order?orderID=" + orders.get(i).getId() + "&orderPrice=" + orders.get(i).getPrice() + "\">оплатить</td><td> <a href = \"/cancel-order?orderID=" + orders.get(i).getId() + "\"> отменить </td>";
//                    } else if (orders.get(i).getStatus().equals("PENDING PAYMENT") && (orders.get(i).getPrice() > user.getBalance() || orders.get(i).getPrice() == 0)) {
//                        orderStatus = "<td>" + orders.get(i).getStatus() + "</td><td colspan = \"2\"> <a href = \"/cancel-order?orderID=" + orders.get(i).getId() + "\"> отменить </td>";
//                   } else {
//                        orderStatus = "<td colspan = \"3\">" + orders.get(i).getStatus() + "</td>";
//                    }
//
//                    String feedbackCraftsman = orders.get(i).getCraftsman().toString();
//                    if(orders.get(i).getFeedback() == null && orders.get(i).getStatus().equals("COMPLETED"))
//                        feedbackCraftsman = feedbackCraftsman + "<form action=\"/leave-feedback\" method=\"post\"> " +
//                                "<input type = \"hidden\" name = \"orderID\" value = \"" + orders.get(i).getId() + "\"/>" +
//                                "<input type=\"submit\" value=\"leave a feedback\" /> </form>";
//
//                    printWriter.println("<tr>" +
//                            "<td>" + orders.get(i).getId() + "</td>" +
//                            "<td>" + orders.get(i).getName() + " </td>" +
//                            "<td>" + orders.get(i).getDescription() + "</td>" +
//                            orderStatus +
//                            "<td>" + orders.get(i).getPrice() + " $ </td>" +
//                            "<td>" + feedbackCraftsman + "</td>" +
//                            "</tr>");
//                }
//            } else {
//                printWriter.println("<tr><td> You don`t have any orders</td></tr>");
//            }
//            printWriter.println(" </table>" );
//            printWriter.println(" <hr>" );
//            printWriter.println(" <form action=\"/create-new-order\" method=\"get\">" +
//                                " <input type=\"submit\" value=\"Create new order\" /> </form>" );
//            printWriter.println(" <hr>" );
//            printWriter.println(finishPage(lang));

         //   request.getRequestDispatcher("PageUser.jsp").forward(request, response);

          //  getServletContext().getRequestDispatcher("/page-user").forward(request, response);
        } else {
            logger.warn("Something try come in page without rights, redirect to main page");
            response.sendRedirect("/");
        }
        String userTitle = bundle.getString("Title - User's page");
        request.setAttribute("userTitle", userTitle);
        String hello = bundle.getString("Hello");
        request.setAttribute("hello", hello);
        String password = bundle.getString("password");
        request.setAttribute("password", password);
        String momeyOnBalance = bundle.getString("Money on balance");
        request.setAttribute("momeyOnBalance", momeyOnBalance);
        String myOrders = bundle.getString("my orders");
        request.setAttribute("myOrders", myOrders);
        String ordersID = bundle.getString("Order ID");
        request.setAttribute("ordersID", ordersID);
        String orderName = bundle.getString("Order name");//"Order's name";
        request.setAttribute("orderName", orderName);
        String ordersFull = bundle.getString("Order description");
        request.setAttribute("ordersFull", ordersFull);
        String ordersStatus = bundle.getString("Order status");
        request.setAttribute("ordersStatus", ordersStatus);
        String ordersPrice = bundle.getString("Order price");
        request.setAttribute("ordersPrice", ordersPrice);
        String ordersCraftsman = bundle.getString("Order craftsman");
        request.setAttribute("ordersCraftsman", ordersCraftsman);
        String payOrder = bundle.getString("Pay order");
        request.setAttribute("payOrder", payOrder);
        String cancelOrder = bundle.getString("Cancel order");
        request.setAttribute("cancelOrder", cancelOrder);
        String createOrder = bundle.getString("Create order");
        request.setAttribute("createOrder", createOrder);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page-user");
            requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
