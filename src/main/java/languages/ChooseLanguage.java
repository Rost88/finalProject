package languages;

import com.mysql.cj.Session;
import jakarta.servlet.http.Cookie;

import java.util.Locale;
import java.util.ResourceBundle;

public class ChooseLanguage {

    ResourceBundle bundle = ResourceBundle.getBundle("languages.resource");
   static ResourceBundle bundleUA = ResourceBundle.getBundle("languages.resource", new Locale("uk", "UA"));
   static ResourceBundle bundleEN = ResourceBundle.getBundle("languages.resource", new Locale("en"));

   public static ResourceBundle chooseBundle(String lang) {
        ResourceBundle bundleChoosed = bundleEN;
        if(lang.equals("uk"))
            bundleChoosed = bundleUA;

        return bundleChoosed;
    }

}
