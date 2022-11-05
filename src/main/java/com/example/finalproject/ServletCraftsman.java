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
        Craftsman craftsman = new Craftsman();
        List<Order> orders = new ArrayList<>();
        try {
//            DriverManager.registerDriver(new Driver());
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
            Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Craftsman WHERE id = '" + id + "';");
            resultSet.next();
            craftsman.setName(resultSet.getString("name"));

            craftsman.setEmail(resultSet.getString("login"));
            craftsman.setPassword(resultSet.getString("password"));
            craftsman.setPhoto(resultSet.getString("photo"));
            resultSet.close();
            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM orders WHERE craftsman_id = " + id);
            while (resultSet1.next()){
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
        if(entity.equals("craftsmen")) {

            PrintWriter printWriter = response.getWriter();
            printWriter.println(startPageStartTitle);
            printWriter.println(bundle.getString("ServletCraftsman title"));
            printWriter.println(finishTitleStartBody(lang));
            printWriter.println(" <table width = \"70%\"><tr><td> " + bundle.getString("Hello") + craftsman.getName() + "<img src = \"/images/" + craftsman.getPhoto() + "\" align = \"right\">");
            printWriter.println("<br>e-mail: " + craftsman.getEmail());
            printWriter.println("<br>" + bundle.getString("password") + craftsman.getPassword());
            printWriter.println("</td></tr></table><br><hr>");
            printWriter.println("<br>" + bundle.getString("my orders") + ": "+
                    "<table border = 1>" +
                    "<tr>" +
                    "<td> " + bundle.getString("Order ID") + " </td>" +
                    "<td> " + bundle.getString("Order name") + " </td>" +
                    "<td> " + bundle.getString("Order description") + " </td>" +
                    "<td> " + bundle.getString("Order status") + " </td>" +
                    "</tr>");
            if(orders.size()>0){
                for (int i = 0; i < orders.size(); i++) {
                    String status = orders.get(i).getStatus();
                    if(status.equals("PAID")) {
                        status = status + "<a href = \"/change-status-to-in-progress?orderID=" +
                                orders.get(i).getId() + "\">" + bundle.getString("change status to") + " \"in progress\" </a>";
                    }
                    if(status.equals("IN PROGRESS")) {
                        status = status + "<a href = \"/change-status-to-completed?orderID=" +
                                orders.get(i).getId() + "\"> " + bundle.getString("change status to") + " \"completed\" </a>";
                    }
                    printWriter.println("<tr>" +
                            "<td>" + orders.get(i).getId() + "</td>" +
                            "<td>" + orders.get(i).getName() + " </td>" +
                            "<td>" + orders.get(i).getDescription() + "</td>" +
                            "<td>" + status + "</td>" +
                            "</tr>");
                }
        } else {
            printWriter.println("<tr><td> " + bundle.getString("You don`t have any orders") + " </td></tr>");
        }
        printWriter.println(" </table>" );
        logger.info("Craftsman id {}, name {}", id, craftsman.getName());
            printWriter.println(finishPage(lang));
       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
