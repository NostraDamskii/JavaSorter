package service;
import core.User;

import java.util.concurrent.ThreadLocalRandom;


public class RandomGen
{
    
    private static final String[] NAMES = new String[]
            {"Василий", "Олег", "Евгений", "Вячеслав", "Сергей", "Матвей", "Александр", "Владислав", "Святослав"};
    private static final String[] EMAILS = new String[]
            {"Vasiliy@mail.ru", "Evgeniy@gmail.com", "Vya4eslav@yandex.ru", "Sergey@sibmail.com", "Matvey@yahoo.com", "Alexander@mail.ru", "Vladislav@mail.ru", "Svyatoslav@yandex.ru"};
    
    public static User randomGenerateUser()
    {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        String name = NAMES[random.nextInt(NAMES.length)];
        String email = EMAILS[random.nextInt(EMAILS.length)];
        
        
        int password = random.nextInt(100000, 999999);
        
        return new User.Builder().name(name).email(email).password(password).build();
    }
}
