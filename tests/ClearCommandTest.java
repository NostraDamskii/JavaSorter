import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.CustomLinkedList;
import core.User;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import menu.ClearCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserManager;
import menu.MenuConfig;

public class ClearCommandTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  private UserManager userManager;
  private ClearCommand command;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));

    userManager = new UserManager();

    CustomLinkedList<User> initialUsers = new CustomLinkedList<>();
    initialUsers.add(new User.Builder().name("Ivan").email("ivan@mail.com").password(123).build());
    initialUsers.add(new User.Builder().name("Petr").email("petr@mail.com").password(456).build());
    userManager.setUsers(initialUsers);

    command = new ClearCommand(7, MenuConfig.CLEAR);
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  void testExecute_WhenListIsAlreadyEmpty_ShouldPrintMessageAndReturnImmediately() {
    UserManager emptyManager = new UserManager();

    Scanner scanner = new Scanner("");

    command.execute(scanner, emptyManager);

    String output = outputStreamCaptor.toString().trim();
    assertTrue(output.contains("Список пользователей уже пуст!"));
  }

  @Test
  void testExecute_WhenUserConfirms_ShouldClearList() {
    Scanner scanner = new Scanner("y\n");

    command.execute(scanner, userManager);

    assertTrue(userManager.getUsers().isEmpty(), "Список должен стать пустым");
    assertEquals(0, userManager.getUsers().size());

    String output = outputStreamCaptor.toString().trim();
    assertTrue(output.contains("Массив очищен."));
  }

  @Test
  void testExecute_WhenUserDeclines_ShouldNotClearList() {
    Scanner scanner = new Scanner("n\n");

    command.execute(scanner, userManager);

    assertFalse(userManager.getUsers().isEmpty(), "Список не должен был очиститься");
    assertEquals(2, userManager.getUsers().size(), "В списке должно остаться 2 элемента");

    String output = outputStreamCaptor.toString().trim();
    assertTrue(output.contains("Отменено."));
  }
}