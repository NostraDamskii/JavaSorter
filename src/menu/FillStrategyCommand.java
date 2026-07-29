package src.menu;

import src.UserManager;
import src.fillCollections.ConsoleFillStrategy;
import src.fillCollections.FileFillStrategy;
import src.fillCollections.RandomFillStrategy;
import java.util.Scanner;

public class FillStrategyCommand implements MenuCommand {
  private final int actionCode;
  private final String description;

  public FillStrategyCommand(int actionCode, String description) {
    this.actionCode = actionCode;
    this.description = description;
  }

  @Override public int getActionCode() { return actionCode; }
  @Override public String getDescription() { return description; }

  @Override
  public void execute(Scanner scanner, UserManager userManager) {
    System.out.print("Введите желаемую длину массива (количество пользователей): ");
    while (!scanner.hasNextInt()) {
      System.out.println("Ошибка: Длина должна быть целым числом!");
      System.out.print("Повторите ввод: ");
      scanner.nextLine();
    }
    int count = scanner.nextInt();
    scanner.nextLine();

    if (count <= 0) {
      System.out.println("Предупреждение: Длина массива должна быть больше 0. Возврат в меню.");
      return;
    }

    userManager.clear();

    switch (actionCode) {
      case 1 -> userManager.setStrategy(new RandomFillStrategy(count));
      case 2 -> userManager.setStrategy(new ConsoleFillStrategy(count));
      case 3 -> {
        System.out.print("Введите путь к файлу для чтения (например, input.txt): ");
        String filePath = scanner.nextLine();
        userManager.setStrategy(new FileFillStrategy(filePath, count));
      }
    }
    userManager.populateUsers();
  }
}
