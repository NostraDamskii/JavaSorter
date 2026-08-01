package fillCollections;


import customCollection.CustomLinkedList;
import user.User;

import java.util.Scanner;


public class ConsoleFillStrategy implements UserFillStrategy {

  private final int count;
  private final Scanner scanner = new Scanner(System.in);

  public ConsoleFillStrategy(int count) {
    this.count = count;
  }

  @Override
  public void fill(CustomLinkedList<User> users) {
    System.out.println("Заполнение вручную (" + count + " пользователей):");

    for (int i = 0; i < count; i++) {

      System.out.println("\nПользователь #" + (i + 1));

      // ===== Ввод имени =====
      String name;

      while (true) {
        System.out.print("Введите имя: ");
        name = scanner.nextLine();

        if (!name.isBlank()) {
          break;
        }

        System.out.println("Ошибка! Имя не может быть пустым.");
      }

      // ===== Ввод пароля =====
      int password;

      while (true) {
        System.out.print("Введите пароль (число): ");

        if (scanner.hasNextInt()) {

          password = scanner.nextInt();
          scanner.nextLine(); // очищаем символ Enter

          if (password > 0) {
            break;
          }

          System.out.println("Ошибка! Пароль должен быть больше 0.");

        } else {

          System.out.println("Ошибка! Введите целое число.");
          scanner.nextLine();
        }
      }

      // ===== Ввод e-mail =====
      String mail;

      while (true) {
        System.out.print("Введите e-mail: ");
        mail = scanner.nextLine();

        if (!mail.isBlank()
                && mail.contains("@")
                && mail.contains(".")) {
          break;
        }

        System.out.println("Ошибка! Некорректный e-mail.");
      }

      User user = new User.Builder()
              .name(name)
              .password(password)
              .email(mail)
              .build();

      users.add(user);
    }
  }
}