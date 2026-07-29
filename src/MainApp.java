package src;

import src.fillCollections.ConsoleFillStrategy;
import src.fillCollections.FileFillStrategy;
import src.fillCollections.RandomFillStrategy;
import src.sorting.UserSortStrategy;
import src.user.User;
import src.sorting.EvenSorting;
import java.util.Arrays;
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
        case 7       -> handleClear();
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
    System.out.println("6. Меню сортировки");
    System.out.println("7. Очистить текущий массив");
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

    List<User> currentUsers = userManager.getUsers();
    if (currentUsers == null || currentUsers.isEmpty()) {
      System.out.println("Ошибка: Массив пуст. Нечего сохранять.");
      return;
    }

    System.out.print("Введите имя файла для сохранения (например, output.txt): ");
    String fileName = scanner.nextLine();

    if (fileName.isBlank()) {
      System.out.println("Имя файла не может быть пустым.");
      return;
    }
    // Конвертируем List в массив для класса FileWrite
    User[] usersArray = currentUsers.toArray(new User[0]);

    // Вызываем метод записи
    FileWrite.usersToFile(usersArray, fileName);
  }


  private void handleSorting() {
    List<User> currentUsers = userManager.getUsers();
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

    // Конвертируем в массив для чётной сортировки (Временно либо на постоянку смотря как сделаем сортировку остальную)
    User[] arrayToSort = currentUsers.toArray(new User[0]);

    switch (choice) {
      case 1 -> System.out.println("Функция сортировки по имени (А-Я) пока не реализована.");
      case 2 -> System.out.println("Функция сортировки по имени (Я-А) пока не реализована.");
      case 3 -> System.out.println("Функция сортировки по email (А-Я) пока не реализована.");
      case 4 -> System.out.println("Функция сортировки по email (Я-А) пока не реализована.");
      case 5 -> System.out.println("Функция сортировки по паролю (возрастание) пока не реализована.");
      case 6 -> System.out.println("Функция сортировки по паролю (убывание) пока не реализова");
      // Для пунктов 1-6 нужно будет добавить сохранение если доделаем
      // Четная сортировка
      case 7 -> {
        System.out.println("Выполняется чётная сортировка...");
        EvenSorting.sortByEvenPassword(arrayToSort);
        // Сохраняем отсортированный массив обратно в менеджер через сеттер
        userManager.setUsers(Arrays.asList(arrayToSort));
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
    // добавил метод очистки массива из UserManager для кнопки в меню очистки
    private void handleClear() {
      System.out.print("Очистить массив? (y/n): ");

      if (scanner.nextLine().equalsIgnoreCase("y")) {
        userManager.clear();
        System.out.println("Массив очищен.");
      } else {
        System.out.println("Отменено.");
      }
    }
  }
