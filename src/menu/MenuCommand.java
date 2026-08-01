package menu;

import user.UserManager;

import java.util.Scanner;

public interface MenuCommand {

  int getActionCode();

  String getDescription();

  void execute(Scanner scanner, UserManager userManager);
}
