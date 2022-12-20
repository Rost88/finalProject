/**
 * The class helps to realize translating webapp to different languages
 * Default language is english
 * @author Kuznietsov Rostyslav
 */
package languages;

import java.util.ListResourceBundle;

public class resource extends ListResourceBundle {

    private static final Object[][] contents =
            {
                    //header
                    {"phone", "Phone"},
                    {"Main page", "Main page"},
                    {"My task for Final Project", "My task for Final Project"},
                    {"Road map", "Road map"},
                    {"Our team", "Our team"},
                    //Road map
                    {"Road map", "Road map"},
                    //index
                    {"testText" , "testText"},
                    {"indexTitle", "Repair agency - final project by Rostyslav Kuznetsov"},
                    {"index Welcome to my Final Project", "Welcome to my Final Project!"},
                    {"index Repair agency \"Hands from that place!\"", "Repair agency \"Hands from that place!\""},
                    {"index welcome2" , "Welcome to the best repair agency in the world! Our craftsmen can repair everything, even if it is not broken else:))!!!"},
                    {"index Call us", "Call us just now! And our team help you as soon as possible!!!"},
                    //Our team
                    {"Our team" , "Our team"},
                    {"Our managers" , "Our managers"},
                    {"Beavis" , "Beavis"},
                    {"Butt-head" , "Butt-heads"},
                    {"Our craftsmen" , "Our craftsmen"},
                    //task
                    {"task", "<p>PROJECT DESCRIPTION</p><p>\n" +
                            "\n" +
                            "    The task of the final project is to develop a web application that supports the functionality according to the task variant.</p><p>\n" +
                            "\n" +
                            "</p><h1>   Repair agency\t</h1>\n" +
                            "\n" +
                            "<p>There are roles: manager, craftsman, user.</p><p>\n" +
                            "    A registered user can create a repair order. The user also has an account from which payment for repairs is made.\n" +
                            "</p><p>The manager can manage user orders:</p><p>\n" +
                            "    - appoint a craftsman;</p><p>\n" +
                            "    - determine the cost of work</p><p>\n" +
                            "    - change the status of the order: \"pending payment\", \"paid\", \"canceled\".</p><p>\n" +
                            "    The manager also has the opportunity to replenish the user's account.\n" +
                            "</p><p>The craftsman has the opportunity to select from the list of orders for repairs and change its status to \"in progress\" or \"completed\". Once the order has been completed, the user has the opportunity to leave feedback on the work of the craftsman.\n" +
                            "</p><p>The manager should be able to view the report with a list of orders where he can sort:\n" +
                            "</p><p>- by orders date;\n" +
                            "</p><p>- by orders  status;</p><p>\n" +
                            "    - by orders cost.</p><p>\n" +
                            "    The manager must also be able to filter orders:</p><p>\n" +
                            "    - by orders  status;</p><p>\n" +
                            "    - by the craftsman who executes / executed the order.</p>"},
                    //Footer
                    {"Page for autorisation users", "Page for autorisation users"},
                    {"Page for registration new users", "Page for registration new users"},
                    {"Page for autorisation managers", "Page for autorisation managers"},
                    {"Page for autorisation craftsmans", "Page for autorisation craftsmans"},
                    //Autorisation
                    {"User's E-mail", "User's E-mail"},
                    {"Manager's E-mail", "Manager's E-mail"},
                    {"Craftsman's E-mail", "Craftsman's E-mail"},
                    {"Enter your password", "Enter your password"},
                    {"Enter", "Enter"},
                    {"Enter your name", "Enter your name"},
                    //CreateNewOrder
                    {"Order's short description", "Order's short description"},
                    {"Order's full description", "Order's full description"},
                    {"Create new order", "Create new order"},
                    //LeaveFeedback
                    {"Leave a feedback", "Leave a feedback after the order ID = "},
                    {"Leave feedback", "Leave a feedback"},
                    //SetCraftsman
                    {"Set Craftsman for", "Set a craftsman for the order ID №"},
                    {"Set Craftsman", "Set Craftsman"},
                    //TopOnUsersBalance
                    {"Top up", "Top up"},
                    {"Top up users balance", "Top up the user's balance"},
                    {"User", "User"},
                    //PageUser
                    {"Title - User's page" , "Title - User's page"},
                    {"Money on balance" , "Money on balance"},
                    {"Order price" , "Order's price"},
                    {"Order craftsman" , "Order's craftsman"},
                    {"Pay order" , "Pay order"},
                    {"Cancel order" , "Cancel order"},
                    {"Create order" , "Create new order"},
                    //ServletCraftsman
                    {"ServletCraftsman title" , "Craftsman's personal page"},
                    {"Hello" , "Hello, "},
                    {"password" , "Password"},
                    {"my orders" , "My orders"},
                    {"Order ID" , "Order ID"},
                    {"Order name" , "Short description"},
                    {"Order description" , "Full description"},
                    {"Order status" , "Order status"},
                    {"change status to" , "change status to"},
                    {"You don`t have any orders" , "You don`t have any orders"},
                    //PageManager
                    {"Title - Manager's page" , "Title - Manager's page"},
                    {"Choose Status" , "Choose Status"},
                    {"Choose Craftsman" , "Choose Craftsman"},
                    {"Apply filters" , "Apply filters"},
                    {"Show" , "Show"},
                    {"Show on the page" , "Show on the page"},
                    {"Prev" , "Prev"},
                    {"Next" , "Next"},
                    {"Reset all filters" , "Reset all filters"},
                    {"Craftsman" , "Craftsman"},
                    {"Orders Data" , "Data"},
                    {"Set craftsman" , "Set craftsman"},
                    //WrongLogin
                    {"You entered a wrong password" , "You entered a wrong password "},
                    {"does not exist" , " does not exist"},

                    {"You entered a wrong login" , "You entered a wrong login "},
                    {"back to Main page" , "back to Main page"}


            };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
