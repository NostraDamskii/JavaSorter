package service;

import core.CustomLinkedList;
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

    CustomLinkedList<User> inputtedUsers = Stream.generate(this::readSingleUser)
        .limit(count)
        .collect(
            CustomLinkedList::new,
            CustomLinkedList::add,
            CustomLinkedList::addAll
        );
    users.addAll(inputtedUsers);
  }

  private User readSingleUser() {
    String name = readName();
    int password = readPassword();
    String mail = readEmail();
    return new User.Builder()
        .name(name)
        .password(password)
        .email(mail)
        .build();
  }


  private String readName() {
    while (true) {
      System.out.print("Введите имя: ");
      String name = scanner.nextLine();

      if (!name.isBlank()) {
        return name;
      }

      System.out.println("Ошибка! Имя не может быть пустым.");
    }
  }

  private int readPassword() {
    int password;
    while (true) {
      System.out.print("Введите пароль (число): ");

      if (scanner.hasNextInt()) {

        password = scanner.nextInt();
        scanner.nextLine();

        if (password > 1000000 && password < 9999999) {
          return password;
        }

        System.out.println("Ошибка! Пароль должен быть больше 0.");
      } else {

        System.out.println("Ошибка! Введите целое число.");
        scanner.nextLine();
      }
    }
  }

  private String readEmail() {
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