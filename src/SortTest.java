package src;

// Класс проверки чётной сортировки
public class SortTest {

    public static void main(String[] args) {
        // Создаем массив куда поместим 5 пользователей
        User[] users = new User[5];
        // Вызов RandomGen чтобы заполнить массив случайными людьми
        for (int i = 0; i < users.length; i++) {
            users[i] = RandomGen.randomGenerateUser();
        }
        //Вывод пользователей в консоль до сортировки
        System.out.println("\nСписок");
        for (int i = 0; i < users.length; i++) {
            System.out.println("индекс [" + i + "] имя: " + users[i].getName()
                    + " | пароль: " + users[i].getPassword());
        }
        // Вызыв метода из класса EvenSorting для выполнения чётной сортировки
        EvenSorting.sortByEvenPassword(users);
        // Вывод пользолателей после сортировки
        System.out.println("\nСписок после чётной сортировки");
        for (int i = 0; i < users.length; i++) {
            System.out.println("индекс [" + i + "] имя: " + users[i].getName()
                    + " | пароль: " + users[i].getPassword());
        }
    }
}
