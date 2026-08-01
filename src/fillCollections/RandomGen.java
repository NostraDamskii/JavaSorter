package fillCollections;
import user.User;

import java.util.concurrent.ThreadLocalRandom;


public class RandomGen
{
    //Массивы с именами и почтами
    private static final String[] NAMES = new String[]
            {"Василий", "Олег", "Евгений", "Вячеслав", "Сергей", "Матвей", "Александр", "Владислав", "Святослав"};
    private static final String[] EMAILS = new String[]
            {"Vasiliy@mail.ru", "Evgeniy@gmail.com", "Vya4eslav@yandex.ru", "Sergey@sibmail.com", "Matvey@yahoo.com", "Alexander@mail.ru", "Vladislav@mail.ru", "Svyatoslav@yandex.ru"};
    //Метод генерирующий случайного пользователя
    public static User randomGenerateUser()
    {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        String name = NAMES[random.nextInt(NAMES.length)];
        String email = EMAILS[random.nextInt(EMAILS.length)];
        // Случайная генерация пароля с помощью метода встроенного класса ThreadLocalRandom
        // .nextInt < метод класса random который возвращает случайное значение в пределах указанного диапазона
        int password = random.nextInt(100000, 999999);
        // Возвращаем пользователя со случайными данными
        return new User.Builder().name(name).email(email).password(password).build();
    }
        // Простой метод для проверки кода
    public static void main(String[] args)
    {
        User testUser = randomGenerateUser();

        System.out.println("имя: " + testUser.getName());
        System.out.println("email: " + testUser.getEmail());
        System.out.println("пароль: " + testUser.getPassword());
    }
}
