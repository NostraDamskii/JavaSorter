package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.CustomLinkedList;
import src.sorting.EvenSorting;
import src.user.User;

class EvenSortingTest
{
    @Test
    void testSortByEvenPassword()
    {
        // создаем кастомный связанный список
        CustomLinkedList<User> list = new CustomLinkedList<>();

        // тестовые пользователи с разными паролями
        User u1 = new User.Builder().name("Анна").password(555555).email("anna@mail.ru").build();  // Нечётный (индекс 0)
        User u2 = new User.Builder().name("Иван").password(444444).email("ivan@mail.ru").build();  // Чётный большой (индекс 1)
        User u3 = new User.Builder().name("Олег").password(222222).email("oleg@mail.ru").build();  // Чётный маленький (индекс 2)
        User u4 = new User.Builder().name("Яна").password(111111).email("yana@mail.ru").build();   // Нечётный (индекс 3)

        // заполняем пользователей в кастомный список
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);

        // запускаем сортировку чётных паролей
        EvenSorting.sortByEvenPassword(list);

        // проверка что нечётные пользователи остались стоять железно на своих местах
        Assertions.assertEquals(555555, list.get(0).getPassword(),
                "Нечётный пароль на индексе 0 не должен был сдвинуться!");

        Assertions.assertEquals(111111, list.get(3).getPassword(),
                "Нечётный пароль на индексе 3 должен остаться на месте!");

        // проверка что чётные пользователи успешно поменялись местами по возрастанию
        // менбший чётный пароль (222222) должен был встать на индекс 1 вместо большего чётного (444444)
        Assertions.assertEquals(222222, list.get(1).getPassword(),
                "Чётный пароль 222222 должен был сдвинуться вперёд на индекс 1!");

        Assertions.assertEquals(444444, list.get(2).getPassword(),
                "Чётный пароль 444444 должен был сместиться назад на индекс 2!");
    }

    @Test
    void testSortByEvenPasswordWithNullAndEmpty()
    {
        // дополнительный тест на экстремальные условия пустой список или null
        // метод не должен падать с ошибками
        Assertions.assertDoesNotThrow(() -> {
            EvenSorting.sortByEvenPassword(null);
        }, "метод упал при передаче null вместо списка!");

        CustomLinkedList<User> emptyList = new CustomLinkedList<>();
        Assertions.assertDoesNotThrow(() -> {
            EvenSorting.sortByEvenPassword(emptyList);
        }, "метод упал при передаче пустого списка!");
    }
}
