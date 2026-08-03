package service;

import core.CustomLinkedList;
import core.DataConstants;
import core.User;

import java.util.Scanner;
import java.util.stream.Stream;

public class ConsoleFillStrategy implements UserFillStrategy {

  private final int count;
  private final Scanner scanner = new Scanner(System.in);
  public ConsoleFillStrategy(int count) {
    this.count = count;
  }

  @Override
  public void fill(CustomLinkedList<User> users) {
    System.out.println("Заполнение вручную (" + count + " пользователей):");

    CustomLinkedList<User> inputtedUsers = Stream.generate(()->readSingleUser(scanner))
        .limit(count)
        .collect(
            CustomLinkedList::new,
            CustomLinkedList::add,
            CustomLinkedList::addAll
        );
    users.addAll(inputtedUsers);
  }

  public static User readSingleUser(Scanner scanner) {
    String name = readName(scanner);
    int password = readPassword(scanner);
    String mail = readEmail(scanner);
    return new User.Builder()
        .name(name)
        .password(password)
        .email(mail)
        .build();
  }


  private static String readName(Scanner scanner) {
    while (true) {
      System.out.print("Введите имя: ");
      String name = scanner.nextLine();

      if (!name.isBlank()) {
        return name;
      }

      System.out.println("Ошибка! Имя не может быть пустым.");
    }
  }

  private static int readPassword(Scanner scanner) {
    int password;
    while (true) {
      System.out.print("Введите пароль (число): ");

      if (scanner.hasNextInt()) {

        password = scanner.nextInt();
        scanner.nextLine();

        if (DataConstants.isPasswordCorrect(password)) {
          return password;
        }

        System.out.println(DataConstants.PASSWORD_ERROR_TEMPLATE);
      } else {

        System.out.println("Ошибка! Введите целое число.");
        scanner.nextLine();
      }
    }
  }

  private static String readEmail(Scanner scanner) {
    String mail;
    while (true) {
      System.out.print("Введите e-mail: ");
      mail = scanner.nextLine();

      if (!mail.isBlank()
          && mail.contains("@")
          && mail.contains(".")) {
        return mail;
      }

      System.out.println("Ошибка! Некорректный e-mail.");
    }
  }
}