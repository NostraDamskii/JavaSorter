package src;

import java.util.List;
import java.util.Scanner;

public class MainApp {
  private final Scanner scanner = new Scanner(System.in);
  private final UserManager userManager = new UserManager();

  public static void main(String[] args) {
    MainApp app = new MainApp();
    app.startApp();
  }

  public void startApp() {
    while (true) {
      printMenu();

      if (!scanner.hasNextInt()) {
        System.out.println("Ошибка: Введите число от 0 до 6!");
        scanner.nextLine();
        continue;
      }

      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 0 -> {
          System.out.println("Программа завершена. До свидания!");
          scanner.close();
          return;
        }
        case 1, 2, 3 -> handleFillStrategy(choice);
        case 4       -> handleDisplayUsers();
        case 5       -> handleExportToFile();
        case 6       -> handleSorting();
        default      -> System.out.println("Неверный выбор. Пожалуйста, выберите пункт от 0 до 6.");
      }
    }
  }

  private void printMenu() {
    System.out.println("\n================= ГЛАВНОЕ МЕНЮ =================");
    System.out.println("1. Заполнить массив случайными пользователями");
    System.out.println("2. Заполнить массив вручную (через консоль)");
    System.out.println("3. Заполнить массив из файла");
    System.out.println("4. Вывести полученный массив на экран");
    System.out.println("5. Сохранить текущий массив в файл");
    System.out.println("6. Отсортировать массив по полям");
    System.out.println("0. Выйти из программы");
    System.out.print("Выберите действие: ");
  }

  private void handleFillStrategy(int choice) {
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

  private void handleDisplayUsers() {
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

  private void handleExportToFile() {

  }

  private void handleSorting() {

  }
}