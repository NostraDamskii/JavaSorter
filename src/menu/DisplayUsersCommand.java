package src.menu;

import src.UserManager;
import src.user.User;
import java.util.List;
import java.util.Scanner;

public class DisplayUsersCommand implements MenuCommand {
  @Override public int getActionCode() { return 4; }
  @Override public String getDescription() { return "Вывести полученный массив на экран"; }

  @Override
  public void execute(Scanner scanner, UserManager userManager) {
    System.out.println("\n--- ТЕКУЩИЙ МАССИВ ПОЛЬЗОВАТЕЛЕЙ ---");
    List<User> currentUsers = userManager.getUsers();
    if (currentUsers.isEmpty()) {
      System.out.println("Ошибка: Массив пуст. Сначала заполните его (пункты 1-3).");
      return;
    }
    System.out.println("Всего элементов в массиве: " + currentUsers.size());
    for (int i = 0; i < currentUsers.size(); i++) {
      System.out.println("[" + (i + 1) + "] " + currentUsers.get(i));
    }
    System.out.println("----------------------------------------");
  }
}
