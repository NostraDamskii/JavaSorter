package service;

import core.CustomLinkedList;
import core.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileFillStrategy implements UserFillStrategy {

  private final String filePath;
  private final int count;

  public FileFillStrategy(String filePath, int count) {
    this.filePath = filePath;
    this.count = count;
  }

  @Override
  public void fill(CustomLinkedList<User> users) {
    int added = 0;
    try (Stream<String> lines = Files.lines(Paths.get(filePath))) {

      CustomLinkedList<User> loadedUsers = lines
          .map(String::trim)
          .filter(line -> !line.isEmpty())
          .limit(count)
          .map(this::parseUser)
          .collect(
              CustomLinkedList::new,
              CustomLinkedList::add,
              CustomLinkedList::addAll
          );

      users.addAll(loadedUsers);
      System.out.println("Из файла успешно загружено пользователей: " + loadedUsers.size());
    } catch (IOException | IllegalArgumentException e) {
      System.out.println("Ошибка при чтении файла: " + e.getMessage());
    }
  }

  private User parseUser(String line) {
    String[] parts = line.split(";");
    if (parts.length < 3) {
      throw new IllegalArgumentException("Нужно 3 поля через ';': Имя;пароль;почта");
    }

    String name = parts[0].trim();
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Имя пользователя не может быть пустым");
    }

    String strPassword = parts[1].trim();
    if (strPassword.isEmpty()) {
      throw new IllegalArgumentException("Пароль пользователя не может быть пустым");
    }
    int password = Integer.parseInt(strPassword);

    String mail = parts[2].trim();
    if (mail.isEmpty()) {
      throw new IllegalArgumentException("Почта пользователя не может быть пустой");
    }

    return new User.Builder().name(name).email(mail).password(password).build();
  }
}