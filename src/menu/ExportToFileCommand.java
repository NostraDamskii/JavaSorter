package src.menu;

import src.UserManager;
import src.user.User;
import src.FileWrite;
import java.util.List;
import java.util.Scanner;

public class ExportToFileCommand implements MenuCommand {
  @Override public int getActionCode() { return 5; }
  @Override public String getDescription() { return "Сохранить текущий массив в файл"; }

  @Override
  public void execute(Scanner scanner, UserManager userManager) {
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
    User[] usersArray = currentUsers.toArray(new User[0]);
    FileWrite.usersToFile(usersArray, fileName);
  }
}