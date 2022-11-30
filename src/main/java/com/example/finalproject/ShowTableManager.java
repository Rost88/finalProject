package com.example.finalproject;

import com.connection.ConnectionPool;
import com.logic.finalproject.Craftsman;
import com.logic.finalproject.Order;
import com.logic.finalproject.User;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShowTableManager {

    private List allOrders;
    private HttpSession session;

    public ShowTableManager(HttpSession sess) {
        allOrders = new ArrayList<>();
        session = sess;
    }

    private final static String ALL_TABLE = "SELECT orders.id, orders.name, orders.date, orders.price, orders.feedback, orders.status, orders.user_id, orders.craftsman_id, craftsman.name as craftsmanName, users.name as username FROM orders LEFT JOIN craftsman ON orders.craftsman_id=craftsman.id LEFT JOIN users ON orders.user_id = users.id";

    public List getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(String statusFilter, String craftsmanFilter, String sort){
        String commandSQL = ALL_TABLE;

        switch (statusFilter) {
            case "PENDING PAYMENT" : commandSQL = ALL_TABLE + " WHERE status = 'PENDING PAYMENT'";
                break;
            case "PAID" : commandSQL = ALL_TABLE + " WHERE status = 'PAID'";
                break;
            case "CANCELED" : commandSQL = ALL_TABLE + " WHERE status = 'CANCELED'";
                break;
            case "IN PROGRESS" : commandSQL = ALL_TABLE + " WHERE status = 'IN PROGRESS'";
                break;
            case "COMPLETED" : commandSQL = ALL_TABLE + " WHERE status = 'COMPLETED'";
                break;
        }

        if((!statusFilter.equals("")) && (!craftsmanFilter.equals("")))
            commandSQL = commandSQL + " AND";

        if(statusFilter.equals("") && (!craftsmanFilter.equals("")))
            commandSQL = commandSQL + " WHERE ";

        switch (craftsmanFilter) {
            case "unknown" : commandSQL = commandSQL + " craftsman.name = 'unknown'";
                break;
            case "Mario Mario" : commandSQL = commandSQL + " craftsman.name = 'Mario Mario'";
                break;
            case "Gadget Hackwrench" : commandSQL = commandSQL + " craftsman.name = 'Gadget Hackwrench'";
                break;
            case "Hubert J. Farnsworth" : commandSQL = commandSQL + " craftsman.name = 'Hubert J. Farnsworth'";
                break;
            case "Samodelkin" : commandSQL = commandSQL + " craftsman.name = 'Samodelkin'";
                break;
            case "Handy Beather" : commandSQL = commandSQL + " craftsman.name = 'Handy Beather'";
                break;
            case "Luigi Mario" : commandSQL = commandSQL + " craftsman.name = 'Luigi Mario'";
                break;
            case "Ketta Duck" : commandSQL = commandSQL + " craftsman.name = 'Ketta Duck'";
                break;
        }
        if(sort.equals("byPrice"))
            commandSQL = commandSQL + " ORDER BY price";
        if(sort.equals("byStatus"))
            commandSQL = commandSQL + " ORDER BY status";
        if(sort.equals("byDate"))
            commandSQL = commandSQL + " ORDER BY date";

 //       List<Order> allOrders = new ArrayList<>();
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(commandSQL);

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setName(resultSet.getString("name"));
                order.setDate(resultSet.getDate("date"));
                order.setPrice(resultSet.getInt("price"));
                order.setStatus(resultSet.getString("status"));
                order.setCraftsman(new Craftsman(resultSet.getInt("craftsman_id"), resultSet.getString("craftsmanName")));
                order.setUser(new User(resultSet.getInt("user_id"), resultSet.getString("username")));

                allOrders.add(order);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        session.setAttribute("allOrders", allOrders);
    }
    public void setEL(String pagination, String currentPageIn) {

        int totalRecord = getAllOrders().size();
        session.setAttribute("totalRecord", totalRecord);
        int pageSize;
        if (pagination.equals("10")) {
            pageSize = 10;
        } else if (pagination.equals("all")) {
            pageSize = totalRecord;
        } else {
            pageSize = 5;
        }
        session.setAttribute("pageSize", pageSize);
        //       logger.info("page size show1 = " + pageSize);
        int currentPage = Integer.parseInt(currentPageIn);
        session.setAttribute("currentPage", currentPage);
        int totalPage = 0;
        if (pageSize != 0){
            totalPage = totalRecord / pageSize;

        if (totalRecord % pageSize != 0)
            totalPage = totalPage + 1;
          }
        session.setAttribute("totalPage", totalPage);

        List ordersOnPage = new ArrayList<>();
        for (int i = (currentPage - 1) * pageSize; i < totalRecord && i < currentPage * pageSize; i++) {
            ordersOnPage.add(allOrders.get(i));
        }
        session.setAttribute("ordersOnPage", ordersOnPage);
    }
}
