package tests;
import org.junit.jupiter.api.Assertions;
import src.UserManager;
import src.user.User;

class UserManagerTest {

    @org.junit.jupiter.api.Test
    void populateUsers() {
        // менеджер пользователей
        UserManager manager = new UserManager();
        // проверяем что метод выбрасывает ошибку IllegalStateException
        // стратегия  null
        Assertions.assertThrows(IllegalStateException.class, () -> {
            manager.populateUsers();
        }, "Метод должен упасть с ошибкой так как стратегия заполнения null");
    }

    @org.junit.jupiter.api.Test
    void clear() {
        // создаем менеджер пользователей
        UserManager manager = new UserManager();

        // добавляем тестового пользователя напрямую в список
        manager.getUsers().add(new User.Builder().name("Тест").password(111111).email("test@mail.ru").build());

        //вызываем метод очистки для тестирования
        manager.clear();

        // проверка результата после clear() размер списка должен стать 0
        int expectedSize = 0;
        int actualSize = manager.getUsers().size();

        Assertions.assertEquals(expectedSize, actualSize, "После вызова clear() список пользователей пуст");
    }
}