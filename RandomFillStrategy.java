import java.util.List;

public class RandomFillStrategy implements UserFillStrategy {

  private final int count;

  public RandomFillStrategy(int count) {
    this.count = count;
  }

  @Override
  public void fill(List<User> users) {
    // TODO: тут генерация новых пользователей и запись в список
  }
}
