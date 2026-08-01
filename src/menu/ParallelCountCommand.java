package menu;

import user.User;
import user.UserManager;

import java.util.Scanner;

public class ParallelCountCommand extends BaseMenuCommand {

  public ParallelCountCommand(int actionCode, MenuConfig commandType) {
    super(actionCode, commandType);
  }

  @Override
  public void execute(Scanner scanner, UserManager userManager) {
    customCollection.CustomLinkedList<User> currentUsers = userManager.getUsers();
    if (currentUsers == null || currentUsers.size() == 0) {
      System.out.println("Список пользователей пуст. Заполните его!");
      return;
    }

    System.out.println("\n--- Введите данные пользователя для подсчета вхождений ---");
    System.out.print("Введите имя: ");
    String name = scanner.nextLine();

    System.out.print("Введите email: ");
    String email = scanner.nextLine();

    System.out.print("Введите пароль (число): ");
    while (!scanner.hasNextInt()) {
      System.out.println("Ошибка: Пароль должен быть целым числом!");
      System.out.print("Введите пароль (число): ");
      scanner.nextLine();
    }
    int password = scanner.nextInt();
    scanner.nextLine();

    User targetUser = new User.Builder()
        .name(name)
        .email(email)
        .password(password)
        .build();

    System.out.println("Запуск параллельного анализа коллекции через многопоточный Stream...");

    long startTime = System.nanoTime();
    long resultCount = currentUsers.parallelStream()
        .filter(targetUser::equals)
        .count();
    long endTime = System.nanoTime();

    System.out.println("=================================================================");
    System.out.printf(" РЕЗУЛЬТАТ МНОГОПОТОЧНОГО ПОДСЧЕТА:%n");
    System.out.printf(" Искомый объект   : %s%n", targetUser);
    System.out.printf(" Найдено вхождений: %d%n", resultCount);
    System.out.printf(" Время обработки  : %,d нс%n", (endTime - startTime));
    System.out.println("=================================================================");
  }
}
