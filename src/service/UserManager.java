package service;

import core.CustomLinkedList;
import core.User;

public class UserManager {

  private CustomLinkedList<User> users = new CustomLinkedList<>();
  private UserFillStrategy strategy;

  public void setStrategy(UserFillStrategy strategy) {
    this.strategy = strategy;
  }

  public void populateUsers() {
    if (strategy == null) {
      throw new IllegalStateException("Стратегия заполнения не установлена!");
    }
    strategy.fill(users);
  }

  public void setUsers(CustomLinkedList<User> users) {
    this.users = users;
  }

  public void clear() {
    if (users != null) {
      users.clear();
    }
  }

  public CustomLinkedList<User> getUsers() {
    return users;
  }

  public boolean isEmpty() {
    return users == null || users.isEmpty();
  }
}
