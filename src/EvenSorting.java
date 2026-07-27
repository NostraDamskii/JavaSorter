package src;

public class EvenSorting {

    public static void sortByEvenPassword(User[] array) {
        // Проверяем если массива нет или в нём мало элементов
        if (array == null || array.length < 2) {
            return;
        }
        // Подсчёт сколько чётных пользователей
        int evenCount = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i].getPassword() % 2 == 0)
            {
                evenCount++; // Запоминаем каждого чётного пользователя
            }
        }
        // Если чётных элементов массива нет или он 1 завершаем метод
        if (evenCount < 2) {
            return;
        }
        // Создаем массив для чётных пользователей, обычный массив фиксированной длины под количество чётных пользователей
        User[] evenUsers = new User[evenCount];
        int index = 0; // Индекс для заполнения нового массива

        for (int i = 0; i < array.length; i++) {
            // С помощью % берём и делим пароль на 2 остаток 0
            if (array[i].getPassword() % 2 == 0) {
                evenUsers[index] = array[i]; // Копируем чётного пользователя если остаток 0
                index++;                     // Сдвигаем индекс для следующего
            }
        }
        // Сортируем массив чётных пользователей
        // Цикл сколько раз пройти по массиву
        for (int i = 0; i < evenUsers.length - 1; i++)
        {
            // Цикл сравнивая, сравнивает (j) со следующим соседом (j + 1).
            for (int j = 0; j < evenUsers.length - 1 - i; j++)
            {
                // Проверка если пароль левого пользователя больше чем у правого
                if (evenUsers[j].getPassword() > evenUsers[j + 1].getPassword())
                {
                    // замена местами используя временную перменную temp
                    User temp = evenUsers[j];
                    evenUsers[j] = evenUsers[j + 1];
                    evenUsers[j + 1] = temp;
                }
            }
        }
        // Заполняем отсортированных пользователей обратно
        int counter = 0; // Счётчик который будет по порядку брать людей из evenUsers
        for (int i = 0; i < array.length; i++)
        {
            // Проверка если в исходном массиве на этом месте стоял чётный пользователь
            if (array[i].getPassword() % 2 == 0)
            {
                // заменяем на отсортированного из текущего массива
                array[i] = evenUsers[counter];
                counter++; // переход к следующему отсортированному пользователю
            }
        }
    }
}
