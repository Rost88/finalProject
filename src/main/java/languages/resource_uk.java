/**
 * The class helps to realize translating webapp to different languages
 * Ukrainian language
 * @author Kuznietsov Rostyslav
 */
package languages;

import java.util.ListResourceBundle;

public class resource_uk extends ListResourceBundle {

    private static final Object[][] contents =
            {
                    //header
                    {"phone", "Телефон"},
                    {"Main page", "Головна"},
                    {"My task for Final Project", "Моє завдання Фінального Проекту"},
                    {"Road map", "Карта проїзду"},
                    {"Our team", "Наша команда"},

                    {"Road map", "Карта проїзду"},
                    //index
                    {"testText" , "testTextУкраїнською"},
                    {"indexTitle", "Ремонтне агенство - фінальний проект Ростислава Кузнєцова"},
                    {"index Welcome to my Final Project", "Ласкаво прошу до мого фінального проекту!"},
                    {"index Repair agency \"Hands from that place!\"", "Ремонтне агенство \"Руки з того місця!\""},
                    {"index welcome2" , "Ласкаво просимо до найкращого ремонтного агенства у світі! Наші майстри здатні полагодити будь-що, навіть якщо воно ще не зламане:))!!!"},
                    {"index Call us", "Телефонуйте нам прямо зараз! Та наша команда допоможе вам якнайшвидше!!!"},
                    //Our team
                    {"Our team" , "Наша команда"},
                    {"Our managers" , "Наші менеджери"},
                    {"Beavis" , "Бівіс"},
                    {"Butt-head" , "Батт-хед"},
                    {"Our craftsmen" , "Наші майстри"},
                    //task
                    {"task", "<p>ОПИС ПРОЕКТУ</p><p> (переклад зроблено на східно-український діалект:)</p><p>\n" +
                            "\n" +
                            "    Задача финального проекта — разработать веб-приложение, поддерживающее функционал согласно варианту задачи.</p>\n" +
                            "<h1>\n" +
                            "    Ремонтное агентство </h1>\n" +
                            "<p>Есть роли: менеджер, мастер, пользователь.\n" +
                            "    Зарегистрированный пользователь может создать заказ на ремонт. Также у пользователя есть аккаунт, с которого производится оплата ремонта.\n" +
                            "</p><p>Менеджер может управлять заказами пользователей:</p><p>\n" +
                            "    - назначить мастера;</p><p>\n" +
                            "    - определить стоимость работ</p><p>\n" +
                            "    - изменить статус заказа: \"ожидает оплаты\", \"оплачен\", \"отменен\".\n" +
                            "</p><p>Также у менеджера есть возможность пополнить счет пользователя.\n" +
                            "</p><p>Мастер имеет возможность выбрать из списка заказы на ремонт и изменить его статус на «выполняется» или «выполнен». После выполнения заказа пользователь имеет возможность оставить отзыв о работе мастера.</p><p>\n" +
                            "    Менеджер должен иметь возможность просматривать отчет со списком заказов, где он может сортировать:</p><p>\n" +
                            "    - по дате заказа;\n" +
                            "</p><p>- по статусу заказов;</p><p>\n" +
                            "    - по стоимости заказов.</p><p>\n" +
                            "    Менеджер также должен иметь возможность фильтровать заказы:\n" +
                            "</p><p>- по статусу заказов;\n" +
                            "</p><p>- мастером, выполняющим/исполнившим заказ.</p>"},
                    //Footer
                    {"Page for autorisation users", "Сторінка авторизації користувачів"},
                    {"Page for registration new users", "Сторінка регистрації нових користувачів"},
                    {"Page for autorisation managers", "Сторінка авторизації менеджерів"},
                    {"Page for autorisation craftsmans", "Сторінка авторизації майстрів"},
                    //Autorisation
                    {"User's E-mail", "E-mail користувача"},
                    {"Manager's E-mail", "E-mail менеджера"},
                    {"Craftsman's E-mail", "E-mail майстра"},
                    {"Enter your password", "Введіть ваш пароль"},
                    {"Enter", "Вхід"},
                    {"Enter your name", "Введіть ваше ім'я"},
                    //CreateNewOrder
                    {"Order's short description", "Опис замовлення короткий"},
                    {"Order's full description", "Опис замовлення повний"},
                    {"Create new order", "Створити нове замовлення"},
                    //LeaveFeedback
                    {"Leave a feedback", "Залишити відгук за виконання замовлення ID = "},
                    {"Leave feedback", "Залишити відгук"},
                    //SetCraftsman
                    {"Set Craftsman for", "Призначити майстра для виконання замовлення ID №"},
                    {"Set Craftsman", "Призначити майстра"},
                    //TopOnUsersBalance
                    {"Top up", "Поповнити"},
                    {"Top up users balance", "Поповнити баланс користувача"},
                    {"User", "Користувач"},
                    //PageUser
                    {"Title - User's page" , "Title - Сторінка користувача"},
                    {"Money on balance" , "Грошей на балансі"},
                    {"Order price" , "Вартість замовлення"},
                    {"Order craftsman" , "Майстер замовлення"},
                    {"Pay order" , "Сплатити замовлення"},
                    {"Cancel order" , "Відмінити замовлення"},
                    {"Create order" , "Створити нове замовлення"},
                    //ServletCraftsman
                    {"ServletCraftsman title" , "Особиста сторінка майстра"},
                    {"Hello" , "Привіт, "},
                    {"password" , "Пароль"},
                    {"my orders" , "Мої замовлення"},
                    {"Order ID" , "ID замовлення"},
                    {"Order name" , "Короткий опис"},
                    {"Order description" , "Повний опис"},
                    {"Order status" , "Статус замовлення"},
                    {"change status to" , "змінити статус на "},
                    {"You don`t have any orders" , "У вас немає замовлень"},
                    //PageManager
                    {"Title - Manager's page" , "Title - Сторінка менеджера "},
                    {"Choose Status" , "Обрати статус"},
                    {"Choose Craftsman" , "Обрати майстра"},
                    {"Apply filters" , "Примінити фільтри"},
                    {"Show" , "Показати"},
                    {"Show on the page" , "Показати на сторінці"},
                    {"Prev" , "Назад"},
                    {"Next" , "Далі "},
                    {"Reset all filters" , "Очистити всі фільтри"},
                    {"Craftsman" , "Майстер"},
                    {"Orders Data" , "Дата"},
                    {"Set craftsman" , "Призначити майстра"},
                    //WrongLogin
                    {"You entered a wrong password" , "Ви ввели не вірний пароль: "},
                    {"does not exist" , " не існує"},

                    {"You entered a wrong login" , "Ви ввели невірний логін "},
                    {"back to Main page" , "повернутися на Головну сторінку"}
            };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}