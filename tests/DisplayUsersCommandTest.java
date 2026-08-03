import static org.junit.jupiter.api.Assertions.assertTrue;

import core.CustomLinkedList;
import core.User;
import menu.DisplayUsersCommand;
import menu.MenuConfig;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserManager;

public class DisplayUsersCommandTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  private DisplayUsersCommand command;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));

    command = new DisplayUsersCommand(4, MenuConfig.DISPLAY);
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  void testExecute_WhenListIsEmpty_ShouldPrintErrorMessage() {
    
    UserManager emptyManager = new UserManager();
    Scanner scanner = new Scanner("");

    command.execute(scanner, emptyManager);

    String output = outputStreamCaptor.toString().trim();
    assertTrue(output.contains("Ошибка: Массив пуст. Сначала заполните его"));
  }

  @Test
  void testExecute_WhenListHasElements_ShouldPrintAllUsersWithIndices() {
    UserManager userManager = new UserManager();
    CustomLinkedList<User> initialUsers = new CustomLinkedList<>();

    User user1 = new User.Builder().name("Alice").email("alice@mail.com").password(111).build();
    User user2 = new User.Builder().name("Bob").email("bob@mail.com").password(222).build();

    initialUsers.add(user1);
    initialUsers.add(user2);
    userManager.setUsers(initialUsers);

    Scanner scanner = new Scanner(""); 
    command.execute(scanner, userManager);

    String output = outputStreamCaptor.toString().trim();

    assertTrue(output.contains("Всего элементов в массиве: 2"), "Должно вывести правильное количество");
    assertTrue(output.contains("[1] " + user1.toString()), "Должен присутствовать первый пользователь под индексом 1");
    assertTrue(output.contains("[2] " + user2.toString()), "Должен присутствовать второй пользователь под индексом 2");
  }
}
