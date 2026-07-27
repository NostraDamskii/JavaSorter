package src.fillCollections;

import src.user.User;

import java.util.List;
import java.util.Scanner;

public class ConsoleFillStrategy implements UserFillStrategy {
  private final int count;
  private final Scanner scanner = new Scanner(System.in);

  public ConsoleFillStrategy(int count) {
    this.count = count;
  }

  @Override
  public void fill(List<User> users) {
    System.out.println("Заполнение вручную (" + count + " пользователей):");
    for (int i = 0; i < count; i++) {
      System.out.println("\nПользователь #" + (i + 1));
      System.out.print("Введите имя: ");
      String name = scanner.nextLine();

      System.out.print("Введите пароль (число): ");
      int password = scanner.nextInt();
      scanner.nextLine();

      System.out.print("Введите e-mail: ");
      String mail = scanner.nextLine();

      User user = new User.Builder().name(name).password(password).email(mail).build();
      users.add(user);
    }
  }
}
