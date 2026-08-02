package sorting;

import core.CustomLinkedList;
import core.User;

public class EvenSorting
{
    // метод sortByEvenPassword для сортировки пользователей с чёткими паролями
    public static void sortByEvenPassword(CustomLinkedList<User> list)
    {
        // проверяем если списка нет или в нём мало элементов, выходим
        if (list == null || list.size() < 2)
        {
            return;
        }

        // подсчёт сколько чётных пользователей в списке
        int evenCount = 0;
        // используем цикл for для прохода по кастомному списку
        for (User user : list)
        {
            // Проверяем на пустую ячейку и чётность пароля
            if (user != null && user.getPassword() % 2 == 0)
            {
                evenCount++;
            }
        }

        // если чётных элементов меньше двух сортировать нечего
        if (evenCount < 2)
        {
            return;
        }

        // создаем временный обычный массив Java для чётных пользователей
        User[] evenArray = new User[evenCount];
        int index = 0;
        // проход по списку чтобы скопировать чётных пользователей в массив
        for (User user : list)
        {
            if (user != null && user.getPassword() % 2 == 0)
            {
                evenArray[index] = user;
                index++;
            }
        }

        // запускаем быструю сортировку для массива чётных пользователей
        // 0 - начальный индекс массива, evenArray.length - 1 конечный индекс
        manualQuickSort(evenArray, 0, evenArray.length - 1);

        // создаем новый кастомный список для сборки итогового результата
        CustomLinkedList<User> resultList = new CustomLinkedList<>();
        int evenCounter = 0;

        // проход по исходному списку и расставляем элементы на свои места
        for (User currentUser : list)
        {
            if (currentUser != null && currentUser.getPassword() % 2 == 0)
            {
                // если пользователь чётный берём его из отсортированного массива
                resultList.add(evenArray[evenCounter]);
                evenCounter++;
            }
            else
            {
                // если нечётный или null, оставляем на своём старом месте
                resultList.add(currentUser);
            }
        }

        // очищаем старый кастомный список
        list.clear();
        // закидываем в него новые отсортированные данные из resultList
        list.addAll(resultList);
    }

    // метод быстрой сортировки по числовому паролю
    private static void manualQuickSort(User[] array, int low, int high)
    {
        // условие выхода если делить больше нечего
        if (low >= high)
        {
            return;
        }

        // ищем индекс середины массива и берём оттуда пароль как опорный элемент (pivot)
        int middle = low + (high - low) / 2;
        int pivot = array[middle].getPassword();

        // обьявляем левый и правый бегунки
        int i = low;
        int j = high;

        // цикл встречного движения бегунков
        while (i <= j)
        {
            // двигаем левый бегунок вправо, пока элементы меньше опорного
            while (array[i].getPassword() < pivot)
            {
                i++;
            }
            // двигаем правый бегунок влево, пока элементы больше опорного
            while (array[j].getPassword() > pivot)
            {
                j--;
            }
            // если бегунки встретились или зашли друг за друга делаем перемещение
            if (i <= j)
            {
                // меняем элементы местами через временную переменную temp
                User temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                // сдвигаем бегунки дальше, чтобы продолжить путь
                i++;
                j--;
            }
        }

        // если левая часть кусочка массива ещё не до конца разобрана запускаем вызов из самой себя для неё
        // если в левой подгруппе осталось больше одного элемента дробим дальше
        if (low < j)
        {
            manualQuickSort(array, low, j);
        }
        // если правая часть кусочка массива не разобрана запускаем вызов из самой себя для неё
        // если в правой подгруппе осталось больше одного элемента дробим дальше
        if (high > i)
        {
            manualQuickSort(array, i, high);
        }
    }
}
