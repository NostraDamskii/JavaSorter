package src.menu;

import java.util.Scanner;
import src.UserManager;

public interface MenuCommand {

  int getActionCode();

  String getDescription();

  void execute(Scanner scanner, UserManager userManager);
}
