package service;

import core.User;
// Импорт FileWriter для открытия файла
import java.io.FileWriter;
import java.io.IOException;
//Импорт PrintWriter для удобной записи в файл.
import java.io.PrintWriter;
public class FileWrite
{
    // Метод UsersToFile для записи массива пользователей в файл
    public static void usersToFile(User[] array, String fileName)
    {
        // Проверяем если массива нет выходим
        if (array == null)
        {
            return;
        }
        // Открываем файл с помощью встроенного класса Java FileWriter, если файл существует дозаписать в него (true указывает на это).
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             PrintWriter printWriter = new PrintWriter(fileWriter))
        {
            // проход циклом по всем пользователям в массиве
            for (int i = 0; i < array.length; i++)
            {
                User user = array[i];
                //проверка на пустые ячейки
                if (user == null)
                {
                    continue;
                }
                // собираем строку точно в таком же формате как в parseuser
                // порядок Имя;Пароль;Почта
                String textLine = user.getName() + ";" + user.getPassword() + ";" + user.getEmail();
                // Записываем эту строчку в файл с помощью printWriter
                printWriter.println(textLine);
            }
            System.out.println("сохранено в файл: " + fileName);
        }
        catch (IOException e)
        {
            System.out.println("ошибка не удалось записать: " + e.getMessage());
        }
    }
}
