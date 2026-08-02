package menu;

import service.UserManager;
import java.util.Scanner;

public class ClearCommand extends BaseMenuCommand {

  public ClearCommand(int actionCode, MenuConfig commandType) {
    super(actionCode, commandType);
  }

  @Override
  public void execute(Scanner scanner, UserManager userManager) {
    if (userManager.isEmpty()) {
      System.out.println("Список пользователей уже пуст!");
      return;
    }

    System.out.print("Очистить массив? (y/n): ");

    if (scanner.nextLine().equalsIgnoreCase("y")) {
      userManager.clear();
      System.out.println("Массив очищен.");
    } else {
      System.out.println("Отменено.");
    }
  }
}