package src;

import src.fillCollections.UserFillStrategy;
import src.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
  private List<User> users = new ArrayList<>();
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
  // Добавил сеттер юзеров что бы сделать запись в файл с результатами сортировки, убрал модификатор final с поля List<user>
  public void setUsers(List<User> users) {
    this.users = users;
  }

  public void clear() {
    users.clear();
  }

  public List<User> getUsers() {
    return users;
  }
}
