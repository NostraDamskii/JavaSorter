package tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import fillCollections.RandomGen;
import user.User;
import java.util.Arrays;
import java.util.List;

class RandomGenTest {

    @Test
    void testRandomGenerateUser() {
        // запускаем метод генерации одного случайного пользователя
        User user = RandomGen.randomGenerateUser();

        // проверка что объект успешно создался и не равен null
        Assertions.assertNotNull(user, "сгенерированный пользователь не должен быть null");

        // проверка что имя пользователя взято из массива NAMES
        List<String> validNames = Arrays.asList(
                "Василий", "Олег", "Евгений", "Вячеслав", "Сергей", "Матвей", "Александр", "Владислав", "Святослав"
        );
        Assertions.assertTrue(validNames.contains(user.getName()),
                "имя пользователя должно быть выбрано только из списка NAMES");

        // проверка что email пользователя взят из массива EMAILS
        List<String> validEmails = Arrays.asList(
                "Vasiliy@mail.ru", "Evgeniy@gmail.com", "Vya4eslav@yandex.ru",
                "Sergey@sibmail.com", "Matvey@yahoo.com", "Alexander@mail.ru",
                "Vladislav@mail.ru", "Svyatoslav@yandex.ru"
        );
        Assertions.assertTrue(validEmails.contains(user.getEmail()),
                "email пользователя должен быть выбран только из списка EMAILS");

        // проверка пароля на соответствие
        int password = user.getPassword();
        Assertions.assertTrue(password >= 100000 && password < 999999,
                "пароль должен быть 6-значным (от 100000 до 999998) текущий пароль: " + password);
    }
}
