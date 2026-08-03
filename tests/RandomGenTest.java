
import service.RandomGen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import core.User;

class RandomGenTest {

    @Test
    void testRandomGenerateUser() {
        
        User user = RandomGen.randomGenerateUser();

        
        Assertions.assertNotNull(user, "сгенерированный пользователь не должен быть null");

        
        List<String> validNames = Arrays.asList(
                "Василий", "Олег", "Евгений", "Вячеслав", "Сергей", "Матвей", "Александр", "Владислав", "Святослав"
        );
        Assertions.assertTrue(validNames.contains(user.getName()),
                "имя пользователя должно быть выбрано только из списка NAMES");

        
        List<String> validEmails = Arrays.asList(
                "Vasiliy@mail.ru", "Evgeniy@gmail.com", "Vya4eslav@yandex.ru",
                "Sergey@sibmail.com", "Matvey@yahoo.com", "Alexander@mail.ru",
                "Vladislav@mail.ru", "Svyatoslav@yandex.ru"
        );
        Assertions.assertTrue(validEmails.contains(user.getEmail()),
                "email пользователя должен быть выбран только из списка EMAILS");

        
        int password = user.getPassword();
        Assertions.assertTrue(password >= 100000 && password < 999999,
                "пароль должен быть 6-значным (от 100000 до 999998) текущий пароль: " + password);
    }
}
