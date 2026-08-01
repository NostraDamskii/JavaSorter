package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.FileWrite;
import src.user.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class FileWriteTest {

    private static final String TEST_FILE = "test_users_db.txt";

    @Test
    void usersToFile() throws IOException {
        // проверка что старого файла теста нет
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }

        // создаем массив пользователей в котором есть пропуски null
        User[] users = new User[4];
        users[0] = new User.Builder().name("Василий").password(123456).email("vas@mail.ru").build();
        users[1] = null; // эту ячейку код должен пропустить
        users[2] = new User.Builder().name("Олег").password(654321).email("oleg@mail.ru").build();
        users[3] = null; // эту тоже пропускаем

        // вызываем метод записи в файл
        FileWrite.usersToFile(users, TEST_FILE);

        // проверяем результаты
        // 1 файл вообще создался?
        Assertions.assertTrue(file.exists(), "файл должен быть успешно создан на диске");

        // проверка 1 считываем строки из файла и проверяем, что записалось ровно 2 человека (без null)
        List<String> lines = Files.readAllLines(Path.of(TEST_FILE));
        Assertions.assertEquals(2, lines.size(), "в файле должно быть ровно 2 строки так как null-элементы должны быть пропущены");

        // проверка 2 правильность формата записи первой строки
        String expectedFirstLine = "Василий;123456;vas@mail.ru";
        Assertions.assertEquals(expectedFirstLine, lines.get(0), "формат записи первой строки не совпадает с ожидаемым");

        // чистим удаляем тестовый файл
        file.delete();
    }

    @Test
    void usersToFileWithNullArray() {
        // проверяем первую строку метода if (array == null) return
        Assertions.assertDoesNotThrow(() -> {
            FileWrite.usersToFile(null, "should_not_exist.txt");
        }, "метод упал с ошибкой при передаче null вместо массива");

        // проверка что файл не создался
        File file = new File("should_not_exist.txt");
        Assertions.assertFalse(file.exists(), "файл не должен создаваться если передан null");
    }
}
