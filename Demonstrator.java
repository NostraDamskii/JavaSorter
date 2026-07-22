public class Demonstrator {

  public static void main(String[] args) {
    UserManager manager = new UserManager();

    System.out.println("--- Заполнение рандомом ---");
    manager.setStrategy(new RandomFillStrategy(5));
    manager.populateUsers();
    manager.setStrategy(new FileFillStrategy("users.txt"));
  }
}
