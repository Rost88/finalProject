/**
 * The class shows information for managers. It helps to create request to BD, tables with orders, applied filters
 * and others.
 *
 * @author Kuznietsov Rostyslav
 */
package com.example.finalproject;

import com.logic.finalproject.Craftsman;
import com.logic.finalproject.Order;
import com.logic.finalproject.User;
import com.mysql.cj.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ShowTable {

private static final Logger logger = LoggerFactory.getLogger(ShowTable.class);
    private final static String ALL_TABLE = "SELECT orders.id, orders.name, orders.date, orders.price, orders.feedback, orders.status, orders.user_id, orders.craftsman_id, craftsman.name as craftsmanName, users.name as username FROM orders LEFT JOIN craftsman ON orders.craftsman_id=craftsman.id LEFT JOIN users ON orders.user_id = users.id";

    /**
     * This method creates string for request to Data Base, connects. Finally, it returns table,
     *  that managers see
     * @author Kuznietsov Rostyslav
     * @param sort
     * @param statusFilter
     * @param craftsmanFilter
     * @param pagination
     * @param currentPageIn
     * @return
     */
    public static String show1(String sort, String statusFilter, String craftsmanFilter, String pagination, String currentPageIn) {
        logger.info("Start show1" + pagination);
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

        List<Order> allOrders = new ArrayList<>();
        try {
            DriverManager.registerDriver(new Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");

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

        int totalRecord = allOrders.size();
        int pageSize;
        if(pagination.equals("10")) {
            pageSize = 10;
        }else if(pagination.equals("all")) {
            pageSize = totalRecord;
        } else {
            pageSize = 5;
        }
        logger.info("page size show1 = " + pageSize);
        int currentPage = Integer.parseInt(currentPageIn);
        int totalPage = totalRecord / pageSize;
        if(totalRecord % pageSize != 0)
            totalPage = totalPage + 1;

        String prev1 = " ";// "  &lt;&lt; Prev  ";
        if(currentPage > 1) {
            prev1 = "<form action=\"/mymanagers\" method=\"get\">" +
                    "<input type = \"hidden\" name = \"currentPage\" value = \"" + (currentPage - 1) + "\"/>" +
                    "<input type = \"hidden\" name = \"pagination\" value = \"" + pageSize + "\"/>" +
                    "<input type = \"hidden\" name = \"status\" value = \"" + statusFilter + "\"/>" +
                    "<input type = \"hidden\" name = \"craftsman\" value = \"" + craftsmanFilter + "\"/>" +
                    "<input type = \"hidden\" name = \"sort\" value = \"" + sort + "\"/>" +
                    "<input type=\"submit\" value=\"&lt;&lt; Prev\" />" +
                    "</form>";
        }
        String next1 = " ";
        if(currentPage < totalPage)
            next1 =  "<form action=\"/mymanagers\" method=\"get\">" +
                    "<input type = \"hidden\" name = \"currentPage\" value = \"" + (currentPage + 1) + "\"/>" +
                    "<input type = \"hidden\" name = \"pagination\" value = \"" + pageSize + "\"/>" +
                    "<input type = \"hidden\" name = \"status\" value = \"" + statusFilter + "\"/>" +
                    "<input type = \"hidden\" name = \"craftsman\" value = \"" + craftsmanFilter + "\"/>" +
                    "<input type = \"hidden\" name = \"sort\" value = \"" + sort + "\"/>" +
                    "<input type=\"submit\" value=\"Next &gt;&gt;\" />" +
                    "</form>";



        String prevNext = "<table><tr><td width = \"48%\" align = \"right\">" + prev1 + "</td><td width = \"2%\">" + currentPage +
                "</td><td width = \"48%\" align = \"left\">" + next1 + "</td></tr></table>";

        String sortByStatus = "<form action=\"/mymanagers\" method=\"get\">" +
                "<input type = \"hidden\" name = \"currentPage\" value = \"" + 1 + "\"/>" +
                "<input type = \"hidden\" name = \"pagination\" value = \"" + pagination + "\"/>" +
                "<input type = \"hidden\" name = \"status\" value = \"" + statusFilter + "\"/>" +
                "<input type = \"hidden\" name = \"craftsman\" value = \"" + craftsmanFilter + "\"/>" +
                "<input type = \"hidden\" name = \"sort\" value = \"byStatus\"/>" +
                "<input type=\"submit\" value=\"Status\" />" +
                "</form>";
        String sortByDate = "<form action=\"/mymanagers\" method=\"get\">" +
                "<input type = \"hidden\" name = \"currentPage\" value = \"" + 1 + "\"/>" +
                "<input type = \"hidden\" name = \"pagination\" value = \"" + pagination + "\"/>" +
                "<input type = \"hidden\" name = \"status\" value = \"" + statusFilter + "\"/>" +
                "<input type = \"hidden\" name = \"craftsman\" value = \"" + craftsmanFilter + "\"/>" +
                "<input type = \"hidden\" name = \"sort\" value = \"byDate\"/>" +
                "<input type=\"submit\" value=\"Date\" />" +
                "</form>";
        String sortByPrice = "<form action=\"/mymanagers\" method=\"get\">" +
                "<input type = \"hidden\" name = \"currentPage\" value = \"1\"/>" +
                "<input type = \"hidden\" name = \"pagination\" value = \"" + pagination + "\"/>" +
                "<input type = \"hidden\" name = \"status\" value = \"" + statusFilter + "\"/>" +
                "<input type = \"hidden\" name = \"craftsman\" value = \"" + craftsmanFilter + "\"/>" +
                "<input type = \"hidden\" name = \"sort\" value = \"byPrice\"/>" +
                "<input type=\"submit\" value=\"Price\" />" +
                "</form>";
        String activeFilters = "<br><b> Active filters:</b>  Status: " + statusFilter + ";  Craftsman: " + craftsmanFilter +
                ";  Sort: " + sort + ";  Total Records: " + totalRecord +
                ";  Page size: " + pageSize + "; Total Page: " +  totalPage +
                ";  Current Page: " + currentPageIn + "<br><p></p> " +
                "<a href = \"/mymanagers\">Reset all filters</a> <br>";

String table = activeFilters + prevNext + "<table border = 1 ><tr><td>№ заказа</td><td>описание</td><td>User</td><td>" + sortByStatus + "</td><td>Мастер</td><td>" + sortByDate + "</td><td>" + sortByPrice + "</td></tr>";

        for (int i = (currentPage - 1) * pageSize; i < totalRecord && i < currentPage * pageSize; i++){
            String s = "";
            if(allOrders.get(i).getPrice()==0 && allOrders.get(i).getStatus().equals("PENDING PAYMENT")) {
                s = "<form action = \"/change-order-price\" method = \"get\">" +
                        "<input type = \"hidden\" name = \"pagination\" value = \"" + pagination + "\"/>" +
                        "<input type = \"hidden\" name = \"sort\" value = \"" + sort + "\"/>" +
                        "<input type = \"hidden\" name = \"status\" value = \"" + statusFilter + "\"/>" +
                        "<input type = \"hidden\" name = \"craftsman\" value = \"" + craftsmanFilter + "\"/>" +
                        "<input type = \"hidden\" name = \"currentPage\" value = \"" + currentPage + "\"/>" +
                        "<input type=\"number\" name = \"newprice\" placeholder=\"new price\" min=\"1\"><button type = \"submit\" name = \"orID\" value = \"" + allOrders.get(i).getId() + "\">Изменить цену</button></form>" + "</td></tr>";
            };
            String craft = allOrders.get(i).getCraftsman().toString();
            if(craft.equals("unknown") && (!allOrders.get(i).getStatus().equals("CANCELED")))
                craft = "<a href = \"/set-craftsman?orderID=" + allOrders.get(i).getId() +
                        "&currentPage=" + currentPage + "&pagination=" + pagination + "&sort=" + sort +
                        "&status=" + statusFilter + "&craftsman=" + craftsmanFilter +
                        "\"> set craftsman </a>";
            String user = "<a href = \"/top-on?userName=" + allOrders.get(i).getUser().toString() + "&userID=" + allOrders.get(i).getUser().getId() +
                    "&currentPage=" + currentPage + "&pagination=" + pagination + "&sort=" + sort +
                    "&status=" + statusFilter + "&craftsman=" + craftsmanFilter +
                    "\"> top up " + allOrders.get(i).getUser() + " balance </a>";

            table = table + "<tr><td>" + allOrders.get(i).getId() + "</td><td>" + allOrders.get(i).getName() + "</td><td>" + user + "</td><td>" +allOrders.get(i).getStatus() + "</td><td>" + craft +
                    "</td><td>" + allOrders.get(i).getDate() + "</td><td> Цена заказа:" + allOrders.get(i).getPrice() + s;
        }

return table + "</table>";
    }

    /**
     * The method creates table with filters, helps to send filter's parameters
     * @param sort
     * @param pagination
     * @param lang
     * @return
     */
    public static String filters(String sort, String pagination, String lang){

        String filter = "<p>Filters</p>";
        String chooseStatus = "Choose status";
        String chooseCraftsman = "Choose craftsman";
        if(lang.equals("uk")) {
            filter = "<p>Фільтри</p>";
            chooseStatus = "Фільтрувати за статусом";
            chooseCraftsman = "Фільтрувати за майстром";
        }
        filter = filter + "<form action=\"/mymanagers\" method=\"get\"><table><tr><td width = \"50%\">" + chooseStatus + "</td><td>" + chooseCraftsman + "</td></tr>" +
        "<tr><td>" +
                "<input type=\"radio\" name=\"status\" value=\"PENDING PAYMENT\"> PENDING PAYMENT" +
                "<input type=\"radio\" name=\"status\" value=\"CANCELED\"> CANCELED" +
                "<input type=\"radio\" name=\"status\" value=\"PAID\"> PAID" +
                "<input type=\"radio\" name=\"status\" value=\"IN PROGRESS\"> IN PROGRESS" +
                "<input type=\"radio\" name=\"status\" value=\"COMPLETED\"> COMPLETED" +
                "</td><td>" +
                "<input type=\"radio\" name=\"craftsman\" value=\"unknown\"> unknown" +
                "<input type=\"radio\" name=\"craftsman\" value=\"Mario Mario\"> Mario Mario" +
                "<input type=\"radio\" name=\"craftsman\" value=\"Gadget Hackwrench\"> Gadget Hackwrench" +
                "<input type=\"radio\" name=\"craftsman\" value=\"Hubert J. Farnsworth\"> Hubert J. Farnsworth" +
                "<br><input type=\"radio\" name=\"craftsman\" value=\"Samodelkin\"> Samodelkin" +
                "<input type=\"radio\" name=\"craftsman\" value=\"Handy Beather\"> Handy Beather" +
                "<input type=\"radio\" name=\"craftsman\" value=\"Luigi Mario\"> Luigi Mario" +
                "<input type=\"radio\" name=\"craftsman\" value=\"Ketta Duck\"> Ketta Duck" +
                "</td></tr><tr><td>" +
                "<input type = \"hidden\" name = \"pagination\" value = \"" + pagination + "\"/>" +
                "<input type = \"hidden\" name = \"sort\" value = \"" + sort + "\"/>" +  // sort
                "</td></tr><tr><td colspan = \"2\"> " +
                "<input type=\"submit\" value=\"Apply filters\"></td>" +
                "</tr></table></form>";
        return filter;
    }

    /**
     * The method creates pagination in the table with orders on the manager's page
     * @param sort
     * @param statusFilter
     * @param craftsmanFilter
     * @return
     */
    public static String pagination(String sort, String statusFilter, String craftsmanFilter){
        String pag = "";
        pag =  "<form action=\"/mymanagers\" method=\"get\"><table><tr><td>Show</td></tr>" +
                "<tr><td>" +
                "<input type=\"radio\" name=\"pagination\" value=\"5\"> 5   " +
                "<input type=\"radio\" name=\"pagination\" value=\"10\"> 10   " +
                "<input type=\"radio\" name=\"pagination\" value=\"all\"> all" +
                "<input type = \"hidden\" name = \"status\" value = \"" + statusFilter + "\"/>" +
                "<input type = \"hidden\" name = \"craftsman\" value = \"" + craftsmanFilter + "\"/>" +
                "<input type = \"hidden\" name = \"sort\" value = \"" + sort + "\"/>" +
                "</td></tr><tr><td><input type=\"submit\" value=\"Show on the page\">" +
                "</td></tr></table></form>";

        return pag;
    }


}
