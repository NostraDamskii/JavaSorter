package src.sorting;

import src.CustomLinkedList;
import src.user.User;

public class EvenSorting {

    public static void sortByEvenPassword(CustomLinkedList<User> list) {
        // Проверяем, если списка нет или в нём мало элементов
        if (list == null || list.size() < 2) {
            return;
        }

        // Подсчёт сколько чётных пользователей
        int evenCount = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPassword() % 2 == 0) {
                evenCount++;
            }
        }

        // Если чётных элементов меньше двух, сортировать нечего
        if (evenCount < 2) {
            return;
        }

        // Создаем временный кастомный список исключительно для чётных пользователей
        CustomLinkedList<User> evenUsers = new CustomLinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getPassword() % 2 == 0) {
                evenUsers.add(user);
            }
        }

        // Сортировка вставками внутри кастомного списка чётных пользователей
        for (int i = 1; i < evenUsers.size(); i++) {
            User current = evenUsers.get(i);
            int j = i - 1;

            // Ищем правильную позицию для текущего элемента
            while (j >= 0 && evenUsers.get(j).getPassword() > current.getPassword()) {
                j--;
            }

            // Перемещаем элемент на его новую позицию
            evenUsers.remove(i);
            evenUsers.insert(j + 1, current);
        }

        // Возвращаем отсортированных чётных пользователей обратно в исходный список
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPassword() % 2 == 0) {
                User sortedUser = evenUsers.get(counter);

                // Заменяем старый элемент на новый отсортированный
                list.remove(i);
                list.insert(i, sortedUser);

                counter++;
            }
        }
    }
}
