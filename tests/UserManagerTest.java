
import service.UserManager;
import org.junit.jupiter.api.Assertions;
import core.User;

class UserManagerTest {

    @org.junit.jupiter.api.Test
    void populateUsers() {
        
        UserManager manager = new UserManager();
        
        
        Assertions.assertThrows(IllegalStateException.class, manager::populateUsers, "Метод должен упасть с ошибкой так как стратегия заполнения null");
    }

    @org.junit.jupiter.api.Test
    void clear() {
        
        UserManager manager = new UserManager();

        
        manager.getUsers().add(new User.Builder().name("Тест").password(111111).email("test@mail.ru").build());

        
        manager.clear();

        
        int expectedSize = 0;
        int actualSize = manager.getUsers().size();

        Assertions.assertEquals(expectedSize, actualSize, "После вызова clear() список пользователей пуст");
    }
}