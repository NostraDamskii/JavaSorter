import java.util.ArrayList;
import java.util.List;

public class UserManager {

  private final List<User> users = new ArrayList<>();
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

  public List<User> getUsers() {
    return users;
  }
}
