import java.util.Random;
public class RandomGen
{
    private static final String[] NAMES = new String[]
            {"Василий", "Олег", "Евгений", "Вячеслав", "Сергей", "Матвей", "Александр", "Владислав", "Святослав"};
    private static final String[] PASSWORDS = new String[]
            {"Kjdawd2L", "W23ahudw", "WDAJ2Sdkna", "djwad2SDK", "WDJAsdaw32", "NSDBA2a32", "dawhda234s", "WDJAWJDsda23", "dwahduauhdw42"};
    private static final String[] EMAILS = new String[]
            {"Vasiliy@mail.ru", "Evgeniy@gmail.com", "Vya4eslav@yandex.ru", "Sergey@sibmail.com", "Matvey@yahoo.com", "Alexander@mail.ru", "Vladislav@mail.ru", "Svyatoslav@yandex.ru"};
    private static final Random RANDOM = new Random();

    public static User randomGenerateUser()
    {
        String name = NAMES[RANDOM.nextInt(NAMES.length)];
        String email = EMAILS[RANDOM.nextInt(EMAILS.length)];
        String password = PASSWORDS[RANDOM.nextInt(PASSWORDS.length)];

        return new User(name, email, password);
    }

    public static void main(String[] args)
    {
        User testUser = randomGenerateUser();

        System.out.println("имя: " + testUser.getName());
        System.out.println("email: " + testUser.getEmail());
        System.out.println("пароль: " + testUser.getPassword());
    }
}
