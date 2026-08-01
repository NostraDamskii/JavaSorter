package menu;

import sorting.EvenSorting;
import sorting.UserSortStrategy;
import sorting.UserSorter;
import user.User;
import user.UserManager;

import java.util.Scanner;

public class SortingCommand extends BaseMenuCommand {

  public SortingCommand(int actionCode, MenuConfig commandType) {
    super(actionCode, commandType);
  }

  @Override
  public void execute(Scanner scanner, UserManager userManager) {
    customCollection.CustomLinkedList<User> currentUsers = userManager.getUsers();
    if (currentUsers == null || currentUsers.isEmpty()) {
      System.out.println("Ошибка: Массив пуст. Нечего сортировать.");
      return;
    }
    System.out.println("\n--- МЕНЮ СОРТИРОВКИ ---");
    System.out.println("1. Сортировать по Имени (А-Я) (НУЖНО СДЕЛАТЬ)");
    System.out.println("2. Сортировать по Имени (Я-А) (НУЖНО СДЕЛАТЬ)");
    System.out.println("3. Сортировать по Email (А-Я) (НУЖНО СДЕЛАТЬ)");
    System.out.println("4. Сортировать по Email (Я-А) (НУЖНО СДЕЛАТЬ)");
    System.out.println("5. Сортировать по Паролю (Возрастание) (НУЖНО СДЕЛАТЬ)");
    System.out.println("6. Сортировать по Паролю (Убывание) (НУЖНО СДЕЛАТЬ)");
    System.out.println("7. ЧЁТНАЯ СОРТИРОВКА (чётные по паролю — сортируются, нечётные — на местах)");
    System.out.println("8. Назад");
    System.out.print("Выберите тип сортировки: ");

    if (!scanner.hasNextInt()) {
      scanner.nextLine();
      return;
    }
    int choice = scanner.nextInt();
    scanner.nextLine();

    if (choice == 8) return;

    UserSorter sorter = new UserSorter();

    switch (choice) {
      case 1 -> {
        System.out.println("Выполняется сортировка по имени (А-Я)...");
        userManager.setUsers(sorter.sort(currentUsers, UserSortStrategy.BY_NAME_ASC));
      }
      case 2 -> {
        System.out.println("Выполняется сортировка по имени (Я-А)...");
        userManager.setUsers(sorter.sort(currentUsers, UserSortStrategy.BY_NAME_DESC));
      }
      case 3 -> {
        System.out.println("Выполняется сортировка по email (А-Я)...");
        userManager.setUsers(sorter.sort(currentUsers, UserSortStrategy.BY_EMAIL_ASC));
      }
      case 4 -> {
        System.out.println("Выполняется сортировка по email (Я-А)...");
        userManager.setUsers(sorter.sort(currentUsers, UserSortStrategy.BY_EMAIL_DESC));
      }
      case 5 -> {
        System.out.println("Выполняется сортировка по паролю (возрастание)...");
        userManager.setUsers(sorter.sort(currentUsers, UserSortStrategy.BY_PASSWORD_ASC));
      }
      case 6 -> {
        System.out.println("Выполняется сортировка по паролю (убывание)...");
        userManager.setUsers(sorter.sort(currentUsers, UserSortStrategy.BY_PASSWORD_DESC));
      }
      case 7 -> {
        System.out.println("Выполняется чётная сортировка...");
        // Передаем кастомный список напрямую в обновленный метод
        EvenSorting.sortByEvenPassword(currentUsers);
      }
      default -> {
        System.out.println("Неверный выбор.");
        return;
      }
    }

    if (choice >= 1 && choice <= 7) {
      System.out.println("Сортировка завершена. Используйте пункт 4 для просмотра или пункт 5 для сохранения.");
    }
  }
}