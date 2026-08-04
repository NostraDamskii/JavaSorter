package menu;

import service.UserManager;
import service.ConsoleFillStrategy;
import service.FileFillStrategy;
import service.RandomFillStrategy;
import java.util.Scanner;

public class FillStrategyCommand extends BaseMenuCommand {

  private final MenuConfig choice;

  public FillStrategyCommand(int actionCode, MenuConfig commandType) {
    super(actionCode, commandType);
    this.choice = commandType;
  }

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

    switch (choice) {
      case FILL_RANDOM -> userManager.setStrategy(new RandomFillStrategy(count));
      case FILL_CONSOLE -> userManager.setStrategy(new ConsoleFillStrategy(count));
      case FILL_FILE -> {
        System.out.print("Введите путь к файлу для чтения (например, input.txt): ");
        String filePath = scanner.nextLine();
        userManager.setStrategy(new FileFillStrategy(filePath, count));
      }
    }
    userManager.populateUsers();
  }
}