package src;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class RandomGen
{
    private static final String[] NAMES = new String[]
            {"Василий", "Олег", "Евгений", "Вячеслав", "Сергей", "Матвей", "Александр", "Владислав", "Святослав"};
    private static final String[] EMAILS = new String[]
            {"Vasiliy@mail.ru", "Evgeniy@gmail.com", "Vya4eslav@yandex.ru", "Sergey@sibmail.com", "Matvey@yahoo.com", "Alexander@mail.ru", "Vladislav@mail.ru", "Svyatoslav@yandex.ru"};
    private static final Random RANDOM = new Random();

    public static User randomGenerateUser()
    {
        String name = NAMES[RANDOM.nextInt(NAMES.length)];
        String email = EMAILS[RANDOM.nextInt(EMAILS.length)];
        int password = ThreadLocalRandom.current().nextInt(100000, 999999);;

        return new User.Builder().name(name).email(email).password(password).build();
    }

    public static void main(String[] args)
    {
        User testUser = randomGenerateUser();

        System.out.println("имя: " + testUser.getName());
        System.out.println("email: " + testUser.getEmail());
        System.out.println("пароль: " + testUser.getPassword());
    }
}
