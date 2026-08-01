package menu;

import java.util.Scanner;
import base.UserManager;

public interface MenuCommand {

  int getActionCode();

  String getDescription();

  void execute(Scanner scanner, UserManager userManager);
}
