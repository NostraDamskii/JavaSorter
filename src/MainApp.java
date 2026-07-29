package src;

import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import src.menu.ClearCommand;
import src.menu.DisplayUsersCommand;
import src.menu.ExportToFileCommand;
import src.menu.FillStrategyCommand;
import src.menu.MenuCommand;
import src.menu.MenuConfig;
import src.menu.ParallelCountCommand;
import src.menu.SortingCommand;

public class MainApp {
  private final Scanner scanner = new Scanner(System.in);
  private final UserManager userManager = new UserManager();

  private final Map<Integer, MenuCommand> menuCommands = new TreeMap<>();

  public MainApp() {
    registerCommand(new FillStrategyCommand(1, MenuConfig.FILL_RANDOM));
    registerCommand(new FillStrategyCommand(2, MenuConfig.FILL_CONSOLE));
    registerCommand(new FillStrategyCommand(3, MenuConfig.FILL_FILE));
    registerCommand(new DisplayUsersCommand(4, MenuConfig.DISPLAY));
    registerCommand(new ExportToFileCommand(5, MenuConfig.EXPORT));
    registerCommand(new SortingCommand(6, MenuConfig.SORT));
    registerCommand(new ClearCommand(7, MenuConfig.CLEAR));
    registerCommand(new ParallelCountCommand(8, MenuConfig.PARALLEL_COUNT));
  }

  private void registerCommand(MenuCommand command) {
    menuCommands.put(command.getActionCode(), command);
  }

  public static void main(String[] args) {
    MainApp app = new MainApp();
    app.startApp();
  }

  public void startApp() {
    while (true) {
      printMenu();

      if (!scanner.hasNextInt()) {
        System.out.println("Ошибка: Введите корректное число из меню!");
        scanner.nextLine();
        continue;
      }

      int choice = scanner.nextInt();
      scanner.nextLine();

      if (choice == 0) {
        System.out.println("Программа завершена. До свидания!");
        scanner.close();
        return;
      }

      MenuCommand command = menuCommands.get(choice);
      if (command != null) {
        command.execute(scanner, userManager);
      } else {
        System.out.println("Неверный выбор. Пожалуйста, выберите существующий пункт.");
      }
    }
  }

  private void printMenu() {
    System.out.println("\n================= ГЛАВНОЕ МЕНЮ =================");

    menuCommands.forEach((code, command) -> {
      System.out.println(code + ". " + command.getDescription());
    });

    System.out.println("0. Выйти из программы");
    System.out.print("Выберите действие: ");
  }
}