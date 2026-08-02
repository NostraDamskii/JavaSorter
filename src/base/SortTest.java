package base;

import core.CustomLinkedList;
import service.RandomGen;
import sorting.EvenSorting;
import core.User;

// Класс проверки чётной сортировки
public class SortTest {

    public static void main(String[] args) {
        // Создаем кастомный список пользователей
        CustomLinkedList<User> users = new CustomLinkedList<>();

        // Вызов RandomGen чтобы заполнить список случайными людьми
        for (int i = 0; i < 5; i++) {
            users.add(RandomGen.randomGenerateUser());
        }

        //Вывод пользователей в консоль до сортировки
        System.out.println("\nСписок");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("индекс [" + i + "] имя: " + users.get(i).getName()
                + " | пароль: " + users.get(i).getPassword());
        }

        // Вызов метода из класса EvenSorting для выполнения чётной сортировки
        EvenSorting.sortByEvenPassword(users);

        // Вывод пользователей после сортировки
        System.out.println("\nСписок после чётной сортировки");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("индекс [" + i + "] имя: " + users.get(i).getName()
                + " | пароль: " + users.get(i).getPassword());
        }
    }
}
