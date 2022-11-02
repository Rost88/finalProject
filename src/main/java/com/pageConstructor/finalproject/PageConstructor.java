/**
 * The class helps to created view for JSP pages with PrintWriter
 *
 * @author Kuznietsov Rostyslav
 */
package com.pageConstructor.finalproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ResourceBundle;

public class PageConstructor {

    private static final Logger logger = LoggerFactory.getLogger("PageConstructor");
    static String langv = "uk";
    public static void lang(String l) {
         langv = l;
        logger.info("start page constructor lang = {}", langv) ;
    }

   static ResourceBundle bundle1 = languages.ChooseLanguage.chooseBundle(langv);
    private final static String header = "<table width=\"100%\"><tr><td width=\"300px\">" +
            "    <a href=\"/\"><img src=\"/images/logo.gif\" align=\"left\"></a> </td><td align = \"center\"> <h3>Phone: +380(88) 888 88 88</h3>" +
            "</td><td width=\"300px\"><form method=\"get\" ><input type=\"hidden\" name=\"lang\" value=\"en\"/>\n" +
            "    <button ><img src=\"/images/en.jpg\"> </button></form><form method=\"get\" ><input type=\"hidden\" name=\"lang\" value=\"uk\"/><button><img src=\"/images/uk.jpg\"> </button></form></td></tr></table>" +
            "<hr>" +
            "<ul class=\"main-menu\">\n" +
            "    <li class=\"active\"><a href=\"/\"> Main page </a> </li>\n" +
            "    <li><a href=\"/task\"> My task for Final Project </a> </li>\n" +
            "    <li><a href=\"/road-map\"> Road map </a> </li>\n" +
            "    <li><a href=\"/team\"> Our team </a> </li>\n" +
            "\n" +
            "</ul> <hr>";
    private final static String headerUk = "<table width=\"100%\"><tr><td width=\"300px\">" +
            "    <a href=\"/\"><img src=\"/images/logo.gif\" align=\"left\"></a> </td><td align = \"center\"> <h3>Телефон: +380 (88) 888 88 88</h3>" +
            "</td><td width=\"300px\"><form method=\"get\" ><input type=\"hidden\" name=\"lang\" value=\"en\"/>\n" +
            "    <button ><img src=\"/images/en.jpg\"> </button></form><form method=\"get\" ><input type=\"hidden\" name=\"lang\" value=\"uk\"/><button><img src=\"/images/uk.jpg\"> </button></form></td></tr></table>" +
            "<hr>" +
            "<ul class=\"main-menu\">\n" +
            "    <li class=\"active\"><a href=\"/\"> Головна </a> </li>\n" +
            "    <li><a href=\"/task\"> Моє завдання фінального проекту </a> </li>\n" +
            "    <li><a href=\"/road-map\"> Карта проїзду </a> </li>\n" +
            "    <li><a href=\"/team\"> Наша команда </a> </li>\n" +
            "\n" +
            "</ul> <hr>";
    private final static String footer = "<hr> <footer style=\"background: gainsboro\">" +
            "<table><tr><td align=\"left\">" +
            "<p><a href=\"/autorisation-users\" style=\"text-decoration: none; color: #B22522\">Page for autorisation users </a></p>\n" +
            "<p><a href=\"/registration-users\" style=\"text-decoration: none; color: #B22522\">Page for registration new users </a> </p>\n" +
            "</td>\n" +
            "  <td><p><a href=\"/autorisation-managers\" style=\"text-decoration: none; color: #B22522\">Page for autorisation managers </a></p><p></p></td>\n" +
            "  <td align=\"right\"><p><a href=\"/autorisation-craftsmen\" style=\"text-decoration: none; color: #B22522\">Page for autorisation craftsmans </a> </p><p></p></td>\n" +
            "</tr>\n" +
            "</table></footer>";
    private static String footerUa = "<hr> <footer style=\"background: gainsboro\">" +
            "<table><tr><td align=\"left\">" +
            "<p><a href=\"/autorisation-users\" style=\"text-decoration: none; color: #B22522\">Сторінка авторизації користувачів </a></p>\n" +
            "<p><a href=\"/registration-users\" style=\"text-decoration: none; color: #B22522\">Сторінка регистрації нових користувачів </a> </p>\n" +
            "</td>\n" +
            "  <td><p><a href=\"/autorisation-managers\" style=\"text-decoration: none; color: #B22522\">Сторінка авторизації менеджерів </a></p><p></p></td>\n" +
            "  <td align=\"right\"><p><a href=\"/autorisation-craftsmen\" style=\"text-decoration: none; color: #B22522\">Сторінка авторизації майстрів </a> </p><p></p></td>\n" +
            "</tr>\n" +
            "</table></footer>";
    public final static String startPageStartTitle = "<html> <head>\n" +
            "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "        <title>";

    /**
     * Create header for html page
     * @param language
     * @return
     */
    public static String finishTitleStartBody(String language) {
        String f =
        "</title>" +
                "<link rel=\"stylesheet\" href=\"resources/style.css\">" +
                " </head><body><div class=\"wrapper\"><div class=\"content\">";
                if(language.equals("uk")) {
                    f = f + headerUk + "<br> servlet lang: " + language;
                } else {
                    f = f + header+ "<br> servlet lang: " + language;
                }
        return f;
    }

    /**
     * Create footer end finish html page
     * @param language
     * @return
     */
    public static String finishPage(String language){
        String fp;
        if(language.equals("uk")) {
            fp = "</div><div class=\"footer\">" + footerUa + "</div></div></body></html>";
        } else {
            fp = "</div><div class=\"footer\">" + footer + "</div></div></body></html>";
        }
        return fp;
    }
}
