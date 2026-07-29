package src.menu;

import java.util.Scanner;
import src.UserManager;

public class ClearCommand extends BaseMenuCommand {

  public ClearCommand(int actionCode, MenuConfig commandType) {
    super(actionCode, commandType);
  }

  @Override
  public void execute(Scanner scanner, UserManager userManager) {
    System.out.print("Очистить массив? (y/n): ");

    if (scanner.nextLine().equalsIgnoreCase("y")) {
      userManager.clear();
      System.out.println("Массив очищен.");
    } else {
      System.out.println("Отменено.");
    }
  }
}