package menu;

import core.CustomLinkedList;
import service.UserManager;
import core.User;
import java.util.Scanner;

public class ParallelCountCommand extends BaseMenuCommand {

  public ParallelCountCommand(int actionCode, MenuConfig commandType) {
    super(actionCode, commandType);
  }

  @Override
  public void execute(Scanner scanner, UserManager userManager) {
    CustomLinkedList<User> users = userManager.getUsers();
    if (checkData(users)) {
      return;
    }
    User targetUser = getUser(scanner);
    long startTime = System.nanoTime();
    long resultCount = search(targetUser, scanner, users);
    long endTime = System.nanoTime();
  }

  private boolean checkData(CustomLinkedList<User> users) {
    if (users == null || users.isEmpty()) {
      System.out.println("Список пользователей пуст. Заполните его!");
      return false;
    }
    return true;
  }

  private void printResult(String targetUser, int resultCount, int startTime, int endTime) {
    System.out.println("=================================================================");
    System.out.printf(" РЕЗУЛЬТАТ МНОГОПОТОЧНОГО ПОДСЧЕТА:%n");
    System.out.printf(" Искомый объект   : %s%n", targetUser);
    System.out.printf(" Найдено вхождений: %d%n", resultCount);
    System.out.printf(" Время обработки  : %,d нс%n", (endTime - startTime));
    System.out.println("=================================================================");
  }

  private long search(User targetUser, Scanner scanner, CustomLinkedList<User> users) {
    return users.parallelStream()
        .filter(targetUser::equals)
        .count();
  }

  private User getUser(Scanner scanner) {
    System.out.println("\n--- Введите данные пользователя для подсчета вхождений ---");
    System.out.print("Введите имя: ");
    String name = scanner.nextLine();

    System.out.print("Введите email: ");
    String email = scanner.nextLine();

    System.out.print("Введите пароль (число): ");
    int password =0;
    while (!scanner.hasNextInt()) {
      System.out.println("Ошибка: Пароль должен быть целым числом!");
      System.out.print("Введите пароль (число): ");
      scanner.nextLine();

      password = scanner.nextInt();
      scanner.nextLine();
    }
    return new User.Builder()
        .name(name)
        .email(email)
        .password(password)
        .build();
  }
}


