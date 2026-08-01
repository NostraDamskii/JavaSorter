package menu;

import customCollection.FileWrite;
import user.User;
import user.UserManager;

import java.util.Scanner;

public class ExportToFileCommand extends BaseMenuCommand {

  public ExportToFileCommand(int actionCode, MenuConfig commandType) {
    super(actionCode, commandType);
  }

  @Override
  public void execute(Scanner scanner, UserManager userManager) {
    customCollection.CustomLinkedList<User> currentUsers = userManager.getUsers();
    if (currentUsers == null || currentUsers.size() == 0) {
      System.out.println("Ошибка: Массив пуст. Нечего сохранять.");
      return;
    }

    System.out.print("Введите имя файла для сохранения (например, output.txt): ");
    String fileName = scanner.nextLine();

    if (fileName.isBlank()) {
      System.out.println("Имя файла не может быть пустым.");
      return;
    }

    User[] usersArray = new User[currentUsers.size()];
    for (int i = 0; i < currentUsers.size(); i++) {
      usersArray[i] = currentUsers.get(i);
    }

    FileWrite.usersToFile(usersArray, fileName);
  }
}