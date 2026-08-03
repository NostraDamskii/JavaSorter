import static org.junit.jupiter.api.Assertions.assertTrue;

import core.CustomLinkedList;
import core.User;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import menu.ParallelCountCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import menu.MenuConfig;
import org.junit.jupiter.api.Test;
import service.UserManager;

public class ParallelCountCommandTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  private ParallelCountCommand command;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
    command = new ParallelCountCommand(8, MenuConfig.PARALLEL_COUNT);
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  void testExecute_WhenListIsEmpty_ShouldPrintWarningAndReturn() {
    UserManager emptyManager = new UserManager();
    Scanner scanner = new Scanner("");
    command.execute(scanner, emptyManager);

    String output = outputStreamCaptor.toString().trim();
    assertTrue(output.contains("Список пользователей пуст. Заполните его!"));
  }

  @Test
  void testExecute_WhenListHasMatches_ShouldCorrectlyCountThem() {
    UserManager userManager = new UserManager();
    CustomLinkedList<User> initialUsers = new CustomLinkedList<>();

    User target = new User.Builder().name("Ivan").email("ivan@mail.com").password(123456).build();
    User other = new User.Builder().name("Petr").email("petr@mail.com").password(654321).build();

    initialUsers.add(target);
    initialUsers.add(other);
    initialUsers.add(target);
    userManager.setUsers(initialUsers);

    String simulatedInput = "Ivan\n123456\nivan@mail.com\n";
    Scanner scanner = new Scanner(simulatedInput);

    command.execute(scanner, userManager);

    String output = outputStreamCaptor.toString().trim();
    assertTrue(output.contains("РЕЗУЛЬТАТ МНОГОПОТОЧНОГО ПОДСЧЕТА"),
        "Должна напечататься карточка результата");
    assertTrue(output.contains("Найдено вхождений: 2"),
        "Параллельный стрим должен насчитать ровно 2 совпадения");
  }
}