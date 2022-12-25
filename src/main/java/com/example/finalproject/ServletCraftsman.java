/**
 * The class shows information for craftsmen, take in DB information about the craftsman and his orders and shows on
 * the page
 * @author Kuznietsov Rostyslav
 */
package com.example.finalproject;

import com.connection.ConnectionPool;
import com.logic.finalproject.Craftsman;
import com.logic.finalproject.Order;
import com.mysql.cj.jdbc.Driver;
import com.pageConstructor.finalproject.PageConstructor;
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

@WebServlet(name = "ServletCraftsman", value = "/ServletCraftsman")
public class ServletCraftsman extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ServletCraftsman.class);

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
        PageConstructor.lang(lang); //надо или нет? вроде не очень))

        Cookie[] cookies = request.getCookies();
        int id = 0;
        String entity = "";
        Boolean isCraftsman = false;
        if(cookies!=null) {
            for (Cookie cook : cookies) {
                if (cook.getName().equals("entity") && cook.getValue().equals("craftsmen")) {
                    isCraftsman = true;
                }
                if (cook.getName().equals("craftsmanID"))
                    id = Integer.parseInt(cook.getValue());
                if (cook.getName().equals("entity"))
                    entity = cook.getValue();
            }
        }
        if(isCraftsman == false) {
            logger.warn("Something try come in page without rights, redirect to main page");
            response.sendRedirect("/");

        }
        Craftsman craftsman = (Craftsman) session.getAttribute("entityCraftsman");
        List<Order> orders = new ArrayList<>();
        if(craftsman != null) {
            try {
//            DriverManager.registerDriver(new Driver());
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
                Connection connection = ConnectionPool.getInstance().getConnection();
                Statement statement = connection.createStatement();

                ResultSet resultSet1 = statement.executeQuery("SELECT * FROM orders WHERE craftsman_id = " + id);
                while (resultSet1.next()) {
                    Order ord = new Order();
                    ord.setName(resultSet1.getString("name"));
                    ord.setDescription(resultSet1.getString("description"));
                    ord.setStatus(resultSet1.getString("status"));
                    ord.setId(resultSet1.getInt("id"));
                    orders.add(ord);
                }
                resultSet1.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("Catch exception", e);
                throw new RuntimeException(e);
            }
        }
        session.setAttribute("craftsmanOrders", orders);
        if(entity.equals("craftsmen")) {

            String craftsmanTitle = bundle.getString("Title - User's page");
            request.setAttribute("craftsmanTitle", craftsmanTitle);
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
            String ordersFull = bundle.getString("Order description");
            request.setAttribute("ordersFull", ordersFull);
            String ordersStatus = bundle.getString("Order status");
            request.setAttribute("ordersStatus", ordersStatus);
            String changeStatus = bundle.getString("change status to");
            request.setAttribute("changeStatus", changeStatus);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page-craftsman");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
