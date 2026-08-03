package base;

import core.CustomLinkedList;
import service.RandomGen;
import sorting.EvenSorting;
import core.User;


public class SortTest {

    public static void main(String[] args) {
        
        CustomLinkedList<User> users = new CustomLinkedList<>();

        
        for (int i = 0; i < 5; i++) {
            users.add(RandomGen.randomGenerateUser());
        }

        
        System.out.println("\nСписок");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("индекс [" + i + "] имя: " + users.get(i).getName()
                + " | пароль: " + users.get(i).getPassword());
        }

        
        EvenSorting.sortByEvenPassword(users);

        
        System.out.println("\nСписок после чётной сортировки");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("индекс [" + i + "] имя: " + users.get(i).getName()
                + " | пароль: " + users.get(i).getPassword());
        }
    }
}
