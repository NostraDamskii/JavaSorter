
import core.CustomLinkedList;
import core.User;
import service.FileFillStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class FileFillStrategyTest
{
    private static final String TEST_FILE = "test_import_users.txt";

    @Test
    void testSuccessfulFileLoad() throws IOException
    {
        // создание файла с корректной строкой
        File file = new File(TEST_FILE);
        try (PrintWriter writer = new PrintWriter(new FileWriter(file)))
        {
            writer.println("Василий;123456;vas@mail.ru");
        }

        CustomLinkedList<User> list = new CustomLinkedList<>();
        // загружаем только 1 пользователя
        FileFillStrategy strategy = new FileFillStrategy(TEST_FILE, 1);

        // читаем файл
        strategy.fill(list);

        // проверка что данные считались правильно
        Assertions.assertEquals(1, list.size(), "Должен загрузиться 1 пользователь");
        Assertions.assertEquals("Василий", list.get(0).getName());
        Assertions.assertEquals(123456, list.get(0).getPassword());
        Assertions.assertEquals("vas@mail.ru", list.get(0).getEmail());

        // чистим за собой
        file.delete();
    }

    @Test
    void testFileLoadWithInvalidData() throws IOException
    {
        // проверка валидации - пишем битую строку всего 2 поля вместо 3
        File file = new File(TEST_FILE);
        try (PrintWriter writer = new PrintWriter(new FileWriter(file)))
        {
            writer.println("СломанныйЮзер;654321");
        }

        CustomLinkedList<User> list = new CustomLinkedList<>();
        FileFillStrategy strategy = new FileFillStrategy(TEST_FILE, 1);

        // метод parseUser выбросит IllegalArgumentException но сам метод fill()
        // перехватывает его внутри catch и не дает программе упасть - проверяем именно это
        Assertions.assertDoesNotThrow(() -> {
            strategy.fill(list);
        }, "Метод fill() должен был поймать ошибку парсинга внутри catch и не падать!");

        // список должен остаться пустым так как юзер не прошел валидацию
        Assertions.assertTrue(list.isEmpty(), "Список должен быть пустым из-за некорректных данных в файле");

        file.delete();
    }
}
