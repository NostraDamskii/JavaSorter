package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileFillStrategy implements UserFillStrategy {
  private final String filePath;
  private final int count;

  public FileFillStrategy(String filePath, int count) {
    this.filePath = filePath;
    this.count = count;
  }

  @Override
  public void fill(List<User> users) {
    int added = 0;
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      int lineNumber = 0;

      while ((line = reader.readLine()) != null && added < count) {
        lineNumber++;
        if (line.trim().isEmpty()) {
          continue;
        }
        try {
          User user = parseUser(line);
          users.add(user);
          added++;
          System.out.println("Загружен из файла: " + user.getName());
        } catch (IllegalArgumentException e) {
          System.err.println("Ошибка в строке " + lineNumber + ": " + e.getMessage());
        }
      }
      System.out.println("Из файла успешно загружено пользователей: " + added);
    } catch (IOException e) {
      System.err.println("Ошибка при чтении файла: " + e.getMessage());
    }
  }

  private User parseUser(String line) {
    String[] parts = line.split(";");
    if (parts.length < 3) {
      throw new IllegalArgumentException("Нужно 3 поля через ';': Имя;пароль;почта");
    }
    String name = parts[0].trim();
    int password = Integer.parseInt(parts[1].trim());
    String mail = parts[2].trim();

    return new User.Builder().name(name).email(mail).password(password).build();
  }
}