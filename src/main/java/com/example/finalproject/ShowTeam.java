/**
 * The class shows information for everybody. It helps to create request to BD, tables with feedback about craftsmen
 * on the page "Our team"
 *
 * @author Kuznietsov Rostyslav
 */
package com.example.finalproject;

import com.connection.ConnectionPool;
import com.logic.finalproject.Craftsman;
import com.logic.finalproject.Feedbacks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowTeam {
    private static final Logger logger = LoggerFactory.getLogger(ShowTeam.class);

    /**
     * The method take feedbacks about craftsmen from Data base and put feedbacks into the table
     * @param id
     * @return
     */
    public static String showFeedbacks(int id) {
        List<Feedbacks> allFeedbacks = new ArrayList<>();
        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");

            Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select orders.name as ordName, craftsman.name as crName, users.name as usName, orders.feedback from orders, craftsman, users where craftsman_id = " + id + " and  orders.feedback != 'NULL'  and craftsman.id = " + id + " and orders.user_id = users.id");
            while (resultSet.next()) {
                String userName = resultSet.getString("usName");
                String orderName = resultSet.getString("ordName");
                String feedback = resultSet.getString("feedback");

                allFeedbacks.add(new Feedbacks(userName, orderName, feedback));

            }
            logger.info("Create table with feedbacks");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.error("Catch exception", e);
            throw new RuntimeException(e);
        }

        String table = "<table border = 1 ><tr><td colspan = \"3\">Feedbacks about the craftsman</td></tr>";
        if(allFeedbacks.size() > 0) {
            for (Feedbacks feed : allFeedbacks) {

                table = table + "<tr><td>" + feed.getUserName() + "</td><td>" + feed.getOrderName() + "</td><td>" + feed.getFeedback() + "</td></tr>";
            }
            table = table + "</table><br>";
        } else {
            table = "This craftsman doesn't have any feedbacks yet!<br> <br>";
        }

        return table;
    }

    /**
     * This method returns craftsman's list
     * @return
     */
    public static List<Craftsman> craftsmanList() {
        List<Craftsman> craftsmen = new ArrayList<>();
        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_project", "root", "rost1980");
            Connection connection = ConnectionPool.getInstance().getConnection();


            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM craftsman");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String email = resultSet.getString("login");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String photo = resultSet.getString("photo");
                craftsmen.add(new Craftsman(id, email, name, password, photo));
            }

        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
            logger.error("Can't create craftsman's list", e);
        throw new RuntimeException(e);
    }
         logger.info("Craftsmans list was created");
        return craftsmen;
    }

    /**
     * This method put craftsman's list into the table
     * @return
     */
    public static String tableCraftsmen() {
        String table = "<center><table width = 95%>";
        for (int i = 1; i < craftsmanList().size(); i++){
           table = table + "<tr><td colspan = 2><hr><center><h3>" + craftsmanList().get(i).getName() + "</h3></center></td></tr><tr><td>" + showFeedbacks(craftsmanList().get(i).getId()) + "</td><td> <img src = \"images/" + craftsmanList().get(i).getPhoto() + "\" align = \"right\"> </td></tr>";
        }
        table = table + "</table></center>";

        return table;
    }
}
