package dataFill;

import base.CustomLinkedList;
import core.User;

public class RandomFillStrategy implements UserFillStrategy {
  private final int count;

  public RandomFillStrategy(int count) {
    this.count = count;
  }

  @Override
  public void fill(CustomLinkedList<User> users) {
    for (int i = 0; i < count; i++) {
      users.add(RandomGen.randomGenerateUser());
    }
    System.out.println("Успешно сгенерировано " + count + " случайных пользователей.");
  }
}
