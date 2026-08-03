
import service.FileWrite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import core.User;

class FileWriteTest {

    private static final String TEST_FILE = "test_users_db.txt";

    @Test
    void usersToFile() throws IOException {
        
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }

        
        User[] users = new User[4];
        users[0] = new User.Builder().name("Василий").password(123456).email("vas@mail.ru").build();
        users[1] = null; 
        users[2] = new User.Builder().name("Олег").password(654321).email("oleg@mail.ru").build();
        users[3] = null; 

        
        FileWrite.usersToFile(users, TEST_FILE);

        
        
        Assertions.assertTrue(file.exists(), "файл должен быть успешно создан на диске");

        
        List<String> lines = Files.readAllLines(Path.of(TEST_FILE));
        Assertions.assertEquals(2, lines.size(), "в файле должно быть ровно 2 строки так как null-элементы должны быть пропущены");

        
        String expectedFirstLine = "Василий;123456;vas@mail.ru";
        Assertions.assertEquals(expectedFirstLine, lines.get(0), "формат записи первой строки не совпадает с ожидаемым");

        
        file.delete();
    }

    @Test
    void usersToFileWithNullArray() {
        
        Assertions.assertDoesNotThrow(() -> {
            FileWrite.usersToFile(null, "should_not_exist.txt");
        }, "метод упал с ошибкой при передаче null вместо массива");

        
        File file = new File("should_not_exist.txt");
        Assertions.assertFalse(file.exists(), "файл не должен создаваться если передан null");
    }
}
