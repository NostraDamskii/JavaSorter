package service;

import core.CustomLinkedList;
import core.User;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomFillStrategy implements UserFillStrategy {

  private final int count;

  public RandomFillStrategy(int count) {
    this.count = count;
  }

  @Override
  public void fill(CustomLinkedList<User> users) {
    if (count < 0) {
      throw new IllegalArgumentException("Количество пользователей не может быть отрицательным");
    }

    List<User> generatedUsers = Stream.generate(RandomGen::randomGenerateUser)
        .limit(count)
        .collect(Collectors.toList());
    users.addAll(new CustomLinkedList<>(generatedUsers));

    System.out.println("Успешно сгенерировано " + count + " случайных пользователей.");
  }
}
